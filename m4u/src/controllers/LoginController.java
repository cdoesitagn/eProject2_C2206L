/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAO.LoginDAO;
import views.LoginView;
import models.User;
import views.Dashboard;
import views.StudentView;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class LoginController {

    private LoginView view;
    private static LoginDAO logDAO;

    public LoginController() {

    }

    public LoginController(LoginView view) {
        this.view = view;
        logDAO = new LoginDAO();
    }

    public void login() {

        String username = view.getUsername();
        String password = view.getPassword();

        User user = LoginDAO.getUserByUserName(username);

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
                    int user_id = user.getUser_id();
                    view.showMessage("Login Successfull By Student Account!");
                    StudentView studentView = new StudentView();
                    studentView.setUserId(user_id);
                    studentView.setVisible(true);
                    view.dispose();
                    break;
                case 3:
                    view.showMessage("Login Successfull By Admin Account!");
                    Dashboard dash = new Dashboard();
                    dash.setVisible(true);
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
