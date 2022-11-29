package ae.rakbank.receipt.utility;

import ae.rakbank.receipt.dto.*;

import java.util.Arrays;

public class TestUtility {

    public static StudentReceiptResponse getStudentReceiptResponse() {
        StudentReceiptResponse studentReceiptResponse = new StudentReceiptResponse();
        studentReceiptResponse.setStudent(getStudentResponse());
        studentReceiptResponse.setFeeReceipt(Arrays.asList(getFeeReceiptResponse()));
        return studentReceiptResponse;
    }

    public static ReferenceReceiptResponse getReferenceFeeReceiptResponse() {
        ReferenceReceiptResponse referenceReceiptResponse = new ReferenceReceiptResponse();
        referenceReceiptResponse.setStudent(getStudentResponse());
        referenceReceiptResponse.setFeeReceipt(new ReferenceFeeReceipt());
        return referenceReceiptResponse;
    }


    public static StudentResponse getStudentResponse() {

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(1001);
        studentResponse.setStudentName("Imran");
        return studentResponse;
    }

    public static FeeReceiptResponse getFeeReceiptResponse() {

        FeeReceiptResponse feeReceiptResponse = new FeeReceiptResponse();
        feeReceiptResponse.setFeeAmount(10.08);
        feeReceiptResponse.setFeeTxnReferenceId("REF0000122");
        feeReceiptResponse.setCardType("MASTER");
        return feeReceiptResponse;
    }


    public static ReferenceFeeReceipt getReferenceFeeReceipt() {

        ReferenceFeeReceipt referenceFeeReceipt = new ReferenceFeeReceipt();
        referenceFeeReceipt.setFeeAmount(10.08);
        referenceFeeReceipt.setStudentId("1001");
        referenceFeeReceipt.setFeeTxnReferenceId("REF0000122");
        referenceFeeReceipt.setCardType("MASTER");
        return referenceFeeReceipt;
    }
}
