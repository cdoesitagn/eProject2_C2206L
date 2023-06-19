/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author GreenRain
 */
public class Course {

    private int course_id;
    private int student_id;
    private int semester_id;
    private String course1;
    private String course2;
    private String course3;
    private String course4;
    private String course5;
    private int teacher_id;

    public Course() {
    }

    public Course(int course_id, int student_id, int semester_id, String course1, String course2, String course3, String course4, String course5, int teacher_id) {
        this.course_id = course_id;
        this.student_id = student_id;
        this.semester_id = semester_id;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.teacher_id = teacher_id;
    }

    public Course(int student_id, int semester_id, String course1, String course2, String course3, String course4, String course5, int teacher_id) {
        this.student_id = student_id;
        this.semester_id = semester_id;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.teacher_id = teacher_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "course_id=" + course_id + ", student_id=" + student_id + ", semester_id=" + semester_id + ", course1=" + course1 + ", course2=" + course2 + ", course3=" + course3 + ", course4=" + course4 + ", course5=" + course5 + ", teacher_id=" + teacher_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(int semester_id) {
        this.semester_id = semester_id;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public String getCourse3() {
        return course3;
    }

    public void setCourse3(String course3) {
        this.course3 = course3;
    }

    public String getCourse4() {
        return course4;
    }

    public void setCourse4(String course4) {
        this.course4 = course4;
    }

    public String getCourse5() {
        return course5;
    }

    public void setCourse5(String course5) {
        this.course5 = course5;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

}
