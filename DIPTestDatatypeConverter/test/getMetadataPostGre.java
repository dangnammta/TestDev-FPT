
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author viettq
 */
public class getMetadataPostGre {

	public static Connection getConnection() {
		try {
			String connectionString = "jdbc:postgresql://10.86.222.24:5432/QLVBQ3";
			Class.forName("org.postgresql.Driver");

			//DriverManager.setLoginTimeout(Configuration._dbTimeout);
			return DriverManager.getConnection(connectionString, "postgres", "123456abcA");
		}
		catch (Exception ex) {
			StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
			//FLogServiceClient.getInstance().printSystemLog("[" + Configuration._serviceName + "] " + this.getClass().getSimpleName() + "." + traceElement.getMethodName() + "(): " + ex.getMessage());
		}
		return null;
	}

	public static void getMetadata(Connection connection) {
		try {
			List<String> lst = new ArrayList<String>();
			DatabaseMetaData dbMetadata = connection.getMetaData();
			ResultSet rsTables = dbMetadata.getTables(null, null, null, new String[]{"TABLE"});
			while (rsTables.next()) {
				String schemaName = rsTables.getString("TABLE_SCHEM");
				String tableName = rsTables.getString("TABLE_NAME");

				ResultSet rsColumns = dbMetadata.getColumns(null, schemaName, tableName, null);

				while (rsColumns.next()) {
					String name = rsColumns.getString("COLUMN_NAME");
					String originalDataType = rsColumns.getString("TYPE_NAME").toUpperCase();
					int colLength = rsColumns.getInt("COLUMN_SIZE");
					int colFractionalDigits = rsColumns.getInt("DECIMAL_DIGITS");
					int colNullable = rsColumns.getInt("NULLABLE");
					lst.add(originalDataType + "-" + tableName + "-" + name + "--" + colLength + "--" + colNullable);
				}
			}
			//removeDuplicate(lst);

			PrintWriter pw = new PrintWriter(new FileOutputStream("/home/viettq/Documents/bpcharQLVB.txt"));
			String str1 = "";
			for (String line : lst)
				if (line.startsWith("BPCHAR")) {
					pw.println(line);
				}
			pw.close();
		}
		catch (Exception ex) {
			StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
			System.out.println(ex.getMessage());
		}
		//return response;
	}

	public static List removeDuplicate(List arrList) {
		HashSet h = new HashSet(arrList);
		arrList.clear();
		arrList.addAll(h);

		return arrList;
	}

	public static void main(String[] args) {
		Connection con = getConnection();
		try {
			getMetadata(con);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
