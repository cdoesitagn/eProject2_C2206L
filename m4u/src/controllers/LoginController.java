/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.prefs.Preferences;
import views.LoginView;
import models.ConnectSQL;
import models.User;


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
                    break;
                // Perform teacher-specific actions
                case 2:
                    view.showMessage("Login Successfull By Student Account!");
                    break;
                default:
                    // Invalid role
                    view.showErrorMessage("Invalid role!");
                    break;
            }

            boolean rememberMe = view.isRememberMe();
            if (rememberMe) {
            // Save the user information for automatic login next time
            saveUserForAutomaticLogin(user);
        }
        } else {
            // Invalid username or password
            view.showErrorMessage("Invalid username or password!");
        }
    }

   private void saveUserForAutomaticLogin(User user) {
    // Example implementation using Preferences API
    Preferences preferences = Preferences.userRoot().node("com.example.app");
    preferences.put("username", user.getUsername());
    preferences.put("password", user.getPassword());
}

}
