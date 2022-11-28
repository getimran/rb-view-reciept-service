package ae.rakbank.receipt.service.impl;

import ae.rakbank.receipt.dto.FeeReceiptResponse;
import ae.rakbank.receipt.dto.ReferenceFeeReceipt;
import ae.rakbank.receipt.dto.StudentResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.ApiInvokerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.util.*;

import static ae.rakbank.receipt.utility.AppConstants.*;
import static ae.rakbank.receipt.utility.LogMessages.*;

/**
 * @author imran
 * Api Invoker Service Implementation
 */
@Slf4j
@Service
public class ApiInvokerServiceImpl implements ApiInvokerService {


    @Value("${view-receipt.service.student.url}")
    private String getStudentByIdUrl;

    @Value("${view-receipt.service.fee-collect.getByStudentId.url}")
    private String getFeeDetailByStudentIdUrl;

    @Value("${view-receipt.service.fee-collect.getByReferenceId.url}")
    private String getFeeDetailByReferenceIdUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = STUDENT_SERVICE, fallbackMethod = STUDENT_SERVICE_FALLBACK)
    public Optional<StudentResponse> invokeGetStudentByIdService(Integer studentId) throws ApiInvokerServiceException, StudentNotFoundException {

        try {
            log.info(INVOKING_STUDENT_GET_SERVICE, studentId);
            ResponseEntity<StudentResponse> studentResponseEntity = restTemplate
                    .getForEntity(getStudentByIdUrl + studentId, StudentResponse.class);
            log.info(RECEIVED_RESPONSE, studentResponseEntity.getStatusCode());
            if (studentResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return Optional.of(studentResponseEntity.getBody());
            }
        } catch (Exception ex) {
            log.error(ERROR_OCCURRED, ex.getMessage());
            throw ex;
        }
        return Optional.empty();
    }

    @Override
    @CircuitBreaker(name = FEE_RECEIPT_BY_STUDENT_SERVICE, fallbackMethod = FEE_RECEIPT_BY_STUDENT_SERVICE_FALLBACK)
    public List<FeeReceiptResponse> invokeGetFeeDetailByStudentId(Integer studentId) throws ApiInvokerServiceException, FeeDetailNotFoundException {
        try {
            log.info(INVOKING_FEE_DETAIL_BY_STUDENT_ID_SERVICE, studentId);
            ResponseEntity<List<FeeReceiptResponse>> feeReceiptListResponseEntity = restTemplate.exchange(
                    getFeeDetailByStudentIdUrl + studentId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<FeeReceiptResponse>>() {}
            );
            log.info(RECEIVED_RESPONSE, feeReceiptListResponseEntity.getStatusCode());
            if (feeReceiptListResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return feeReceiptListResponseEntity.getBody();
            }
        } catch (Exception ex) {
            log.error(ERROR_OCCURRED, ex.getMessage());
            throw ex;
        }
        return Collections.emptyList();
    }

    @Override
    @CircuitBreaker(name = FEE_RECEIPT_BY_REFERENCE_SERVICE, fallbackMethod = FEE_RECEIPT_BY_REFERENCE_SERVICE_FALLBACK)
    public Optional<ReferenceFeeReceipt> invokeGetFeeDetailByReferenceId(String referenceId)
            throws ApiInvokerServiceException, FeeDetailNotFoundException {
        try {
            log.info(INVOKING_FEE_DETAIL_BY_REFERENCE_ID_SERVICE, referenceId);
            ResponseEntity<ReferenceFeeReceipt> feeReceiptResponseEntity = restTemplate
                    .getForEntity(getFeeDetailByReferenceIdUrl + referenceId, ReferenceFeeReceipt.class);
            log.info(RECEIVED_RESPONSE, feeReceiptResponseEntity.getStatusCode());
            if (feeReceiptResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return Optional.of(feeReceiptResponseEntity.getBody());
            }
        } catch (Exception ex) {
            log.error(ERROR_OCCURRED, ex.getMessage());
            throw ex;
        }
        return Optional.empty();
    }


    private Optional<StudentResponse> studentServiceFallbackMethod(Integer studentId, Exception ex) throws Exception {
        log.info(FALLBACK_METHOD, STUDENT_SERVICE_FALLBACK);
        if(ex instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            if(httpClientErrorException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new StudentNotFoundException(STUDENT_NOT_FOUND);
            }
            throw ex;
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(studentId);
        return Optional.of(studentResponse);
    }

    private List<FeeReceiptResponse> feeReceiptsByStudentIdFallbackMethod(Exception ex) throws Exception {
        log.info(FALLBACK_METHOD, FEE_RECEIPT_BY_STUDENT_SERVICE_FALLBACK);
        if(ex instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            if(httpClientErrorException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new FeeDetailNotFoundException(FEE_NOT_FOUND);
            }
            throw ex;
        }
        return Collections.emptyList();
    }

    private Optional<ReferenceFeeReceipt> feeReceiptsByReferenceIdFallbackMethod(String referenceId, Exception ex) throws Exception {
        log.info(FALLBACK_METHOD, FEE_RECEIPT_BY_REFERENCE_SERVICE_FALLBACK);
        if(ex instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            if(httpClientErrorException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new FeeDetailNotFoundException(FEE_NOT_FOUND);
            }
            throw ex;
        }
        if(ex instanceof ResourceAccessException) {
            throw new ServiceUnavailableException("FEE_DETAIL " + SERVICE_UNAVAILABLE);
        }
        ReferenceFeeReceipt referenceFeeReceipt = new ReferenceFeeReceipt();
        referenceFeeReceipt.setFeeTxnReferenceId("REF00001");
        return Optional.of(referenceFeeReceipt);
    }
}
