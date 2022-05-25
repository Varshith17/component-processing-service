package com.digitalhonor.componentprocessing.payloaddata;

import lombok.Data;

public class RequestData {
	private String userName;
	private long contactNumber;
	private String componentType;
	private String componentName;
	private int quantity;

	public String getUserName() {
		return userName;
	}

	public RequestData(String userName, long contactNumber, String componentType, String componentName, int quantity) {
		super();
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.componentType = componentType;
		this.componentName = componentName;
		this.quantity = quantity;
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

	public RequestData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
