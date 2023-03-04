public class Config {
    private String ip;
    private String port;
    private String database;
    private String filename;

    public Config(String ip, String port, String database, String filename) {
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.filename = filename;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }
    public String getFilename() {
        return filename;
    }
}
