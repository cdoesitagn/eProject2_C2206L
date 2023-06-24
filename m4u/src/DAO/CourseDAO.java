/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import controllers.CourseController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Course;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class CourseDAO extends ConnectSQL {

    private TeacherView view;

    public int getMax() {
        int id = 0;
        open();
        String sql = "select max(course_id) from course";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public boolean getID(int id) {
        open();
        try {
            String sql = "select * from student where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TeacherView.jTextField8.setText(String.valueOf(resultSet.getInt(1)));
                return true;
            } else {
                view.showMessage("Student id doesn't exists");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int countSemester(int id) {
        int total = 0;
        open();
        try {
            String sql = "SELECT COUNT(DISTINCT semester_id) AS total FROM Course WHERE student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return total;
    }

    public boolean isSemesterExists(int sid, int semesterNo) {
        open();
        try {
            String sql = "select * from course where student_id = ? and semester_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);
            statement.setInt(2, semesterNo);

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

    public boolean isCourseExists(int course_id, String courseNo, String course) {
        open();
        try {
            String sql = "select * from course where student_id = ? and " + courseNo + "= ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, course_id);
            statement.setString(2, course);
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

    public static void insert(Course cou) {
        open();
        try {

            String sql = "insert into course (student_id, semester_id, course1, course2, course3, course4, course5, teacher_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, cou.getStudent_id());
            statement.setInt(2, cou.getSemester_id());
            statement.setString(3, cou.getCourse1());
            statement.setString(4, cou.getCourse2());
            statement.setString(5, cou.getCourse3());
            statement.setString(6, cou.getCourse4());
            statement.setString(7, cou.getCourse5());
            statement.setInt(8, cou.getTeacher_id());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static List<Course> select() {
        List<Course> dataList = new ArrayList<>();

        open();
        try {

            String sql = "select * from course";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course cou = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("semester_id"),
                        resultSet.getString("course1"),
                        resultSet.getString("course2"),
                        resultSet.getString("course3"),
                        resultSet.getString("course4"),
                        resultSet.getString("course5"),
                        resultSet.getInt("teacher_id")
                );
                dataList.add(cou);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return dataList;
    }
    
    public static List<Course> search(String searchValue) {
        List<Course> dataList = new ArrayList<>();

        open();

        try {
            String sql = "SELECT * FROM course WHERE concat(student_id, course1, course2, course3, course4, course5) LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course cou = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("semester_id"),
                        resultSet.getString("course1"),
                        resultSet.getString("course2"),
                        resultSet.getString("course3"),
                        resultSet.getString("course4"),
                        resultSet.getString("course5"),
                        resultSet.getInt("teacher_id")
                );
                dataList.add(cou);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return dataList;
    }
}
