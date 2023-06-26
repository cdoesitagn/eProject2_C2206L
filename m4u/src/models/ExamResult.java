/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author GreenRain
 */
public class ExamResult {

    private int examResultId;
    private int studentId;
    private int semesterId;
    private String courseName;
    private float ltPoint1;
    private float thPoint1;
    private float ltPoint2;
    private float thPoint2;
    private float totalPoint1;
    private float totalPoint2;
    

    public ExamResult() {
    }

    public ExamResult(int examResultId, int studentId, int semesterId, String courseName, float ltPoint1, float thPoint1, float ltPoint2, float thPoint2, float totalPoint1, float totalPoint2) {
        this.examResultId = examResultId;
        this.studentId = studentId;
        this.semesterId = semesterId;
        this.courseName = courseName;
        this.ltPoint1 = ltPoint1;
        this.thPoint1 = thPoint1;
        this.ltPoint2 = ltPoint2;
        this.thPoint2 = thPoint2;
        this.totalPoint1 = totalPoint1;
        this.totalPoint2 = totalPoint2;
    }

    public ExamResult(int studentId, int semesterId, String courseName, float ltPoint1, float thPoint1, float ltPoint2, float thPoint2, float totalPoint1, float totalPoint2) {
        this.studentId = studentId;
        this.semesterId = semesterId;
        this.courseName = courseName;
        this.ltPoint1 = ltPoint1;
        this.thPoint1 = thPoint1;
        this.ltPoint2 = ltPoint2;
        this.thPoint2 = thPoint2;
        this.totalPoint1 = totalPoint1;
        this.totalPoint2 = totalPoint2;
    }

    public ExamResult(int studentId, int semesterId, String courseName, float totalPoint1, float totalPoint2) {
        this.studentId = studentId;
        this.semesterId = semesterId;
        this.courseName = courseName;
        this.totalPoint1 = totalPoint1;
        this.totalPoint2 = totalPoint2;
    }

    
    public int getExamResultId() {
        return examResultId;
    }

    public void setExamResultId(int examResultId) {
        this.examResultId = examResultId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getLtPoint1() {
        return ltPoint1;
    }

    public void setLtPoint1(float ltPoint1) {
        this.ltPoint1 = ltPoint1;
    }

    public float getThPoint1() {
        return thPoint1;
    }

    public void setThPoint1(float thPoint1) {
        this.thPoint1 = thPoint1;
    }

    public float getLtPoint2() {
        return ltPoint2;
    }

    public void setLtPoint2(float ltPoint2) {
        this.ltPoint2 = ltPoint2;
    }

    public float getThPoint2() {
        return thPoint2;
    }

    public void setThPoint2(float thPoint2) {
        this.thPoint2 = thPoint2;
    }

    public float getTotalPoint1() {
        return totalPoint1;
    }

    public void setTotalPoint1(float totalPoint1) {
        this.totalPoint1 = totalPoint1;
    }

    public float getTotalPoint2() {
        return totalPoint2;
    }

    public void setTotalPoint2(float totalPoint2) {
        this.totalPoint2 = totalPoint2;
    }
    
    public float getTotalPoint(float a, float b) {
        return (a+b)/2;
    }
}
