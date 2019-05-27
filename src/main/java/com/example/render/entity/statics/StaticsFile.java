package com.example.render.entity.statics;

public class StaticsFile {

	private String file;
	private String filetype;
	
	
	public StaticsFile() {
		super();
	}
	public StaticsFile(String file, String filetype) {
		super();
		this.file = file;
		this.filetype = filetype;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
