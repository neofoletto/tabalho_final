package connector;

public class Connector extends DBConnector {

  private String userName = "";
  private String password = "";
  private String port = "";
  private String database = "";
  private String host = ""; // jdbc:mysql://localhost:3306/fxdb

  public Connector() {
  }

  public void setConnector() {
    DBConnector.setUserName(this.userName);
    DBConnector.setPassword(this.password);
    DBConnector.setPort(this.port);
    DBConnector.setDatabase(this.database);
    DBConnector.setHost(this.host);
  }

  public Connector record() {
    Connector connector = new Connector();
    connector.userName = DBConnector.getUserName();
    connector.password = DBConnector.getPassword();
    connector.port     = DBConnector.getPort();
    connector.database = DBConnector.getDatabase();
    connector.host     = DBConnector.getHost();
    return connector;
  }
}
