package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MarksSheetDAO extends ConnectSQL{

    public MarksSheetDAO() {
    }

    
    public boolean isIdExist(int sid) {
        open();
        try {
            statement = conn.prepareStatement("select * from score where student_id = ?");
            statement.setInt(1, sid);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarksSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return false;
    }
    
    public void getScoreValue(JTable table, int sid){
        open();
        String sql = "select * from score where student_id = ?";
        try {
        statement = conn.prepareStatement(sql);
        statement.setInt(1, sid);
        ResultSet rs = statement.executeQuery();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row;
        
            while (rs.next()){
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getInt(13);
                row[13] = rs.getInt(14);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarksSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    
    public double getCGPA(int sid){
        open();
        double cgpa = 0.0;
        Statement st;
        
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select avg(average) from score where student_id = "+sid+"");
            if(rs.next()){
                cgpa = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarksSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return cgpa;  
    }
}
