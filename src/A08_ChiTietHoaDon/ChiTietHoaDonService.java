/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A08_ChiTietHoaDon;

import A07_HoaDon.HoaDon;

/**
 *
 * @author CuongNP
 */
public class ChiTietHoaDonService {
    private ChiTietHoaDonDao chiTietHoaDonDao;
    public ChiTietHoaDonService(){
        chiTietHoaDonDao = new ChiTietHoaDonDao();
    }
    public void addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon){
        chiTietHoaDonDao.addChiTietHoaDon(chiTietHoaDon);
    }
    //public void addCTHD(ChiTietHoaDon cthd){
    //    ChiTietHoaDonDao.addCTHD(cthd);
    //}
    public ChiTietHoaDon getCTHDByMaHD(String MAHD){
        return  chiTietHoaDonDao.getChiTietHoaDonByMAHD(MAHD);
    }
    
    public void deleteChiTietHoaDon(String MAHD){
         chiTietHoaDonDao.deleteChiTietHoaDon(MAHD);
    }
}
