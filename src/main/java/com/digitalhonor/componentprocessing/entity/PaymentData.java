package com.digitalhonor.componentprocessing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_info")
public class PaymentData {

	@Id
	private String requestId;
	private long cardNumber;
	private int cvv;
	private double processingCharge;
	private double packageAndDeliveryCharge;
	
	public double getPackageAndDeliveryCharge() {
		return packageAndDeliveryCharge;
	}

	public PaymentData(String requestId, long cardNumber, int cvv, double processingCharge,
			double packageAndDeliveryCharge) {
		super();
		this.requestId = requestId;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.processingCharge = processingCharge;
		this.packageAndDeliveryCharge = packageAndDeliveryCharge;
	}

	public PaymentData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setPackageAndDeliveryCharge(double packageAndDeliveryCharge) {
		this.packageAndDeliveryCharge = packageAndDeliveryCharge;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getProcessingCharge() {
		return processingCharge;
	}

	public void setProcessingCharge(double processingCharge) {
		this.processingCharge = processingCharge;
	}

}
