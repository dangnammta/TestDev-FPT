/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.ssl;

/**
 *
 * @author hoaronal
 */
public class SslStores {
	
    protected String keystoreFile;
    protected String keystorePassword;
    protected String truststoreFile;
    protected String truststorePassword;

    public static SslStores create(String keystoreFile,
                                String keystorePassword,
                                String truststoreFile,
                                String truststorePassword) {

        return new SslStores(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
    }

    private SslStores(String keystoreFile,
                      String keystorePassword,
                      String truststoreFile,
                      String truststorePassword) {
        this.keystoreFile = keystoreFile;
        this.keystorePassword = keystorePassword;
        this.truststoreFile = truststoreFile;
        this.truststorePassword = truststorePassword;
    }

    public String keystoreFile() {
        return keystoreFile;
    }

    public String keystorePassword() {
        return keystorePassword;
    }

    public String trustStoreFile() {
        return truststoreFile;
    }

    public String trustStorePassword() {
        return truststorePassword;
    }
}
