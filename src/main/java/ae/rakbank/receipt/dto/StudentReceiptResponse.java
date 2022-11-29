package ae.rakbank.receipt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author imran
 * View Receipt By Student Id Resposne dto
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentReceiptResponse {

    private StudentResponse student;
    private List<FeeReceiptResponse> feeReceipt;
}
