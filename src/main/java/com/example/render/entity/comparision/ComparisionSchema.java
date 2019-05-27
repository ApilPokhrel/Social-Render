package com.example.render.entity.comparision;


import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comparisions")
public class ComparisionSchema {

 @Id
 private String id;
 private Object refId;
 private String refSlug;
 private String is;
 private String file1;
 private String file1type;
 private String file2type;
 private String file2;
 @Size(min=6, message="Tiilt should have Atleast 6 words")
 private String tittle;
 private String subtag;
 @TextIndexed

 private String brandtag;
 @TextIndexed
// private String description;
 private String owntag;
 private Date createdAt;
 private String URL1;
 private String URL2;
 private String URL3;
 private ArrayList<String> f1likes;
 private ArrayList<String> f2likes;
 private ArrayList<?> opinions;
 
 
 
public ComparisionSchema() {
	super();
}



public ComparisionSchema(String id, Object refId, String file1, String file1type, String file2type, String file2,
		 String tittle, String subtag, String brandtag,
		 String owntag, String uRL1, String uRL2, String uRL3, ArrayList<String> f1likes,ArrayList<String> f2likes, ArrayList<?> opinions) {
	super();
	this.id = id;
	this.refId = refId;
	this.file1 = file1;
	this.file1type = file1type;
	this.file2type = file2type;
	this.file2 = file2;
	this.tittle = tittle;
	this.subtag = subtag;
	this.brandtag = brandtag;
	this.owntag = owntag;
	URL1 = uRL1;
	URL2 = uRL2;
	URL3 = uRL3;
	this.f1likes = f1likes;
	this.f2likes = f2likes;
	this.opinions = opinions;
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



public String getIs() {
	return is;
}



public void setIs(String is) {
	this.is = is;
}



public String getRefSlug() {
	return refSlug;
}



public void setRefSlug(String refSlug) {
	this.refSlug = refSlug;
}



public String getFile1() {
	return file1;
}



public void setFile1(String file1) {
	this.file1 = file1;
}



public String getFile1type() {
	return file1type;
}



public void setFile1type(String file1type) {
	this.file1type = file1type;
}



public String getFile2type() {
	return file2type;
}



public void setFile2type(String file2type) {
	this.file2type = file2type;
}



public String getFile2() {
	return file2;
}



public void setFile2(String file2) {
	this.file2 = file2;
}



public String getTittle() {
	return tittle;
}



public void setTittle(String tittle) {
	this.tittle = tittle;
}



public String getSubtag() {
	return subtag;
}



public void setSubtag(String subtag) {
	this.subtag = subtag;
}



public String getBrandtag() {
	return brandtag;
}



public void setBrandtag(String brandtag) {
	this.brandtag = brandtag;
}



public String getOwntag() {
	return owntag;
}



public void setOwntag(String owntag) {
	this.owntag = owntag;
}



public String getURL1() {
	return URL1;
}



public void setURL1(String uRL1) {
	URL1 = uRL1;
}



public String getURL2() {
	return URL2;
}



public void setURL2(String uRL2) {
	URL2 = uRL2;
}



public String getURL3() {
	return URL3;
}



public void setURL3(String uRL3) {
	URL3 = uRL3;
}



public ArrayList<String> getF1likes() {
	return f1likes;
}



public Date getCreatedAt() {
	return createdAt;
}



public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}



public void setF1likes(ArrayList<String> f1likes) {
	this.f1likes = f1likes;
}



public ArrayList<String> getF2likes() {
	return f2likes;
}



public void setF2likes(ArrayList<String> f2likes) {
	this.f2likes = f2likes;
}



public ArrayList<?> getOpinions() {
	return opinions;
}



public void setOpinions(ArrayList<?> opinions) {
	this.opinions = opinions;
}



 
}
