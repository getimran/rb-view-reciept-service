package ae.rakbank.receipt.service.impl;

import ae.rakbank.receipt.dto.ReferenceReceiptResponse;
import ae.rakbank.receipt.dto.StudentReceiptResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.ViewReceiptOrchestrator;
import ae.rakbank.receipt.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViewReceiptServiceImplTest {

    @InjectMocks
    ViewReceiptServiceImpl viewReceiptService;

    @Mock
    private ViewReceiptOrchestrator viewReceiptOrchestrator;


    @Test
    public void testGetReceiptByStudentId() throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        Mockito.when(viewReceiptOrchestrator.processReceiptForStudentId(Mockito.anyInt())).thenReturn(TestUtility.getStudentReceiptResponse());
        StudentReceiptResponse studentReceiptResponse = viewReceiptService.getReceiptByStudentId(1001);
        Assertions.assertNotNull(studentReceiptResponse.getFeeReceipt());
    }


    @Test
    public void testGetReceiptByReferenceId() throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        Mockito.when(viewReceiptOrchestrator.processReceiptForReferenceId(Mockito.anyString())).thenReturn(TestUtility.getReferenceFeeReceiptResponse());
        ReferenceReceiptResponse referenceReceiptResponse = viewReceiptService.getReceiptByReferenceId("REF000122");
        Assertions.assertNotNull(referenceReceiptResponse.getFeeReceipt());
    }
}
