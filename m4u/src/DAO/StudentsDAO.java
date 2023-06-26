package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Students;

public class StudentsDAO extends ConnectSQL {

    public static List<Students> search(String searchValue) {
        List<Students> dataList = new ArrayList<>();

        open();

        try {
            String sql = "SELECT * FROM student WHERE concat(student_id, fullname, email, phoneNumber) LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Students std = new Students(
                        resultSet.getInt("student_id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("birthday"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("image_path")
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
                        resultSet.getString("birthday"),
                        resultSet.getString("image_path")
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
                        resultSet.getString("birthday"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("image_path")
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

            String sql = "insert into student (fullname, birthday, gender, address, email, phoneNumber, image_path) values (?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, std.getFullname());
            statement.setString(2, std.getBirthday());
            statement.setString(3, std.getGender());
            statement.setString(4, std.getAddress());
            statement.setString(5, std.getEmail());
            statement.setString(6, std.getPhoneNumber());
            statement.setString(7, std.getImage_path());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static void update(Students std) {
        open();
        try {
            String sql = "update student set fullname = ?, email = ?, birthday = ?, address = ?, gender = ?, phoneNumber = ?, "
                    + "image_path = ? where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, std.getFullname());
            statement.setString(2, std.getEmail());
            statement.setString(3, std.getBirthday());
            statement.setString(4, std.getAddress());
            statement.setString(5, std.getGender());
            statement.setString(6, std.getPhoneNumber());
            statement.setString(7, std.getImage_path());
            statement.setInt(8, std.getStudent_id());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static void delete(int id) {
        open();
        try {

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
                        resultSet.getString("gender"),
                        resultSet.getString("image_path")
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
                        resultSet.getString("gender"),
                        resultSet.getString("image_path")
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
        close();
        return id + 1;
    }

    public static void updateByEmail(Students std) {
        System.out.println("---update import ---");
        open();
        try {

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

    public boolean isEmailExits(String email) {
        open();
        try {
            String sql = "select * from student where email = ?";
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
            String sql = "select * from student where phoneNumber = ?";
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

    public boolean isIDExits(int id) {
        open();
        try {
            String sql = "select * from student where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

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
