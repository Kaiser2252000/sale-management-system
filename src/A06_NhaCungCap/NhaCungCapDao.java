/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A06_NhaCungCap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import A00_Connection.JDBCConnection;
import A00_Connection.MyCombobox;
import A00_Connection.ThongBao;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author ADMIN
 */
public class NhaCungCapDao {
    public java.util.List<NhaCungCap> getAllNhaCungCaps(){
        java.util.List<NhaCungCap> nhaCungCaps = new ArrayList<NhaCungCap>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM nhacungcap";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setMANCC(rs.getString("MANCC"));
                nhaCungCap.setTENNCC(rs.getString("TENNCC"));
                nhaCungCap.setDIACHI(rs.getString("DIACHI"));
                nhaCungCap.setSDT(rs.getString("SDT"));                              
                nhaCungCap.setGHICHU(rs.getString("GHICHU"));
                              
                nhaCungCaps.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCaps;
    }
    
    public static void DoDuLieuVaoCBBNhaCungCap(JComboBox cbb, String keyword){
        cbb.removeAllItems();
        try {
            ResultSet rs = GetByKeyword(keyword);
            
            DefaultComboBoxModel cbbModel = (DefaultComboBoxModel)cbb.getModel();
           
            while(rs.next()){
                MyCombobox mb = new MyCombobox(rs.getString("TENNCC"), rs.getString("MANCC"));
                cbbModel.addElement(mb);                
            }
        } catch (SQLException ex) {
            ThongBao.ThongBao("Thông báo", "Lỗi truy vấn dữ liệu.");
        }
    }
    public static  ResultSet GetByKeyword(String keyword){
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "Select * from nhacungcap where MANCC like '%" + keyword + "%'  or " 
                + " TENNCC like N'%" + keyword + "%'";
        try {
            Statement stm = connection.createStatement();           
            ResultSet rs = stm.executeQuery(cauTruyVan);
            
            return rs;
            
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu");
            return null; 
        }
    }
    
    public NhaCungCap getNhaCungCapByMANCC(String MANCC){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM nhacungcap WHERE MANCC = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MANCC);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setMANCC(rs.getString("MANCC"));
                nhaCungCap.setTENNCC(rs.getString("TENNCC"));
                nhaCungCap.setDIACHI(rs.getString("DIACHI"));
                nhaCungCap.setSDT(rs.getString("SDT"));
                nhaCungCap.setGHICHU(rs.getString("GHICHU"));                             
                return nhaCungCap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addNhaCungCap(NhaCungCap nhaCungCap){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO nhacungcap (MANCC, TENNCC, DIACHI, SDT, GHICHU) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nhaCungCap.getMANCC());
            preparedStatement.setString(2, nhaCungCap.getTENNCC());
            preparedStatement.setString(3, nhaCungCap.getDIACHI());
            preparedStatement.setString(4, nhaCungCap.getSDT());
            preparedStatement.setString(5, nhaCungCap.getGHICHU());
     
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
    public void updateNhaCungCap(NhaCungCap nhaCungCap){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE nhacungcap SET TENNCC = ?,DIACHI = ?, SDT = ?,GHICHU = ? WHERE MANCC = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, nhaCungCap.getTENNCC());
            preparedStatement.setString(2, nhaCungCap.getDIACHI());
            preparedStatement.setString(3, nhaCungCap.getSDT());          ;
            preparedStatement.setString(4, nhaCungCap.getGHICHU());
            preparedStatement.setString(5, nhaCungCap.getMANCC());
                       
            preparedStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    public void deleteNhaCungCap(String MANCC){
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM nhacungcap WHERE MANCC = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MANCC);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public int getCountMANCC(String MANCC)
    {
        Connection connection=JDBCConnection.getJDBCConnection();
        String sql = "select * from nhacungcap where MANCC = '"+MANCC+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next())
            {
                count++;
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
