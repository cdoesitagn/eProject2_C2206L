/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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

    public boolean getDetails(int id) {
        open();
        try {
            String sql = "select * from student where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

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
    
    
}
