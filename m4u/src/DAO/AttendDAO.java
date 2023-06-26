/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Attendance;

/**
 *
 * @author GreenRain
 */
public class AttendDAO extends ConnectSQL {

    public void markAttendance(int scheduleId, int studentId, String status, Date date) {
        open();
        try {
            String query = "INSERT INTO Attendance (schedule_id, student_id, status, date) VALUES (?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, scheduleId);
            statement.setInt(2, studentId);
            statement.setString(3, status);
            statement.setDate(4, new java.sql.Date(date.getTime()));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Attendance marked successfully.");
            } else {
                System.out.println("Failed to mark attendance.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while marking the attendance.");
        }
        close();
    }

    public List<Attendance> getAttendancesBySchedule(int scheduleId) {
        open();
        List<Attendance> attendances = new ArrayList<>();
        try {
            String query = "SELECT * FROM Attendance WHERE schedule_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, scheduleId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int attendanceId = resultSet.getInt("attend_id");
                int studentId = resultSet.getInt("student_id");
                String status = resultSet.getString("status");
                Date date = resultSet.getDate("date");

                Attendance attendance = new Attendance(attendanceId, scheduleId, studentId, status, date);
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the attendances.");
        }
        return attendances;
    }

    public void updateAttendance(int attendanceId, String status) {
        open();
        try {
            String query = "UPDATE Attendance SET status = ? WHERE attend_id = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, attendanceId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Attendance updated successfully.");
            } else {
                System.out.println("Failed to update attendance.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating the attendance.");
        }
        close();
    }

    public void deleteAttendance(int attendanceId) {
        open();
        try {
            String query = "DELETE FROM Attendance WHERE attend_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, attendanceId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Attendance deleted successfully.");
            } else {
                System.out.println("Failed to delete attendance.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the attendance.");
        }
        close();
    }

}
