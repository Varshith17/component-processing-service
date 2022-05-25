package com.digitalhonor.componentprocessing.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitalhonor.componentprocessing.clients.PackageDeliveryClient;
import com.digitalhonor.componentprocessing.payloaddata.RequestData;
import com.digitalhonor.componentprocessing.payloaddata.ResponseData;

public class AccessoryPartsService implements ComponentProcessingService {

	private final int PROCESSING_DAYS = 2;
	private final double PROCESSING_CHARGE = 300;

	@Override
	public ResponseData processRequest(RequestData requestData) {
		LocalDate date = LocalDate.now().plusDays(PROCESSING_DAYS);
		ResponseData res = new ResponseData();
		res.setRequestId(UUID.randomUUID().toString().replace("-", ""));
		res.setProcessingCharge(PROCESSING_CHARGE);
		res.setDateOfDelivery(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return res;
	}
}
