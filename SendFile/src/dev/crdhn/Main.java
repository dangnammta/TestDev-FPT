/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.crdhn;

import dev.crdhn.connector.SQLServer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.net.Authenticator.RequestorType.SERVER;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import static oracle.jdbc.replay.OracleDataSource.PASSWORD;
import static org.apache.hadoop.fs.XAttr.NameSpace.USER;
import org.apache.hadoop.io.IOUtils;
import static org.postgresql.jdbc2.EscapedFunctions.DATABASE;

/**
 *
 * @author cuongnc
 */
public class Main {

	public static void main(String[] args) {
		Connection mConnection;
		try {
//			mConnection = SQLServer.getConnection("TestImporter", "10.86.222.24", 1444, "sa", "123456abcA");
//			ScriptRunner runner = new ScriptRunner(mConnection, false, false);
//			String file = "./scripfile.sql";
//			runner.runScript(new BufferedReader(new FileReader(file)));
			NtlmPasswordAuthentication creds = new NtlmPasswordAuthentication("CRH-HN", "administrator", "P@ssword");
			SmbFile remoteFile = new SmbFile("smb://10.86.222.24/Backups/", creds);
			
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", "ddtech@10.15.146.59", "d3m0@123"); //or whatever authentication works on your local machine.
//			SmbFile myFile = new SmbFile("smb://localhost/D\$/Backup", auth)
			SmbFile myFile = new SmbFile("smb:/tmp/Backups/",auth);
//			myFile.createNewFile();
//			remoteFile.listFiles();
			remoteFile.copyTo(myFile);

		}
		catch (Exception e) {
			System.err.println("Unable to get mysql driver: " + e);
		}

	}
}
