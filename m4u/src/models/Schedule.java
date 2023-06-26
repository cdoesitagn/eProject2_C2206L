/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


/**
 *
 * @author GreenRain
 */
public class Schedule {
    private int scheduleId;
    private int teacherId;
    private int classId;
    private int studentId;
    private String courseName;
    private String startTime;
    private String endTime;
    private String dateOfWeek;
    private String timeOfDay;

    public Schedule() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Schedule(int scheduleId, int studentId) {
        this.scheduleId = scheduleId;
        this.studentId = studentId;
    }
    
    
    public Schedule(int scheduleId, int teacherId, int classId, String courseName, String startTime, String endTime, String dateOfWeek, String timeOfDay) {
        this.scheduleId = scheduleId;
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfWeek = dateOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public Schedule(int teacherId, int classId, String courseName, String startTime, String endTime, String dateOfWeek, String timeOfDay) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfWeek = dateOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDateOfWeek() {
        return dateOfWeek;
    }

    public void setDateOfWeek(String dateOfWeek) {
        this.dateOfWeek = dateOfWeek;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    
    
   
}

