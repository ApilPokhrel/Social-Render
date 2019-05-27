package com.example.render.entity.user;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userprogress")
public class UserAccountProgress {

	@Id
	private String id;
	private Object refId;
	private long progress;
	private int stage;
	private boolean official;
	private boolean superior;
	private boolean donator;
	private boolean gaveads;
	private boolean isfunded;
	private boolean isworker;
	private boolean socialworker;
	private String spentTimeSummedHrs;
	private ArrayList<?> devoted;
	private Object[] promotedTo;
	private Object[] promotedBy;
    private ArrayList<?> postsuggested;
    
    
    
    public UserAccountProgress() {
		super();
	}


	public UserAccountProgress(String id, Object refId, long progress, int stage, boolean official, boolean superior,
			boolean donator, boolean gaveads, boolean isfunded, boolean isworker, boolean socialworker,
			String spentTimeSummedHrs, ArrayList<?> devoted, Object[] promotedTo, Object[] promotedBy,
			ArrayList<?> postsuggested) {
		super();
		this.id = id;
		this.refId = refId;
		this.progress = progress;
		this.stage = stage;
		this.official = official;
		this.superior = superior;
		this.donator = donator;
		this.gaveads = gaveads;
		this.isfunded = isfunded;
		this.isworker = isworker;
		this.socialworker = socialworker;
		this.spentTimeSummedHrs = spentTimeSummedHrs;
		this.devoted = devoted;
		this.promotedTo = promotedTo;
		this.promotedBy = promotedBy;
		this.postsuggested = postsuggested;
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
	public long getProgress() {
		return progress;
	}
	public void setProgress(long progress) {
		this.progress = progress;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public boolean isOfficial() {
		return official;
	}
	public void setOfficial(boolean official) {
		this.official = official;
	}
	public boolean isSuperior() {
		return superior;
	}
	public void setSuperior(boolean superior) {
		this.superior = superior;
	}
	public boolean isDonator() {
		return donator;
	}
	public void setDonator(boolean donator) {
		this.donator = donator;
	}
	public boolean isGaveads() {
		return gaveads;
	}
	public void setGaveads(boolean gaveads) {
		this.gaveads = gaveads;
	}
	public boolean isIsfunded() {
		return isfunded;
	}
	public void setIsfunded(boolean isfunded) {
		this.isfunded = isfunded;
	}
	public boolean isIsworker() {
		return isworker;
	}
	public void setIsworker(boolean isworker) {
		this.isworker = isworker;
	}
	public boolean isSocialworker() {
		return socialworker;
	}
	public void setSocialworker(boolean socialworker) {
		this.socialworker = socialworker;
	}
	public String getSpentTimeSummedHrs() {
		return spentTimeSummedHrs;
	}
	public void setSpentTimeSummedHrs(String spentTimeSummedHrs) {
		this.spentTimeSummedHrs = spentTimeSummedHrs;
	}
	public ArrayList<?> getDevoted() {
		return devoted;
	}
	public void setDevoted(ArrayList<?> devoted) {
		this.devoted = devoted;
	}
	public Object[] getPromotedTo() {
		return promotedTo;
	}
	public void setPromotedTo(Object[] promotedTo) {
		this.promotedTo = promotedTo;
	}
	public Object[] getPromotedBy() {
		return promotedBy;
	}
	public void setPromotedBy(Object[] promotedBy) {
		this.promotedBy = promotedBy;
	}
	public ArrayList<?> getPostsuggested() {
		return postsuggested;
	}
	public void setPostsuggested(ArrayList<?> postsuggested) {
		this.postsuggested = postsuggested;
	}
	
}
