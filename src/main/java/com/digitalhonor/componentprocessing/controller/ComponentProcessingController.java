package com.digitalhonor.componentprocessing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalhonor.componentprocessing.clients.AuthorizationClient;
import com.digitalhonor.componentprocessing.entity.PaymentData;
import com.digitalhonor.componentprocessing.payloaddata.RequestData;
import com.digitalhonor.componentprocessing.payloaddata.ResponseData;
import com.digitalhonor.componentprocessing.service.ProcessReturnService;

@RestController
@RequestMapping("/returns")
public class ComponentProcessingController {

//	@Autowired
//	private AuthorizationClient authFeignClient;

	@Autowired
	private ProcessReturnService processReturnService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentProcessingController.class);

	@Value("${AUTH_URL:http://localhost:8000}")
	private String authServiceHost;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	

	@PostMapping("/createReturnRequest")
	public ResponseData createReturnRequest(@RequestHeader("Authorization") String token,
			@RequestBody RequestData requestData) {
		LOGGER.info("STARTED - Create Return Request");
		
		headers.set("Authorization", token);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<Boolean> response = restTemplate.exchange(authServiceHost + "/auth/authorize", HttpMethod.GET,
				request, Boolean.class, 1);

//		authFeignClient.validateAndReturnUser(token);
		ResponseData res = processReturnService.processReturnRequest(requestData);
		LOGGER.info("END - Create Return complete");
		return res;

	}

	@PostMapping("/completeProcessingPayment")
	public ResponseEntity<?> completeProcessingPayment(@RequestHeader("Authorization") String token,
			@RequestBody PaymentData paymentData) {
		LOGGER.info("STARTED - completeProcessingPayment");
		
		headers.set("Authorization", token);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<Boolean> response = restTemplate.exchange(authServiceHost + "/auth/authorize", HttpMethod.GET,
				request, Boolean.class, 1);
		
		//authFeignClient.validateAndReturnUser(token);
		ResponseEntity<?> res = new ResponseEntity<>(processReturnService.completePaymentProcessing(paymentData),
				HttpStatus.OK);
		LOGGER.info("END - completeProcessingPayment");
		return res;
	}

}
