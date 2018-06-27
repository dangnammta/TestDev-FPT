/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.utils;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class RessourceFinderExample {

	private static final String packageName = "com.example.controller";

	private static final String[] classPaths = {"plugins", ".", "dist"};

	public static void main(String args[]) {

		URLClassLoader cl = new URLClassLoader(findJarURLsInClasspath(), Thread.currentThread().getContextClassLoader());


		TreeMap<String, Class> classes = RessourceFinderExample.getClassesFromPackage(packageName, cl);

		for (Class c : classes.values()) {
			System.out.println("   CLASS   : " + c.getCanonicalName());
		}

	}

	private static URL[] findJarURLsInClasspath() {
		URL url;
		ArrayList<URL> jarURLs = new ArrayList();

		for (String path : classPaths) {

			File[] jars = new File(path).listFiles(new FileFilter() {
				public boolean accept(File pathname) {

					return pathname.getName().toLowerCase().endsWith(".jar");
				}
			});
			if (jars != null) {
				for (int i = 0; i < jars.length; i++) {
					try {
						url = jars[i].toURI().toURL();

						jarURLs.add(url);
					}
					catch (Exception e) {
					}
				}
			}
		}

		URL[] urls = jarURLs.toArray(new URL[0]);
		return urls;
	}

	private static URL[] getJarURLs(URLClassLoader cl) {
		URL[] result = cl.getURLs();
		ArrayList<URL> urls = new ArrayList();

		for (URL url : result) {

			try {
				Path jarPath = Paths.get(url.toURI());

				for (String classPathString : classPaths) {

					Path classPath = Paths.get(classPathString).toAbsolutePath();

					if (jarPath.startsWith(classPath)) {
						urls.add(url);
					}
				}

			}
			catch (URISyntaxException ex) {
			}
		}

		result = new URL[urls.size()];
		result = urls.toArray(result);

		return result;
	}

	private static TreeMap<String, Class> getClassesFromPackage(String pckgname, URLClassLoader cl) {
		TreeMap<String, Class> result = new TreeMap();
		ArrayList<File> files = new ArrayList();

		for (URL jarURL : getJarURLs(cl)) {
			getClassesInSamePackageFromJar(result, pckgname, jarURL.getPath(), cl);
		}

		return result;
	}

	private static void getClassesInSamePackageFromJar(TreeMap<String, Class> result, String packageName, String jarPath, URLClassLoader cl) {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarPath);

			Enumeration<JarEntry> en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry entry = en.nextElement();
				String entryName = entry.getName();

				packageName = packageName.replace('.', '/');

				if (entryName != null && entryName.endsWith(".class") && entryName.startsWith(packageName) && !entryName.substring(packageName.length() + 1).contains("/")) {

					try {
						Class<?> entryClass = cl.loadClass(entryName.substring(0, entryName.length() - 6).replace('/', '.'));
						if (entryClass != null) {
							result.put(entryClass.getCanonicalName(), entryClass);
						}
					}
					catch (Throwable e) {
                        System.err.println("Error instanciating: " + entryName + " " + e.toString());
					}
				}
			}
		}
		catch (Exception e) {
		}
		finally {
			try {
				if (jarFile != null) {
					jarFile.close();
				}
			}
			catch (Exception e) {
			}
		}
	}

	private static TreeSet<String> getPackageFromPackage(String pckgname, URLClassLoader cl) {
		TreeSet<String> result = new TreeSet();

		for (URL jarURL : getJarURLs(cl)) {
			getPackageInPackageFromJar(result, pckgname, jarURL.getPath(), cl);
		}

		return result;
	}

	private static void getPackageInPackageFromJar(TreeSet<String> result, String packageName, String jarPath, URLClassLoader cl) {
		JarFile jarFile = null;
		try {
			System.out.println("");
			System.out.println("** IN JAR : " + jarPath);

			jarFile = new JarFile(jarPath);

			Enumeration<JarEntry> en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry entry = en.nextElement();
				String entryName = entry.getName();

				packageName = packageName.replace('.', '/');

				if (entryName != null && entryName.endsWith("/") && entryName.startsWith(packageName + "/")) {

					try {
						String packageEntryName = entryName.substring(packageName.length() + 1);
						packageEntryName = packageEntryName.substring(0, packageEntryName.indexOf("/"));

						result.add(packageName.replace('/', '.') + "." + packageEntryName);

						System.out.println("   PACKAGE : " + packageName.replace('/', '.') + "." + packageEntryName);
					}
					catch (Throwable e) {
					}
				}
			}
		}
		catch (Exception e) {
		}
		finally {
			try {
				if (jarFile != null) {
					jarFile.close();
				}
			}
			catch (Exception e) {
			}
		}
	}
}
