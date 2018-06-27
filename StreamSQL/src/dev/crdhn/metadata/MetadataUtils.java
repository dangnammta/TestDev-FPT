/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.crdhn.metadata;

import dev.crdhn.Global;
import java.util.HashMap;
import java.util.Map;
import java.sql.DatabaseMetaData;

/**
 *
 * @author cuongnc
 */
public class MetadataUtils {
	public static Map getSchemaMap(String tableName){
		Map<String, String> schema = new HashMap<String, String>();
		schema.put("TIN", "string");
		schema.put("NORM_NAME", "string");
		schema.put("TRAN_ADDR", "string");
		return schema;
	}
	
	public static Map getSchemaMapFromDB(String tableName){
		DatabaseMetaData metadata = Global.getInstance().getDbMetadata();
		
		return null;
	}
}
