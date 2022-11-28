package ae.rakbank.receipt.service;

import ae.rakbank.receipt.dto.ReferenceReceiptResponse;
import ae.rakbank.receipt.dto.StudentReceiptResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;

/**
 * @author imran
 * Interface for View Receipt Service
 */
public interface ViewReceiptService {


    public StudentReceiptResponse getReceiptByStudentId(Integer studentId) throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException;

    public ReferenceReceiptResponse getReceiptByReferenceId(String referenceId) throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException;
}
