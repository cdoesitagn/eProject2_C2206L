/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Schedule;

/**
 *
 * @author GreenRain
 */
public class ScheduleDAO extends ConnectSQL {

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
                int courseId = resultSet.getInt("course_id");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                Date dateOfWeek = resultSet.getDate("date_of_week");

                Schedule schedule = new Schedule(scheduleId, teacherId, classId, courseId, startTime, endTime, dateOfWeek);
                scheduleList.add(schedule);
            }
        } catch (SQLException e) {
        }
        close();
        return scheduleList;
    }

    public List<Schedule> getScheduleByDate(Date date) {
        List<Schedule> scheduleList = new ArrayList<>();
        open();
        String sql = "SELECT * FROM Schedule WHERE date_of_week = ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(date.getTime()));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int scheduleId = resultSet.getInt("schedule_id");
                int teacherId = resultSet.getInt("teacher_id");
                int classId = resultSet.getInt("class_id");
                int courseId = resultSet.getInt("course_id");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                Date dateOfWeek = resultSet.getDate("date_of_week");

                Schedule schedule = new Schedule(scheduleId, teacherId, classId, courseId, startTime, endTime, dateOfWeek);
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
        String sql = "INSERT INTO Schedule (teacher_id, class_id, course_id, start_time, end_time, date_of_week) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, schedule.getTeacherId());
            statement.setInt(2, schedule.getClassId());
            statement.setInt(3, schedule.getCourseId());
            statement.setTime(4, schedule.getStartTime());
            statement.setTime(5, schedule.getEndTime());
            statement.setDate(6, (java.sql.Date) schedule.getDateOfWeek());

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

    public List<Schedule> getSchedulesByTeacher(int teacherId) {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String query = "SELECT * FROM Schedule WHERE teacher_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, teacherId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int scheduleId = resultSet.getInt("schedule_id");
                int classId = resultSet.getInt("class_id");
                int courseId = resultSet.getInt("course_id");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                Date dateOfWeek = resultSet.getDate("date_of_week");

                Schedule schedule = new Schedule(scheduleId, teacherId, classId, courseId, startTime, endTime, dateOfWeek);
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching schedules.");
        }

        return schedules;
    }
    
    public void updateSchedule(Schedule schedule) {
        try {
            String query = "UPDATE Schedule SET teacher_id = ?, class_id = ?, course_id = ?, start_time = ?, end_time = ?, date_of_week = ? WHERE schedule_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, schedule.getTeacherId());
            statement.setInt(2, schedule.getClassId());
            statement.setInt(3, schedule.getCourseId());
            statement.setTime(4, schedule.getStartTime());
            statement.setTime(5, schedule.getEndTime());
            statement.setDate(6, (java.sql.Date) schedule.getDateOfWeek());
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
