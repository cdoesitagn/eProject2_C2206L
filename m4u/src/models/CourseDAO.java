/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controllers.CourseController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "select count(*) as 'total' from course where student_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                total = rs.getInt(1);
                
            }
            if(total == 8){
                view.showMessage("This student has completed all the courses");
                return -1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
