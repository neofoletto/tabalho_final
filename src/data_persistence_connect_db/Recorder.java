package data_persistence_connect_db;

import connector.Connector;

public interface Recorder {

    public boolean record(Connector connector, String fileName);
    public Connector read(String fileName);
}
