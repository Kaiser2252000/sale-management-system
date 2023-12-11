/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A08_ChiTietHoaDon;

import A00_Connection.JDBCConnection;
import A07_HoaDon.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CuongNP
 */
public class ChiTietHoaDonDao {   
    public void addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO chitiethoadon (MAHD, MASP, TENSP,GIANHAP, GIABAN, SOLUONG) VALUES(?,?,?,?,?,?)";
        try {                     
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, chiTietHoaDon.getMAHD());
            preparedStatement.setString(2, chiTietHoaDon.getMASP());
            preparedStatement.setString(3, chiTietHoaDon.getTENSP());
            preparedStatement.setDouble(4, chiTietHoaDon.getGIANHAP());
            preparedStatement.setDouble(5, chiTietHoaDon.getGIABAN());
            preparedStatement.setInt(6, chiTietHoaDon.getSOLUONG());
            preparedStatement.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }
    public ChiTietHoaDon getChiTietHoaDonByMAHD(String MAHD){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM chitiethoadon WHERE MAHD = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MAHD);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMAHD(rs.getString("MAHD"));
                chiTietHoaDon.setMASP(rs.getString("MASP"));
                chiTietHoaDon.setTENSP(rs.getString("TENSP"));
                chiTietHoaDon.setGIANHAP(rs.getDouble("GIANHAP"));
                chiTietHoaDon.setGIABAN(rs.getDouble("GIABAN"));
                chiTietHoaDon.setSOLUONG(rs.getInt("SOLUONG"));
                                            
                return chiTietHoaDon;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteChiTietHoaDon(String MAHD){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "Delete from chitiethoadon where MAHD=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MAHD);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
