/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.fpt.sqlorc.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cuongnc
 */
public abstract class SQL {
	public abstract Connection getDBConnection(String jdbcUrl) throws ClassNotFoundException, SQLException;
	public abstract Connection getDBConnection(String host, int port, String userName, String password, String databaseName) throws ClassNotFoundException, SQLException;
	public abstract PreparedStatement getSelectPrepareStatement(Connection conn, String sql) throws ClassNotFoundException, SQLException;
	public abstract PreparedStatement getSelectTablePrepareStatement(Connection conn, String tableName) throws ClassNotFoundException, SQLException;
	public abstract PreparedStatement getMetadata() throws ClassNotFoundException, SQLException;
}
