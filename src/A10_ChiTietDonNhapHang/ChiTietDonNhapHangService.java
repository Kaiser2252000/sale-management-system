/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A10_ChiTietDonNhapHang;


/**
 *
 * @author CuongNP
 */
public class ChiTietDonNhapHangService {
    private ChiTietDonNhapHangDao chiTietDonNhapHangDao;
    public ChiTietDonNhapHangService(){
        chiTietDonNhapHangDao = new ChiTietDonNhapHangDao();
    }
    public void addChiTietDonNhapHang(ChiTietDonNhapHang chiTietNhapHangDao){
        chiTietDonNhapHangDao.addChiTietDonNhapHang(chiTietNhapHangDao);
    }
    //public void addCTHD(ChiTietHoaDon cthd){
    //    DAOChiTietHoaDon.addCTHD(cthd);
    //}
    public ChiTietDonNhapHang getChiTietDonNhapHangByMADNH(String MADNH){
        return chiTietDonNhapHangDao.getChiTietDonNhapHangByMADNH(MADNH);
    }
}
