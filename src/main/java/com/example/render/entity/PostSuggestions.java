package com.example.render.entity;

public class PostSuggestions {

	private Object of;
	private String suggestion;
	
	
	public PostSuggestions(Object of, String suggestion) {
		super();
		this.of = of;
		this.suggestion = suggestion;
	}
	
	
	public Object getOf() {
		return of;
	}
	public void setOf(Object of) {
		this.of = of;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	
}
