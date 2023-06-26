/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author GreenRain
 */
public class User {
    private int id;
    private int user_id;
    private String username;
    private String password;
    private int role;

    public User() {
    }

    public User(int id, int user_id, String username, String password, int role) {
        this.id = id;
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int user_id, String username, String password, int role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
