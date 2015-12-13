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
		try {
			for (int i = 0; i < threads.size(); i++) {
				JsonObject thread = (JsonObject) this.threads.get(i);
				codes.add(new Code(thread.get("name").getAsString(), thread.get("code").getAsString()));
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((threads == null) ? 0 : threads.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeParser other = (CodeParser) obj;
		if (threads == null) {
			if (other.threads != null)
				return false;
		} else if (!threads.equals(other.threads))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CodeParser [threads=" + threads + "]";
	}
}