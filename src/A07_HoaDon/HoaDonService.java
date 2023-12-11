/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A07_HoaDon;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonService {
    private HoaDonDao hoaDonDao;
    
    public HoaDonService(){
        hoaDonDao = new HoaDonDao();
    }
    public List<HoaDon> getAllHoaDons(){
        return hoaDonDao.getAllHoaDon();
    }
    public void addHoaDon(HoaDon hoaDon){
        hoaDonDao.addHoaDon(hoaDon);
    }
    public void deleteHoaDon(String MAHD){
        hoaDonDao.deleteHoaDon(MAHD);    
    }
    public HoaDon getHoaDonByMAHD(String MAHD){
        return  hoaDonDao.getHoaDonByMAHD(MAHD);
    }
    public void updateHoaDon(HoaDon hoaDon){
        hoaDonDao.updateHoaDon(hoaDon);
    }
}
