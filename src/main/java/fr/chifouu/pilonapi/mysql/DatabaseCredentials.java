package fr.chifouu.pilonapi.mysql;

public class DatabaseCredentials {
    private String host;

    private String user;

    private String pass;

    private String dbName;

    private int port;

    public DatabaseCredentials(String host, String user, String pass, String dbName, int port) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.dbName = dbName;
        this.port = port;
    }

    public String toURL() {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://")
                .append(this.host)
                .append(":")
                .append(this.port)
                .append("/")
                .append(this.dbName);
        return sb.toString();
    }

    public int getPort() {
        return this.port;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getHost() {
        return this.host;
    }

    public String getPass() {
        return this.pass;
    }

    public String getUser() {
        return this.user;
    }
}