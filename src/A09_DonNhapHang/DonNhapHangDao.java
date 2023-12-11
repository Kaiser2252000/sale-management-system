/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A09_DonNhapHang;



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
public class DonNhapHangDao {
    public java.util.List<DonNhapHang> getAllDonNhapHang(){
        java.util.List<DonNhapHang> nhapHangs = new ArrayList<DonNhapHang>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM donnhaphang";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DonNhapHang nhapHang = new DonNhapHang();
                nhapHang.setMADNH(rs.getString("MADNH"));
                nhapHang.setTHOIGIAN(rs.getTimestamp("THOIGIAN"));
                nhapHang.setMANCC(rs.getString("MANCC"));
                nhapHang.setTONGTIEN(rs.getDouble("TONGTIEN"));
                nhapHang.setGHICHU(rs.getString("GHICHU"));               
                
                nhapHangs.add(nhapHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhapHangs;
    }
    
    public static ResultSet CountSoNH(String MANH) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "select Count(*) from nhaphang where MANH like N'%" + MANH + "%'";
        try {
            Statement stm = connection.createStatement();           
            ResultSet rs = stm.executeQuery(cauTruyVan);
            
            return rs;
        } catch (Exception e) {
        }
        return null;
    }
    
        //7 Hàm lấy theo SoNhapHang
    public static ResultSet GetBySoNH(String MANH) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "select * from nhaphang where SONH = '" + MANH + "'";
        try {
            Statement stm = connection.createStatement();           
            ResultSet rs = stm.executeQuery(cauTruyVan);
            
            return rs;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static int GetMaNHBySoNH(String SoNH){
        ResultSet rs = GetBySoNH(SoNH);
        try {
            if(rs.next()){
                return rs.getInt("MANH");
            }
        } catch (SQLException ex) {
             ThongBao.ThongBao("Lỗi lấy mã nhập hàng từ số hóa đơn", "Thông báo lỗi");
        }
        return -1;
    }
    
  
    
    public DonNhapHang getDonNhapHangByMADNH(String MADNH){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM donnhaphang WHERE MADNH = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MADNH);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DonNhapHang donNhapHang = new DonNhapHang();
                donNhapHang.setMADNH(rs.getString("MADNH"));
                donNhapHang.setTHOIGIAN(rs.getTimestamp("THOIGIAN"));
                donNhapHang.setMANCC(rs.getString("MANCC"));
                donNhapHang.setTONGTIEN(rs.getDouble("TONGTIEN"));
                donNhapHang.setGHICHU(rs.getString("GHICHU"));
                                            
                return donNhapHang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addDonNhapHang(DonNhapHang donNhapHang){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO donnhaphang (MADNH, THOIGIAN, MANCC, TONGTIEN, GHICHU) VALUES(?,?,?,?,?)";
        try {                     
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, donNhapHang.getMADNH());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(donNhapHang.getTHOIGIAN().getTime()));
            preparedStatement.setString(3, donNhapHang.getMANCC());
            preparedStatement.setDouble(4, donNhapHang.getTONGTIEN());
            preparedStatement.setString(5, donNhapHang.getGHICHU());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
    public void updateDonNhapHang(DonNhapHang donNhapHang){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE donnhaphang THOIGIAN = ?,MANCC = ?,TONGTIEN = ?,GHICHU = ? WHERE MADNH = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
           
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(donNhapHang.getTHOIGIAN().getTime()));
            preparedStatement.setString(2, donNhapHang.getMANCC());
            preparedStatement.setDouble(3, donNhapHang.getTONGTIEN());
            preparedStatement.setString(4, donNhapHang.getGHICHU());
            preparedStatement.setString(5, donNhapHang.getMADNH());
            preparedStatement.executeUpdate();
          
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    public void deleteDonNhapHang(String MADNH){
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM donnhaphang WHERE MADNH = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MADNH);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
