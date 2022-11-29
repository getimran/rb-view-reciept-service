package ae.rakbank.receipt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


/**
 * @author imran
 * Response DTO for Fee Detail Service
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeReceiptResponse {

    private String feeTxnReferenceId;
    private Double feeAmount;
    private String cardNumber;
    private String cardType;
    private LocalDateTime timestamp;

}
