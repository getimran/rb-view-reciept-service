package ae.rakbank.receipt.service;

import ae.rakbank.receipt.dto.FeeReceiptResponse;
import ae.rakbank.receipt.dto.ReferenceFeeReceipt;
import ae.rakbank.receipt.dto.StudentResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.Optional;

/**
 * @author imran
 * Interface for ApiInvoker Service
 */
public interface ApiInvokerService {

    public Optional<StudentResponse> invokeGetStudentByIdService(Integer studentId) throws ApiInvokerServiceException, StudentNotFoundException;

    public List<FeeReceiptResponse> invokeGetFeeDetailByStudentId(Integer studentId) throws ApiInvokerServiceException, FeeDetailNotFoundException;

    public Optional<ReferenceFeeReceipt> invokeGetFeeDetailByReferenceId(String referenceId) throws ApiInvokerServiceException, FeeDetailNotFoundException;
}
