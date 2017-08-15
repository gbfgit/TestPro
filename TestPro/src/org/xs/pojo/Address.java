package org.xs.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("address")
public class Address {
	
	private String street;
	private int houseNumber;
	private String city;
	
	
	public Address() {
	}
	
	public Address(String street, int houseNumber, String city) {
		super();
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
