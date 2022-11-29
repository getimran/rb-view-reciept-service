package ae.rakbank.receipt.service.impl;

import ae.rakbank.receipt.dto.ReferenceReceiptResponse;
import ae.rakbank.receipt.dto.StudentReceiptResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.ViewReceiptOrchestrator;
import ae.rakbank.receipt.service.ViewReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author imran
 * View Receipt Service Implementation
 */
@Service
public class ViewReceiptServiceImpl implements ViewReceiptService {


    @Autowired
    private ViewReceiptOrchestrator viewReceiptOrchestrator;

    @Override
    public StudentReceiptResponse getReceiptByStudentId(Integer studentId) throws ApiInvokerServiceException,
            StudentNotFoundException, FeeDetailNotFoundException {
        return viewReceiptOrchestrator.processReceiptForStudentId(studentId);
    }

    @Override
    public ReferenceReceiptResponse getReceiptByReferenceId(String referenceId) throws ApiInvokerServiceException,
            StudentNotFoundException, FeeDetailNotFoundException {
        return viewReceiptOrchestrator.processReceiptForReferenceId(referenceId);
    }
}
