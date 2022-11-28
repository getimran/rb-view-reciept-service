package ae.rakbank.receipt.controller;

import ae.rakbank.receipt.dto.ReferenceReceiptResponse;
import ae.rakbank.receipt.dto.StudentReceiptResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.impl.ViewReceiptServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ae.rakbank.receipt.utility.LogMessages.REQUEST_RECEIVED;
import static ae.rakbank.receipt.utility.LogMessages.RESPONSE_SENT;

/**
 * @author imran
 * RestController for View Receipt Microservice
 */
@Slf4j
@RestController
@RequestMapping("/rakbank/api/receipt")
@Tag(name = "View Receipt API", description = "API for for viewing fee receipt of student based on fee referenceId and studentId")
public class ViewReceiptController {

    @Autowired
    private ViewReceiptServiceImpl viewReceiptService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentReceiptResponse> getReceiptByStudentId(@PathVariable Integer studentId)
            throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        log.info(REQUEST_RECEIVED, studentId);
        StudentReceiptResponse studentReceiptResponse = viewReceiptService.getReceiptByStudentId(studentId);
        log.info(RESPONSE_SENT, HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(studentReceiptResponse, HttpStatus.OK);
    }


    @GetMapping("/{referenceId}")
    public ResponseEntity<ReferenceReceiptResponse> getReceiptByReferenceId(@PathVariable String referenceId)
            throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        log.info(REQUEST_RECEIVED, referenceId);
        ReferenceReceiptResponse referenceReceiptResponse = viewReceiptService.getReceiptByReferenceId(referenceId);
        log.info(RESPONSE_SENT, HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(referenceReceiptResponse, HttpStatus.OK);
    }
}
