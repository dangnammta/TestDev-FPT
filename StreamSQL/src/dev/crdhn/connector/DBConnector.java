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

/**
 *
 * @author cuongnc
 */
public class DBConnector {
	
	public enum DBType
	{
		POSTGRESQL, ORACLE, MYSQL, SQLSERVER
	}
	
	private static DBConnector _instance = null;
	
	private String _dbName;
	private String _host;
	private int _port;
	private String _userName;
	private String _password;
	private DBType _dbType;

	public static DBConnector getInstance() {
		if (_instance == null) {
			_instance = new DBConnector();
		}
		return _instance;
	}
	
	public void setDBInfo(DBType dbtype, String dbName, String host, int port, String userName, String password){
		_dbType = dbtype;
		_dbName = dbName;
		_host = host;
		_port = port;
		_userName = userName;
		_password = password;
		
		//getMetadata cache TOriginMetadata Object here
		
		
		//
		
	}
	
	//private static final String connectionString = "jdbc:sqlserver://10.86.222.24:1444;databaseName=CucThue;user=sa;password=123456abcA";
	
	//private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public static String getDataStatement(String tableName){
		return "Select * from dbo.DANHBA";
	}
	
	public Connection getConnection(){
		if(_dbType == DBType.SQLSERVER){
			return SQLServer.getConnection(_dbName, _host, _port, _userName, _password);
		}
		return null;
	}
	
	public Map getMetadata(){
		//getcache here
		
		
		//
		return null;
	}
}
