/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author GreenRain
 */
public class Student {

    int student_id;
    String fullname;
    String email;
    String phoneNumber;
    String address;
    String date;
    String gender;

    public Student() {
    }

    public Student(int student_id, String fullname, String email, String phoneNumber, String address, String date, String gender) {
        this.student_id = student_id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.date = date;
        this.gender = gender;
    }

    public Student(String fullname, String email, String phoneNumber, String address, String date, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.date = date;
        this.gender = gender;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "student_id=" + student_id + ", fullname=" + fullname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", date=" + date + ", gender=" + gender;
    }

}
