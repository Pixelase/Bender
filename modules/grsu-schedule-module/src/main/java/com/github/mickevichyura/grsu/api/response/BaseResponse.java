package com.github.mickevichyura.grsu.api.response;

import java.util.List;

import com.github.mickevichyura.grsu.api.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

	@SerializedName("items")
	@Expose
	private List<BaseModel> items;

	public List<BaseModel> getItems() {
		return items;
	}

	public String[][] itemsToStringArray() {
		int n = items.size();
		String[][] array = new String[n][1];

		for (int i = 0; i < n; i++) {
			array[i][0] = items.get(i).toString();
		}

		return array;
	}

	public String findId(String title) {
		BaseModel indexOf = null;

		for (BaseModel baseModel : items) {
			if (title.equals(baseModel.getTitle()))
				indexOf = baseModel;
		}
		return indexOf == null ? null : indexOf.getId();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (BaseModel baseModel : items) {
			sb.append(baseModel + "\n");
		}

		return sb.toString();
	}

}
