package ae.rakbank.receipt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author imran
 * Response DTO for Student Service
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {

    private Integer studentId;
    private String studentName;
    private String grade;
    private String schoolName;
    private String mobileNum;

}
