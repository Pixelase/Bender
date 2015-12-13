package com.github.union.one.bus.api.converter;

import java.io.IOException;

public interface Serializer {
	<T> void Serialize(T employee, String path) throws IOException;

	<T> T Deserialize(String path, Class<T> type) throws IOException;

}