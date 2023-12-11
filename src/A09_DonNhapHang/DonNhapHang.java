/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A09_DonNhapHang;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DonNhapHang {
   
    private String MADNH;
    private Timestamp THOIGIAN;
    private String MANCC;
    private double TONGTIEN;
    private String GHICHU;

    public String getMADNH() {
        return MADNH;
    }

    public void setMADNH(String MADNH) {
        this.MADNH = MADNH;
    }

    public Timestamp getTHOIGIAN() {
        return THOIGIAN;
    }

    public void setTHOIGIAN(Timestamp THOIGIAN) {
        this.THOIGIAN = THOIGIAN;
    }

    public String getMANCC() {
        return MANCC;
    }

    public void setMANCC(String MANCC) {
        this.MANCC = MANCC;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }

    
   
}
