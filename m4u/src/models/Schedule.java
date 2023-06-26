/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author GreenRain
 */
public class Schedule {
    private int scheduleId;
    private int teacherId;
    private int classId;
    private String courseName;
    private Time startTime;
    private Time endTime;
    private String dateOfWeek;

    public Schedule() {
    }

    public Schedule(int teacherId, int classId, String courseName, Time startTime, Time endTime, String dateOfWeek) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfWeek = dateOfWeek;
    }

    public Schedule(int scheduleId, int teacherId, int classId, String courseName, Time startTime, Time endTime, String dateOfWeek) {
        this.scheduleId = scheduleId;
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfWeek = dateOfWeek;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDateOfWeek() {
        return dateOfWeek;
    }

    public void setDateOfWeek(String dateOfWeek) {
        this.dateOfWeek = dateOfWeek;
    }
   
}

