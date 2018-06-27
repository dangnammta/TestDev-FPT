/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoaronal
 */
public class ClassFinder {

    public static List<Class<?>> getAllClassesInPackage(String pckgname) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        try {
            File directory = null;
            try {
                directory = new File(Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile());
            } catch (NullPointerException x) {
                throw new ClassNotFoundException(pckgname + " không tồn tại");
            }
            if (directory.exists()) {
                String[] files = directory.list();
				for (String file : files) {
					if (file.endsWith(".class") && !file.contains("$")) {
						classes.add(Class.forName(pckgname + "." + file.substring(0, file.length() - 6)));
					}
				}
            } else {
                throw new ClassNotFoundException(pckgname + " not found");
            }
            return classes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Class<?>> getAllClassesInPackage(String pckgname, File jarFile) {
        try {
            List<Class<?>> classes = new ArrayList<>();
            JarFile jar;
            jar = new JarFile(jarFile.getAbsolutePath());
            
            URL[] urls = { jarFile.toURL() };
            URLClassLoader cl = URLClassLoader.newInstance(urls);
            
            Enumeration<JarEntry> e = jar.entries();
            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
				String clsName = je.getName();
				if((clsName.contains("$"))
						||  je.isDirectory() || !clsName.endsWith(".class")
						|| !clsName.startsWith(pckgname.replace('.', '/'))){
                    continue;
                }
                // -6 because of .class
                String shortName = clsName.replaceFirst(pckgname.replace('.', '/'), "");
				if(shortName.startsWith("/")){
					shortName = shortName.replaceFirst("/", "");
				}
                classes.add(Class.forName(pckgname + "." + shortName.substring(0, shortName.length() - 6)));
            }
            return classes;
        } catch (Exception ex) {
            System.out.println(pckgname + " init from jar not found");
        }
        return null;
    }

    public static List<Method> getAllMethodInClass(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        List<Method> listmethod = new ArrayList<>();
        List<Method> method = new ArrayList<Method>(methods.length);
        for (Method m : methods) {
            listmethod.add(m);
        }
        return listmethod;
    }
}
