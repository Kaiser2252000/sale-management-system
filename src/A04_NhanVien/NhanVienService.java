/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A04_NhanVien;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienService {
    private NhanVienDao nhanVienDao;
    
    public NhanVienService(){
        nhanVienDao = new NhanVienDao();
    }
    public List<NhanVien> getAllNhanviens(){
        return nhanVienDao.getAllNhanviens();
    }
    public void addNhanVien(NhanVien nhanVien){
        nhanVienDao.addNhanVien(nhanVien);
    }
    public void deleteNhanVien(String MANV){
        nhanVienDao.deleteNhanVien(MANV);    
    }
    public NhanVien getNhanVienByMANV(String MANV){
        return  nhanVienDao.getNhanVienByMANV(MANV);
    }
    public void updateNhanVien(NhanVien nhanVien){
        nhanVienDao.updateNhanVien(nhanVien);
    }
    public NhanVien getMANVByTENNV(String TENNV){
        return  nhanVienDao.getMANVByTENNV(TENNV);
    }
    public int getCountMANV(String MANV){
        return nhanVienDao.getCountMANV(MANV);
    }
    public int getCountTAIKHOAN(String TAIKHOAN){
        return nhanVienDao.getCountTAIKHOAN(TAIKHOAN);
    }
}
