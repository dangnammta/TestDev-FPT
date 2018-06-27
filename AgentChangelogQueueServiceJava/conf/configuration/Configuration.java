package configuration;


public class Configuration {
	public static String _serviceName;
	public static String _serviceHost;
    public static int _servicePort;
	public static int _serviceWorkerCount;
	public static int _dbTimeout;
	
	public static String _mongodbHost;
    public static int _mongodbPort;
	public static String _mongodbDatabaseName;
	
	public static int _workerWaitingTime;
	public static int _workerNumber;

	public static int _mongodbConnectTimeout;
	public static int _mongodbSocketTimeout;
	public static int _mongodbMaxWaitTime;
	public static int _mongodbServerSelectionTimeout;
	
    static void init() {
        _serviceName = Config.getParamString("service", "name", "");
		_serviceHost = Config.getParamString("service", "host", "");
		_servicePort = Config.getParamInt("service", "port");
		_serviceWorkerCount = Config.getParamInt("service", "worker_count");
		_dbTimeout = Config.getParamInt("param", "dbconnector_timeout");
		
		_workerWaitingTime = Config.getParamInt("workers", "waiting_time");
		_workerNumber = Config.getParamInt("workers", "number_of_workers");
		
		_mongodbHost = Config.getParamString("external", "mongodb_host", "");
		_mongodbPort = Config.getParamInt("external", "mongodb_port");
		_mongodbDatabaseName = Config.getParamString("external", "mongodb_databasename", "");
		_mongodbConnectTimeout = Config.getParamInt("external", "mongodb_connect_timeout");
		_mongodbSocketTimeout = Config.getParamInt("external", "mongodb_socket_timeout");
		_mongodbMaxWaitTime = Config.getParamInt("external", "mongodb_max_waittime");
		_mongodbServerSelectionTimeout = Config.getParamInt("external", "mongodb_server_selection_timeout");
    
    }

    static {
        init();
    }
}
