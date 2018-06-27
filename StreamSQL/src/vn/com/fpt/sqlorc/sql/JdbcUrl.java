/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.fpt.sqlorc.sql;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author cuongnc
 */
public class JdbcUrl {
	public static final Log LOG = LogFactory.getLog(JdbcUrl.class.getName());

  private JdbcUrl() {
  }

  /**
   * @return the database name from the connect string, which is typically the
   * 'path' component, or null if we can't.
   */
  public static String getDatabaseName(String connectString) {
    try {
      String sanitizedString = null;
      int schemeEndOffset = connectString.indexOf("://");
      if (-1 == schemeEndOffset) {
        // couldn't find one? try our best here.
        sanitizedString = "http://" + connectString;
        LOG.warn("Could not find database access scheme in connect string "
            + connectString);
      } else {
        sanitizedString = "http" + connectString.substring(schemeEndOffset);
      }

      URL connectUrl = new URL(sanitizedString);
      String databaseName = connectUrl.getPath();
      if (null == databaseName) {
        return null;
      }

      // This is taken from a 'path' part of a URL, which may have leading '/'
      // characters; trim them off.
      while (databaseName.startsWith("/")) {
        databaseName = databaseName.substring(1);
      }

      return databaseName;
    } catch (MalformedURLException mue) {
      LOG.error("Malformed connect string URL: " + connectString
          + "; reason is " + mue.toString());
      return null;
    }
  }

  /**
   * @return the hostname from the connect string, or null if we can't.
   */
  public static String getHostName(String connectString) {
    try {
      String sanitizedString = null;
      int schemeEndOffset = connectString.indexOf("://");
      if (-1 == schemeEndOffset) {
        // Couldn't find one? ok, then there's no problem, it should work as a
        // URL.
        sanitizedString = connectString;
      } else {
        sanitizedString = "http" + connectString.substring(schemeEndOffset);
      }

      URL connectUrl = new URL(sanitizedString);
      return connectUrl.getHost();
    } catch (MalformedURLException mue) {
      LOG.error("Malformed connect string URL: " + connectString
          + "; reason is " + mue.toString());
      return null;
    }
  }

  /**
   * @return the port from the connect string, or -1 if we can't.
   */
  public static int getPort(String connectString) {
    try {
      String sanitizedString = null;
      int schemeEndOffset = connectString.indexOf("://");
      if (-1 == schemeEndOffset) {
        // Couldn't find one? ok, then there's no problem, it should work as a
        // URL.
        sanitizedString = connectString;
      } else {
        sanitizedString = "http" + connectString.substring(schemeEndOffset);
      }

      URL connectUrl = new URL(sanitizedString);
      return connectUrl.getPort();
    } catch (MalformedURLException mue) {
      LOG.error("Malformed connect string URL: " + connectString
          + "; reason is " + mue.toString());
      return -1;
    }
  }
}
