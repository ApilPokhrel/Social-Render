package com.example.render.entity.comparision;

import java.util.ArrayList;


public class ComparisionTags  {

	private String Tagvalue;
	private ArrayList<?> f1;
	private ArrayList<?> f2;
	
	
	public ComparisionTags() {
		super();
	}
	public ComparisionTags(String tagvalue, ArrayList<?> f1, ArrayList<?> f2) {
		super();
		Tagvalue = tagvalue;
		this.f1 = f1;
		this.f2 = f2;
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
	public ArrayList<?> getF1() {
		return f1;
	}
	/**
	 * @param f1 the f1 to set
	 */
	public void setF1(ArrayList<?> f1) {
		this.f1 = f1;
	}
	/**
	 * @return the f2
	 */
	public ArrayList<?> getF2() {
		return f2;
	}
	/**
	 * @param f2 the f2 to set
	 */
	public void setF2(ArrayList<?> f2) {
		this.f2 = f2;
	}
}
