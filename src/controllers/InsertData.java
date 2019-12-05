package controllers;

import connector.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

  @FXML
  private TextField txtName;
  @FXML
  private TextField txtAge;
  @FXML
  private TextField txtMail;
  private Alert alert = new Alert(Alert.AlertType.INFORMATION);

  private String name, age, mail;
  private Connection InsConn = null;
  private Statement InsSt = null;
  private String SQLQuery = null;
  private Alert ialert = new Alert(Alert.AlertType.INFORMATION);


  private void getUserData() {
    name = txtName.getText();
    age = txtAge.getText();
    mail = txtMail.getText();
  }

  private boolean validateUserData() {
    getUserData();
    System.out.println("LOG :: Entering Validation Stage");

    if (name.isEmpty()) {
      System.out.println("\n Campo Nome vazio");
      alert.setTitle("ntrada de dados incompleta");
      alert.setHeaderText("Faltam informações no campo Nome");
      alert.setContentText("Por favor, preencha os dados no campo Nome");
      alert.showAndWait();
      return false;
    } else if (age.isEmpty()) {
      System.out.println("\n Campo idade vazio");
      alert.setTitle("ntrada de dados incompleta");
      alert.setHeaderText("Faltam informações no campo idade");
      alert.setContentText("Por favor, preencha os dados no campo idade");
      alert.showAndWait();
      return false;
    } else if (mail.isEmpty()) {
      System.out.println("\n Campo Mail vazio");
      alert.setTitle("ntrada de dados incompleta");
      alert.setHeaderText("Faltam informações no campo Mail");
      alert.setContentText("Por favor, preencha os dados no campo Mail");
      alert.showAndWait();
      return false;
    } else {
      return true;
    }
  }

  private void insertDataIntoDB() throws SQLException {
    try {
      InsConn = DBConnector.getConnection();
      InsSt = InsConn.createStatement();
      SQLQuery = "INSERT INTO "
          + DBConnector.getDatabase()
          + ".pessoa values('"
          + name
          + "','"
          + age
          + "','"
          + mail
          + "');"
          + "";
      System.out.println(SQLQuery);
      InsSt.executeUpdate(SQLQuery);
      System.out.println("\n LOG :: DATA INSERTED");
    } catch (SQLException e) {
      System.err.print(e);
    } finally {
      if (InsSt != null)
        InsSt.close();
      if (InsConn != null)
        InsConn.close();

    }
  }

  public void Register() throws SQLException {
    boolean valResult = validateUserData();
    if (valResult) {
      insertDataIntoDB();
      ialert.setTitle("Dado adicionado com sucesso");
      ialert.setHeaderText(null);
      ialert.setContentText("Dados obrigatórios inseridos");
      ialert.showAndWait();
    } else
      System.out.println("Dados obrigatórios não podem ser adicionados");
  }
}