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
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class mysql_datetime {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.86.222.24:3306/testlongdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "12345";

	public static void createTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql = "ALTER TABLE TIMECOL MODIFY COLUMN TIMETYPE TIME(3);";
			//String sql = "CREATE TABLE TIMECOL(TIMETYPE TIME)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table TIMECOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insertTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String insertSql = "INSERT INTO TIMECOL(TIMETYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			String lz = "1289375173771";
			long val = Long.valueOf(lz);

			ps.setTime(1, new java.sql.Time(val));

			ps.executeUpdate();
			System.out.println("Record is inserted into TIMECOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void getTimeColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

		String insertSql = "SELECT * FROM TIMECOL";
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery(insertSql);

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.print(columnName + "   ");

		}
		System.out.println();
		System.out.println("----------------------");

		while (rs.next()) {
			java.sql.Time tm = rs.getTime("TIMETYPE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}

	public static void createDateCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql = "CREATE TABLE DATECOL(DATE_TYPE DATE)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table DATECOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insertDateCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String insertSql = "INSERT INTO DATECOL(DATE_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			ps.setDate(1, java.sql.Date.valueOf("2010-10-14"));

			ps.executeUpdate();
			System.out.println("Record is inserted into DATECOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void getDateColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

		String insertSql = "SELECT * FROM DATECOL";
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery(insertSql);

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.print(columnName + "   ");

		}
		System.out.println();
		System.out.println("----------------------");

		while (rs.next()) {
			java.sql.Date tm = rs.getDate("DATE_TYPE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}
	

	public static void createDateTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql = "CREATE TABLE DATETIMECOL(DATETIME_TYPE DATETIME)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table DATETIMECOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void insertDateTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String insertSql = "INSERT INTO DATETIMECOL(DATETIME_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			String str = "1289375173771";
			long val = Long.valueOf(str);
			ps.setTimestamp(1, new java.sql.Timestamp(val));
			//ps.setTimestamp(1, java.sql.Timestamp.valueOf("2016-12-30 20:30:20"));

			ps.executeUpdate();
			System.out.println("Record inserted into DATETIMECOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void getDateTimeColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

		String insertSql = "SELECT * FROM DATETIMECOL";
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery(insertSql);

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.print(columnName + "   ");

		}
		System.out.println();
		System.out.println("----------------------");

		while (rs.next()) {
			java.sql.Timestamp tm = rs.getTimestamp("DATETIME_TYPE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}
	
	public static void createTimestampCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql = "CREATE TABLE TIMESTAMPCOL(TIMESTAMP_TYPE TIMESTAMP)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table TIMESTAMPCOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void insertTimestampCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String insertSql = "INSERT INTO TIMESTAMPCOL(TIMESTAMP_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			String str = "1289375173771";
			long val = Long.valueOf(str);
			ps.setTimestamp(1, new java.sql.Timestamp(val));
			//ps.setTimestamp(1, java.sql.Timestamp.valueOf("2016-12-30 20:30:20"));

			ps.executeUpdate();
			System.out.println("Record inserted into TIMESTAMP table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void getTimestampColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

		String insertSql = "SELECT * FROM TIMESTAMPCOL";
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery(insertSql);

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.print(columnName + "   ");

		}
		System.out.println();
		System.out.println("----------------------");

		while (rs.next()) {
			java.sql.Timestamp tm = rs.getTimestamp("TIMESTAMP_TYPE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}
	
	public static void createYearCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql = "CREATE TABLE YEARCOL(YEAR_TYPE YEAR)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table YEARCOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void insertYearCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String insertSql = "INSERT INTO YEARCOL(YEAR_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			ps.setInt(1,1986);
			ps.executeUpdate();
			System.out.println("Record inserted into YEARCOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void getYearColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

		String insertSql = "SELECT * FROM YEARCOL";
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery(insertSql);

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.print(columnName + "   ");

		}
		System.out.println();
		System.out.println("----------------------");

		while (rs.next()) {
			java.sql.Timestamp tm = rs.getTimestamp("YEAR_TYPE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}

	public static void getMetaDataTimestamp() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String tblName = "YEARCOL";
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			DatabaseMetaData dbMetadata = conn.getMetaData();
			ResultSet rsTables = dbMetadata.getTables(null, null, null, new String[]{"TABLE"});

			while (rsTables.next()) {
				String schemaName = rsTables.getString("TABLE_SCHEM");
				String tableName = rsTables.getString("TABLE_NAME");

				if (tblName.equalsIgnoreCase(tableName)) {
					ResultSet rsColumns = dbMetadata.getColumns(null, schemaName, tableName, null);

					while (rsColumns.next()) {
						String colName = rsColumns.getString("COLUMN_NAME");
						String colDataType = rsColumns.getString("TYPE_NAME").toUpperCase();
						int colLength = rsColumns.getInt("COLUMN_SIZE");
						int colFractionalDigits = rsColumns.getInt("DECIMAL_DIGITS");
						int colNullable = rsColumns.getInt("NULLABLE");
					}
					break;
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			//createTimeCol();
			//insertTimeCol();
			//getTimeColData();
//			createDateCol();
//			insertDateCol();
//			getDateColData();
//			createDateTimeCol();
//			insertDateTimeCol();
//			getDateTimeColData();
			//createTimestampCol();
			//insertTimestampCol();
			//getTimestampColData();
			//createYearCol();
			//insertYearCol();
			getYearColData();
			//getMetaDataTimestamp();
			System.out.println("Test File");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
}
