/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.crdhn;

//import crdhn.dip.thrift.data.TAgentInfo;

import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import crdhn.dip.thrift.data.TImportedDBInfo;

/**
 *
 * @author cuongnc
 */
public class Global {
	private static Global _instance = null;
	//private TAgentInfo _agentInfo = null;
	//private TImportedDBInfo _dbInfo = null;
	private String _metadata = null;
	private long _lastDBInfoUpdateTime = -1;
	private long _lastMetadataUpdateTime = -1;
	
	
	//test
	private DatabaseMetaData _dbMetadata;
	
	static {
		//_serviceName = Config.getParamString("service", "name", "");
		//_host = Config.getParamString("external", "dipagentinfostoreservice_host", "");
		//_port = Config.getParamInt("external", "dipagentinfostoreservice_port");
	}

	
	public static Global getInstance() {
		if(_instance == null){
			_instance =  new Global();
		}
		return _instance;
	}
	
	public void setDbMetadata(DatabaseMetaData metadata){
		_dbMetadata = metadata;
	}
	
	public void setDbMetadata(Connection connection){
		try {
			_dbMetadata = connection.getMetaData();
		}
		catch (SQLException ex) {
			Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public DatabaseMetaData getDbMetadata(){
		return _dbMetadata;
	}
	
	/*public void setAgentInfo(TAgentInfo tInfo){
		_agentInfo = tInfo;
	}
	
	public TAgentInfo getAgentInfo() {
		return _agentInfo;
	}
	
	public boolean isSetAgentInfo(){
		return _agentInfo != null;
	}

	public TImportedDBInfo getDbInfo() {
		return _dbInfo;
	}

	public void setDbInfo(TImportedDBInfo _dbInfo) {
		this._dbInfo = _dbInfo;
	}*/
	
	
	
}
