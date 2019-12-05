package controllers;

import connector.Connector;
import connector.DBConnector;
import data_persistence_connect_db.Json;
import data_persistence_connect_db.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController {

  @FXML
  private TextField txtHost;
  @FXML
  private TextField txtUser;
  @FXML
  private TextField txtDatabase;
  @FXML
  private TextField txtPort;
  @FXML
  private TextField txtPassword;
  @FXML
  private CheckBox idCkboxMySQL;
  @FXML
  private CheckBox idCkboxPostgreSQL;
  @FXML
  private CheckBox idCkboxMongDB;
  @FXML
  private CheckBox idCkboxOracleDB;
  private Alert alert = new Alert(Alert.AlertType.INFORMATION);

  private FXMLLoader fxmlLoader = null;
  private Parent root1 = null;
  private Stage stage = null;
  private String host, user, password, database, url, port;
  private Alert ialert = new Alert(Alert.AlertType.INFORMATION);
  private Connection InsConn = null;
  private Statement InsSt = null;
  private static Persistence persistence = null;

  private void getDatabaseData() {
    this.host = this.txtHost.getText();
    this.port = this.txtPort.getText();
    this.user = this.txtUser.getText();
    this.password = this.txtPassword.getText();
    this.database = this.txtDatabase.getText();
  }

  /**
   * @return boolean
   */
  private boolean validateDatabseData() {
    getDatabaseData();
    System.out.println("\n\nLOG :: Entering Validation Stage");

    if (host.isEmpty()) {
      System.out.println("\n Host capom vazio");
      alert.setTitle("Entrada de dados incompleta");
      alert.setHeaderText(" Host campo está faltando");
      alert.setContentText("Por favor, preencha os dados: Host");
      alert.showAndWait();
      return false;
    } else if (port.isEmpty()) {
      System.out.println("\n Port capom vazio");
      alert.setTitle("Entrada de dados incompleta");
      alert.setHeaderText(" Port campo está faltando");
      alert.setContentText("Por favor, preencha os dados: Port");
      alert.showAndWait();
      return false;
    } else if (user.isEmpty()) {
      System.out.println("\n User capom vazio");
      alert.setTitle("Entrada de dados incompleta");
      alert.setHeaderText(" User campo está faltando");
      alert.setContentText("Por favor, preencha os dados: User");
      alert.showAndWait();
      return false;
    } else if (password.isEmpty()) {
      System.out.println("\n Password capom vazio");
      alert.setTitle("Entrada de dados incompleta");
      alert.setHeaderText(" Password campo está faltando");
      alert.setContentText("Por favor, preencha os dados: Password");
      alert.showAndWait();
      return false;
    } else if (database.isEmpty()) {
      System.out.println("\n Database capom vazio");
      alert.setTitle("Entrada de dados incompleta");
      alert.setHeaderText(" Database campo está faltando");
      alert.setContentText("Por favor, preencha os dados: Database");
      alert.showAndWait();
      return false;
    } else {
      return true;
    }
  }

  /**
   * @throws SQLException
   */
  private void connectToDatabase() throws SQLException {
    DBConnector.setHost(this.host);
    DBConnector.setPort(this.port);
    DBConnector.setDatabase(this.database);
    DBConnector.setUserName(this.user);
    DBConnector.setPassword(this.password);

    try {
      InsConn = DBConnector.getConnection();
      System.out.println("\n LOG :: CONNECT TO DATABASE");
    } catch (SQLException e) {
      System.err.print(e);
    } finally {
      if (InsSt != null)
        InsSt.close();
      if (InsConn != null)
        InsConn.close();
    }
  }

  @FXML
  private void create() {
    try {
      fxmlLoader = new FXMLLoader(getClass().getResource("../fxml_windows/InsertDataWindow.fxml"));
      root1 = (Parent) fxmlLoader.load();
      stage = new Stage();
      stage.setTitle("Insert Data");
      stage.setScene(new Scene(root1));
      stage.show();
    } catch (Exception e) {
      System.err.print(e);
    }
  }

  @FXML
  private void read() {
    try {
      fxmlLoader = new FXMLLoader(getClass().getResource("../fxml_windows/ReadDataWindow.fxml"));
      root1 = (Parent) fxmlLoader.load();
      stage = new Stage();
      stage.setTitle("Read Data");
      stage.setScene(new Scene(root1));
      stage.show();
    } catch (Exception e) {
      System.err.print(e);
    }
  }

  @FXML
  private void delete() {
    try {
      fxmlLoader = new FXMLLoader(getClass().getResource("../fxml_windows/DeleteDataWindow.fxml"));
      root1 = (Parent) fxmlLoader.load();
      stage = new Stage();
      stage.setTitle("Delete Data");
      stage.setScene(new Scene(root1));
      stage.show();
    } catch (Exception e) {
      System.err.print(e);
    }
  }

  @FXML
  private void update() {
    try {
      fxmlLoader = new FXMLLoader(getClass().getResource("../fxml_windows/UpdateDataWindow.fxml"));
      root1 = (Parent) fxmlLoader.load();
      stage = new Stage();
      stage.setTitle("Update Data");
      stage.setScene(new Scene(root1));
      stage.show();
    } catch (Exception e) {
      System.err.print(e);
    }
  }

  @FXML
  private void connectBD() throws SQLException {
    if (validateDatabseData()) {
      connectToDatabase();

      new Persistence(new Json()).record(new Connector().record(), "mysql");

      ialert.setTitle("Connect Successfully");
      ialert.setHeaderText(null);
      ialert.setContentText("Connect to Database " + this.database + " Successfully");
      ialert.showAndWait();
    } else {
      System.out.println("Connect to Database " + this.database + " Failed");
    }
  }

  @FXML
  private void ckboxMySQL() {
//    if (idCkboxMySQL.isSelected() || (idCkboxMongDB.isSelected() || idCkboxOracleDB.isSelected() || idCkboxPostgreSQL.isSelected())) {
    if (idCkboxMySQL.isSelected()) {
//      idCkboxPostgreSQL.setSelected(false);
//      idCkboxOracleDB.setSelected(false);
//      idCkboxMongDB.setSelected(false);

      Connector connector = new Persistence(new Json()).read("mysql");
      connector.setConnector();

      this.txtHost.setText(DBConnector.getHost());
      this.txtPort.setText(DBConnector.getPort());
      this.txtUser.setText(DBConnector.getUserName());
      this.txtPassword.setText(DBConnector.getPassword());
      this.txtDatabase.setText(DBConnector.getDatabase());
    } else {
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    }
  }

  @FXML
  private void ckboxPostgreSQL(){
    if (idCkboxPostgreSQL.isSelected() || (idCkboxMongDB.isSelected() || idCkboxOracleDB.isSelected() || idCkboxMySQL.isSelected())) {
      idCkboxMySQL.setSelected(false);
      idCkboxOracleDB.setSelected(false);
      idCkboxMongDB.setSelected(false);

      Connector connector = new Persistence(new Json()).read("postgresql");
      connector.setConnector();

      this.txtHost.setText(DBConnector.getHost());
      this.txtPort.setText(DBConnector.getPort());
      this.txtUser.setText(DBConnector.getUserName());
      this.txtPassword.setText(DBConnector.getPassword());
      this.txtDatabase.setText(DBConnector.getDatabase());
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    } else {
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    }
  }

  @FXML
  private void ckboxMongDB(){
    if (idCkboxMongDB.isSelected() || (idCkboxMySQL.isSelected() || idCkboxOracleDB.isSelected() || idCkboxPostgreSQL.isSelected())) {
      idCkboxPostgreSQL.setSelected(false);
      idCkboxOracleDB.setSelected(false);
      idCkboxMySQL.setSelected(false);

//      Connector connector = new Persistence(new Json()).read("mongodb");
//      connector.setConnector();
//
//      this.txtHost.setText(DBConnector.getHost());
//      this.txtPort.setText(DBConnector.getPort());
//      this.txtUser.setText(DBConnector.getUserName());
//      this.txtPassword.setText(DBConnector.getPassword());
//      this.txtDatabase.setText(DBConnector.getDatabase());
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    } else {
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    }
  }

  @FXML
  private void ckboxOracleDB(){
    if (idCkboxOracleDB.isSelected() || (idCkboxMongDB.isSelected() || idCkboxMySQL.isSelected() || idCkboxPostgreSQL.isSelected())) {
      idCkboxPostgreSQL.setSelected(false);
      idCkboxMySQL.setSelected(false);
      idCkboxMongDB.setSelected(false);

//      Connector connector = new Persistence(new Json()).read("oracledb");
//      connector.setConnector();
//
//      this.txtHost.setText(DBConnector.getHost());
//      this.txtPort.setText(DBConnector.getPort());
//      this.txtUser.setText(DBConnector.getUserName());
//      this.txtPassword.setText(DBConnector.getPassword());
//      this.txtDatabase.setText(DBConnector.getDatabase());
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    } else {
      this.txtHost.setText(null);
      this.txtPort.setText(null);
      this.txtUser.setText(null);
      this.txtPassword.setText(null);
      this.txtDatabase.setText(null);
    }
  }
}

