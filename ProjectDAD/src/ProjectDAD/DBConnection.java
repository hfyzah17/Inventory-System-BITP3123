package ProjectDAD;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3307/";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DBNAME = "inventorymanagement";
    private static final String PARAM = "?verifyServerCertificate=false&useSSL=false";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL + DBNAME + PARAM, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();

        if (conn != null) {
            System.out.println("Connected");
        } else {
            System.out.println("Not Connected");
        }

        disconnect(conn);
    }
}