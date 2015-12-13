package com.github.union.one.bus.api.converter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.union.one.bus.api.model.Code;

public class CodeManager {
	private List<Code> codes;
	private Serializer serializer;
	private String dbPath;

	public CodeManager(String dbPath) {
		this(new ArrayList<Code>(), new JsonFileSerializer(), dbPath);
	}

	public CodeManager(List<Code> codes, Serializer serializer, String dbPath) {
		this.codes = codes;
		this.serializer = serializer;
		this.dbPath = dbPath;
	}

	public boolean readBase() throws IOException {
		boolean isReaded = false;

		if (new File(dbPath).exists()) {
			codes = Arrays.asList(serializer.Deserialize(dbPath, Code[].class));
			isReaded = true;
		} else {
			isReaded = false;
		}
		return isReaded;
	}

	public void updateOrCreateBase() throws IOException {
		serializer.Serialize(codes.toArray(), dbPath);
	}

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public String getDbPath() {
		return dbPath;
	}

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codes == null) ? 0 : codes.hashCode());
		result = prime * result + ((dbPath == null) ? 0 : dbPath.hashCode());
		result = prime * result + ((serializer == null) ? 0 : serializer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeManager other = (CodeManager) obj;
		if (codes == null) {
			if (other.codes != null)
				return false;
		} else if (!codes.equals(other.codes))
			return false;
		if (dbPath == null) {
			if (other.dbPath != null)
				return false;
		} else if (!dbPath.equals(other.dbPath))
			return false;
		if (serializer == null) {
			if (other.serializer != null)
				return false;
		} else if (!serializer.equals(other.serializer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CodeManager [codes=" + codes + ", serializer=" + serializer + ", dbPath=" + dbPath + "]";
	}
}