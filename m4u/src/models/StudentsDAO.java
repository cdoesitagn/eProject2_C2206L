package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentsDAO extends ConnectSQL {

    public static List<Students> searchByName(String name) {
        List<Students> dataList = new ArrayList<>();

        open();

        try {
            String sql = "SELECT * FROM student WHERE fullname LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Students std = new Students(
                        resultSet.getInt("student_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("birthday")
                );
                dataList.add(std);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return dataList;
    }

    public static List<Students> sortByName() {
        List<Students> dataList = new ArrayList<>();

        open();
        try {
            //B2. Query du lieu ra
            String sql = "select * from student order by fullname asc";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Students std = new Students(
                        resultSet.getInt("student_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("birthday")
                );
                dataList.add(std);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return dataList;
    }

    public static List<Students> select() {
        List<Students> dataList = new ArrayList<>();

        open();
        try {

            String sql = "select * from student";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Students std = new Students(
                        resultSet.getInt("student_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("birthday"),
                        resultSet.getString("gender")
                );
                dataList.add(std);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return dataList;
    }

    public static void insert(Students std) {
        open();
        try {
            //B2. Query du lieu ra
            String sql = "insert into student (fullname, birthday, gender, address, email, phoneNumber) values (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, std.getFullname());
            statement.setString(2, std.getBirthday());
            statement.setString(3, std.getGender());
            statement.setString(4, std.getAddress());
            statement.setString(5, std.getEmail());
            statement.setString(6, std.getPhoneNumber());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static void update(Students std) {
        open();
        try {
            //B2. Query du lieu ra
            String sql = "update student set fullname = ?, email = ?, birthday = ?, address = ?, gender = ?, phoneNumber = ? where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, std.getFullname());
            statement.setString(2, std.getEmail());
            statement.setString(3, std.getBirthday());
            statement.setString(4, std.getAddress());
            statement.setString(5, std.getGender());
            statement.setString(6, std.getPhoneNumber());
            statement.setInt(7, std.getStudent_id());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static void delete(int id) {
        open();
        try {
            //B2. Query du lieu ra
            String sql = "delete from student where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static Students findByEmail(String email) {
        Students std = null;
        open();
        try {
            //B2. Query du lieu ra
            String sql = "select * from student where email = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                std = new Students(
                        resultSet.getInt("student_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("birthday"),
                        resultSet.getString("gender")
                );
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return std;
    }

    public static void importData(Students std) {
        Students checkItem = findByEmail(std.getEmail());
        if (checkItem != null) {
            //update
            updateByEmail(std);
        } else {
            //insert
            insert(std);
        }
    }

    public static Students findById(int id) {
        Students std = null;
        open();
        try {
            //B2. Query du lieu ra
            String sql = "select * from student where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                std = new Students(
                        resultSet.getInt("student_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("birthday"),
                        resultSet.getString("gender")
                );
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return std;
    }

    public int getMax() {
        int id = 0;
        open();
        String sql = "select max(student_id) from student";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public static void updateByEmail(Students std) {
        System.out.println("---update import ---");
        open();
        try {
            //B2. Query du lieu ra
            String sql = "update student set fullname = ?, address = ?, birthday = ?, gender = ?, phoneNumber = ? where email = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, std.getFullname());
            statement.setString(2, std.getAddress());
            statement.setString(3, std.getBirthday());
            statement.setString(4, std.getGender());
            statement.setString(5, std.getPhoneNumber());
            statement.setString(6, std.getEmail());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
}
