package net.chomookun.apps.sdk.core;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonConverter {

	public static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
	}

	/**
	 * return objectMapper
	 * @return
	 */
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * toJson
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object obj) throws Exception {
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

	/**
	 * toJson
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String toJson(List<?> list) throws Exception {
		return objectMapper.writeValueAsString(list);
	}

	/**
	 * toObject
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T toObject(String json, Class<T> clazz) throws Exception {
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * toList
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) throws Exception {
		return objectMapper.readValue(json,TypeFactory.defaultInstance().constructCollectionType(Collection.class, clazz));
	}

	/**
	 * convert JSON string to string
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static String stringify(String string) throws Exception {
		String json = objectMapper.writeValueAsString(string);
		return objectMapper.writeValueAsString(json);
	}

	/**
	 * toJson
	 * @param value
	 * @return
	 */
	public static boolean isJson(String value) {
		boolean valid = true;
		try {
			objectMapper.readTree(value);
		} catch (Exception e) {
			valid = false;
		}
		return valid;
	}

}