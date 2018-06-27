
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class test {
	
	public static void testInsertPostgresImage(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/TestExport";
		
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			
			File file = new File("/home/cuongnc/Documents/image.jpg");
			FileInputStream fis = new FileInputStream(file);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO testimage VALUES (?, ?)");
			int ival = 1;
			ps.setInt(1, 1);
			ps.setBinaryStream(2, fis, (int)file.length());
			ps.executeUpdate();
			ps.close();
			fis.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public static void testSelectPostgresImage(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/TestExport";
		
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			
			PreparedStatement ps = conn.prepareStatement("SELECT imagecol FROM \"TestXMLTable\" WHERE \"ID\" = 2");
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				byte[] imgBytes = rs.getBytes(1);
				// use the data in some way here
				FileOutputStream fos = new FileOutputStream("/home/cuongnc/Documents/image2.jpg");
				fos.write(imgBytes);
				fos.close();
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void testSelectMSSqlImage(){
		String connectionString = "jdbc:sqlserver://10.86.222.24:1433;databaseName=testlongdb;user=sa;password=123456abcA";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
			
			//File file = new File("/home/cuongnc/Documents/image2.jpg");
			
			PreparedStatement ps = conn.prepareStatement("SELECT imagecol FROM TestXMLTable WHERE ID = ?");
			ps.setInt(1, 3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				byte[] imgBytes = rs.getBytes(1);
				// use the data in some way here
				FileOutputStream fos = new FileOutputStream("/home/cuongnc/Documents/image_sql2.jpg");
				fos.write(imgBytes);
				fos.close();
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void testPostgresBit(){
		String connectionString = "jdbc:postgresql://127.0.0.1:5432/testexport";
		
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString, "hotspotuser", "123456");
			
			//Select
			/*PreparedStatement ps = conn.prepareStatement("SELECT bitcol FROM bittable WHERE id = 2");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				byte[] imgBytes = rs.getBytes(1);
				// use the data in some way here
				
			}
			rs.close();
			ps.close();*/
			
			
			//insert
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bittable(id,bitcol) VALUES (?,?::BIT)");
			ps.setInt(1, 1);
			ps.setString(2, "1010111");
			ps.execute();
			ps.close();
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public static void testPostgresTime(){
		String connectionString = "jdbc:postgresql://127.0.0.1:5432/testexport";
		
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString, "hotspotuser", "123456");
			
			//Select
			/*PreparedStatement ps = conn.prepareStatement("SELECT bitcol FROM bittable WHERE id = 2");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				byte[] imgBytes = rs.getBytes(1);
				// use the data in some way here
				
			}
			rs.close();
			ps.close();*/
			
			
			//insert
			PreparedStatement ps = conn.prepareStatement("INSERT INTO timetable(id,timecol,datecol, tscol) VALUES (?,?,?,?)");
			ps.setInt(1, 4);
			String value = "07:34:03";
			java.sql.Time time = java.sql.Time.valueOf(value);
			ps.setTime(2, time);
			
			String value1 = "2016-08-31";
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );  // United States style of format.
			java.util.Date dat = format.parse(value1); 
			ps.setDate(3, new java.sql.Date(dat.getTime()));
			
			
			String value2 = "2016-07-31 11:10:09";
			SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");  // United States style of format.
			java.util.Date dat1 = format1.parse(value2); 
			ps.setTimestamp(4, new java.sql.Timestamp(dat1.getTime()));
			
			ps.execute();
			ps.close();
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			//getMetadata();
//			getData();
//			getDBChangeLog();
			
			//testInsertPostgresImage();
			//testSelectPostgresImage();
			
			//testSelectMSSqlImage();
			
			//testPostgresTime();
			System.out.println("Test File");
			
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}