package ae.rakbank.receipt.service.impl;

import ae.rakbank.receipt.dto.FeeReceiptResponse;
import ae.rakbank.receipt.dto.ReferenceFeeReceipt;
import ae.rakbank.receipt.dto.StudentResponse;
import ae.rakbank.receipt.exception.ApiInvokerServiceException;
import ae.rakbank.receipt.exception.FeeDetailNotFoundException;
import ae.rakbank.receipt.exception.StudentNotFoundException;
import ae.rakbank.receipt.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ApiInvokerServiceImplTest {

    @InjectMocks
    ApiInvokerServiceImpl apiInvokerService;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    public void before() {
        ReflectionTestUtils.setField(apiInvokerService, "getStudentByIdUrl", "http://localhost:8080/rakbank/api/student/");
        ReflectionTestUtils.setField(apiInvokerService, "getFeeDetailByStudentIdUrl", "http://localhost:8082/rakbank/api/fee/student/");
        ReflectionTestUtils.setField(apiInvokerService, "getFeeDetailByReferenceIdUrl", "http://localhost:8082/rakbank/api/fee/");
    }

    @Test
    public void testInvokeGetStudentByIdService() throws ApiInvokerServiceException, StudentNotFoundException {

        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenReturn(ResponseEntity.ok(TestUtility.getStudentResponse()));
        Optional<StudentResponse> studentResponseOptional= apiInvokerService.invokeGetStudentByIdService(1001);
        Assertions.assertTrue(studentResponseOptional.isPresent());
    }

    @Test
    public void testInvokeGetFeeDetailByStudentId() throws ApiInvokerServiceException, FeeDetailNotFoundException {

        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(),Mockito.any(),
                        Mockito.any(ParameterizedTypeReference.class)))
                .thenReturn(ResponseEntity.ok(Arrays.asList(TestUtility.getFeeReceiptResponse())));
        List<FeeReceiptResponse> feeReceiptResponses= apiInvokerService.invokeGetFeeDetailByStudentId(1001);
        Assertions.assertTrue(!feeReceiptResponses.isEmpty());
    }

    @Test
    public void testInvokeGetFeeDetailByReferenceId() throws ApiInvokerServiceException, FeeDetailNotFoundException {

        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenReturn(ResponseEntity.ok(TestUtility.getReferenceFeeReceipt()));
        Optional<ReferenceFeeReceipt> feeDetailByReferenceId= apiInvokerService.invokeGetFeeDetailByReferenceId("REF0000122");
        Assertions.assertTrue(feeDetailByReferenceId.isPresent());
    }
}
