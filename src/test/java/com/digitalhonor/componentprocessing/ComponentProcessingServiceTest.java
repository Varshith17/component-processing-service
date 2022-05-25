package com.digitalhonor.componentprocessing;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalhonor.componentprocessing.entity.PaymentData;
import com.digitalhonor.componentprocessing.payloaddata.RequestData;
import com.digitalhonor.componentprocessing.payloaddata.ResponseData;
import com.digitalhonor.componentprocessing.service.ProcessReturnService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.digitalhonors.componentprocessing.service" })
@AutoConfigureMockMvc
public class ComponentProcessingServiceTest {

	@Autowired
	private ProcessReturnService processReturnService;

	@Test
	public void contextLoads() {
		assertNotNull(processReturnService);
	}

	@Test
	public void processReturnRequestTestSuccess() {
		RequestData req = new RequestData("varshith", 989887898, "integral", "Return order1 ", 2);
		ResponseData expected = new ResponseData();
		expected.setProcessingCharge(500.0);
		expected.setPackageAndDeliveryCharge(700.0);
		ResponseData actual = processReturnService.processReturnRequest(req);
		assertEquals(expected.getProcessingCharge(),actual.getProcessingCharge(),0);
		assertEquals(expected.getPackageAndDeliveryCharge(),actual.getPackageAndDeliveryCharge(),0);
	}
	
	@Test
	public void processReturnRequestTestFailure() {
		RequestData req = new RequestData("varshith", 989887898, "integral", "Return order1 ", 2);
		ResponseData expected = new ResponseData();
		expected.setProcessingCharge(400.0);
		expected.setPackageAndDeliveryCharge(500.0);
		ResponseData actual = processReturnService.processReturnRequest(req);
		assertNotEquals(expected.getProcessingCharge(),actual.getProcessingCharge(),0);
		assertNotEquals(expected.getPackageAndDeliveryCharge(),actual.getPackageAndDeliveryCharge(),0);
	}
	
	@Test
	public void completePaymentDataSuccess(){
		PaymentData expected = new PaymentData("35c44343df",123334532,321,300.0,450.0);
		PaymentData actual = processReturnService.completePaymentProcessing(expected);
		assertEquals(expected.getRequestId(),actual.getRequestId());
	}
	
	@Test
	public void completePaymentDataFailure(){
		PaymentData data = new PaymentData("35c443df",1233345,3321,390.0,490.0);
		PaymentData expected = new PaymentData("35c44343df",123334532,321,300.0,450.0);
		PaymentData actual = processReturnService.completePaymentProcessing(expected);
		assertNotEquals(expected,actual);
	}
	
}
