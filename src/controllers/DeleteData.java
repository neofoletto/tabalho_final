package controllers;

import connector.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DeleteData {
  private Alert dalert = new Alert(Alert.AlertType.INFORMATION);
  private Connection delConn = null;
  private String SQLQuery = null;
  private PreparedStatement delSt = null;
  private FXMLLoader fxmlLoader = null;
  private Parent root1 = null;
  private Stage stage = null;
  private String reqName = null;
  private String checkQuery = null;
  private ResultSet delResultSet = null;

  @FXML
  TextField txtName;

  public void DeleteAllData() throws SQLException {
    try {
      delConn = DBConnector.getConnection();
      SQLQuery = "TRUNCATE TABLE " + DBConnector.getDatabase() + ".pessoa";
      checkQuery = "SELECT * FROM " + DBConnector.getDatabase() + ".pessoa";
      delResultSet = delConn.createStatement().executeQuery(checkQuery);
      if (delResultSet.next()) {
        delSt = delConn.prepareStatement(SQLQuery);
        delSt.executeUpdate();
        dalert.setTitle("Dado deletado com sucesso");
        dalert.setHeaderText(null);
        dalert.setContentText("Todos os dados foram deletados");
        dalert.showAndWait();
      } else {
        dalert.setTitle("Campo delete");
        dalert.setHeaderText(null);
        dalert.setContentText("Nenhum dado encontrado");
        dalert.showAndWait();
      }

    } catch (SQLException se) {
      System.err.print(se);
    } finally {
      if (delSt != null) {
        delSt.close();
      }
      if (delResultSet != null) {
        delResultSet.close();
      }
      if (delConn != null) {
        delConn.close();
      }
    }
  }

  @FXML
  private void ShowDetails() throws IOException {
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
  private void DeleteRequiredData() throws SQLException {
    reqName = txtName.getText();
    SQLQuery = "DELETE FROM " + DBConnector.getDatabase() + ".pessoa WHERE uname='" + reqName + "';";
    checkQuery = "SELECT * FROM " + DBConnector.getDatabase() + ".pessoa WHERE uname='" + reqName + "';";
    if (reqName.isEmpty()) {
      dalert.setTitle("Entrada inválida");
      dalert.setHeaderText(null);
      dalert.setContentText(" Digite o nome necessário a ser excluído ");
      dalert.showAndWait();
    } else {
      try {
        delConn = DBConnector.getConnection();
        delResultSet = delConn.createStatement().executeQuery(checkQuery);
        if (delResultSet.next()) {
          delSt = delConn.prepareStatement(SQLQuery);
          delSt.executeUpdate();
          dalert.setTitle("Dado deltado com sucesso");
          dalert.setHeaderText(null);
          dalert.setContentText("Dados obrigatórios inseridos");
          dalert.showAndWait();
        } else {
          dalert.setTitle("DELETE");
          dalert.setHeaderText(null);
          dalert.setContentText("Dado requerido não encontrado");
          dalert.showAndWait();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        if (delSt != null) {
          delSt.close();
        }
        if (delResultSet != null) {
          delResultSet.close();
        }
        if (delConn != null) {
          delConn.close();
        }
      }
    }
  }
}