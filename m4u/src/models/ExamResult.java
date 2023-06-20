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
    private int courseId;
    private float ltPoint1;
    private float thPoint1;
    private float ltPoint2;
    private float thPoint2;
    private float totalPoint;

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public float getTotalPoint() {
        if(ltPoint1 < 0 || thPoint1 <0){
            totalPoint = (ltPoint2 + thPoint2)/2;
            return totalPoint;
        }else{
            totalPoint = (ltPoint1 + thPoint1)/2;
            return totalPoint;
        }
    }

    @Override
    public String toString() {
        return "examResultId=" + examResultId + ", studentId=" + studentId + ", courseId=" + courseId + ", ltPoint1=" + ltPoint1 + ", thPoint1=" + thPoint1 + ", ltPoint2=" + ltPoint2 + ", thPoint2=" + thPoint2 + ", totalPoint=" + totalPoint;
    }
    
    
}
