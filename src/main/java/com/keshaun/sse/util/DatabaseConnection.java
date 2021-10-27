package com.keshaun.sse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection _instance;
    private Connection connection;

    private DatabaseConnection() {
        String url = "jdbc:mysql:// localhost:3306/library";
        String user = "root";
        String pass = "Kdway123!";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() {
        if (_instance == null) {
            synchronized (DatabaseConnection.class) {
                if (_instance == null)
                    _instance = new DatabaseConnection();
            }
        }
        return _instance;
    }
}
