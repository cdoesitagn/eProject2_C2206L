/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GreenRain
 */
public class ConnectSQL {
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    static void open() {
        try {
            //B1. Ket noi CSDL
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/m4u", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void close() {
        //B3. Dong ket noi
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        statement = null;
        conn = null;
    }
}
