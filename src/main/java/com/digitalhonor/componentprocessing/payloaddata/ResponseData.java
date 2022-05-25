package com.digitalhonor.componentprocessing.payloaddata;

import lombok.Data;

import java.util.Date;

public class ResponseData {

	private String requestId;
	private double processingCharge;
	private double packageAndDeliveryCharge;

	public ResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseData(String requestId, double processingCharge, double packageAndDeliveryCharge,
			Date dateOfDelivery) {
		super();
		this.requestId = requestId;
		this.processingCharge = processingCharge;
		this.packageAndDeliveryCharge = packageAndDeliveryCharge;
		this.dateOfDelivery = dateOfDelivery;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public double getProcessingCharge() {
		return processingCharge;
	}

	public void setProcessingCharge(double processingCharge) {
		this.processingCharge = processingCharge;
	}

	public double getPackageAndDeliveryCharge() {
		return packageAndDeliveryCharge;
	}

	public void setPackageAndDeliveryCharge(double packageAndDeliveryCharge) {
		this.packageAndDeliveryCharge = packageAndDeliveryCharge;
	}

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	private Date dateOfDelivery;
}
