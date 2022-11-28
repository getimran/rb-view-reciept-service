package ae.rakbank.receipt.service;

import ae.rakbank.receipt.dto.ReferenceReceiptResponse;
import ae.rakbank.receipt.dto.StudentReceiptResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.impl.ApiInvokerServiceImpl;
import ae.rakbank.receipt.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class ViewReceiptOrchestratorTest {

    @InjectMocks
    ViewReceiptOrchestrator viewReceiptOrchestrator;

    @Mock
    ApiInvokerServiceImpl apiInvokerService;


    @Test
    public void testProcessReceiptByStudentId() throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        Mockito.when(apiInvokerService.invokeGetStudentByIdService(Mockito.anyInt())).thenReturn(Optional.of(TestUtility.getStudentResponse()));
        Mockito.when(apiInvokerService.invokeGetFeeDetailByStudentId(Mockito.anyInt())).thenReturn(Arrays.asList(TestUtility.getFeeReceiptResponse()));
        StudentReceiptResponse studentReceiptResponse = viewReceiptOrchestrator.processReceiptForStudentId(1001);
        Assertions.assertNotNull(studentReceiptResponse.getFeeReceipt());
    }


    @Test
    public void testProcessReceiptForReferenceId() throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        Mockito.when(apiInvokerService.invokeGetFeeDetailByReferenceId(Mockito.anyString())).thenReturn(Optional.of(TestUtility.getReferenceFeeReceipt()));
        Mockito.when(apiInvokerService.invokeGetStudentByIdService(Mockito.anyInt())).thenReturn(Optional.of(TestUtility.getStudentResponse()));
        ReferenceReceiptResponse referenceReceiptResponse = viewReceiptOrchestrator.processReceiptForReferenceId("REF0000122");
        Assertions.assertNotNull(referenceReceiptResponse.getFeeReceipt());
    }

}
