package edu.mum.library.common;

import java.lang.reflect.Field;

public class ReflectionHelper {
	private ReflectionHelper() {

	}
	
	@SuppressWarnings("unchecked")
	public static <T> T readField(Object obj, String name) {
		Field f;
		try {
			f = obj.getClass().getDeclaredField(name);
			f.setAccessible(true);
			return (T) f.get(obj);
		} catch (Exception e) {
			throw new RuntimeException("fail to read Field", e);
		}
	}
}
