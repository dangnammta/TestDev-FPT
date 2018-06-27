package configuration;


public class Configuration {
	public static String _serviceName;
	public static String _serviceHost;
    public static int _servicePort;
	public static int _serviceWorkerCount;
	public static int _dbTimeout;
	
	public static int _workerWaitingTime;
	public static int _workerNumber;
	
    static void init() {
        _serviceName = Config.getParamString("service", "name", "");
		_serviceHost = Config.getParamString("service", "host", "");
		_servicePort = Config.getParamInt("service", "port");
		_serviceWorkerCount = Config.getParamInt("service", "worker_count");
		_dbTimeout = Config.getParamInt("param", "dbconnector_timeout");
		
		_workerWaitingTime = Config.getParamInt("workers", "waiting_time");
		_workerNumber = Config.getParamInt("workers", "number_of_workers");
    }

    static {
        init();
    }
}
