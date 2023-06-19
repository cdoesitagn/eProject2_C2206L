/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.CourseDAO;
import views.TeacherView;


/**
 *
 * @author GreenRain
 */
public class CourseController {
    private TeacherView view;
    CourseDAO cou = new CourseDAO();

    public CourseController() {
    }

    public CourseController(TeacherView view) {
        this.view = view;
    }
    
    public int getMax() {
        int max = cou.getMax();
        return max;
    }
    
    public boolean getID(int id){
        return cou.getID(id);
    }
    
    public int countSemester(int id){
        return cou.countSemester(id);
    }
}
