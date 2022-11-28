package ae.rakbank.receipt.utility;

import lombok.experimental.UtilityClass;

/**
 * @author imran
 * Constant class for View Receipt Service
 */
@UtilityClass
public class AppConstants {

    public static final String STUDENT_NOT_FOUND = "Student not found";
    public static final String FEE_NOT_FOUND = "Receipt Not Found";


    public static final String CONNECT_EXCEPTION = "ConnectException";

    public static final String SERVICE_UNAVAILABLE = "Service Unavailable";

    public static final String SERVICE_ERROR = "Service Error";

    public static final String STUDENT_SERVICE = "studentService";
    public static final String STUDENT_SERVICE_FALLBACK = "studentServiceFallbackMethod";


    public static final String FEE_RECEIPT_BY_STUDENT_SERVICE = "feeReceiptsByStudentIdService";
    public static final String FEE_RECEIPT_BY_STUDENT_SERVICE_FALLBACK = "feeReceiptsByStudentIdFallbackMethod";


    public static final String FEE_RECEIPT_BY_REFERENCE_SERVICE = "feeReceiptsByReferenceIdService";
    public static final String FEE_RECEIPT_BY_REFERENCE_SERVICE_FALLBACK = "feeReceiptsByReferenceIdFallbackMethod";


}
