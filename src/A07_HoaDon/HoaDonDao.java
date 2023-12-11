/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A07_HoaDon;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import A00_Connection.JDBCConnection;
import A00_Connection.ThongBao;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class HoaDonDao {
    public java.util.List<HoaDon> getAllHoaDon(){
        java.util.List<HoaDon> hoaDons = new ArrayList<HoaDon>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM hoadon";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMAHD(rs.getString("MAHD"));
                hoaDon.setTHOIGIAN(rs.getTimestamp("THOIGIAN"));
                hoaDon.setMAKH(rs.getString("MAKH"));
                hoaDon.setMANV(rs.getString("MANV"));
                hoaDon.setTONGTIEN(rs.getDouble("TONGTIEN"));
                hoaDon.setGHICHU(rs.getString("GHICHU"));               
                
                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }
    
    public static ResultSet CountSoHoaDon(String MAHD) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "select Count(*) from hoadon where MAHD like N'%" + MAHD + "%'";
        try {
            Statement stm = connection.createStatement();           
            ResultSet rs = stm.executeQuery(cauTruyVan);
            
            return rs;
        } catch (Exception e) {
        }
        return null;
    }
    
        //7 Hàm lấy theo SoHoaDon
    public static ResultSet GetBySoHoaDon(String MAHD) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "select * from hoa_don where SOHD = N'" + MAHD + "'";
        try {
            Statement stm = connection.createStatement();           
            ResultSet rs = stm.executeQuery(cauTruyVan);
            
            return rs;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static int GetMaHDBySoHoaDon(String SoHoaDon){
        ResultSet rs = GetBySoHoaDon(SoHoaDon);
        try {
            if(rs.next()){
                return rs.getInt("MAHD");
            }
        } catch (SQLException ex) {
             ThongBao.ThongBao("Lỗi lấy mã hóa đơn từ số hóa đơn", "Thông báo lỗi");
        }
        return -1;
    }
    
    
    public HoaDon getHoaDonByMAHD(String MAHD){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM hoadon WHERE MAHD = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MAHD);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMAHD(rs.getString("MAHD"));              
                hoaDon.setTHOIGIAN(rs.getTimestamp("THOIGIAN"));
                hoaDon.setMAKH(rs.getString("MAKH"));
                hoaDon.setMANV(rs.getString("MANV"));
                hoaDon.setTONGTIEN(rs.getDouble("TONGTIEN"));
                hoaDon.setGHICHU(rs.getString("GHICHU"));
                                            
                return hoaDon;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addHoaDon(HoaDon hoaDon){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO hoadon (MAHD, THOIGIAN, MAKH, MANV, TONGTIEN, GHICHU) VALUES(?,?,?,?,?,?)";
        try {                     
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hoaDon.getMAHD());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(hoaDon.getTHOIGIAN().getTime()));
            preparedStatement.setString(3, hoaDon.getMAKH());
            preparedStatement.setString(4, hoaDon.getMANV());
            preparedStatement.setDouble(5, hoaDon.getTONGTIEN());
            preparedStatement.setString(6, hoaDon.getGHICHU());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
    public void updateHoaDon(HoaDon hoaDon){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE hoadon  THOIGIAN = ?,MAKH = ?,MANV = ?,TONGTIEN = ?,GHICHU = ? WHERE MAHD = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
         
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(hoaDon.getTHOIGIAN().getTime()));
            preparedStatement.setString(2, hoaDon.getMAKH());
            preparedStatement.setString(3, hoaDon.getMANV());           
            preparedStatement.setDouble(4, hoaDon.getTONGTIEN());
            preparedStatement.setString(5, hoaDon.getGHICHU());            
            preparedStatement.setString(6, hoaDon.getMAHD());
            preparedStatement.executeUpdate();
          
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    public void deleteHoaDon(String MAHD){
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM hoadon WHERE MAHD = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MAHD);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
