package com.digitalhonor.componentprocessing.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "process_requests")
public class ProcessData {

	@Id
	private String requestId;
	private String userName;
	private long contactNumber;
	private long cardNumber;
	//private boolean isPriorityRequest;

	private String componentType;
	private String componentName;
	private int quantity;

	private double processingCharge;
	private double packageAndDeliveryCharge;
	private Date dateOfDelivery;

	public ProcessData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

//	public boolean isPriorityRequest() {
//		return isPriorityRequest;
//	}
//
//	public void setPriorityRequest(boolean isPriorityRequest) {
//		this.isPriorityRequest = isPriorityRequest;
//	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

}
