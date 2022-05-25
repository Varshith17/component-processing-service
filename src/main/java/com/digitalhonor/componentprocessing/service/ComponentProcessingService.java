package com.digitalhonor.componentprocessing.service;

import com.digitalhonor.componentprocessing.payloaddata.RequestData;
import com.digitalhonor.componentprocessing.payloaddata.ResponseData;

public interface ComponentProcessingService {
	public ResponseData processRequest(RequestData requestData);
}
