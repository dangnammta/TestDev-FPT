/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.confkeeper.controller;

import com.sun.swing.internal.plaf.metal.resources.metal;
import crdhn.confkeeper.models.HistoryInfo;
import crdhn.confkeeper.models.ServiceInfo;
import crdhn.confkeeper.template.RenderEngine;
import firo.Controller;
import firo.ModelAndView;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author vietanh
 */
public class ConkeepperController extends Controller {

    public ConkeepperController() {
        rootPath = "/conf";
    }

    @RouteInfo(method = "get,post", path = "/conf1")
    public Route viewConf() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                return RenderEngine.getInstance().render(attributes, "confkeeper.html");
            }
        };

    }

    @RouteInfo(method = "get,post", path = "/conf2")
    public Route getHisConf() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                JSONObject mapjson = new JSONObject();
                String date1 = rqst.queryParams("date");
                System.out.println("date1:" + date1);
                String date2 = rqst.queryParams("date1");
                System.out.println("date2:" + date2);
                Date d1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
                Date d2 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
                HistoryInfo his = new HistoryInfo();
                his.logDate = "23/01/2017";
                Date d3 = new SimpleDateFormat("dd/MM/yyyy").parse(his.logDate);
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                String date = df.format(d3);               
                boolean before1 = d3.before(d1);
                boolean after1 = d3.after(d1);
                boolean before2 = d3.before(d2);
                boolean after2 = d3.after(d2);
                
                String datesearch = null;
                int t;
                if (before1 == false && after2 == false) {
                   String date = df.format(d3);
                   datesearch = date;
                    t = 1;
                }
                else{
                    t = 2;
                }
//                List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
//                serviceInfoList = his.addServiceInfo();
//                String path = null;
//                String serviceName = null;
//                int status = 0;
//                String host = null;
//                int port = 0;
//                Date lastUpdate = null;
//                for (int i = 0; i < serviceInfoList.size(); i++) {
//                    path = serviceInfoList.get(i).getPath();
//                    serviceName = serviceInfoList.get(i).getServiceName();
//                    status = serviceInfoList.get(i).getStatus();
//                    host = serviceInfoList.get(i).getHost();
//                    port = serviceInfoList.get(i).getPort();
//                    lastUpdate = serviceInfoList.get(i).getLastUpdated();
//                }
//                String lastdate = df.format(lastUpdate);
                mapjson.put("tinh", t);
                mapjson.put("ketqua", datesearch);
