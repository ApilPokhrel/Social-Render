package com.example.render.entity;

import javax.validation.constraints.Size;

public class opinion {
	@Size(min=5, max=55, message="Must give At least two Opinion")
	private String a;
	@Size(min=5, max=55, message="cannot be empty or too long")
	private String b;
	@Size(max=55, message = "Too much long make Shorter")
	private String c;
	@Size(max=55, message = "Too much long")
	private String d;
	@Size(max=55, message = "Too much long")
	private String e;
	
	
	public opinion() {
		super();
	}
	public opinion(String a, String b, String c, String d, String e) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
	}
	/**
	 * @return the a
	 */
	public String getA() {
		return a;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * @return the b
	 */
	public String getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(String b) {
		this.b = b;
	}
	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}
	/**
	 * @return the d
	 */
	public String getD() {
		return d;
	}
	/**
	 * @param d the d to set
	 */
	public void setD(String d) {
		this.d = d;
	}
	/**
	 * @return the e
	 */
	public String getE() {
		return e;
	}
	/**
	 * @param e the e to set
	 */
	public void setE(String e) {
		this.e = e;
	}

}
