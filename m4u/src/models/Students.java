/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Scanner;

/**
 *
 * @author GreenRain
 */
public class Students {

    private int student_id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private String birthday;
    private String gender;
    private String image_path;

    public Students() {
    }

    public Students(int student_id, String fullname, String birthday, String gender, String email, String phoneNumber, String address, String image_path) {
        this.student_id = student_id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.image_path = image_path;
    }

    public Students(String fullname, String email, String phoneNumber, String address, String birthday, String gender, String image_path) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.image_path = image_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "student_id=" + student_id + ", fullname=" + fullname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", birthday=" + birthday + ", gender=" + gender + ", image_path=" + image_path;
    }

    

}
