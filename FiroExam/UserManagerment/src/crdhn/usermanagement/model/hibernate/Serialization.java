/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.model.hibernate;

import crdhn.usermanagement.model.Model;
import firo.utils.AnnotationUtils;
import firo.utils.ClassFinder;
import java.beans.Expression;
import java.beans.Statement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hoaronal
 */
public class Serialization {

	public static Object fromHibernate(Object obj, Class modelClass) throws Exception {
		Object model = modelClass.newInstance();
		List<Method> listSetter = AnnotationUtils.findSetterMethods(modelClass);
		List<Method> listGetter = AnnotationUtils.findGetterMethods(obj.getClass());
		Object value = "";
		Statement stmt;
		Expression expr;
		for (int i = 0; i < listSetter.size(); i++) {
			expr = new Expression(obj, listGetter.get(i).getName(), new Object[0]);
			expr.execute();
			value = expr.getValue();
			stmt = new Statement(model, "set" + listGetter.get(i).getName().substring(3), new Object[]{value});
			stmt.execute();
		}
		return model;
	}

	public static Object toHibernate(Object model, Object obj) throws Exception {
		List<Method> listGetter = AnnotationUtils.findGetterMethods(model.getClass());
		List<Method> listSetter = AnnotationUtils.findSetterMethods(obj.getClass());
		Object value = "";
		Statement stmt;
		Expression expr;
		for (int i = 0; i < listSetter.size(); i++) {
			expr = new Expression(model, listGetter.get(i).getName(), new Object[0]);
			expr.execute();
			value = expr.getValue();
			stmt = new Statement(obj, "set" + listGetter.get(i).getName().substring(3), new Object[]{value});
			stmt.execute();
		}
		return obj;
	}

	public static List<Method> findGetterMethods(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		List<Method> listmethods = new ArrayList<>(methods.length);
		Field[] fields = clazz.getDeclaredFields();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
				listmethods.add(method);
			}
		}
		return listmethods;
	}

	public static List<Method> findSetterMethods(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		List<Method> listmethods = new ArrayList<>(methods.length);
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				listmethods.add(method);
			}
		}
		return listmethods;
	}
}
