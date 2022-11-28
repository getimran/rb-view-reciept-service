package ae.rakbank.receipt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author imran
 * Response DTO for Reference Fee Receipt
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReferenceFeeReceipt {

    private String feeTxnReferenceId;
    private String studentId;
    private Double feeAmount;
    private String cardNumber;
    private String cardType;
    private LocalDateTime timestamp;
}
