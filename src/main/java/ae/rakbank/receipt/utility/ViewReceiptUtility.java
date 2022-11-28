package ae.rakbank.receipt.utility;

import ae.rakbank.receipt.dto.*;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author imran
 * Utility class for View Receipt
 */
@UtilityClass
public class ViewReceiptUtility {


    public static StudentReceiptResponse buildStudentReceiptResponse(StudentResponse studentResponse,
                                                                     List<FeeReceiptResponse> feeReceiptListResponse) {
        StudentReceiptResponse studentReceiptResponse = new StudentReceiptResponse();
        studentReceiptResponse.setStudent(studentResponse);
        studentReceiptResponse.setFeeReceipt(feeReceiptListResponse);
        return studentReceiptResponse;
    }

    public static ReferenceReceiptResponse buildReferenceReceiptResponse(StudentResponse studentResponse,
                                                                         ReferenceFeeReceipt feeReceiptResponse) {
        ReferenceReceiptResponse referenceReceiptResponse = new ReferenceReceiptResponse();
        referenceReceiptResponse.setStudent(studentResponse);
        feeReceiptResponse.setStudentId(null);
        referenceReceiptResponse.setFeeReceipt(feeReceiptResponse);
        return referenceReceiptResponse;
    }
}
