/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.confkeeper.models;

//import static firo.Firo.path;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vietanh
 */
public class HistoryInfo {

//    public Date logDate;
    public String logDate;
    
//    List<ServiceInfo> serviceInfoList = new ArrayList<>();
    List<String> knownPathList = new ArrayList<>();

    public List<ServiceInfo>  addServiceInfo() {
        List<ServiceInfo> infos = new ArrayList<>();
        ServiceInfo serviceInfo = new ServiceInfo("dbinfo1", "serviceabc", 1, "12345", 8080, new Date(2017/01/02));
//        ServiceInfo serviceInfo1 = new ServiceInfo("dbinfo1", "service1", 0, "12345", 8080, new Date());
        infos.add(serviceInfo);
//        infos.add(serviceInfo1);
        return infos;
    }
    public List<ServiceInfo>  addServiceInfo2() {
        List<ServiceInfo> infos2 = new ArrayList<>();
        ServiceInfo serviceInfo2 = new ServiceInfo("dbinfo2", "servicexyz", 2, "123", 6969, new Date(2017/01/02));
//        ServiceInfo serviceInfo1 = new ServiceInfo("dbinfo1", "service1", 0, "12345", 8080, new Date());
        infos2.add(serviceInfo2);
//        infos.add(serviceInfo1);
        return infos2;
    }
    

    public void AddKnow() {
        knownPathList.add("dbinfo1");
        knownPathList.add("dbinfo2");

    }

}
