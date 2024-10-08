package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://javadatabase.ct8eco4eyeal.us-east-2.rds.amazonaws.com:5432/postgres";
    private static final String USER = "Gabriel";
    private static final String PASSWORD = "ojZJWITnzgwXi7hvBFaG";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Falha na conexão: " + e.getMessage());
        }
        return connection;
    }
}
