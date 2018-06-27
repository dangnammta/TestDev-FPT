/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.crdhn.connector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cuongnc
 */
public class SQLServer {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public static Map getSchemaMap(){
		return null;
	}
	
	public static Connection getConnection(String dbName, String host, int port, String userName, String password){
		try {
			String connectionString = "jdbc:sqlserver://"
					+ host + ":" + Integer.toString(port) + ";databaseName=" + dbName
					+ ";user=" + userName + ";password=" + password;
			
			Class.forName(JDBC_DRIVER);
			DriverManager.setLoginTimeout(5);
			Connection connection = DriverManager.getConnection(connectionString);
			return connection;
		}
		catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(SQLServer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	
	public static Object getMetadata(Connection connection){
		try {
			DatabaseMetaData metadata = connection.getMetaData();
		}
		catch (SQLException ex) {
			Logger.getLogger(SQLServer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
