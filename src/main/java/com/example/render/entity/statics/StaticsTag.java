package com.example.render.entity.statics;

import java.util.ArrayList;




public class StaticsTag {
	
	private String Tagvalue;
	private ArrayList f1;
	
	
	
	public StaticsTag() {
		super();
	}
	public StaticsTag(String tagvalue, ArrayList f1) {
		super();
		Tagvalue = tagvalue;
		this.f1 = f1;
	}
	/**
	 * @return the tagvalue
	 */
	public String getTagvalue() {
		return Tagvalue;
	}
	/**
	 * @param tagvalue the tagvalue to set
	 */
	public void setTagvalue(String tagvalue) {
		Tagvalue = tagvalue;
	}
	/**
	 * @return the f1
	 */
	public ArrayList getF1() {
		return f1;
	}
	/**
	 * @param f1 the f1 to set
	 */
	public void setF1(ArrayList f1) {
		this.f1 = f1;
	}
	

}
