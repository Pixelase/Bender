package com.github.mickevichyura.grsu.api.model;

public class Teacher {

	private String id;
	private String fullname;
	private String post;

	public String getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public String getPost() {
		return post;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(fullname);
		
		
		return sb.toString();
	}

}
