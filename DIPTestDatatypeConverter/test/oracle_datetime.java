
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class oracle_datetime {

	private static final String connectionString = "jdbc:oracle:thin:@10.86.222.24:1521:xe";
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String USER = "system";
	private static final String PWD = "12345";

	/*OK*/
	public static void createCharCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String createTableSQL = "CREATE TABLE CHARCOL("
				+ "USERNAME CHAR(20) NOT NULL "
				+ ")";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(createTableSQL);

			// execute insert SQL stetement
			preStatement.executeUpdate();
			System.out.println("Table is created!");
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.getMessage();
		}
	}

	/*OK*/
	public static void insertCharCol() {
		//create table.
		Connection conn = null;
		PreparedStatement preStatement = null;
		String insertTableSql = "INSERT INTO CHARCOL(USERNAME) VALUES(?) ";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(insertTableSql);
			preStatement.setString(1, "testname");

			// execute insert SQL stetement
			preStatement.executeUpdate();

			System.out.println("Record is inserted into CHARCOL table!");
			preStatement.close();
			conn.close();
		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	/*OK*/
	public static void createDateCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String createTableSQL = "CREATE TABLE DATECOL("
				+ "DATETIME DATE"
				+ ")";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(createTableSQL);

			// execute insert SQL stetement
			preStatement.executeUpdate();
			System.out.println("Table DATECOL is created!");
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.getMessage();
		}
	}

	/*OK*/
 /* ref: http://stackoverflow.com/questions/18614836/using-setdate-in-preparedstatement*/
	public static void insertDataInDateCol() {
		//create table.
		Connection conn = null;
		PreparedStatement preStatement = null;
		String insertTableSql = "INSERT INTO DATECOL(DATETIME) VALUES(?) ";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(insertTableSql);
			//preStatement.setDate(1,  java.sql.Date.valueOf("2013-09-04"));
			preStatement.setTimestamp(1, java.sql.Timestamp.valueOf("2013-09-04 13:30:00"));

			// execute insert SQL stetement
			preStatement.executeUpdate();

			System.out.println("Record is inserted into DATECOL table!");
			preStatement.close();
			conn.close();
		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	/*OK*/
	public static void updateDataDateCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String stringSql = "UPDATE DATECOL SET DATETIME =? ";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(stringSql);
			preStatement.setTimestamp(1, java.sql.Timestamp.valueOf("2016-09-04 14:30:00"));

			// execute insert SQL stetement
			preStatement.executeUpdate();

			//Check
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM DATECOL");
			outputResultSet(rs);

			System.out.println("Table updated");
			preStatement.close();
			conn.close();
		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void delDataInDateCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String stringSql = "DELETE FROM DATECOL WHERE DATETIME =? ";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(stringSql);
			preStatement.setTimestamp(1, java.sql.Timestamp.valueOf("2016-09-04 14:30:00"));

			// execute insert SQL stetement
			preStatement.executeUpdate();

			//Check
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM DATECOL");
			outputResultSet(rs);

			System.out.println("Table deleted col ");
			preStatement.close();
			conn.close();
		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	private static void createTimeStampCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String createTableSQL = "CREATE TABLE TIMESTAMPCOLTABLE("
				+ "TIMESTAMP_TYPE TIMESTAMP"
				+ ")";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(createTableSQL);

			// execute insert SQL stetement
			preStatement.executeUpdate();
			System.out.println("Table TIMESTAMPCOL created!");
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.getMessage();
		}
	}

	private static void modifyTimeStampCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String modifyTableSQL = "ALTER TABLE TIMESTAMPCOLTABLE MODIFY "
				+ "TIMESTAMP_TYPE TIME";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(modifyTableSQL);

			// execute insert SQL stetement
			preStatement.executeUpdate();
			System.out.println("Table TIMESTAMPCOL modified!");
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.getMessage();
		}
	}

	public static void insertTimeStampCol() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String insertTableSql = "INSERT INTO TIMESTAMPCOLTABLE(TIMESTAMP_TYPE) VALUES(?) ";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(insertTableSql);
			preStatement.setTimestamp(1, java.sql.Timestamp.valueOf("2009-02-24 12:51:42.123456789"));
			// execute insert SQL stetement
			preStatement.executeUpdate();

			//print values on console
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM TIMESTAMPCOLTABLE");
			outputResultSet(rs);

			System.out.println("Record is inserted into TIMESTAMPCOLTABLE table!");
			preStatement.close();
			conn.close();
		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	public static void createTimeStampWithTimeZone() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String createTableSQL = "CREATE TABLE TIMESTAMPWITHTIMEZONE("
				+ "TIMESTAMPWITHTIMEZONE TIMESTAMP WITH TIME ZONE"
				+ ")";
		//create table foo ( tswtz TIMESTAMP WITH TIME ZONE);
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(createTableSQL);

			// execute insert SQL stetement
			preStatement.executeUpdate();
			System.out.println("Table TIMESTAMPWITHTIMEZONE created!");
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.getMessage();
		}

	}

	public static void insertTimeStampWithTimeZone() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		//TZH:TZM or TZR TZD format elements
		String insertTableSql = "INSERT INTO TIMESTAMPWITHTIMEZONE(TIMESTAMPWITHTIMEZONE) "
				+ "VALUES(TO_TIMESTAMP_TZ (?, 'DD-MON-YYYY HH24:MI:SS TZH:TZM')) ";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(insertTableSql);
			preStatement.setString(1, "21-FEB-2009 18:00:00 +10");
			// execute insert SQL stetement
			preStatement.executeUpdate();

			System.out.println("Record is inserted into TIMESTAMPWITHTIMEZONE table!");

			//get values
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT TIMESTAMPWITHTIMEZONE at time zone 'UTC' as TIMESTAMPWITHTIMEZONE FROM TIMESTAMPWITHTIMEZONE");
			outputResultSet(rs);

			preStatement.close();
			conn.close();

		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	private static void createTimeStampWithLocalTimeZone() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String createTableSQL = "CREATE TABLE TIMESTAMPLOCALZONE("
				+ "LOCAL_TIME_ZONE TIMESTAMP WITH LOCAL TIME ZONE"
				+ ")";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(createTableSQL);

			// execute insert SQL stetement
			preStatement.executeUpdate();
			System.out.println("Table TIMESTAMPLOCALZONE created!");
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.getMessage();
		}
	}
	
	public static void InsertTimeStampLocalZone(){
		Connection conn = null;
		PreparedStatement preStatement = null;
		String insertTableSql = "INSERT INTO TIMESTAMPLOCALZONE(LOCAL_TIME_ZONE) "
				+ "VALUES(TO_TIMESTAMP_TZ (?, 'DD-MON-YYYY HH24:MI:SS TZR'))";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionString, USER, PWD);
			preStatement = conn.prepareStatement(insertTableSql);
			preStatement.setString(1, "21-FEB-2009 15:23:45 EST");
			// execute insert SQL stetement
			preStatement.executeUpdate();

			System.out.println("Record is inserted into TIMESTAMPLOCALZONE table!");

			//get values
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT LOCAL_TIME_ZONE at time zone 'UTC' as LOCAL_TIME_ZONE FROM TIMESTAMPLOCALZONE");
			outputResultSet(rs);
			preStatement.close();
			conn.close();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void outputResultSet(ResultSet rs) throws Exception {
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			System.out.print(columnName + "   ");

		}
		System.out.println();
		System.out.println("----------------------");

		while (rs.next()) {
			java.sql.Timestamp tm = rs.getTimestamp("TIMESTAMPWITHTIMEZONE");
			System.out.print(tm.toString());
			System.out.println();
		}

	}

	public static void getMetaDataTimestamp() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		//String tblName = "TIMESTAMPWITHTIMEZONE";
		String tblName = "TIMESTAMPLOCALZONE";
		
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			conn = DriverManager.getConnection(connectionString, USER, PWD);

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
			//createCharCol();
			//insertCharCol();
			//createDateCol();
			//insertDataInDateCol();
			//updateDataDateCol();
			//delDataInDateCol();
			//createTimeColTable();
			//createTimeStampCol();
			//modifyTimeStampCol();
			//insertTimeStampCol();
			getMetaDataTimestamp();
			//createTimeStampWithTimeZone();
			//insertTimeStampWithTimeZone();
			//createTimeStampWithLocalTimeZone();
			//InsertTimeStampLocalZone();
			System.out.println("Test File");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
