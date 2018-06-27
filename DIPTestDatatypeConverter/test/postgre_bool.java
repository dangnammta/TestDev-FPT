
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
public class postgre_bool {

	public static void createBooleanCol() {
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/viettq_test";
		try {
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			Statement stmt = connection.createStatement();
			stmt.setQueryTimeout(5);
			String query = "CREATE TABLE BOOLEANCOL(BOOL_TYPE BOOLEAN)";
			stmt.execute(query);
			System.out.println("boolean table is created");
			stmt.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insertBooleanCol() {
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/viettq_test";
		try {
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString, "postgres", "123456abcA");
			String query = "INSERT INTO BOOLEANCOL VALUES (?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setQueryTimeout(5);

			//pstmt.setBoolean(1, true);
			
			//pstmt.setString(1, "true"); -- khong nhan kieu string hay int
			
			
			pstmt.setNull(1, java.sql.Types.BOOLEAN);
			pstmt.executeUpdate();
			System.out.println("1 record had been inserted");
			pstmt.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void getBitColData() throws Exception {
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/viettq_test";
		Class.forName("org.postgresql.Driver");
		DriverManager.setLoginTimeout(5);
		Connection conn = DriverManager.getConnection(connectionString, "postgres", "123456abcA");

		String insertSql = "SELECT * FROM BOOLEANCOL";
		Statement st = conn.createStatement();
		ResultSet rs;
		try {
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
				Boolean tm = rs.getBoolean("BOOL_TYPE");
				//int tm = rs.getInt("BOOL_TYPE");
				System.out.print(tm.toString());
				System.out.println();
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void getMetaData() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		String tblName = "BoolTable";
		String connectionString = "jdbc:postgresql://10.86.222.24:5432/TestPGLongDB";
		try {
			Class.forName("org.postgresql.Driver");
			DriverManager.setLoginTimeout(5);
			conn = DriverManager.getConnection(connectionString,"postgres","123456abcA");

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
			//createBooleanCol();
			//insertBooleanCol();
			//getBitColData();
			getMetaData();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
