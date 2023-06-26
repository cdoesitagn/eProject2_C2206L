/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Schedule;

/**
 *
 * @author GreenRain
 */
public class ScheduleDAO extends ConnectSQL {
    
    public int getMax() {
        int id = 0;
        open();
        String sql = "select max(schedule_id) from schedule";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        open();
        String sql = "SELECT * FROM Schedule";

        try {
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int scheduleId = resultSet.getInt("schedule_id");
                int teacherId = resultSet.getInt("teacher_id");
                int classId = resultSet.getInt("class_id");
                String courseName = resultSet.getString("course_name");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                String dateOfWeek = resultSet.getString("date_of_week");

                Schedule schedule = new Schedule(scheduleId, teacherId, classId, courseName, startTime, endTime, dateOfWeek);
                scheduleList.add(schedule);
            }
        } catch (SQLException e) {
        }
        close();
        return scheduleList;
    }

    public List<Schedule> getScheduleByDate(String search) {
        List<Schedule> scheduleList = new ArrayList<>();
        open();
        String sql = "SELECT * FROM Schedule WHERE concat(teacher_id, date_of_week, class_id, course_name) LIKE ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, search);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int scheduleId = resultSet.getInt("schedule_id");
                int teacherId = resultSet.getInt("teacher_id");
                int classId = resultSet.getInt("class_id");
                String courseName = resultSet.getString("course_name");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                String dateOfWeek = resultSet.getString("date_of_week");

                Schedule schedule = new Schedule(scheduleId, teacherId, classId, courseName, startTime, endTime, dateOfWeek);
                scheduleList.add(schedule);
            }
        } catch (SQLException e) {
        }
        close();
        return scheduleList;
    }

    public void removeSchedule(int scheduleId) {
        open();
        String sql = "DELETE FROM Schedule WHERE schedule_id = ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, scheduleId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Schedule removed successfully");
            } else {
                System.out.println("Failed to remove schedule");
            }
        } catch (SQLException e) {
        }
        close();
    }

    public void addSchedule(Schedule schedule) {
        open();
        String sql = "INSERT INTO Schedule (teacher_id, class_id, course_name, start_time, end_time, date_of_week) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, schedule.getTeacherId());
            statement.setInt(2, schedule.getClassId());
            statement.setString(3, schedule.getCourseName());
            statement.setTime(4, schedule.getStartTime());
            statement.setTime(5, schedule.getEndTime());
            statement.setString(6, schedule.getDateOfWeek());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Schedule added successfully");
            } else {
                System.out.println("Failed to add schedule");
            }
        } catch (SQLException e) {
        }
        close();
    }
    
    public void updateSchedule(Schedule schedule) {
        try {
            String query = "UPDATE Schedule SET teacher_id = ?, class_id = ?, course_name = ?, start_time = ?, end_time = ?, date_of_week = ? WHERE schedule_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, schedule.getTeacherId());
            statement.setInt(2, schedule.getClassId());
            statement.setString(3, schedule.getCourseName());
            statement.setTime(4, schedule.getStartTime());
            statement.setTime(5, schedule.getEndTime());
            statement.setString(6, schedule.getDateOfWeek());
            statement.setInt(7, schedule.getScheduleId());
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Schedule updated successfully.");
            } else {
                System.out.println("Failed to update schedule.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating the schedule.");
        }
    }
}
