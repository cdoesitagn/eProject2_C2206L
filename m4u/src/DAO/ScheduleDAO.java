/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean isIDExits(int id) {
        open();
        try {
            String sql = "select * from schedule where schedule_id = ?";
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

    public boolean isScheduleIdRegistered(int studentId, int scheduleId) {
        open();
        try {
            String sql = "SELECT * FROM student_schedule WHERE  schedule_id = ? AND student_id = ?;";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, scheduleId);
            statement.setInt(2, studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
               return true;
            }
        } catch (SQLException ex) {
        }
        close();
        return false;
    }

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
        String sql = "SELECT * FROM schedule";

        try {
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Schedule schedule = new Schedule(
                        resultSet.getInt("schedule_id"),
                        resultSet.getInt("teacher_id"),
                        resultSet.getInt("class_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("start_time"),
                        resultSet.getString("end_time"),
                        resultSet.getString("date_of_week"),
                        resultSet.getString("time_of_day"));
                scheduleList.add(schedule);
            }
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        close();
        return scheduleList;
    }

    public List<Schedule> getScheduleBySearch(String search) {
        List<Schedule> scheduleList = new ArrayList<>();
        open();
        String sql = "SELECT * FROM schedule WHERE concat(schedule_id, teacher_id, class_id, course_name) LIKE ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + search + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Schedule schedule = new Schedule(
                        resultSet.getInt("schedule_id"),
                        resultSet.getInt("teacher_id"),
                        resultSet.getInt("class_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("start_time"),
                        resultSet.getString("end_time"),
                        resultSet.getString("date_of_week"),
                        resultSet.getString("time_of_day"));
                scheduleList.add(schedule);
            }
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        close();
        return scheduleList;
    }

    public void removeSchedule(int scheduleId) {
        open();
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, scheduleId);

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        close();
    }

    public void addSchedule(Schedule schedule) {
        open();
        try {
            String sql = "INSERT INTO schedule (teacher_id, class_id, course_name, start_time, end_time, date_of_week, time_of_day) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, schedule.getTeacherId());
            statement.setInt(2, schedule.getClassId());
            statement.setString(3, schedule.getCourseName());
            statement.setString(4, schedule.getStartTime());
            statement.setString(5, schedule.getEndTime());
            statement.setString(6, schedule.getDateOfWeek());
            statement.setString(7, schedule.getTimeOfDay());

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        close();
    }

    public void addScheduleStudent(Schedule schedule) {
        open();
        try {
            String sql = "INSERT INTO student_schedule (schedule_id, student_id) VALUES (?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, schedule.getScheduleId());
            statement.setInt(2, schedule.getStudentId());

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        close();
    }

    public List<Schedule> getRegisteredByStudent(int sid) {
        List<Schedule> scheduleList = new ArrayList<>();
        open();
        try {
            String sql = "SELECT s.schedule_id, s.teacher_id, s.class_id, s.course_name, s.start_time, s.end_time, s.date_of_week, s.time_of_day " +
                    "FROM Schedule s " +
                    "INNER JOIN Student_Schedule ss ON s.schedule_id = ss.schedule_id " +
                    "INNER JOIN Student st ON ss.student_id = st.student_id " +
                    "WHERE st.student_id = ?;";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Schedule schedule = new Schedule(
                        resultSet.getInt("schedule_id"),
                        resultSet.getInt("teacher_id"),
                        resultSet.getInt("class_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("start_time"),
                        resultSet.getString("end_time"),
                        resultSet.getString("date_of_week"),
                        resultSet.getString("time_of_day"));
                scheduleList.add(schedule);
            }
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        close();
        return scheduleList;
    }

    public void updateSchedule(Schedule schedule) {
        open();
        try {
            String query = "UPDATE schedule SET teacher_id = ?, class_id = ?, course_name = ?, start_time = ?, end_time = ?, date_of_week = ?, time_of_day = ? WHERE schedule_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, schedule.getTeacherId());
            statement.setInt(2, schedule.getClassId());
            statement.setString(3, schedule.getCourseName());
            statement.setString(4, schedule.getStartTime());
            statement.setString(5, schedule.getEndTime());
            statement.setString(6, schedule.getDateOfWeek());
            statement.setString(7, schedule.getTimeOfDay());
            statement.setInt(8, schedule.getScheduleId());

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("An error occurred while updating the schedule.");
        }
        close();
    }

}
