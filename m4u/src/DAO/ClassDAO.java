/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Classes;

/**
 *
 * @author GreenRain
 */
public class ClassDAO extends ConnectSQL {
    public int getMax() {
        int id = 0;
        open();
        String sql = "select max(class_id) from class";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }
    
    public static void insert(Classes classes) {
        open();
        try {

            String sql = "insert into class (class_id, class_name) values (?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, classes.getClass_id());
            statement.setString(2, classes.getClass_name());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static List<Classes> select() {
        List<Classes> dataList = new ArrayList<>();

        open();
        try {

            String sql = "select * from class";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Classes cou = new Classes(
                        resultSet.getInt("class_id"),
                        resultSet.getString("class_name")
                );
                dataList.add(cou);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return dataList;
    }
    
    public static List<Classes> search(String searchValue) {
        List<Classes> dataList = new ArrayList<>();

        open();

        try {
            String sql = "SELECT * FROM class WHERE class_name LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Classes cou = new Classes(
                        resultSet.getInt("class_id"),
                        resultSet.getString("class_name")
                );
                dataList.add(cou);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return dataList;
    }
    
    public static void update(Classes cl) {
        open();
        try {
            String sql = "update class set class_name = ?  where class_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, cl.getClass_name());
            statement.setInt(2, cl.getClass_id());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    public boolean isIDExits(int id) {
        open();
        try {
            String sql = "select * from class where class_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return false;
    }
    
    public static void delete(int id) {
        open();
        try {

            String sql = "delete from class where class_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
}
