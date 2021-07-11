package com.vivek.priceTracker.Amazon.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AmazonEntityCopy{
	private String url;
	private String date;
	private String price;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "AmazonEntity [url=" + url + ", date=" + date + ", price=" + price + "]";
	}
	public AmazonEntityCopy() {
	}
	public AmazonEntityCopy(String url, String date, String price) {
		super();
		this.url = url;
		this.date = date;
		this.price = price;
	}
}
