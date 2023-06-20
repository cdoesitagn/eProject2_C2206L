/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import models.ExamResult;
import models.ExamResultDAO;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class ExamResultController {

    private TeacherView view;
    ExamResultDAO exa = new ExamResultDAO();
    private List<ExamResult> dataList = new ArrayList<>();

    public ExamResultController() {
    }

    public ExamResultController(TeacherView view) {
        this.view = view;
    }

    public int getMax() {
        int max = exa.getMax();
        return max;
    }

    public List<String> getCoursesByStudentAndSemester(int studentId, int semesterId) {
        ExamResultDAO examResultDAO = new ExamResultDAO();
        return examResultDAO.getCoursesByStudentAndSemester(studentId, semesterId);
    }

}
