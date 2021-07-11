package com.vivek.priceTracker.Amazon.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "amazonpricetracker")
public class AmazonEntity{
	@Id
	@Column(name="url")
	private String url;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String price;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public AmazonEntity() {
	}
	public AmazonEntity(String url, Date date, String price) {
		super();
		this.url = url;
		this.date = date;
		this.price = price;
	}
}
