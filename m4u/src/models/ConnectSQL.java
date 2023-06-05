/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GreenRain
 */
public class ConnectSQL {

    static Connection conn = null;
    static PreparedStatement statement = null;

    public static void open() {
        try {
            //B1. Ket noi CSDL
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/m4u", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close() {
        //B3. Dong ket noi
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        statement = null;
        conn = null;
    }

    public static User getUserByUserName(String username) {
        User user = null;
        open();

        try {
            String sql = "SELECT * FROM UserAccount WHERE username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            // Thực thi truy vấn
            ResultSet resultSet = statement.executeQuery();

            // Kiểm tra kết quả truy vấn
            if (resultSet.next()) {
                // Lấy thông tin từ ResultSet và tạo đối tượng User tương ứng
                int userId = resultSet.getInt("user_id");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                user = new User(userId, username, password, role);
            }
        } catch (SQLException e) {
        }
        close();
        return user;
    }
}
