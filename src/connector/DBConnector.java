package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

  private static String userName = "";
  private static String password = "";
  private static String port = "";
  private static String database = "";
  private static String host = "";

  public static Connection getConnection() throws SQLException {
    // conn exemple: jdbc:mysql://localhost:3306/fxdb
    System.out.println("jdbc:mysql://" + host + ":" + port + "/" + database);
    return DriverManager.getConnection(
        "jdbc:mysql://"
            + host
            + ":"
            + port
            + "/"
            + database
        , userName
        , password);
  }

  /**
   * @param --
   * @return userName
   */
  public static String getUserName() {
    return userName;
  }

  /**
   * @param userName
   * @return null
   */
  public static void setUserName(String userName) {
    DBConnector.userName = userName;
  }

  /**
   * @param --
   * @return password
   */
  public static String getPassword() {
    return password;
  }

  /**
   * @param password
   * @return null
   */
  public static void setPassword(String password) {
    DBConnector.password = password;
  }

  /**
   * @param --
   * @return port
   */
  public static String getPort() {
    return port;
  }

  /**
   * @param port
   * @return null
   */
  public static void setPort(String port) {
    DBConnector.port = port;
  }

  /**
   * @param --
   * @return database
   */
  public static String getDatabase() {
    return database;
  }

  /**
   * @param database
   * @return null
   */
  public static void setDatabase(String database) {
    DBConnector.database = database;
  }

  /**
   * @param --
   * @return host
   */
  public static String getHost() {
    return host;
  }

  /**
   * @param host
   * @return null
   */
  public static void setHost(String host) {
    DBConnector.host = host;
  }
}
