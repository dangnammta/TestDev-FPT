/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.confkeeper.models;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author vietanh
 */
public class ServiceInfo {

    private String path;
    private String serviceName;
    private int status;
    private String host;
    private int port;
    private Date lastUpdated;

    public ServiceInfo() {
    }

    public ServiceInfo(String path, String serviceName, int status, String host, int port, Date lastUpdated) {
        this.path = path;
        this.serviceName = serviceName;
        this.status = status;
        this.host = host;
        this.port = port;
        this.lastUpdated = lastUpdated;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
