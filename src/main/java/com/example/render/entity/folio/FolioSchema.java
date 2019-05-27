package com.example.render.entity.folio;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="folios")
public class FolioSchema {

	@Id
	private String id;
	@Size(min=8, message="its not an email")
	private String optionalemail;
	
	@Size(min=3, message="not enough word for name")
	private String folioname;
	@Size(min=1, message="compulsary main tag")
	private String maintag;
	private String folioimg;
	private String[] subtags;
	private String[] brandtags;
	private Object refuserId;
	private String owner;
	private Date createdAt;
	private String url;
	private boolean isOfficial;
	private String officialOf;
	private String description;
	private Object[] likedBy;
	
	
	
	
	
	public FolioSchema() {
		super();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOptionalemail() {
		return optionalemail;
	}
	public void setOptionalemail(String optionalemail) {
		this.optionalemail = optionalemail;
	}
	public String getFolioname() {
		return folioname;
	}
	public void setFolioname(String folioname) {
		this.folioname = folioname;
	}
	public String getMaintag() {
		return maintag;
	}
	public void setMaintag(String maintag) {
		this.maintag = maintag;
	}
	public String getFolioimg() {
		return folioimg;
	}
	public void setFolioimg(String folioimg) {
		this.folioimg = folioimg;
	}
	public String[] getSubtags() {
		return subtags;
	}
	public void setSubtags(String[] subtags) {
		this.subtags = subtags;
	}
	public String[] getBrandtags() {
		return brandtags;
	}
	public void setBrandtags(String[] brandtags) {
		this.brandtags = brandtags;
	}
	public Object getRefuserId() {
		return refuserId;
	}
	public void setRefuserId(Object refuserId) {
		this.refuserId = refuserId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public boolean isOfficial() {
		return isOfficial;
	}


	public void setOfficial(boolean isOfficial) {
		this.isOfficial = isOfficial;
	}


	public String getOfficialOf() {
		return officialOf;
	}


	public void setOfficialOf(String officialOf) {
		this.officialOf = officialOf;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Object[] getLikedBy() {
		return likedBy;
	}


	public void setLikedBy(Object[] likedBy) {
		this.likedBy = likedBy;
	}
	
}
