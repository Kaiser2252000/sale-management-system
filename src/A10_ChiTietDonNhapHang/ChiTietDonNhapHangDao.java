/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A10_ChiTietDonNhapHang;

import A00_Connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CuongNP
 */
public class ChiTietDonNhapHangDao {   
    public void addChiTietDonNhapHang(ChiTietDonNhapHang chiTietNhapHang){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO chitietdonnhaphang (MADNH, MASP, GIANHAP, SOLUONG) VALUES(?,?,?,?)";
        try {                     
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, chiTietNhapHang.getMADNH());
            preparedStatement.setString(2, chiTietNhapHang.getMASP());
            preparedStatement.setDouble(3, chiTietNhapHang.getGIANHAP());
            preparedStatement.setInt(4, chiTietNhapHang.getSOLUONG());
            preparedStatement.executeUpdate();
  
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }
    public ChiTietDonNhapHang getChiTietDonNhapHangByMADNH(String MADNH){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM chitietdonnhaphang WHERE MADNH = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MADNH);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ChiTietDonNhapHang chiTietNhapHang = new ChiTietDonNhapHang();
                
                chiTietNhapHang.setMADNH(rs.getString("MADNH"));
                chiTietNhapHang.setMASP(rs.getString("MASP"));
                chiTietNhapHang.setGIANHAP(rs.getDouble("GIANHAP"));
                chiTietNhapHang.setSOLUONG(rs.getInt("SOLUONG"));
                return chiTietNhapHang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
