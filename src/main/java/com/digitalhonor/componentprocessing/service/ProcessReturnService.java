package com.digitalhonor.componentprocessing.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digitalhonor.componentprocessing.clients.PackageDeliveryClient;
import com.digitalhonor.componentprocessing.controller.ComponentProcessingController;
import com.digitalhonor.componentprocessing.entity.PaymentData;
import com.digitalhonor.componentprocessing.entity.ProcessData;
import com.digitalhonor.componentprocessing.payloaddata.RequestData;
import com.digitalhonor.componentprocessing.payloaddata.ResponseData;
import com.digitalhonor.componentprocessing.repository.PaymentRepository;
import com.digitalhonor.componentprocessing.repository.ReturnRequestRepository;

@Component
@Service
public class ProcessReturnService {

	@Autowired
	private ReturnRequestRepository returnRequest;
	@Autowired
	private PaymentRepository paymentRepository;
	
//	@Autowired
//	private PackageDeliveryClient packagingDeliveryClient;
	
	@Value("${PACKAGING_DELIVERY_URL:http://localhost:8083}")
	private String packageDeliveryServiceHost;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessReturnService.class);
	
	public double getPackageAndDeliveryCharge(String type, int quantity) {
		
		HttpEntity request = new HttpEntity(headers);
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("type",type);
		uriVariables.put("quantity",String.valueOf(quantity));
		ResponseEntity<Double> response = new RestTemplate().getForEntity
				(packageDeliveryServiceHost+"/packagingAndDelivery/getCharge/{type}/{quantity}", 
						Double.class, uriVariables);
		return response.getBody();
	}
	public ResponseData processReturnRequest(RequestData req) {
		if (req.getComponentType().equals("integral")) {
			LOGGER.info("STARTED: Integral type service process");
			IntegralPartsService integral = new IntegralPartsService();
			ResponseData res = integral.processRequest(req);
			//res.setPackageAndDeliveryCharge(packagingDeliveryClient.getPackagingAndDeliveryCharge(req.getComponentType(), req.getQuantity()));
			res.setPackageAndDeliveryCharge(getPackageAndDeliveryCharge(req.getComponentType(),req.getQuantity()));
			saveReturnRequestsData(req, res);
			LOGGER.info("END: Integral type service process");
			return res;
		} else {
			LOGGER.info("STARTED: Accessory type service process");
			AccessoryPartsService accessory = new AccessoryPartsService();
			ResponseData res = accessory.processRequest(req);
			//res.setPackageAndDeliveryCharge(packagingDeliveryClient.getPackagingAndDeliveryCharge(req.getComponentType(), req.getQuantity()));
			res.setPackageAndDeliveryCharge(getPackageAndDeliveryCharge(req.getComponentType(),req.getQuantity()));
			saveReturnRequestsData(req, res);
			LOGGER.info("END: Accessory type service process");
			return res;
		}
	}

	public void saveReturnRequestsData(RequestData req, ResponseData res) {
		ProcessData requestProcessData = new ProcessData();
		requestProcessData.setRequestId(res.getRequestId());
		requestProcessData.setComponentName(req.getComponentName());
		requestProcessData.setComponentType(req.getComponentType());
		requestProcessData.setContactNumber(req.getContactNumber());
		requestProcessData.setQuantity(req.getQuantity());
		requestProcessData.setUserName(req.getUserName());
		requestProcessData.setDateOfDelivery(res.getDateOfDelivery());
		requestProcessData.setProcessingCharge(res.getProcessingCharge());
		requestProcessData.setPackageAndDeliveryCharge(res.getPackageAndDeliveryCharge());
		returnRequest.save(requestProcessData);
		LOGGER.info("SAVE: Save processed Data");
	}

	public PaymentData completePaymentProcessing(PaymentData paymentData) {
		LOGGER.info("SAVE: Save Payement Data");
		return paymentRepository.save(paymentData);
	}
}
