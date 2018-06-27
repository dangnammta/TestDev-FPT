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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class mssql_datetime {

	private static final String connectionString = "jdbc:sqlserver://10.86.222.24:1433;databaseName=testlongdb;user=sa;password=123456abcA";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static void createTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
			String sqlmodify = "ALTER TABLE TIMECOL "
					+ "ALTER COLUMN TIMETYPE TIME(5)";
			//String sql = "CREATE TABLE TIMECOL(TIMETYPE TIME(4))";
			PreparedStatement ps = conn.prepareStatement(sqlmodify);
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
			Connection conn = DriverManager.getConnection(connectionString);

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
		Connection conn = DriverManager.getConnection(connectionString);

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
			java.sql.Timestamp tm = rs.getTimestamp("TIMETYPE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}

	public static void createDateCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
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
			Connection conn = DriverManager.getConnection(connectionString);

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
		Connection conn = DriverManager.getConnection(connectionString);

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
	
	public static void createSmallDateTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
			String sql = "CREATE TABLE SMALLDATETIMECOL(SMALLDATETIMECOL SMALLDATETIME)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table SMALLDATETIMECOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void insertSmallDateTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);

			String insertSql = "INSERT INTO SMALLDATETIMECOL(SMALLDATETIMECOL) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			
			String str = "1289375173771";
			long val = Long.valueOf(str);
			ps.setTimestamp(1, new java.sql.Timestamp(val));
			//ps.setTimestamp(1, java.sql.Timestamp.valueOf("2016-12-30 20:30:20"));

			ps.executeUpdate();
			System.out.println("Record inserted into SMALLDATETIMECOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void getSmallDateTimeColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(connectionString);

		String insertSql = "SELECT * FROM SMALLDATETIMECOL";
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
			java.sql.Timestamp tm = rs.getTimestamp("SMALLDATETIMECOL");
			System.out.print(tm.toString());
			System.out.println();
		}

	}
	
	public static void createDateTimeCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
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
			Connection conn = DriverManager.getConnection(connectionString);

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
		Connection conn = DriverManager.getConnection(connectionString);

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
	
	public static void createDateTime2Col() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
			String sql = "CREATE TABLE DATETIME2COL(DATETIME_TYPE DATETIME2)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table DATETIMECOL2 created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void insertDateTime2Col() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);

			String insertSql = "INSERT INTO DATETIME2COL(DATETIME_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			String str = "1289375173771";
			long val = Long.valueOf(str);
			ps.setTimestamp(1, new java.sql.Timestamp(val));
			//ps.setTimestamp(1, java.sql.Timestamp.valueOf("2016-12-30 20:30:20"));

			ps.executeUpdate();
			System.out.println("Record inserted into DATETIME2COL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void getDateTime2ColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(connectionString);

		String insertSql = "SELECT * FROM DATETIME2COL";
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
	
	public static void createDateTimeOffsetCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
			String sql = "CREATE TABLE DATETIMEOFFSETCOL(DATETIMEOFFSET_TYPE DATETIMEOFFSET)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table DATETIMEOFFSETCOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void insertDateTimeOffsetCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);

			String insertSql = "INSERT INTO DATETIMEOFFSETCOL(DATETIMEOFFSET_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			String str = "1289375173771";
			long val = Long.valueOf(str);
			ps.setTimestamp(1, new java.sql.Timestamp(val));
			//ps.setTimestamp(1, java.sql.Timestamp.valueOf("2016-12-30 20:30:20"));

			ps.executeUpdate();
			System.out.println("Record inserted into DATETIMEOFFSETCOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void getDateTimeOffsetColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(connectionString);

		String insertSql = "SELECT * FROM DATETIME2COL";
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

	public static void getMetaDataTimestamp() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String tblName = "TIMECOL";
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			conn = DriverManager.getConnection(connectionString);

			DatabaseMetaData dbMetadata = conn.getMetaData();
			ResultSet rsTables = dbMetadata.getTables(null, null, null, new String[]{"TABLE"});

			while (rsTables.next()) {
				String schemaName = rsTables.getString("TABLE_SCHEM");
				String tableName = rsTables.getString("TABLE_NAME");

				if (tblName.equals(tableName)) {
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
			//createDateCol();
			//insertDateCol();
			//getDateColData();
			//createSmallDateTimeCol();
			//insertSmallDateTimeCol();
			//getSmallDateTimeColData();
			//createDateTimeCol();
			//insertDateTimeCol();
			//getDateTimeColData(); //kiem tra insert db
			//createDateTime2Col();
			//insertDateTime2Col();
			//getDateTime2ColData(); --kiem tra lai insert ddb
			createDateTimeOffsetCol();
			insertDateTimeOffsetCol();
			getDateTimeOffsetColData();

			//getMetaDataTimestamp();
			System.out.println("Test File");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
