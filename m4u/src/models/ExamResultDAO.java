/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public boolean isSidSemesterNoExists(int sid, int semesterNo) {
        open();
        try {
            String sql = "select * from result where student_id and semester_id = ?";
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

    public static void insert(ExamResult exa) {
        open();
        try {

            String sql = "insert into result (student_id, course_id, semester_id, lt_point1, lt_point2, th_point1, th_point2, total_point1, total_point2) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, exa.getStudentId());
            statement.setInt(2, exa.getCourseId());
            statement.setInt(2, exa.getSemesterId());
            statement.setFloat(3, exa.getLtPoint1());
            statement.setFloat(4, exa.getLtPoint2());
            statement.setFloat(5, exa.getThPoint1());
            statement.setFloat(6, exa.getThPoint2());
            statement.setFloat(5, exa.getTotalPoint1());
            statement.setFloat(6, exa.getTotalPoint2());
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
            String sql = "SELECT r.*, c.course1 , c.course2 , c.course3 , c.course4 , c.course5 "
                    + "FROM result r INNER JOIN course c ON r.course_id = c.course_id;";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = "";
                switch (courseId) {
                    case 1:
                        courseName = resultSet.getString("course1");
                        break;
                    case 2:
                        courseName = resultSet.getString("course2");
                        break;
                    case 3:
                        courseName = resultSet.getString("course3");
                        break;
                    case 4:
                        courseName = resultSet.getString("course4");
                        break;
                    case 5:
                        courseName = resultSet.getString("course5");
                        break;
                    default:
                        break;
                }

                ExamResult exa = new ExamResult(
                        resultSet.getInt("result_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("semester_id"),
                        resultSet.getFloat("lt_point1"),
                        resultSet.getFloat("th_point1"),
                        resultSet.getFloat("lt_point2"),
                        resultSet.getFloat("th_point2"),
                        resultSet.getFloat("total_point1"),
                        resultSet.getFloat("total_point2")
                );
                exa.setCourseName(courseName);
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
            String sql = "SELECT r.*, c.course1 AS course_name_1, c.course2 AS course_name_2, c.course3 AS course_name_3, c.course4 AS course_name_4, c.course5 AS course_name_5 "
                    + "FROM result r "
                    + "INNER JOIN course c ON r.course_id = c.course_id "
                    + "WHERE r.student_id LIKE ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = "";
                switch (courseId) {
                    case 1:
                        courseName = resultSet.getString("course_name_1");
                        break;
                    case 2:
                        courseName = resultSet.getString("course_name_2");
                        break;
                    case 3:
                        courseName = resultSet.getString("course_name_3");
                        break;
                    case 4:
                        courseName = resultSet.getString("course_name_4");
                        break;
                    case 5:
                        courseName = resultSet.getString("course_name_5");
                        break;
                    default:
                        break;
                }
                ExamResult exa = new ExamResult(
                        resultSet.getInt("result_id"),
                        resultSet.getInt("student_id"),
                        courseId,
                        resultSet.getInt("semester_id"),
                        resultSet.getFloat("lt_point1"),
                        resultSet.getFloat("th_point1"),
                        resultSet.getFloat("lt_point2"),
                        resultSet.getFloat("th_point2"),
                        resultSet.getFloat("total_point1"),
                        resultSet.getFloat("total_point2")
                );
                exa.setCourseName(courseName);
                dataList.add(exa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return dataList;
    }
}
