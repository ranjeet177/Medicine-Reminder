/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Mohan
 */
public class DBClass {
    
    public Connection cn;
    public Statement st,st1;
    public ResultSet rs;
    public final String title="Medical Management System";
    
    public void connectToDB()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
        errorMsg(ex.getMessage());
        }
        
        try{
            cn=DriverManager.getConnection("jdbc:mysql://localhost/Medicom","root","");
            st=cn.createStatement();            
            st1=cn.createStatement();            
            
        }catch(SQLException ex)
        {
            errorMsg(ex.getMessage());
        }
    }
    
    public void errorMsg(String msg)
    {
        JOptionPane.showMessageDialog(null,msg, title, JOptionPane.ERROR_MESSAGE);
    }
     public void infoMsg(String msg){
        JOptionPane.showMessageDialog(null, msg, title,JOptionPane.INFORMATION_MESSAGE);
    }
    public boolean recordExists(String sql){
        try{
        rs=st.executeQuery(sql);
        while(rs.next()){
            return true;
         }
        }catch(SQLException ex){
        errorMsg(ex.getMessage());
        }
        return false;
    }
    public void ExecuteCommand(String sql,String msg)
    {
        try{
            int i=st.executeUpdate(sql);
            if(i!=-1){
                infoMsg(msg);
            }else{
                errorMsg(msg);
            }
            
        }catch(SQLException ex){
            
            errorMsg(ex.getMessage());
        }
    }
    
     public void fillTable(JTable tab,String sql){
        Object[] s;
        try{
           rs=st.executeQuery(sql); 
           ResultSetMetaData rsm=rs.getMetaData();
           s=new Object[rsm.getColumnCount()];
           DefaultTableModel dtm=(DefaultTableModel)tab.getModel();
           dtm.setRowCount(0);
           while(rs.next()){
               for(int i=0;i<s.length;i++){
                   s[i]=rs.getString(i+1);
               }
               dtm.addRow(s);
               
           }
        }catch(SQLException ex){
            errorMsg(ex.getMessage());
        }
    }
     
    public void fillCombo(JComboBox cmb,String sql){
        try{
        rs=st.executeQuery(sql);
        DefaultComboBoxModel dcb=(DefaultComboBoxModel)cmb.getModel();
        
        while(rs.next()){
            dcb.addElement(rs.getString(1));
        }
        }catch(SQLException ex){
            errorMsg(ex.getMessage());
       }
     }
    
      public int getName()
    {
        int srNo = 0;
        try{
        ResultSet rs=st1.executeQuery("select max(SrNo) from PatientRecord");
        if(rs.next())
        {
            return rs.getInt(1) + 1;
        }
        }
        catch(Exception ex)
        {
            
        }
        return srNo;
        //return "NA";
    }
}

