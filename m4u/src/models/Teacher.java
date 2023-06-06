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
public class Teacher {

    private int teacher_id;
    private String fullname;
    private String gender;
    private String email;
    private String phoneNumber;

    public Teacher() {
    }

    public Teacher(int teacher_id, String fullname, String gender, String email, String phoneNumber) {
        this.teacher_id = teacher_id;
        this.fullname = fullname;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap ten: ");
        fullname = scan.nextLine();
        System.out.println("Nhap email: ");
        email = scan.nextLine();
        System.out.println("Nhap so dien thoai: ");
        phoneNumber = scan.nextLine();
        System.out.println("Nhap gioi tinh: ");
        gender = scan.nextLine();
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "teacher_id=" + teacher_id + ", fullname=" + fullname + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber;
    }

}
