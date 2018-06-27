
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author viettq
 */
public class mssql_bit_boolean {

	private static final String connectionString = "jdbc:sqlserver://10.86.222.24:1433;databaseName=testlongdb;user=sa;password=123456abcA";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static void createBitCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);
			//String sql = "ALTER TABLE BITCOL "
			//		+ "ALTER COLUMN BIT_TYPE BIT";
			String sql = "CREATE TABLE BITCOL(BIT_TYPE BIT)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Table BITCOL created!");
			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insertBitCol() {
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection conn = DriverManager.getConnection(connectionString);

			String insertSql = "INSERT INTO BITCOL(BIT_TYPE) VALUES(?)";

			PreparedStatement ps = conn.prepareStatement(insertSql);

			ps.setInt(1, 1);

			ps.executeUpdate();
			System.out.println("Record is inserted into BITCOL table!");

			conn.close();
			ps.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void getBitColData() throws Exception {
		Class.forName(JDBC_DRIVER);
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(connectionString);

		String insertSql = "SELECT * FROM BITCOL";
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
			Boolean tm = rs.getBoolean("BIT_TYPE");
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
			//createBitCol();
//			insertBitCol();
//			getBitColData();
			//getMetaDataTimestamp();
			System.out.println("Test ok");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
