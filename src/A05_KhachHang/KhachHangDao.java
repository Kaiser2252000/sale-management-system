/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A05_KhachHang;



import A00_Connection.JDBCConnection;
import A00_Connection.MyCombobox;
import A00_Connection.ThongBao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author ADMIN
 */
public class KhachHangDao {
    public java.util.List<KhachHang> getAllKhachHangs(){
        java.util.List<KhachHang> khachHangs = new ArrayList<KhachHang>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM khachhang";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMAKH(rs.getString("MAKH"));
                khachHang.setTENKH(rs.getString("TENKH"));
                khachHang.setSDT(rs.getString("SDT"));
                khachHang.setDIACHI(rs.getString("DIACHI"));               
                khachHang.setGHICHU(rs.getString("GHICHU"));
                khachHangs.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHangs;
    }
    
         //3. Hàm đổ dữ liệu vào Combobox Loại sản phẩm
    public static void DoDuLieuVaoCBBKhachHang(JComboBox cbb, String keyword){
        cbb.removeAllItems();
        try {
            ResultSet rs = GetByKeyword(keyword);
            
            DefaultComboBoxModel cbbModel = (DefaultComboBoxModel)cbb.getModel();
           
            while(rs.next()){
                MyCombobox mb = new MyCombobox(rs.getString("TENKH"), rs.getString("MAKH"));
                cbbModel.addElement(mb);                
            }
        } catch (SQLException ex) {
            ThongBao.ThongBao("Thông báo", "Lỗi truy vấn dữ liệu.");
        }
    }
    
    public static  ResultSet GetByKeyword(String keyword){
        Connection connection = JDBCConnection.getJDBCConnection();
        String cauTruyVan = "Select * from khachhang where MAKH like '%" + keyword + "%'  or " 
                + " TENKH like N'%" + keyword + "%'";
        try {
            Statement stm = connection.createStatement();           
            ResultSet rs = stm.executeQuery(cauTruyVan);
            
            return rs;
            
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu");
            return null; 
        }
    }
    
    public KhachHang getKhachHangByMAKH(String MaKH){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM khachhang WHERE MAKH = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MaKH);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMAKH(rs.getString("MAKH"));
                khachHang.setTENKH(rs.getString("TENKH"));
                khachHang.setSDT(rs.getString("SDT"));
                khachHang.setDIACHI(rs.getString("DIACHI"));                
                khachHang.setGHICHU(rs.getString("GHICHU"));
         
                return khachHang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addKhachHang(KhachHang khachHang){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO khachhang (MAKH, TENKH, SDT, DIACHI, GHICHU) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getMAKH());
            preparedStatement.setString(2, khachHang.getTENKH());
            preparedStatement.setString(3, khachHang.getSDT());
            preparedStatement.setString(4, khachHang.getDIACHI());            
            preparedStatement.setString(5, khachHang.getGHICHU());          
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
    public void updateKhachHang(KhachHang khachHang){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE khachhang SET TENKH = ?,SDT = ?,DIACHI = ?,GHICHU = ? WHERE MAKH = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, khachHang.getTENKH());
            preparedStatement.setString(2, khachHang.getSDT());
            preparedStatement.setString(3, khachHang.getDIACHI());           
            preparedStatement.setString(4, khachHang.getGHICHU());
            preparedStatement.setString(5, khachHang.getMAKH());
                     
            preparedStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    public void deleteKhachHang(String MAKH){
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM khachhang WHERE MAKH = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MAKH);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public int getCountMAKH(String MAKH)
    {
        Connection connection=JDBCConnection.getJDBCConnection();
        String sql = "select * from khachhang where MAKH = '"+MAKH+"'";
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
            Logger.getLogger(KhachHangDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
