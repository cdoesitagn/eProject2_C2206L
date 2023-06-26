/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.ConnectSQL.close;
import static DAO.ConnectSQL.conn;
import static DAO.ConnectSQL.open;
import static DAO.ConnectSQL.statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;

/**
 *
 * @author GreenRain
 */
public class LoginDAO extends ConnectSQL {

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
                int userAccount_id = resultSet.getInt("userAccount_id");
                int userId = resultSet.getInt("user_id");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");

                user = new User(userAccount_id, userId, username, password, role);
            }
        } catch (SQLException e) {
        }
        close();
        return user;
    }
}
