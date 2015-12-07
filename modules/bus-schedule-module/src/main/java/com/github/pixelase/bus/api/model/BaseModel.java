package com.github.pixelase.bus.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseModel {
	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("title")
	@Expose
	private String title;

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "BaseModel [title=" + title + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return title.equals(obj);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
}
