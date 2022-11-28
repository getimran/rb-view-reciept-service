package ae.rakbank.receipt.controller;

import ae.rakbank.receipt.dto.ReferenceReceiptResponse;
import ae.rakbank.receipt.dto.StudentReceiptResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.service.impl.ViewReceiptServiceImpl;
import ae.rakbank.receipt.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ViewReceiptControllerTest {

    @InjectMocks
    ViewReceiptController viewReceiptController;

    @Mock
    private ViewReceiptServiceImpl viewReceiptService;

    @Test
    public void testGetReceiptByStudentId() throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        Mockito.when(viewReceiptService.getReceiptByStudentId(Mockito.anyInt())).thenReturn(TestUtility.getStudentReceiptResponse());
        ResponseEntity<StudentReceiptResponse> response = viewReceiptController.getReceiptByStudentId(1001);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }


    @Test
    public void testGetReceiptByReferenceId() throws ApiInvokerServiceException, StudentNotFoundException, FeeDetailNotFoundException {

        Mockito.when(viewReceiptService.getReceiptByReferenceId(Mockito.anyString())).thenReturn(TestUtility.getReferenceFeeReceiptResponse());
        ResponseEntity<ReferenceReceiptResponse> response = viewReceiptController.getReceiptByReferenceId("REF0000122");
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }
}
