/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static DBContext instance = new DBContext();
    private Connection connection;

    private static final String DB_URL = "jdbc:sqlserver://ALONGDEPTRAI:1433;instanceName=SQLEXPRESS01;databaseName=ASM";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            reconnect();
        }
        return connection;
    }

    public static DBContext getInstance() {
        return instance;
    }

    private void reconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();  
            }
            Class.forName(DB_DRIVER);  
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException("Kết nối lại cơ sở dữ liệu không thành công: " + e.getMessage());
        }
    }

    public void checkAndReconnect() {
        try {
            if (connection == null || connection.isClosed()) {
                reconnect(); 
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối lại: " + e.getMessage());
        }
    }
}
