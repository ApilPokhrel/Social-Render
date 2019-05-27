package com.example.render.entity.statics;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "statics")
public class StaticsSchema {

	private String id;
	private Object refId;
	private String refSlug;
	private String is;
	@Size(min=6, message="Tiilt should have Atleast 6 words")
	private String tittle;
	private ArrayList<StaticsFile> file;
//	 @Size(min=5, message="too short for Owntag")
	 @TextIndexed
	private String owntag;
	 private Date createdAt;
	private String subtag;
	@TextIndexed
//	@Size(min=1, message="brand tag cannot be null")
	private String brandtag;
	private String URL;
	private ArrayList<String> filelikes;
	private ArrayList<Object> opinions;
	
	
	public StaticsSchema() {
		super();
	}


	public StaticsSchema(String id, Object refId,
			 String tittle, ArrayList<StaticsFile> file,
			 String owntag, String subtag, String brandtag, String uRL, ArrayList<String> filelikes, ArrayList<Object> opinions) {
		super();
		this.id = id;
		this.refId = refId;
		this.tittle = tittle;
		this.file = file;
		this.owntag = owntag;
		this.subtag = subtag;
		this.brandtag = brandtag;
		URL = uRL;
		this.filelikes = filelikes;
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


	public String getRefSlug() {
		return refSlug;
	}


	public String getIs() {
		return is;
	}


	public void setIs(String is) {
		this.is = is;
	}


	public void setRefSlug(String refSlug) {
		this.refSlug = refSlug;
	}


	public String getTittle() {
		return tittle;
	}


	public void setTittle(String tittle) {
		this.tittle = tittle;
	}


	public ArrayList<StaticsFile> getFile() {
		return file;
	}


	public void setFile(ArrayList<StaticsFile> file) {
		this.file = file;
	}


	public String getOwntag() {
		return owntag;
	}


	public void setOwntag(String owntag) {
		this.owntag = owntag;
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


	public String getURL() {
		return URL;
	}


	public void setURL(String uRL) {
		URL = uRL;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public ArrayList<String> getFilelikes() {
		return filelikes;
	}


	public void setFilelikes(ArrayList<String> filelikes) {
		this.filelikes = filelikes;
	}


	public ArrayList<Object> getOpinions() {
		return opinions;
	}


	public void setOpinions(ArrayList<Object> opinions) {
		this.opinions = opinions;
	}


	
	
}
