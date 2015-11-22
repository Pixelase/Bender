package com.github.mickevichyura.grsu.api.response;

import java.util.List;

import com.github.mickevichyura.grsu.api.model.Teacher;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherResponse {

	@SerializedName("items")
	@Expose
	private List<Teacher> items;

	public List<Teacher> getItems() {
		return items;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append(count.toString() + " ");
		
		for (Teacher department : items) {
			sb.append(department + "\n");
		}
		
		return sb.toString();
	}
	
}
