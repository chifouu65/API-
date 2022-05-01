package fr.chifouu.pilonapi.mysql;

public enum DatabaseManager {
    DEV(new DatabaseCredentials("localhost", "root", "chifouu", "dev", 3306));

    private DatabaseAccess access;

    DatabaseManager(DatabaseCredentials credentials) {
        this.access = new DatabaseAccess(credentials);
    }

    public DatabaseAccess getDatabaseAccess() {
        return this.access;
    }

    public static void initAllDatabaseConnection() {
        for (DatabaseManager databaseManager : values())
            databaseManager.getDatabaseAccess().initPool();
    }

    public static void closeAllDatabaseConnection() {
        for (DatabaseManager databaseManager : values())
            databaseManager.getDatabaseAccess().closePool();
    }

}
