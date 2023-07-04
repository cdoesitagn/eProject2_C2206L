/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.ExamResult;
import DAO.ExamResultDAO;
import views.StudentView;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class ExamResultController {

    private TeacherView view;
    private StudentView stdview;
    ExamResultDAO exa = new ExamResultDAO();
    private List<ExamResult> dataList = new ArrayList<>();
    ExamResult ex = new ExamResult();

    public ExamResultController() {
    }

    public ExamResultController(TeacherView view) {
        this.view = view;
    }

    public ExamResultController(StudentView stdview) {
        this.stdview = stdview;
    }

    public int getMax() {
        int max = exa.getMax();
        return max;
    }

    public boolean getDetails(int sid, int semesterNo) {
        return exa.getDetails(sid, semesterNo);
    }

    public boolean isIdExist(int id) {
        return exa.isIdExists(id);
    }
    
     public boolean isIdStudentExist(int id) {
        return exa.isIdStudentExists(id);
    }
     
      public boolean isCourseNameExist(String courseName) {
        return exa.isCourseNameExist(courseName);
    }

    public List<String> getCoursesByStudentAndSemester(int studentId, int semesterId) {
        ExamResultDAO examResultDAO = new ExamResultDAO();
        return examResultDAO.getCoursesByStudentAndSemester(studentId, semesterId);
    }

    public float getTotalPoint(float ltPoint, float thPoint) {
        return ex.getTotalPoint(ltPoint, thPoint);
    }

    public void saveScore() {
        int student_id = Integer.parseInt(view.getStudentIDScore());
        int semester_id = Integer.parseInt(view.getSemesterIDScore());
        String course_name = view.getCourse();
        float lt_point1 = Float.parseFloat(view.getTScore1());
        float th_point1 = Float.parseFloat(view.getPScore1());
        float lt_point2;
        float th_point2;
        if (lt_point1 >= 4 && th_point1 >= 4) {
            lt_point2 = 0;
            th_point2 = 0;
        } else {
            lt_point2 = Float.parseFloat(view.getTScore2());
            th_point2 = Float.parseFloat(view.getPScore2());
        }
        float total_point1 = ex.getTotalPoint(lt_point1, th_point1);
        float total_point2 = ex.getTotalPoint(lt_point2, th_point2);

        ex.setStudentId(student_id);
        ex.setSemesterId(semester_id);
        ex.setCourseName(course_name);
        ex.setLtPoint1(lt_point1);
        ex.setThPoint1(th_point1);
        ex.setLtPoint2(lt_point2);
        ex.setThPoint2(th_point2);
        ex.setTotalPoint1(total_point1);
        ex.setTotalPoint2(total_point2);

        ExamResultDAO.insert(ex);
        view.clearScore();
        showNewData();
    }

    public void updateScore() {
        int result_id = Integer.parseInt(view.getIdScore());
        if (exa.isIdExists(result_id)) {
            int student_id = Integer.parseInt(view.getStudentIDScore());
            int semester_id = Integer.parseInt(view.getSemesterIDScore());
            String course_name = view.getCourse();
            float lt_point1 = Float.parseFloat(view.getTScore1());
            float th_point1 = Float.parseFloat(view.getPScore1());
            float lt_point2;
            float th_point2;
            if (lt_point1 >= 4 && th_point1 >= 4) {
                lt_point2 = 0;
                th_point2 = 0;
            } else {
                lt_point2 = Float.parseFloat(view.getTScore2());
                th_point2 = Float.parseFloat(view.getPScore2());
            }
            float total_point1 = ex.getTotalPoint(lt_point1, th_point1);
            float total_point2 = ex.getTotalPoint(lt_point2, th_point2);

            ex.setExamResultId(result_id);
            ex.setStudentId(student_id);
            ex.setSemesterId(semester_id);
            ex.setCourseName(course_name);
            ex.setLtPoint1(lt_point1);
            ex.setThPoint1(th_point1);
            ex.setLtPoint2(lt_point2);
            ex.setThPoint2(th_point2);
            ex.setTotalPoint1(total_point1);
            ex.setTotalPoint2(total_point2);

            ExamResultDAO.update(ex);
            view.clearScore();
            showNewData();

        } else {
            view.showMessage("Result id doesn't exists");
        }
    }

    public void showNewData() {
        dataList = ExamResultDAO.select();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTableScore();
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

    public void searchScore() {
        String searchTxt = view.getSearchScore();
        dataList = ExamResultDAO.search(searchTxt);
        showTable();
    }

    public float showCGPA() {
        int studentId = Integer.parseInt(view.getSearchGPA());
        dataList = ExamResultDAO.getScoreValue(studentId);
        float cgpa = calculateCGPA(dataList);
        return cgpa;
    }

    public void showNewDataGPA() {
        int studentId = Integer.parseInt(view.getSearchGPA());
        dataList = ExamResultDAO.getScoreValue(studentId);
        showTableGPA();
    }

    public void showTableGPA() {
        DefaultTableModel tableModel = view.getTableScoreGPA();
        tableModel.setRowCount(0);

        for (ExamResult exams : dataList) {
            tableModel.addRow(new Object[]{
                exams.getStudentId(),
                exams.getSemesterId(),
                exams.getCourseName(),  
                exams.getTotalPoint1(),
                exams.getTotalPoint2()
            });
        }
    }
    
    public float showCGPAstd() {
        int studentId = Integer.parseInt(stdview.getSearchGPA());
        dataList = ExamResultDAO.getScoreValue(studentId);
        float cgpa = calculateCGPA(dataList);
        return cgpa;
    }

    public void showNewDataGPAstd() {
        int studentId = Integer.parseInt(stdview.getSearchGPA());
        dataList = ExamResultDAO.getScoreValue(studentId);
        showTableGPAstd();
    }

    public void showTableGPAstd() {
        DefaultTableModel tableModel = stdview.getTableScoreGPA();
        tableModel.setRowCount(0);

        for (ExamResult exams : dataList) {
            tableModel.addRow(new Object[]{
                exams.getStudentId(),
                exams.getSemesterId(),
                exams.getCourseName(),  
                exams.getTotalPoint1(),
                exams.getTotalPoint2()
            });
        }
    }
    
    private float calculateCGPA(List<ExamResult> examResults) {
        float totalGradePoints = 0;
        float totalCredits = 0;

        for (ExamResult examResult : examResults) {
            float totalPoint = examResult.getTotalPoint1();

            // Sử dụng total point 2 nếu total point 1 < 4
            if (totalPoint < 4) {
                totalPoint = examResult.getTotalPoint2();
            }

            totalGradePoints += totalPoint;

            totalCredits++;
        }

        // Tính CGPA
        if (totalCredits > 0) {
            float cgpa = totalGradePoints / totalCredits;
            return cgpa;
        } else {
            return 0;
        }
    }
}
