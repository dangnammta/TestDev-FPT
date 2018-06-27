/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import firo.RouteInfo;

/**
 *
 * @author hoaronal
 */
public class AnnotationUtils {

	public static RouteInfo getAnnotationForMethod(String nameMethod, Object nameClass) throws NoSuchMethodException {
		Class<?> aClass = nameClass.getClass();
		Method method = aClass.getDeclaredMethod(nameMethod);
		// Lấy ra danh sách các Annotation của method.
		Annotation[] methodAnns = method.getAnnotations();
		for (Annotation methodAnn : methodAnns) {
//			System.out.println("Annotation: " + methodAnn.toString());
		}
		// Lấy cụ thể.
		Annotation methodAnn = method.getAnnotation(RouteInfo.class);
		RouteInfo infoMethod = (RouteInfo) methodAnn;
		return infoMethod;
	}

	public static Method findAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		for (Method method : clazz.getMethods())
			if (method.isAnnotationPresent(annotationClass))
				return (method);
		return (null);
	}

	public static List<Method> findAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Method[] methods = clazz.getMethods();
		List<Method> annotatedMethods = new ArrayList<Method>(methods.length);
		for (Method method : methods) {
			if (method.isAnnotationPresent(annotationClass)) {
				annotatedMethods.add(method);
			}
		}
		return annotatedMethods;
	}
//	public static void main(String[] args) throws NoSuchMethodException {
//		findAnnotatedMethod(UserController.class, RouteInfo.class);
//		System.out.println(findAnnotatedMethods(UserController.class, RouteInfo.class).toString());
//	}

}
