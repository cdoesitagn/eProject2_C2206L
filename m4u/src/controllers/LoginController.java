/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import views.LoginView;
import DAO.ConnectSQL;
import models.User;
import views.StudentView;
import views.TeacherView;


/**
 *
 * @author GreenRain
 */
public class LoginController {

    private LoginView view;

    public LoginController() {

    }

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void login() {

        String username = view.getUsername();
        String password = view.getPassword();
        

        User user = ConnectSQL.getUserByUserName(username);

        if (user != null && user.getPassword().equals(password)) {
            switch (user.getRole()) {
                // Perform student-specific actions
                case 1:
                    view.showMessage("Login Successfull By Teacher Account!");
                    TeacherView teacherView = new TeacherView();
                    teacherView.setVisible(true);
                    view.dispose();
                    break;
                case 2:
                    view.showMessage("Login Successfull By Student Account!");
                    StudentView studentView = new StudentView();
                    studentView.setVisible(true);
                    view.dispose();
                    break;
                default:
                    // Invalid role
                    view.showErrorMessage("Invalid role!");
                    break;
            }
        } else {
            // Invalid username or password
            view.showErrorMessage("Invalid username or password!");
        }
    }
}
