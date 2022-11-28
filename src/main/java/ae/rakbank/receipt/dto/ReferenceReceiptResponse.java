package ae.rakbank.receipt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author imran
 * View Receipt By Reference Id Resposne dto
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReferenceReceiptResponse {

    private StudentResponse student;
    private ReferenceFeeReceipt feeReceipt;
}
