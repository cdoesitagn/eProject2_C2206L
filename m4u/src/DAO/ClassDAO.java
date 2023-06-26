/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GreenRain
 */
public class ClassDAO extends ConnectSQL {

    public void addClass(String className) {
        open();
        try {
            String query = "INSERT INTO Class (class_name) VALUES (?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, className);
            statement.executeUpdate();
            System.out.println("Thêm lớp học thành công");
        } catch (SQLException e) {
        }
        close();
    }

    public void enrollStudent(int studentId, int classId) {
        open();
        try {
            String query = "INSERT INTO Student_Class (student_id, class_id) VALUES (?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.setInt(2, classId);
            statement.executeUpdate();
            System.out.println("Ghi danh sinh viên thành công");
        } catch (SQLException e) {
        }
        close();
    }

    public List<String> getClassList() {
        List<String> classList = new ArrayList<>();
        try {
            String query = "SELECT class_name FROM Class";
            statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String className = resultSet.getString("class_name");
                classList.add(className);
            }
        } catch (SQLException e) {
        }
        close();
        return classList;
    }

    public List<String> getEnrolledStudents(int classId) {
        open();
        List<String> enrolledStudents = new ArrayList<>();
        try {
            String query = "SELECT fullname FROM Student "
                    + "INNER JOIN Student_Class ON Student.student_id = Student_Class.student_id "
                    + "WHERE class_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, classId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullname");
                enrolledStudents.add(fullName);
            }
        } catch (SQLException e) {
        }
        close();
        return enrolledStudents;
    }
}
