package configuration;

import firo.utils.config.Config;

public class Configuration {

    public static final String SERVICE_NAME;
//    public static final String FILE_ID_COUNTER_KEY;
    public static final int _dbTimeout;
    public static int _mongodbConnectTimeout;
    public static int _mongodbSocketTimeout;
    public static int _mongodbMaxWaitTime;
    public static int _mongodbServerSelectionTimeout;
    public static  String DBT_ORACLE_10g;
    public static  String DBT_ORACLE_11g;
    public static  String DBT_ORACLE_12c;
    public static  String DBT_SQLSERVER_2005;
    public static  String DBT_SQLSERVER_2008;
    public static  String DBT_SQLSERVER_2012;
    public static  String DBT_SQLSERVER_2014;
    public static  String DBT_SQLSERVER_2016;
    public static  String DBT_POSTGRESQL_9_1;
    public static  String DBT_POSTGRESQL_9_4;
    public static  String DBT_MYSQL_5_6;
    public static  String DBT_MONGODB_2_6_5;
    
    
    static {
        SERVICE_NAME = ConfigHelper.getParamString("service", "name", "");
//        FILE_ID_COUNTER_KEY = ConfigHelper.getParamString("counter", "fileid_counter_key", "");
        _dbTimeout = Config.getParamInt("param", "dbconnector_timeout");
        _mongodbConnectTimeout = Config.getParamInt("mongodb", "connect_timeout");
        _mongodbSocketTimeout = Config.getParamInt("mongodb", "socket_timeout");
        _mongodbMaxWaitTime = Config.getParamInt("mongodb", "max_waittime");
        _mongodbServerSelectionTimeout = Config.getParamInt("mongodb", "server_selection_timeout");
    }
}
