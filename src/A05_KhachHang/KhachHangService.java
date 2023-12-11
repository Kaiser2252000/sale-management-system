/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A05_KhachHang;



import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangService {
    private KhachHangDao khachHangDao;
    
    public KhachHangService(){
        khachHangDao = new KhachHangDao();
    }
    public List<KhachHang> getAllKhachHangs(){
        return khachHangDao.getAllKhachHangs();
    }
    public void addKhachHang(KhachHang khachHang){
        khachHangDao.addKhachHang(khachHang);
    }
    public void deleteKhachHang(String MaKH){
        khachHangDao.deleteKhachHang(MaKH);    
    }
    public KhachHang getKhachHangByMAKH(String MaKH){
        return  khachHangDao.getKhachHangByMAKH(MaKH);
    }
    public void updateKhachHang(KhachHang khachHang){
        khachHangDao.updateKhachHang(khachHang);
    }
    public int getCountMAKH(String MAKH){
        return khachHangDao.getCountMAKH(MAKH);
    }
}
