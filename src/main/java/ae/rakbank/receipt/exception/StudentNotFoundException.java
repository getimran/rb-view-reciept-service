package ae.rakbank.receipt.exception;

/**
 * @author imran
 * Exception class for Student not found
 */
public class StudentNotFoundException extends Exception {

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
