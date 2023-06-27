/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import static DAO.ConnectSQL.open;
import DAO.StudentsDAO;
import DAO.TeacherDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import models.Teacher;
import views.Dashboard;

/**
 *
 * @author hieuv
 */
public class TcController {
    private Dashboard view;
    private List<Teacher> dataList = new ArrayList<>();
    TeacherDAO tc = new TeacherDAO();

    public TcController() {
    }

    public TcController(Dashboard view) {
        this.view = view;
    }
    
    public int getMax() {
        int max = tc.getMax();
        return max;
    }

    public void showNewData() {
        dataList = TeacherDAO.select();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTableTeacher();
        tableModel.setRowCount(0);

        for (Teacher teacher : dataList) {
            tableModel.addRow(new Object[]{
                teacher.getTeacher_id(),
                teacher.getFullname(),
                teacher.getGender(),
                teacher.getEmail(),
                teacher.getPhoneNumber()
            });
        }
    }
    
    public void saveTeacher() {
        String fullname = view.getFullName();
        String gender = view.getGender();
        String email = view.getEmail();
        String phoneNumber = view.getPhoneNumber();

        Teacher teacher = new Teacher();
        teacher.setFullname(fullname);
        teacher.setEmail(email);
        teacher.setPhoneNumber(phoneNumber);
        teacher.setGender(gender);
        
        TeacherDAO.insert(teacher);
        view.clearTeacher();
        showNewData();
    }

    public void updateTeacher() {
        int teacher_id = Integer.parseInt(view.getTeachID());
        if (tc.isIDExits(teacher_id)) {
            if (!view.checkPhoneEmailUpdate()) {
                String fullname = view.getFullName();
                String gender = view.getGender();
                String email = view.getEmail();
                String phoneNumber = view.getPhoneNumber();

                Teacher teacher = new Teacher();
                teacher.setTeacher_id(teacher_id);
                teacher.setFullname(fullname);
                teacher.setEmail(email);
                teacher.setPhoneNumber(phoneNumber);
                teacher.setGender(gender);

                TeacherDAO.update(teacher);
                view.clearTeacher();
                showNewData();
            }
        } else {
            view.showMessage("Teacher id doesn't exists");
        }
    }

    public void deleteTeacher() {
        int teacher_id = Integer.parseInt(view.getTeachID());
        if (tc.isIDExits(teacher_id)) {
            int yesOrNo = view.showConfirmDeleteDialog("Teacher records will also be deleted", "Teacher Delete");
            if (yesOrNo == view.OK_Option()) {
                TeacherDAO.delete(teacher_id);
            }
            showNewData();
            view.clearTeacher();
        } else {
            view.showMessage("Teacher id doesn't exists");
        }
    }

    public void searchTeacher() {
        String searchTxt = view.getSearchTeacher();
        dataList = TeacherDAO.search(searchTxt);
        showTable();
    }
}
