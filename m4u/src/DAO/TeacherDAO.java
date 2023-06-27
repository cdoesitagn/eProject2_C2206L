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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Teacher;

/**
 *
 * @author GreenRain
 */
public class TeacherDAO extends ConnectSQL {

    public static List<Teacher> search(String searchValue) {
        List<Teacher> dataList = new ArrayList<>();

        open();

        try {
            String sql = "SELECT * FROM teacher WHERE concat(teacher_id, fullname, gender, email, phoneNumber) LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher tc = new Teacher(
                        resultSet.getInt("teacher_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber")
                );
                dataList.add(tc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return dataList;
    }

    public static List<Teacher> select() {
        List<Teacher> dataList = new ArrayList<>();

        open();
        try {

            String sql = "select * from teacher";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher tc = new Teacher(
                        resultSet.getInt("teacher_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber")
                );
                dataList.add(tc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return dataList;
    }

    public static void insert(Teacher tc) {
        open();
        try {

            String sql = "insert into teacher (fullname, gender, email, phoneNumber) values (?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, tc.getFullname());
            statement.setString(2, tc.getGender());
            statement.setString(3, tc.getEmail());
            statement.setString(4, tc.getPhoneNumber());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static void update(Teacher tc) {
        open();
        try {
            String sql = "update teacher set fullname = ?, email = ?, gender = ?, phoneNumber = ? where teacher_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, tc.getFullname());
            statement.setString(2, tc.getEmail());
            statement.setString(3, tc.getGender());
            statement.setString(4, tc.getPhoneNumber());
             statement.setInt(5, tc.getTeacher_id());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static void delete(int id) {
        open();
        try {

            String sql = "delete from teacher where teacher_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public int getMax() {
        int id = 0;
        open();
        String sql = "select max(teacher_id) from teacher";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public boolean isIDExits(int id) {
        open();
        try {
            String sql = "select * from teacher where teacher_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return false;
    }

    public boolean isEmailExits(String email) {
        open();
        try {
            String sql = "select * from teacher where email = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return false;
    }

    public boolean isPhoneExits(String phone) {
        open();
        try {
            String sql = "select * from teacher where phoneNumber = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, phone);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return false;
    }
}
