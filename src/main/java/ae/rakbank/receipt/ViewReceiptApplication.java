package ae.rakbank.receipt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author imran
 * SpringBoot Main Class for View Receipt Microservice
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "View Receipt Service Definition",
		description = "Microservice for viewing fee receipt of student",
		contact = @Contact(
				name = "Mohammad Imran",
				url= "https://www.linkedin.com/in/md-imran-02524a125/",
				email= "mailmeinfo.imran@gmail.com"
		),
		version = "v1"
))
public class ViewReceiptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewReceiptApplication.class, args);
	}

}
