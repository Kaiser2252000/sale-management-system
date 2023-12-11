/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A03_SanPham;

import A00_Connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class SanPhamDao {
    public java.util.List<SanPham> getAllHangHoas(){
        java.util.List<SanPham> sanPhams = new ArrayList<SanPham>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM sanpham";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMASP(rs.getString("MASP"));
                sanPham.setTENSP(rs.getString("TENSP"));
                sanPham.setMANCC(rs.getString("MANCC"));
                sanPham.setLOAISP(rs.getString("LOAISP"));
                sanPham.setGIANHAP(rs.getDouble("GIANHAP"));
                sanPham.setGIABAN(rs.getDouble("GIABAN"));
                sanPham.setSOLUONG(rs.getInt("SOLUONG"));
                sanPham.setGHICHU(rs.getString("GHICHU"));
                
                
                sanPhams.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }
    
    public SanPham getSanPhamByMASP(String MASP){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM sanpham WHERE MASP= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MASP);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                
                sanPham.setMASP(rs.getString("MASP"));
                sanPham.setTENSP(rs.getString("TENSP"));
                sanPham.setMANCC(rs.getString("MANCC"));
                sanPham.setLOAISP(rs.getString("LOAISP"));
                sanPham.setGIANHAP(rs.getDouble("GIANHAP"));
                sanPham.setGIABAN(rs.getDouble("GIABAN"));
                sanPham.setSOLUONG(rs.getInt("SOLUONG"));
                sanPham.setGHICHU(rs.getString("GHICHU"));
                             
                return sanPham;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public SanPham getSOLUONGByMASP(String MASP){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT SOLUONG FROM sanpham WHERE MASP = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MASP);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                            
                sanPham.setSOLUONG(rs.getInt("SOLUONG"));               
                             
                return sanPham;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addSanPham(SanPham sanPham){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO sanpham (MASP, TENSP, MANCC, LOAISP, GIANHAP, GIABAN, SOLUONG, GHICHU) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sanPham.getMASP());
            preparedStatement.setString(2, sanPham.getTENSP());
            preparedStatement.setString(3, sanPham.getMANCC());
            preparedStatement.setString(4, sanPham.getLOAISP());
            preparedStatement.setDouble(5, sanPham.getGIANHAP());
            preparedStatement.setDouble(6, sanPham.getGIABAN());
            preparedStatement.setInt(7, sanPham.getSOLUONG());
            preparedStatement.setString(8, sanPham.getGHICHU());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
    public void updateSanPham(SanPham sanPham){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE sanpham SET TENSP = ?, MANCC = ?, LOAISP = ?, GIANHAP = ?, GIABAN = ?, SOLUONG = ?, GHICHU = ? WHERE MASP = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, sanPham.getTENSP());
            preparedStatement.setString(2, sanPham.getMANCC());
            preparedStatement.setString(3, sanPham.getLOAISP());
            preparedStatement.setDouble(4, sanPham.getGIANHAP());
            preparedStatement.setDouble(5, sanPham.getGIABAN());
            preparedStatement.setInt(6, sanPham.getSOLUONG());
            preparedStatement.setString(7, sanPham.getGHICHU());
            preparedStatement.setString(8, sanPham.getMASP());
            preparedStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    public void updateSOLUONG(String MASP, int SOLUONG){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE sanpham SET SOLUONG = ? WHERE MASP = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);          
            preparedStatement.setInt(1, SOLUONG);
            preparedStatement.setString(2, MASP);          
            preparedStatement.executeUpdate();
          
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    public void deleteSanPham(String MASP){
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM sanpham WHERE MASP = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MASP);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public int getCountMASP(String MASP)
    {
        Connection connection=JDBCConnection.getJDBCConnection();
        String sql = "select * from sanpham where MASP = '"+MASP+"'";
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
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
