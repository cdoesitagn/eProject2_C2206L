/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAO.ClassDAO;
import java.util.ArrayList;
import java.util.List;
import views.Dashboard;

/**
 *
 * @author hieuv
 */
public class ClassController {
    private Dashboard view;
    private List<Class> dataList = new ArrayList<>();
    ClassDAO cl = new ClassDAO();

    public ClassController() {
    }

    public ClassController(Dashboard view) {
        this.view = view;
    }
    
    public int getMax() {
        int max = Class.getMax();
        return max;
    }

    public void showNewData() {
        dataList = ClassDAO.select();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTableClass();
        tableModel.setRowCount(0);

        for (Class class : dataList) {
            tableModel.addRow(new Object[]{
                Class.getClass_id(),
                Class.getClassname()
            });
        }
    }
    
    public void saveClass() {
        String Classname = view.getClassName();

        Class class = new Class();
        class.setFullname(fullname);
        
        ClassDAO.insert(class);
        view.clearClass();
        showNewData();
    }

    public void updateClass() {
        int Class_id = Integer.parseInt(view.getID());
        if (cl.isIDExits(Class_id)) {
            if (!view.checkPhoneEmailUpdate()) {
                String fullname = view.getFullName();
                String gender = view.getGender();
                String email = view.getEmail();
                String phoneNumber = view.getPhoneNumber();

                Class class = new Class();
                class.setClass_id(class_id);
                class.setClassname(classname);

                ClassDAO.update(class);
                view.clearClass();
                showNewData();
            }
        } else {
            view.showMessage("Class id doesn't exists");
        }
    }

    public void deleteClass() {
        int class_id = Integer.parseInt(view.getID());
        if (cl.isIDExits(class_id)) {
            int yesOrNo = view.showConfirmDeleteDialog("Course and score records will also be deleted", "Class Delete");
            if (yesOrNo == view.OK_Option()) {
                ClassDAO.delete(class_id);
            }
            showNewData();
            view.clearClass();
        } else {
            view.showMessage("Class id doesn't exists");
        }
    }

    public void searchClass() {
        String searchTxt = view.getSearchField();
        dataList = ClassDAO.search(searchTxt);
        showTable();
    }
}
