/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A04_NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import A00_Connection.JDBCConnection;
import java.lang.invoke.MethodHandles;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class NhanVienDao {
    public java.util.List<NhanVien> getAllNhanviens(){
        java.util.List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM nhanvien";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMANV(rs.getString("MANV"));
                nhanVien.setTENNV(rs.getString("TENNV"));
                nhanVien.setNGAYSINH(rs.getString("NGAYSINH"));
                nhanVien.setGIOITINH(rs.getString("GIOITINH"));
                nhanVien.setDIACHI(rs.getString("DIACHI"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setCMND(rs.getString("CMND"));
                nhanVien.setCHUCDANH(rs.getString("CHUCDANH"));
                nhanVien.setTAIKHOAN(rs.getString("TAIKHOAN"));             
                nhanVien.setMATKHAU(rs.getString("MATKHAU"));
                nhanVien.setGHICHU(rs.getString("GHICHU"));
              
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanViens;
    }
    
    public NhanVien getMANVByTENNV(String TENNV) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "SELECT MANV FROM nhanvien WHERE TENNV = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(cauTruyVan);
            preparedStatement.setString(1, TENNV);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMANV(rs.getString("MANV"));
                nhanVien.setTENNV(rs.getString("TENNV"));
                nhanVien.setNGAYSINH(rs.getString("NGAYSINH"));
                nhanVien.setGIOITINH(rs.getString("GIOITINH"));
                nhanVien.setDIACHI(rs.getString("DIACHI"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setCMND(rs.getString("CMND"));
                nhanVien.setCHUCDANH(rs.getString("CHUCDANH"));
                nhanVien.setTAIKHOAN(rs.getString("TAIKHOAN"));             
                nhanVien.setMATKHAU(rs.getString("MATKHAU"));
                nhanVien.setGHICHU(rs.getString("GHICHU"));
                
                return nhanVien;
            }
                  
        } catch (Exception e) {
            e.printStackTrace();
        
        } 
        return null;
    }
    
    public NhanVien getNhanVienByMANV(String MANV){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM nhanvien WHERE MANV = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MANV);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMANV(rs.getString("MANV"));
                nhanVien.setTENNV(rs.getString("TENNV"));
                nhanVien.setNGAYSINH(rs.getString("NGAYSINH"));
                nhanVien.setGIOITINH(rs.getString("GIOITINH"));
                nhanVien.setDIACHI(rs.getString("DIACHI"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setCMND(rs.getString("CMND"));
                nhanVien.setCHUCDANH(rs.getString("CHUCDANH"));
                nhanVien.setTAIKHOAN(rs.getString("TAIKHOAN"));             
                nhanVien.setMATKHAU(rs.getString("MATKHAU"));
                nhanVien.setGHICHU(rs.getString("GHICHU"));
                
                return nhanVien;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addNhanVien(NhanVien nhanVien){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO nhanvien (MANV, TENNV, NGAYSINH, GIOITINH, DIACHI, SDT, CMND, CHUCDANH, TAIKHOAN, MATKHAU, GHICHU) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getMANV());
            preparedStatement.setString(2, nhanVien.getTENNV());
            preparedStatement.setString(3, nhanVien.getNGAYSINH());
            preparedStatement.setString(4, nhanVien.getGIOITINH());
            preparedStatement.setString(5, nhanVien.getDIACHI());
            preparedStatement.setString(6, nhanVien.getSDT());
            preparedStatement.setString(7, nhanVien.getCMND());
            preparedStatement.setString(8, nhanVien.getCHUCDANH());
            preparedStatement.setString(9, nhanVien.getTAIKHOAN());
            preparedStatement.setString(10, nhanVien.getMATKHAU());
            preparedStatement.setString(11, nhanVien.getGHICHU());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
    public void updateNhanVien(NhanVien nhanVien){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE nhanvien SET TENNV = ?,NGAYSINH= ?,GIOITINH = ?,DIACHI = ?,SDT = ?,CMND = ?,CHUCDANH = ?,TAIKHOAN = ?,MATKHAU = ?,GHICHU = ? WHERE MANV = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, nhanVien.getTENNV());
            preparedStatement.setString(2, nhanVien.getNGAYSINH());
            preparedStatement.setString(3, nhanVien.getGIOITINH());
            preparedStatement.setString(4, nhanVien.getDIACHI());
            preparedStatement.setString(5, nhanVien.getSDT());
            preparedStatement.setString(6, nhanVien.getCMND());
            preparedStatement.setString(7, nhanVien.getCHUCDANH());
            preparedStatement.setString(8, nhanVien.getTAIKHOAN());
            preparedStatement.setString(9, nhanVien.getMATKHAU());
            preparedStatement.setString(10, nhanVien.getGHICHU());
            preparedStatement.setString(11, nhanVien.getMANV());
            preparedStatement.executeUpdate();
          
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    public void deleteNhanVien(String MANV){
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM nhanvien WHERE MANV = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MANV);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public int getCountMANV(String MANV)
    {
        Connection connection=JDBCConnection.getJDBCConnection();
        String sql = "select * from nhanvien where MANV = '"+MANV+"'";
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
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int getCountTAIKHOAN(String TAIKHOAN)
    {
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql ="select * from nhanvien where TAIKHOAN = '"+TAIKHOAN+"'";
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
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
