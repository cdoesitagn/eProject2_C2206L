/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.CourseDAO;
import DAO.ConnectSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ExamResult;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class ExamResultDAO extends ConnectSQL {

    private TeacherView view;

    public int getMax() {
        int id = 0;
        open();
        try {
            String sql = "select max(result_id) from result";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public boolean getDetails(int sid, int semesterNo) {
        open();
        try {
            String sql = "select * from course where student_id = ? and semester_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);
            statement.setInt(2, semesterNo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TeacherView.jTextField11.setText(String.valueOf(resultSet.getInt(2)));
                TeacherView.jTextField13.setText(String.valueOf(resultSet.getInt(3)));
                return true;
            } else {
                view.showMessage("Student id or semester no doesn't exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<String> getCoursesByStudentAndSemester(int studentId, int semesterId) {
        List<String> courseNames = new ArrayList<>();
        open();
        try {
            String sql = "SELECT course1, course2, course3, course4, course5 FROM Course WHERE student_id = ? AND semester_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, semesterId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    String courseName = rs.getString("course" + i);
                    if (courseName != null) {
                        courseNames.add(courseName);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return courseNames;
    }

    public boolean isIdExists(int id) {
        open();
        try {
            String sql = "SELECT * FROM result WHERE result_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public static void insert(ExamResult exa) {
        open();
        try {

            String sql = "insert into result (student_id, course_name, semester_id, lt_point1, lt_point2, th_point1, th_point2, total_point1, total_point2) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, exa.getStudentId());
            statement.setString(2, exa.getCourseName());
            statement.setInt(3, exa.getSemesterId());
            statement.setFloat(4, exa.getLtPoint1());
            statement.setFloat(5, exa.getLtPoint2());
            statement.setFloat(6, exa.getThPoint1());
            statement.setFloat(7, exa.getThPoint2());
            statement.setFloat(8, exa.getTotalPoint1());
            statement.setFloat(9, exa.getTotalPoint2());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    public static List<ExamResult> select() {
        List<ExamResult> dataList = new ArrayList<>();

        open();
        try {
            String sql = "select * from result";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ExamResult exa = new ExamResult(
                        resultSet.getInt("result_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("semester_id"),
                        resultSet.getString("course_name"),
                        resultSet.getFloat("lt_point1"),
                        resultSet.getFloat("th_point1"),
                        resultSet.getFloat("lt_point2"),
                        resultSet.getFloat("th_point2"),
                        resultSet.getFloat("total_point1"),
                        resultSet.getFloat("total_point2"));
                dataList.add(exa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();

        return dataList;
    }

    public static List<ExamResult> search(String searchValue) {
        List<ExamResult> dataList = new ArrayList<>();

        open();

        try {
            String sql = "SELECT * FROM result WHERE concat(student_id, course_name) LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ExamResult exa = new ExamResult(
                        resultSet.getInt("result_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("semester_id"),
                        resultSet.getString("course_name"),
                        resultSet.getFloat("lt_point1"),
                        resultSet.getFloat("th_point1"),
                        resultSet.getFloat("lt_point2"),
                        resultSet.getFloat("th_point2"),
                        resultSet.getFloat("total_point1"),
                        resultSet.getFloat("total_point2"));
                dataList.add(exa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return dataList;
    }

    public static void update(ExamResult exa) {
        open();
        try {
            String sql = "UPDATE result SET course_name = ?, lt_point1 = ?, th_point1 = ?, lt_point2 = ?, "
                    + "th_point2 = ?, total_point1 = ?, total_point2 = ?, student_id = ?, semester_id = ? WHERE result_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, exa.getCourseName());
            statement.setFloat(2, exa.getLtPoint1());
            statement.setFloat(3, exa.getThPoint1());
            statement.setFloat(4, exa.getLtPoint2());
            statement.setFloat(5, exa.getThPoint2());
            statement.setFloat(6, exa.getTotalPoint1());
            statement.setFloat(7, exa.getTotalPoint2());
            statement.setInt(8, exa.getStudentId());
            statement.setInt(9, exa.getSemesterId());
            statement.setInt(10, exa.getExamResultId());

            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error executing SQL query: " + ex.getMessage());
        } finally {
            close();
        }
    }
}
