/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Students;
import models.StudentsDAO;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class TeacherController {
    private TeacherView view;
    private List<Students> dataList = new ArrayList<>();

    public TeacherController() {
    }

    public TeacherController(TeacherView view) {
        this.view = view;
    }
    
    public int getMax(){
        StudentsDAO std = new StudentsDAO();
        return std.getMax();
    }
    
    public void showNewData() {
        dataList = StudentsDAO.select();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTable();
        tableModel.setRowCount(0);

        for (Students students : dataList) {
            tableModel.addRow(new Object[]{
                tableModel.getRowCount() + 1,
                students.getFullname(),
                students.getGender(),
                students.getBirthday(),
                students.getPhoneNumber(),
                students.getEmail(),
                students.getAddress()
            });
        }
    }
}
