package com.keshaun.sse.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection _instance;
    private Connection connection;

    private DatabaseConnection() {
        Dotenv env = Dotenv.load();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(env.get("MYSQL_URL"), env.get("MYSQL_USER"), env.get("MYSQL_PASS"));
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
