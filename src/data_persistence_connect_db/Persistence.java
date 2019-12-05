package data_persistence_connect_db;

import connector.Connector;

public class Persistence {

  private Recorder recorder;

  public Persistence(Recorder recorder) {
    this.recorder = recorder;
  }

  public boolean record(Connector connector, String fileName) {
    return recorder.record(connector, fileName);
  }

  public Connector read(String fileName) {
    return recorder.read(fileName);
  }
}
