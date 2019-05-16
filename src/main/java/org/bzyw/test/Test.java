package org.bzyw.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {
		Field field1 = ClassA.class.getDeclaredField("id");
		Field field2 = ClassB.class.getDeclaredField("name");
		Set<Class<?>> clazzSet = new HashSet<>();
		clazzSet.add(field1.getDeclaringClass());
		clazzSet.add(field2.getDeclaringClass());
		
		for (Class<?> clazz : clazzSet) {
			Method method = getMethod("getName", clazz);
			if (method != null) {
				System.out.println(method);
				break;
			}
		}
	}

	private static Method getMethod(String methodName, Class<?> clazz) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName);
		} catch (NoSuchMethodException | SecurityException e) {
			// ignore
		}
		return method;
	}

}
