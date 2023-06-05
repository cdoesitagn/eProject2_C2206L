/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import views.LoginView;
import models.ConnectSQL;
import models.User;

/**
 *
 * @author GreenRain
 */
public class LoginController {

    public LoginView view;

    public LoginController() {

    }

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void login() {
        if (view == null) {
        System.out.println("Error: View is null");
        return;
    }
        
        String username = view.getUsername();
        String password = view.getPassword();

        User user = ConnectSQL.getUserByUserName(username);

        if (user != null && user.getPassword().equals(password)) {
            switch (user.getRole()) {
                // Perform student-specific actions
                case 1:
                    view.showMessage("Login Successfull!");
                    break;
                // Perform teacher-specific actions
                case 2:
                    view.showMessage("Login Successfull!");
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
