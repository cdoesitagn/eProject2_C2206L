/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author teacher
 */
public class Main {
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
//        Students std = new Students("Xin Chao", "abc@gmail.com", "2023-02-12", "2023-02-20");
//        StudentsDAO.insert(std);
//
//        List<Students> dataList = StudentsDAO.select();
//
//        for (Students students : dataList) {
//            System.out.println(students);
//        }
    
        int choose;
        
        do {            
            showMenu();
            choose = Integer.parseInt(scan.nextLine());
            
            switch(choose) {
                case 1:
                    display();
                    break;
                case 2:
                    input();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    sort();
                    break;
                case 7:
                    System.out.println("Thoat!!!");
                    break;
                default:
                    System.out.println("Nhap sai!!!");
                    break;
            }
        } while (choose != 7);
    }
    
    static void showMenu() {
        System.out.println("1. Hien thi danh sach");
        System.out.println("2. Them sinh vien moi");
        System.out.println("3. Sua");
        System.out.println("4. Xoa");
        System.out.println("5. Tim kiem theo ten");
        System.out.println("6. Sap xep");
        System.out.println("7. Thoat");
        System.out.println("Chon: ");
    }

    private static void display() {
        List<Students> dataList = StudentsDAO.select();
        
        System.out.println("Danh sach sinh vien: ");
        for (Students students : dataList) {
            System.out.println(students);
        }
    }

    private static void input() {
        Students std = new Students();
        std.input();
        
        StudentsDAO.insert(std);
    }

    private static void edit() {
        System.out.println("Nhap ID can sua: ");
        int id = Integer.parseInt(scan.nextLine());
        
        Students std = StudentsDAO.findById(id);
        std.input();
        
        StudentsDAO.update(std);
    }

    private static void delete() {
        System.out.println("Nhap ID can xoa: ");
        int id = Integer.parseInt(scan.nextLine());
        
        StudentsDAO.delete(id);
        System.out.println("Xoa thanh cong!!!");
    }

    private static void search() {
        System.out.println("Nhap ten can tim kiem: ");
        String search = scan.nextLine();
        
        List<Students> dataList = StudentsDAO.searchByName(search);
        
        System.out.println("Ket quan tim thay: ");
        for (Students students : dataList) {
            System.out.println(students);
        }
    }

    private static void sort() {
        List<Students> dataList = StudentsDAO.sortByName();
        
        for (Students students : dataList) {
            System.out.println(students);
        }
    }
}