//                mapjson.put("path", path);
//                mapjson.put("serviceName", serviceName);
//                mapjson.put("status", status);
//                mapjson.put("host", host);
//                mapjson.put("port", port);
//                mapjson.put("date", lastdate);       
                return mapjson;
            }
        };

    }
    @RouteInfo(method = "get,post", path = "/conf5")
    public Route getHisConf2() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                JSONObject mapjson = new JSONObject();                
                HistoryInfo his = new HistoryInfo();                
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");   
                List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
                serviceInfoList = his.addServiceInfo();
                String path = null;
                String serviceName = null;
                int status = 0;
                String host = null;
                int port = 0;
                Date lastUpdate = null;
                for (int i = 0; i < serviceInfoList.size(); i++) {
                    path = serviceInfoList.get(i).getPath();
                    serviceName = serviceInfoList.get(i).getServiceName();
                    status = serviceInfoList.get(i).getStatus();
                    host = serviceInfoList.get(i).getHost();
                    port = serviceInfoList.get(i).getPort();
                    lastUpdate = serviceInfoList.get(i).getLastUpdated();
                }
                String lastdate = df.format(lastUpdate);               
                mapjson.put("path", path);
                mapjson.put("serviceName", serviceName);
                mapjson.put("status", status);
                mapjson.put("host", host);
                mapjson.put("port", port);
                mapjson.put("date", lastdate);       
                return mapjson;
            }
        };

    }
    @RouteInfo(method = "get,post", path = "/conf6")
    public Route getHisConf3() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                JSONObject mapjson = new JSONObject();                
                HistoryInfo his = new HistoryInfo();                
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");   
                List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
                serviceInfoList = his.addServiceInfo2();
                String path = null;
                String serviceName = null;
                int status = 0;
                String host = null;
                int port = 0;
                Date lastUpdate = null;
                for (int i = 0; i < serviceInfoList.size(); i++) {
                    path = serviceInfoList.get(i).getPath();
                    serviceName = serviceInfoList.get(i).getServiceName();
                    status = serviceInfoList.get(i).getStatus();
                    host = serviceInfoList.get(i).getHost();
                    port = serviceInfoList.get(i).getPort();
                    lastUpdate = serviceInfoList.get(i).getLastUpdated();
                }
                String lastdate = df.format(lastUpdate);               
                mapjson.put("path", path);
                mapjson.put("serviceName", serviceName);
                mapjson.put("status", status);
                mapjson.put("host", host);
                mapjson.put("port", port);
                mapjson.put("date", lastdate);       
                return mapjson;
            }
        };

    }

    @RouteInfo(method = "get,post", path = "/conf3")
    public Route getHTConf() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                JSONObject mapjson = new JSONObject();
                HistoryInfo his = new HistoryInfo();
                List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
                serviceInfoList = his.addServiceInfo();              
                String path = null;
                String serviceName = null;
                int status = 0;
                String host = null;
                int port = 0;
                for (int i = 0; i < serviceInfoList.size(); i++) {
                    path = serviceInfoList.get(i).getPath();
                    serviceName = serviceInfoList.get(i).getServiceName();
                    status = serviceInfoList.get(i).getStatus();
                    host = serviceInfoList.get(i).getHost();
                    port = serviceInfoList.get(i).getPort();
                }
                mapjson.put("htpath", path);
                mapjson.put("htserviceName", serviceName);
                mapjson.put("status", status);
                mapjson.put("host", host);
                mapjson.put("port", port);
                return mapjson;

            }
        };

    }
    @RouteInfo(method = "get,post", path = "/conf4")
    public Route getHTConf2() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                JSONObject mapjson = new JSONObject();
                HistoryInfo his = new HistoryInfo();
                List<ServiceInfo> serviceInfoList2 = new ArrayList<ServiceInfo>();
                serviceInfoList2 = his.addServiceInfo2();              
                String path2 = null;
                String serviceName2 = null;
                int status2 = 0;
                String host2 = null;
                int port2 = 0;
                for (int i = 0; i < serviceInfoList2.size(); i++) {
                    path2 = serviceInfoList2.get(i).getPath();
                    serviceName2 = serviceInfoList2.get(i).getServiceName();
                    status2 = serviceInfoList2.get(i).getStatus();
                    host2 = serviceInfoList2.get(i).getHost();
                    port2 = serviceInfoList2.get(i).getPort();
                }
                mapjson.put("htpath2", path2);
                mapjson.put("htserviceName2", serviceName2);
                mapjson.put("status2", status2);
                mapjson.put("host2", host2);
                mapjson.put("port2", port2);
                return mapjson;

            }
        };

    }
    @RouteInfo(method = "get,post", path = "/conf5")
    public Route getHTConf3() {
        return new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                Map<String, Object> attributes = new HashMap<>();
                JSONObject mapjson = new JSONObject();
                HistoryInfo his = new HistoryInfo();
                List<ServiceInfo> serviceInfoList2 = new ArrayList<ServiceInfo>();
                serviceInfoList2 = his.addServiceInfo2();              
                String path2 = null;
                String serviceName2 = null;
                int status2 = 0;
                String host2 = null;
                int port2 = 0;
                for (int i = 0; i < serviceInfoList2.size(); i++) {
                    path2 = serviceInfoList2.get(i).getPath();
                    serviceName2 = serviceInfoList2.get(i).getServiceName();
                    status2 = serviceInfoList2.get(i).getStatus();
                    host2 = serviceInfoList2.get(i).getHost();
                    port2 = serviceInfoList2.get(i).getPort();
                }
                mapjson.put("htpath2", path2);
                mapjson.put("htserviceName2", serviceName2);
                mapjson.put("status2", status2);
                mapjson.put("host2", host2);
                mapjson.put("port2", port2);
                return mapjson;

            }
        };

    }

}
