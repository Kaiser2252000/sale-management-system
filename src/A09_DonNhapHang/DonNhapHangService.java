/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A09_DonNhapHang;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DonNhapHangService {
    private DonNhapHangDao donNhapHangDao;
    
    public DonNhapHangService(){
        donNhapHangDao = new DonNhapHangDao();
    }
    public List<DonNhapHang> getAllDonNhapHangs(){
        return donNhapHangDao.getAllDonNhapHang();
    }
    public void addDonNhapHang(DonNhapHang donNhapHang){
        donNhapHangDao.addDonNhapHang(donNhapHang);
    }
    public void deleteDonNhapHang(String MADNH){
        donNhapHangDao.deleteDonNhapHang(MADNH);    
    }
    public DonNhapHang getDonNhapHangByMADNH(String MADNH){
        return  donNhapHangDao.getDonNhapHangByMADNH(MADNH);
    }
    public void updateDonNhapHang(DonNhapHang donNhapHang){
        donNhapHangDao.updateDonNhapHang(donNhapHang);
    }
}
