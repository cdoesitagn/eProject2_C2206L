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
    private int courseId;
    private Time startTime;
    private Time endTime;
    private Date dateOfWeek;

    public Schedule() {
    }
    
    public Schedule(int scheduleId, int teacherId, int classId, int courseId, Time startTime, Time endTime, Date dateOfWeek) {
        this.scheduleId = scheduleId;
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseId = courseId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfWeek = dateOfWeek;
    }

    public Schedule(int teacherId, int classId, int courseId, Time startTime, Time endTime, Date dateOfWeek) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseId = courseId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfWeek = dateOfWeek;
    }

    
    public int getScheduleId() {
        return scheduleId;
    }
    
    public int getTeacherId() {
        return teacherId;
    }
    
    public int getClassId() {
        return classId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public Time getStartTime() {
        return startTime;
    }
    
    public Time getEndTime() {
        return endTime;
    }
    
    public Date getDateOfWeek() {
        return dateOfWeek;
    }
    
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    
    public void setClassId(int classId) {
        this.classId = classId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
    public void setDateOfWeek(Date dateOfWeek) {
        this.dateOfWeek = dateOfWeek;
    }
}

