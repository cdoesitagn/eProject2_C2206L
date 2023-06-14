/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.text.SimpleDateFormat;
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
    private int maxValue;

    public TeacherController() {
    }

    public TeacherController(TeacherView view) {
        this.view = view;
    }

    public int getMax() {
        StudentsDAO std = new StudentsDAO();
        int max = std.getMax();
        this.maxValue = max; // Gán giá trị lớn nhất cho biến maxValue trong TeacherController
        return max;
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
                students.getStudent_id(),
                students.getFullname(),
                students.getBirthday(),
                students.getGender(),
                students.getEmail(),
                students.getPhoneNumber(),
                students.getAddress(),
                students.getImage_path()
            });
        }
    }

    public void saveOrUpdateStudent() {
        if (view.isEmptyStudent()) {
            int student_id = getMax();
            String fullname = view.getFullName();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(view.getBirthDate());
            String gender = view.getGender();
            String email = view.getEmail();
            String phoneNumber = view.getPhoneNumber();
            String address = view.getAddress();
            String image_path = view.getJLabelImagePath();

            if (view.getCurrentIndex() >= 0) {
                Students student = view.getDataList().get(view.getCurrentIndex());
                student.setFullname(fullname);
                student.setEmail(email);
                student.setBirthday(date);
                student.setGender(gender);
                student.setPhoneNumber(phoneNumber);
                student.setAddress(address);
                student.setImage_path(image_path);

                StudentsDAO.update(student);
                view.setCurrentIndex(-1);
                showTable();
            } else {
                Students student = new Students();
                student.setFullname(fullname);
                student.setEmail(email);
                student.setBirthday(date);
                student.setPhoneNumber(phoneNumber);
                student.setGender(gender);
                student.setAddress(address);
                student.setImage_path(image_path);

                StudentsDAO.insert(student);
                showNewData();
            }

            view.clearFields();
        }
    }

    public void deleteStudent() {
        int index = view.getJTable1().getSelectedRow();
        if (index < 0) {
            view.showMessage("Chua co ban ghi nao dc chon");
            return;
        }

        int option = view.showConfirmDialog("BAN CHAC CHAN MUON XOA BAN GHI NAY KHONG");
        if (option != 0) {
            return;
        }

        Students std = dataList.get(index);
        StudentsDAO.delete(std.getStudent_id());

        showNewData();
        view.currentIndex = -1;
    }

}
