package fr.chifouu.pilonapi.mysql;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

import static fr.chifouu.pilonapi.commons.Constant.PREFIX_DATABASEMANAGER;

public class DataBaseManager {

    private final String urlBase;
    private final String host;
    private final String database;
    private final String userName;
    private final String password;
    private static Connection connection;

    public DataBaseManager(String urlBase,String host,String database,String userName,String password) {
        this.urlBase = urlBase;
        this.host = host;
        this.database= database;
        this.userName = userName;
        this.password = password;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void createAccount(UUID uuid) {
        if (!hasAccount(uuid)) {
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSER INTO players (uuid_player, pseudo_player, coins) VALUES (?,?,?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setString(2, Bukkit.getPlayer(uuid).getName());
                preparedStatement.setFloat(3, 100F);
                preparedStatement.execute();
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasAccount(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT coins FROM players WHERE uuid_player = ? ");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Connexion a la base de données

    public  void connexion() {
        if(!isOnline()) {
            try {
                connection = DriverManager.getConnection(this.urlBase + this.host + "/" + this.database , this.userName, this.password);
                System.out.println(PREFIX_DATABASEMANAGER+ "Is Disable");
                java.util.Properties info = new java.util.Properties();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public  void deconnexion() {
        if (isOnline()) {
            try {
                connection.close();
                System.out.println(PREFIX_DATABASEMANAGER + "iS Enable");
                return;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline() {
        try {
            if ((connection == null) || (connection.isClosed())) {
                return false;
                }
                return true;
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
