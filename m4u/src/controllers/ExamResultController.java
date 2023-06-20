/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
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
    ExamResult ex = new ExamResult();

    public ExamResultController() {
    }

    public ExamResultController(TeacherView view) {
        this.view = view;
    }

    public int getMax() {
        int max = exa.getMax();
        return max;
    }

    public boolean getDetails(int sid, int semesterNo) {
        return exa.getDetails(sid, semesterNo);
    }
    
    public boolean isIdExist(int id){
        return exa.isIdExists(id);
    }
    
    public boolean isSidSemesterNoExists(int sid, int semesterNo) {
        return exa.isSidSemesterNoExists(sid, semesterNo);
    }

    public List<String> getCoursesByStudentAndSemester(int studentId, int semesterId) {
        ExamResultDAO examResultDAO = new ExamResultDAO();
        return examResultDAO.getCoursesByStudentAndSemester(studentId, semesterId);
    }
    
    public float getTotalPoint(float ltPoint, float thPoint) {
        return ex.getTotalPoint(ltPoint, thPoint);
    }
    
//    public void saveScore() {
//        
//        int student_id = Integer.parseInt(view.getStudentID());
//        int semester_id = Integer.parseInt(view.getSemesterID());
//        
//       
//
//        Course cou = new Course();
//        cou.setStudent_id(student_id);
//        cou.setSemester_id(semester_id);
//        cou.setCourse1(course1);
//        cou.setCourse2(course2);
//        cou.setCourse3(course3);
//        cou.setCourse4(course4);
//        cou.setCourse5(course5);
//        cou.setTeacher_id(teacher_id);
//        
//        CourseDAO.insert(cou);
//        view.clearCourse();
//        showNewData();
//    }
    
    public void showNewData() {
        dataList = ExamResultDAO.select();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTableCourse();
        tableModel.setRowCount(0);

        for (ExamResult exams : dataList) {
            tableModel.addRow(new Object[]{
                exams.getExamResultId(),
                exams.getStudentId(),
                exams.getSemesterId(),
                exams.getCourseName(),
                exams.getLtPoint1(),
                exams.getThPoint1(),
                exams.getLtPoint2(),
                exams.getThPoint2(),
                exams.getTotalPoint1(),
                exams.getTotalPoint2()
            });
        }
    }
    
     public void searchCourse() {
        String searchTxt = view.getSearchScore();
        dataList = ExamResultDAO.search(searchTxt);
        showTable();
    }
}
