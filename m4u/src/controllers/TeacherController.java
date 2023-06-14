/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.StudentsDAO;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class TeacherController {
    private TeacherView view;

    public TeacherController() {
    }

    public TeacherController(TeacherView view) {
        this.view = view;
    }
    
    public int getMax(){
        StudentsDAO std = new StudentsDAO();
        return std.getMax();
    }
}
