package com.tanmoy.CustomAnnotation.Processor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.tanmoy.CustomAnnotation.annotations.JsonField;
import com.tanmoy.CustomAnnotation.exception.JsonProcessorException;

/**
 * {@link JsonField} annotation processor class.
 * 
 * @author tanmoy.tushar
 * @since Mar 3, 2022
 *
 */
public class JsonFieldProcessor {

	public String processor(Object object) throws JsonProcessorException {
		try {
			Class<?> objectClass = object.getClass();
			Map<String, String> jsonElements = new HashMap<>();

			for (Field field : objectClass.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(JsonField.class)) {
					jsonElements.put(getKey(field),
							getValue(field).isEmpty() ? (String) field.get(object) : getValue(field));
				}
			}
			System.out.println(toJsonString(jsonElements));
			return toJsonString(jsonElements);
		} catch (IllegalAccessException e) {
			throw new JsonProcessorException(e.getMessage());
		}

	}

	private String toJsonString(Map<String, String> jsonMap) {
		String elementsString = jsonMap.entrySet().stream()
				.map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(","));
		return "{" + elementsString + "}";
	}

	/**
	 * Get the value from {@link JsonField} annotation.
	 * If doesn't provide any value then return object value or null.
	 * 
	 * @param field
	 * @return filed value.
	 */
	private static String getValue(Field field) {
		String annotationValue = field.getAnnotation(JsonField.class).value();
		if (annotationValue.isEmpty()) {
			return "";
		} else {
			return annotationValue;
		}
	}

	/**
	 * Get the key from {@link JsonField} annotation.
	 * If doesn't provide any name(key) then return field declared name.
	 * 
	 * @param field
	 * @return field name
	 */
	private static String getKey(Field field) {
		String annotationKey = field.getAnnotation(JsonField.class).name();
		if (annotationKey.isEmpty()) {
			return field.getName();
		} else {
			return annotationKey;
		}
	}

}
