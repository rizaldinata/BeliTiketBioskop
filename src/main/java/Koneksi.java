/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rizal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/tiketBioskop";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        Connection conn = null;
        try {
            // Mendaftarkan driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Koneksi ke database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database berhasil!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }
}