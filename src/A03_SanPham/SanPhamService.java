/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A03_SanPham;


import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanPhamService {
    private SanPhamDao sanPhamDao;
    
    public SanPhamService(){
        sanPhamDao = new SanPhamDao();
    }
    public List<SanPham> getAllHangHoas(){
        return sanPhamDao.getAllHangHoas();
    }
    public void addSanPham(SanPham sanPham){
        sanPhamDao.addSanPham(sanPham);
    }
    public void deleteSanPham(String MASP){
        sanPhamDao.deleteSanPham(MASP);    
    }
    public SanPham getSanPhamByMASP(String MASP){
        return  sanPhamDao.getSanPhamByMASP(MASP);
    }
    public void updateSanPham(SanPham sanPham){
        sanPhamDao.updateSanPham(sanPham);
    }
    public SanPham getSOLUONGByMASP(String MASP){
       return sanPhamDao.getSOLUONGByMASP(MASP);
    }
    public void updateSOLUONG(String MASP, int SOLUONG){
        sanPhamDao.updateSOLUONG(MASP, SOLUONG);
    }
    public int getCountMASP(String MASP){
        return sanPhamDao.getCountMASP(MASP);
    }
}
