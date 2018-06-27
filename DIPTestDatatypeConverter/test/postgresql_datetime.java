/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author cuongnc
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class postgresql_datetime {
	/*OK*/
	public static void createTimestampWithoutTimezoneCol(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/DevDatatypeConverter";
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			Statement stmt = connection.createStatement();
			stmt.setQueryTimeout(5);
			String query = "CREATE TABLE \"TimestampTable4\"(\"TimestampCol\" timestamp(8))";
			stmt.execute(query);
			stmt.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*OK*/
	public static void insertTimestampWithoutTimezoneCol(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/DevDatatypeConverter";
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			
			//query
			String query = "INSERT INTO \"TimestampTable4\" VALUES (?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setQueryTimeout(5);
			
			//Convert from long epoch
			String value = "1454557265132";
			long val = Long.valueOf(value);
			pstmt.setTimestamp(1, new java.sql.Timestamp(val));
			
			//value
			//
			//String value = "2016-04-03 02:03:05.12356789";
			
			/*SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );  // United States style of format.
			java.util.Date dat = format.parse(value); 
			pstmt.setTimestamp(1, new java.sql.Timestamp(dat.getTime()));*/
			
			//pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(value));
			pstmt.executeUpdate();
			pstmt.close();
			//System.out.println("Insert: " + value);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*OK*/
	public static void createTimestampWithTimezoneCol(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/DevDatatypeConverter";
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			Statement stmt = connection.createStatement();
			stmt.setQueryTimeout(5);
			String query = "CREATE TABLE \"TimestampTable3\"(\"ID\" INTEGER,\"TimestampCol\" timestamp(5) with time zone)";
			stmt.execute(query);
			stmt.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*OK*/
	public static void insertTimestampWithTimezoneCol(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/DevDatatypeConverter";
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			
			//query
			String query = "INSERT INTO \"TimestampTable3\" VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setQueryTimeout(5);
			
			pstmt.setInt(1, 1);
			
			//Convert from long epoch
			String value = "1454557265132";
			long val = Long.valueOf(value);
			pstmt.setTimestamp(2, new java.sql.Timestamp(val));
			
			//value
			//String value = "2016-06-02 02:03:15.123+10:30";
			
			//SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSSXXX" );  // United States style of format.
			//java.util.Date dat = format.parse(value); 
			//pstmt.setTimestamp(2, new java.sql.Timestamp(dat.getTime()));
			
			//System.out.println("Insert date: " + dat);
			//java.sql.Timestamp tm = java.sql.Timestamp.valueOf(value);
			//pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(value));
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void selectTimestampWithoutTimezoneCol(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/DevDatatypeConverter";
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			Statement stmt = connection.createStatement();
			stmt.setQueryTimeout(5);
			//query
			String query = "SELECT * FROM \"TimestampTable3\"";
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				
				String valstr =  result.getString("TimestampCol");
				System.out.println("Select string out: " + valstr);
				
				java.sql.Timestamp val =  result.getTimestamp("TimestampCol");
				
				//System.out.println("Select out: " + val.toString());
				
				System.out.println("Select direct out: " + val.getTime());
				
				System.out.println("Select direct out: " + val.toLocalDateTime());
				
				
				
				
				
				
				/*long milis = val.getTime() + (val.getNanos()/1000000000);
				1464895995123
				
				
				int nano = val.getNanos();
				System.out.println("Select out second: " + val.getTime());
				System.out.println("Select out mili: " + nano/1000000000);
				
				
				Date dat = new Date(milis);
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
				System.out.println("Select out: " + format.format(dat));*/
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void selectTOracle(){
		String connectionString = "jdbc:oracle:thin:@10.86.222.24:1521:xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "system", "12345");
			Statement stmt = connection.createStatement();
			stmt.setQueryTimeout(5);
			//query
			String query = "SELECT * FROM \"TIMESTAMPWITHTIMEZONE\"";
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				
				String valstr =  result.getString("TIMESTAMPWITHTIMEZONE");
				System.out.println("Select string out: " + valstr);
				
				java.sql.Timestamp val =  result.getTimestamp("TIMESTAMPWITHTIMEZONE");
				
				//System.out.println("Select out: " + val.toString());
				
				System.out.println("Select direct out: " + val.getTime());
				
				System.out.println("Select direct local out: " + val.toLocalDateTime());
				
				System.out.println("-------------");
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void getMetaDataCol(){
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/DevDatatypeConverter";
		String tblName = "TimestampTable3";
		String colName = "TimestampCol";
		try{
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			
			DatabaseMetaData dbMetadata = connection.getMetaData();
			ResultSet rsTables = dbMetadata.getTables(null, null, null, new String[]{"TABLE"});
			
			while (rsTables.next()) {
				String schemaName = rsTables.getString("TABLE_SCHEM");
				String tableName = rsTables.getString("TABLE_NAME");
				
				if(tblName.equals(tableName)){
					ResultSet rsColumns = dbMetadata.getColumns(null, schemaName, tableName, null);
					
					while (rsColumns.next()) {
						String columnName = rsColumns.getString("COLUMN_NAME");
						if (colName.equals(columnName)){
							String columnDataType = rsColumns.getString("TYPE_NAME").toUpperCase();
							int columnLength = rsColumns.getInt("COLUMN_SIZE");
							int columnFractionalDigits = rsColumns.getInt("DECIMAL_DIGITS");
							int columnNullable = rsColumns.getInt("NULLABLE");
						}
					}
					break;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			//createTimestampWithoutTimezoneCol();
			//insertTimestampWithoutTimezoneCol();
			//createTimestampWithTimezoneCol();
			insertTimestampWithTimezoneCol();
			//getMetaDataCol();
			insertTimestampWithoutTimezoneCol();
			
			//selectTimestampWithoutTimezoneCol();
			
			//selectTOracle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}