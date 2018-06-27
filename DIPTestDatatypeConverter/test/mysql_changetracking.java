//
//import java.sql.Connection;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Map;
//
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author cuongnc
// */
//public class mysql_changetracking {
//	
//	private String getCreateInsertTriggerQuery(TImportedTableInfo tableInfo, Map<Long, String> mapColId2ColName) {
//		//String query = "DROP TRIGGER IF EXISTS dip_trigger_" + tableInfo.name + "_insert;\n"
//		//String query =  "DELIMITER $$\n"
//		String query =  "CREATE TRIGGER dip_trigger_" + tableInfo.name + "_insert "
//				+ "AFTER INSERT ON " + tableInfo.name + " "
//				+ "FOR EACH ROW "
//				+ "BEGIN "
//				+ " INSERT INTO dip_logging.dip_change_log "
//				+ "   (schema_name, table_name, log_operation, new_rowid, new_data) "
//				+ " VALUES "
//				+ "   (\"\", \"" + tableInfo.name + "\", \"INSERT\"";
//		String newRowId = "";
//		for (Long columnId : tableInfo.primaryKeys) {
//			String columnName = mapColId2ColName.get(columnId);
//			if (newRowId.isEmpty())
//				newRowId = newRowId.concat(", CONCAT(CAST(NEW." + columnName + " AS CHAR)");
//			else
//				newRowId = newRowId.concat(", CAST(NEW." + columnName + " AS CHAR)");
//		}
//		newRowId = newRowId.concat(")");
//		query = query.concat(newRowId);
//
//		String newData = "";
//		for (Long columnId : tableInfo.columns) {
//			String columnName = mapColId2ColName.get(columnId);
//			if(columnName.endsWith("time_type") || columnName.endsWith("timestamp_type")|| columnName.endsWith("timestamp_type")){
//				if (newData.isEmpty())
//					newData = ", CONCAT(\"" + columnName + ":\", CAST(NEW." + columnName + " AS CHAR)";
//				else
//					newData += ", \"#DIP#" + columnName + ":\", CAST(NEW." + columnName + " AS CHAR)";
//			}
//			
//		}
//		newData = newData.concat(")");
//		
//		query = query.concat(newData);
//		query += " ); "
//				+ " END ;";
//				//+ "DELIMITER ;";
//		return query;
//	}
//
//	private String getCreateUpdateTriggerQuery(TImportedTableInfo tableInfo, Map<Long, String> mapColId2ColName) {
//		//String query = "DROP TRIGGER IF EXISTS dip_trigger_" + tableInfo.name + "_update;\n"
//		//		+ "DELIMITER $$\n"
//		String query =  "CREATE TRIGGER dip_trigger_" + tableInfo.name + "_update "
//				+ "AFTER UPDATE ON " + tableInfo.name + " "
//				+ "FOR EACH ROW "
//				+ "BEGIN "
//				+ " INSERT INTO dip_logging.dip_change_log "
//				+ "   (schema_name, table_name, log_operation, old_rowid, new_rowid, new_data) "
//				+ " VALUES "
//				+ "   (\"\", \"" + tableInfo.name + "\", \"UPDATE\"";
//		//old_rowid
//		String oldRowId = "";
//		for (Long columnId : tableInfo.primaryKeys) {
//			String columnName = mapColId2ColName.get(columnId);
//			if (oldRowId.isEmpty())
//				oldRowId = oldRowId.concat(", CONCAT(CAST(OLD." + columnName + " AS CHAR)");
//			else
//				oldRowId = oldRowId.concat(", CAST(OLD." + columnName + " AS CHAR)");
//		}
//		oldRowId = oldRowId.concat(")");
//		query = query.concat(oldRowId);
//
//		//new_rowid
//		String newRowId = "";
//		for (Long columnId : tableInfo.primaryKeys) {
//			String columnName = mapColId2ColName.get(columnId);
//			if (newRowId.isEmpty())
//				newRowId = newRowId.concat(", CONCAT(CAST(NEW." + columnName + " AS CHAR)");
//			else
//				newRowId = newRowId.concat(", CAST(NEW." + columnName + " AS CHAR)");
//		}
//		newRowId = newRowId.concat(")");
//		query = query.concat(newRowId);
//
//		//new_data
//		String newData = "";
//		for (Long columnId : tableInfo.columns) {
//			String columnName = mapColId2ColName.get(columnId);
//			if (newData.isEmpty())
//				newData = ", CONCAT(\"" + columnName + ":\", CAST(NEW." + columnName + " AS CHAR)";
//			else
//				newData += ", \"#DIP#" + columnName + ":\", CAST(NEW." + columnName + " AS CHAR)";
//		}
//		newData = newData.concat(")");
//		query = query.concat(newData);
//		query += "); "
//				+ " END ;";
//				//+ "DELIMITER ;";
//		return query;
//	}
//
//	private String getCreateDeleteTriggerQuery(Map<Long, String> mapColId2ColName) {
//		//String query = "DROP TRIGGER IF EXISTS dip_trigger_" + tableInfo.name + "_delete;\n"
//		//		+ "DELIMITER $$\n"
//		
//		String table_name = "testtable";
//		String query =  "CREATE TRIGGER dip_trigger_" + table_name + "_delete "
//				+ "AFTER DELETE ON " + table_name + " "
//				+ "FOR EACH ROW "
//				+ "BEGIN "
//				+ " INSERT INTO dip_logging.dip_change_log "
//				+ "   (schema_name, table_name, log_operation, old_rowid) "
//				+ " VALUES "
//				+ "   (\"\", \"" + table_name + "\", \"DELETE\"";
//		String oldRowId = "";
//		for (Long columnId : tableInfo.primaryKeys) {
//			String columnName = mapColId2ColName.get(columnId);
//			if (oldRowId.isEmpty())
//				oldRowId = oldRowId.concat(", CONCAT(CAST(OLD." + columnName + " AS CHAR)");
//			else
//				oldRowId = oldRowId.concat(", CAST(OLD." + columnName + " AS CHAR)");
//		}
//		oldRowId = oldRowId.concat(")");
//		query = query.concat(oldRowId);
//		query += " ); "
//				+ "END ;";
//				//+ "DELIMITER ;";
//		return query;
//	}
//	
//	public boolean enableChangeTracking(Connection connection, TImportedDBInfo dbInfo) {
//		try (Statement stmt = connection.createStatement()) {
//			stmt.setQueryTimeout(Configuration._dbTimeout);
//			String query = "DROP SCHEMA IF EXISTS dip_logging;";
//			stmt.executeUpdate(query);
//			query = "CREATE SCHEMA IF NOT EXISTS dip_logging;";
//			stmt.executeUpdate(query);
//			query = "CREATE TABLE IF NOT EXISTS dip_logging.dip_change_log("
//					+ "	log_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
//					+ "	log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
//					+ "	schema_name VARCHAR(64) NOT NULL,"
//					+ "	table_name VARCHAR(64) NOT NULL,"
//					+ "	log_operation VARCHAR(64) NOT NULL,"
//					+ "	old_rowid VARCHAR(512) DEFAULT \"\","
//					+ "	new_rowid VARCHAR(512) DEFAULT \"\","
//					+ "	new_data VARCHAR(9000) DEFAULT \"\""
//					+ ");";
//			//System.out.println("MYsql changetracking query: " + query);
//			stmt.executeUpdate(query);
//			
//			
//
//			for (Long tableId : dbInfo.tables) {
//				//TImportedTableInfo tableInfo = DIPImportedTableInfoStoreServiceClient.getInstance().getTableInfo(tableId);
//				if (tableInfo.primaryKeys.isEmpty())
//					continue;
//				Map<Long, String> mapColId2ColName = new HashMap<>();
//				for (Map.Entry<String, Long> entry : tableInfo.mapColName2ColId.entrySet()) {
//					mapColId2ColName.put(entry.getValue(), entry.getKey());
//				}
//				query = "DROP TRIGGER IF EXISTS dip_trigger_" + tableInfo.name + "_insert;";
//				stmt.execute(query);
//				query = "DROP TRIGGER IF EXISTS dip_trigger_" + tableInfo.name + "_update;";
//				stmt.execute(query);
//				query = "DROP TRIGGER IF EXISTS dip_trigger_" + tableInfo.name + "_delete;";
//				stmt.execute(query);
//				
//				String queryTrigger = getCreateInsertTriggerQuery(tableInfo, mapColId2ColName);
//				//System.out.println("MYsql changetracking query: " + queryTrigger);
//				
//				stmt.execute(queryTrigger);
//
//				queryTrigger = getCreateUpdateTriggerQuery(tableInfo, mapColId2ColName);
//				
//				stmt.execute(queryTrigger);
//
//				queryTrigger = getCreateDeleteTriggerQuery(tableInfo, mapColId2ColName);
//				
//				stmt.execute(queryTrigger);
//			}
//		}
//		catch (Exception ex) {
//			StackTraceElement traceElement = Thread.currentThread().getStackTrace()[1];
//			//FLogServiceClient.getInstance().printSystemLog("[" + Configuration._serviceName + "] " + this.getClass().getSimpleName() + "." + traceElement.getMethodName() + "(): " + ex.getMessage());
//			return false;
//		}
//		return true;
//	}
//	
//	public static void main(String[] args ){
//		try{
//			
//			System.out.println("Test ok");
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//		
//	}
//}
