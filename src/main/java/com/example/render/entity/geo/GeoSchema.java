package com.example.render.entity.geo;

public class GeoSchema {

	private String status;
	private double lat;
	private double lon;
	private String zip;
	private String city;
	private String country;
	private String countryCode;
	private String regionName;
	
	
	
	
	public GeoSchema() {
		super();
	}
	public GeoSchema(String status, double lat, double lon, String zip, String city, String country, String countryCode,
			String regionName) {
		super();
		this.status = status;
		this.lat = lat;
		this.lon = lon;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.countryCode = countryCode;
		this.regionName = regionName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	} 
}
