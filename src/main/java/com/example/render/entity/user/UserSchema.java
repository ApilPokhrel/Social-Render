package com.example.render.entity.user;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "userinfos")
public class UserSchema{
    @Id
    private String id;
    private Object refId;
    private String studied;
    @TextIndexed
    private String lives;
    private String worksAt;
    private String fond;
    private String relation;
    private String single;
    private String dreamprofession;
    private String efield;
    private String visit;
    private double lat;
    private double lng;
    private String country;
    private String city;
    private String zip;
    
    
    
	public UserSchema() {
		super();
	}
	
	
	public UserSchema(String id, Object refId, String studied, String lives, String fond,
			String relation, String single, String dreamprofession,String worksAt , String efield, String visit, double lat, double lng,
			String country, String city, String zip) {
		super();
		this.id = id;
		this.refId = refId;
		this.studied = studied;
		this.lives = lives;
		this.fond = fond;
		this.relation = relation;
		this.single = single;
		this.dreamprofession = dreamprofession;
		this.efield = efield;
		this.visit = visit;
		this.worksAt = worksAt;
		this.lat = lat;
		this.lng = lng;
		this.country = country;
		this.city = city;
		this.zip = zip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getRefId() {
		return refId;
	}
	public void setRefId(Object refId) {
		this.refId = refId;
	}
	public String getStudied() {
		return studied;
	}
	public void setStudied(String studied) {
		this.studied = studied;
	}
	public String getWorksAt() {
		return worksAt;
	}


	public void setWorksAt(String worksAt) {
		this.worksAt = worksAt;
	}


	public String getLives() {
		return lives;
	}
	public void setLives(String lives) {
		this.lives = lives;
	}
	public String getFond() {
		return fond;
	}
	public void setFond(String fond) {
		this.fond = fond;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getSingle() {
		return single;
	}
	public void setSingle(String single) {
		this.single = single;
	}
	public String getDreamprofession() {
		return dreamprofession;
	}
	public void setDreamprofession(String dreamprofession) {
		this.dreamprofession = dreamprofession;
	}
	public String getEfield() {
		return efield;
	}
	public void setEfield(String efield) {
		this.efield = efield;
	}
	public String getVisit() {
		return visit;
	}
	public void setVisit(String visit) {
		this.visit = visit;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
    


}
