package com.github.union.one.bus.api.parser;

import java.net.MalformedURLException;
import java.util.List;

import com.github.union.one.bus.api.core.Parser;
import com.github.union.one.bus.api.model.Code;
import com.github.union.one.bus.api.utils.Api;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class CodeParser extends Parser<Code> {
	private JsonArray threads;

	public CodeParser() throws MalformedURLException {
		super(Api.CODES_URL);
		this.threads = super.getJsonObj().getAsJsonArray("codes");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse(List<Code> codes) {
		// TODO Auto-generated method stub
		//Map<String, String> map = new HashMap<>();
		try {
			for (int i = 0; i < threads.size(); i++) {
				JsonObject thread = (JsonObject) this.threads.get(i);
				codes.add(new Code(thread.get("name").getAsString(), thread.get("code").getAsString()));
				//map.put(thread.get("name").getAsString(), thread.get("code").getAsString());
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
	}

	public JsonArray getThreads() {
		return threads;
	}

	public void setThreads(JsonArray threads) {
		this.threads = threads;
	}

	@Override
	public String toString() {
		return "CodeParser [threads=" + threads + "]";
	}
}