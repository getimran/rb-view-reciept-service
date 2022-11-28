package ae.rakbank.receipt.utility;

import lombok.experimental.UtilityClass;

/**
 * @author imran
 * Class for Log Messages
 */
@UtilityClass
public class LogMessages {

    public static final String REQUEST_RECEIVED = "Request Received: {}";
    public static final String RESPONSE_SENT = "Response Sent: {}";
    public static final String ATTEMPT = "Attempt: {}";
    public static final String INVOKING_STUDENT_GET_SERVICE = "Invoking Student Service Get By Id {}";

    public static final String INVOKING_FEE_DETAIL_BY_STUDENT_ID_SERVICE = "Invoking Fee Details by StudentId Service {}";
    public static final String INVOKING_FEE_DETAIL_BY_REFERENCE_ID_SERVICE = "Invoking Fee Details by ReferenceId Service {}";
    public static final String RECEIVED_RESPONSE = "Received Response: {}";
    public static final String ERROR_OCCURRED = "Error Occurred: {}";

    public static final String FALLBACK_METHOD = "Fallback Method In Action: {}";


}
