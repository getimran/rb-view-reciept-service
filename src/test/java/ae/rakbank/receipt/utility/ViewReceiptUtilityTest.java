package ae.rakbank.receipt.utility;

import ae.rakbank.receipt.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ViewReceiptUtilityTest {

    @Test
    public void testPrivateConstructor() throws NoSuchMethodException {

        Constructor<ViewReceiptUtility> constructor = ViewReceiptUtility.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Assertions.assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
    }


    @Test
    public void testBuildStudentReceiptResponse() {

        StudentReceiptResponse studentReceiptResponse = ViewReceiptUtility
                .buildStudentReceiptResponse(new StudentResponse(), Arrays.asList(new FeeReceiptResponse()));
        Assertions.assertNotNull(studentReceiptResponse.getFeeReceipt());
    }


    @Test
    public void testBuildReferenceReceiptResponse() {

        ReferenceReceiptResponse referenceReceiptResponse = ViewReceiptUtility
                .buildReferenceReceiptResponse(new StudentResponse(), new ReferenceFeeReceipt());
        Assertions.assertNotNull(referenceReceiptResponse.getFeeReceipt());
    }
}
