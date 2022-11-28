package ae.rakbank.receipt.service;

import ae.rakbank.receipt.dto.*;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.impl.ApiInvokerServiceImpl;
import ae.rakbank.receipt.utility.ViewReceiptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ae.rakbank.receipt.utility.AppConstants.FEE_NOT_FOUND;
import static ae.rakbank.receipt.utility.AppConstants.STUDENT_NOT_FOUND;

/**
 * @author imran
 * Orchestrator class for View Receipt Service
 */
@Service
public class ViewReceiptOrchestrator {

    @Autowired
    ApiInvokerServiceImpl apiInvokerService;


    public StudentReceiptResponse processReceiptForStudentId(Integer studentId) throws ApiInvokerServiceException,
            StudentNotFoundException, FeeDetailNotFoundException {

        Optional<StudentResponse> studentResponseOptional = apiInvokerService.invokeGetStudentByIdService(studentId);
        if(!studentResponseOptional.isPresent()) {
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);
        }
        List<FeeReceiptResponse> feeReceiptListResponseOptional = apiInvokerService.invokeGetFeeDetailByStudentId(studentId);
        return ViewReceiptUtility.buildStudentReceiptResponse(studentResponseOptional.get(),
                feeReceiptListResponseOptional);
    }


    public ReferenceReceiptResponse processReceiptForReferenceId(String referenceId) throws ApiInvokerServiceException,
            StudentNotFoundException, FeeDetailNotFoundException {
        StudentResponse studentResponse = null;
        Optional<ReferenceFeeReceipt> feeReceiptResponseOptional = apiInvokerService
                .invokeGetFeeDetailByReferenceId(referenceId);
        if(!feeReceiptResponseOptional.isPresent()) {
            throw new FeeDetailNotFoundException(FEE_NOT_FOUND);
        }

        if(feeReceiptResponseOptional.get().getStudentId() != null) {
            studentResponse = invokeStudentService(Integer.parseInt(feeReceiptResponseOptional.get().getStudentId()));
        }
        return ViewReceiptUtility.buildReferenceReceiptResponse(studentResponse,
                feeReceiptResponseOptional.get());
    }

    private StudentResponse invokeStudentService(Integer studentId) throws StudentNotFoundException, ApiInvokerServiceException {

        Optional<StudentResponse> studentResponseOptional = apiInvokerService.invokeGetStudentByIdService(studentId);
        if(!studentResponseOptional.isPresent()) {
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);
        }
        return studentResponseOptional.get();
    }
}
