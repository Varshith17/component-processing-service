package com.digitalhonor.componentprocessing;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digitalhonor.componentprocessing.controller.ComponentProcessingController;
import com.digitalhonor.componentprocessing.entity.PaymentData;
import com.digitalhonor.componentprocessing.payloaddata.RequestData;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ComponentProcessingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXJzaGl0aCIsImV4cCI6MTY1MjkwMjI5NSwiaWF0IjoxNjUyODg0Mjk1fQ.EyxotFscvO9pHPwxPKA6BI7MWcxpLGHP8KBdPmTGZpHcaF7tFZeu97R_GwuHVVa2jc4ywZ97ULJ4cqYjRdUr9Q";

	@Autowired
	private ComponentProcessingController componentProcessing;

	@Test
	public void contextLoads() {

		assertNotNull(componentProcessing);

	}

	@Test
	public void CreateReturnRequestSuccessTest() throws Exception {
		RequestData req = new RequestData("varshith", 989887898, "integral", "Return order 1", 2);

		ResultActions actions = mockMvc.perform(post("/returns/createReturnRequest").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(req)));

		actions.andExpect(status().isOk());
	}
	
	@Test
	public void CreateReturnRequestFailureTest() throws Exception {
		RequestData req = new RequestData("varshith", 989887898, "integral", "Return order 1", 2);

		ResultActions actions = mockMvc.perform(get("/returns/createReturnRequest").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(req)));
		actions.andExpect(status().isMethodNotAllowed());
	}
	
	
	@Test
	public void ProcessReturnRequestSuccess() throws Exception{
		PaymentData data = new PaymentData("35c44343df",123334532,321,300.0,450.0);
		
		ResultActions actions = mockMvc.perform(post("/returns/completeProcessingPayment").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonStringForProcesReturn(data)));
		actions.andExpect(status().isOk());
		
	}

	@Test
	public void ProcessReturnRequestFailure() throws Exception{
		PaymentData data = new PaymentData("35c44343df",123334532,321,300.0,450.0);
		
		ResultActions actions = mockMvc.perform(get("/returns/completeProcessingPayment").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonStringForProcesReturn(data)));
		actions.andExpect(status().isMethodNotAllowed());
		
	}
	public static String asJsonString(RequestData processPensionInput) {
		try {
			return new ObjectMapper().writeValueAsString(processPensionInput);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String asJsonStringForProcesReturn(PaymentData paymentData) {
		try {
			return new ObjectMapper().writeValueAsString(paymentData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
