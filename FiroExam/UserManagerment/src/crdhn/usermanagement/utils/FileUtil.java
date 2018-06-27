/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;

import crdhn.usermanagement.entity.User;

/**
 *
 * @author Chungnv
 */
public class FileUtil {

	private static String realPath;

	/**
	 * @param realPath
	 *            the realPath to set
	 */
	public static void setRealPath(String realPath) {
		FileUtil.realPath = realPath;
	}

	public static void main(String[] args) {
	}
	/**
	 * Hàm upload ảnh dùng part của servlet 3.0
	 * 
	 * @param part
	 * @param contextPath
	 * @param pathImage
	 * @return
	 */
	public static String uploadImage(String dataURL, User user, String pathImage) {
		String base64 = dataURL.substring(dataURL.indexOf("base64,")+7);
		FileOutputStream outThumbnail;
		String thumbnails = "";
		Random rd = new Random();
		try {
			String fileName = "UserID_"+ "hoa" + "_Avatar"+ rd.nextInt()+".png";
			thumbnails = fileName;
			File theDir = new File(realPath + pathImage);
			String thumbnailCreate = theDir + "/" + getFolderSaveImage() + "/" + fileName;
			//Create new file
			final File tempFile = new File(thumbnailCreate);
			if (!tempFile.exists()) {
				tempFile.getParentFile().mkdirs();
				tempFile.createNewFile();
			} else {
				tempFile.deleteOnExit();
			}
			//Write data to fie
			outThumbnail = new FileOutputStream(tempFile);
			outThumbnail.write(DatatypeConverter.parseBase64Binary(base64));
			outThumbnail.close();
		} catch (IOException ex) {
			Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return thumbnails;
	}
	public static String getFolderSaveImage() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// System.out.println(year + "_" + month + "_" + day);
		return year + "_" + month + "_" + day;
	}

	/**
	 * Hàm xóa file
	 * 
	 * @param serverPath
	 * @return
	 */
	public static boolean deleteFile(String serverPath) {
		boolean deleted = false;
		File file = new File(serverPath);

		if (file.exists() && !file.isDirectory()) {
			deleted = file.delete();
		}
		return deleted;
	}

	/**
	 * Hàm lấy tên file theo Part
	 * 
	 * @param part
	 * @return
	 */
	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}

}
