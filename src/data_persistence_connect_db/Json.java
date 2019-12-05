package data_persistence_connect_db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connector.Connector;

public class Json implements Recorder {

  private String setNome(String nome) {
    if (nome.length() != 0) {
      return nome + ".json";
    }
    return "none.json";
  }

  @Override
  public boolean record(Connector connector, String fileName) {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    try {
      FileWriter writer = new FileWriter(setNome(fileName));
      writer.write(gson.toJson(connector));
      writer.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Connector read(String fileName) {
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(setNome(fileName)));
      Type listType = new TypeToken<Connector>() {}.getType();
      return new Gson().fromJson(bufferedReader, listType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}