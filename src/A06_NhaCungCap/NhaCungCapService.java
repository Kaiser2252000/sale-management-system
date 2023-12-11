/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A06_NhaCungCap;


import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhaCungCapService {
    private NhaCungCapDao nhaCungCapDao;
    
    public NhaCungCapService(){
        nhaCungCapDao = new NhaCungCapDao();
    }
    public List<NhaCungCap> getAllNhaCungCaps(){
        return nhaCungCapDao.getAllNhaCungCaps();
    }
    public void addNhaCungCap(NhaCungCap nhaCungCap){
        nhaCungCapDao.addNhaCungCap(nhaCungCap);
    }
    public void deleteNhaCungCap(String MANCC){
        nhaCungCapDao.deleteNhaCungCap(MANCC);    
    }
    public NhaCungCap getNhaCungCapByMANCC(String MANCC){
        return  nhaCungCapDao.getNhaCungCapByMANCC(MANCC);
    }
    public void updateNhaCungCap(NhaCungCap nhaCungCap){
        nhaCungCapDao.updateNhaCungCap(nhaCungCap);
    }
    public int getCountMANCC(String MANCC){
        return nhaCungCapDao.getCountMANCC(MANCC);
    }
    
}
