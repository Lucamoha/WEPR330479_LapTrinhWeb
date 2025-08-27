package com.duong.connection;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.DriverManager;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DBConnect {
    String serverName = "localhost";
    String dbName = "LTWeb_Test";
    String portNumber = "1433";
    String userID = "sa";
    String password = "123456";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
}
