/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A11_BaoCao;

import A00_Connection.JDBCConnection;
import static A00_Support.DateValidation.dateValidation;
import A01_DangNhap.FormDangNhap;
import A03_SanPham.FormSanPham;
import A03_SanPham.SanPham;
import A03_SanPham.SanPhamService;
import A07_HoaDon.FormHoaDon;
import A07_HoaDon.HoaDonService;
import A05_KhachHang.FormKhachHang;
import A05_KhachHang.KhachHangService;
import A06_NhaCungCap.FormNhaCungCap;
import A04_NhanVien.FormNhanVien;
import A09_DonNhapHang.FormDonNhapHang;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class FormBaoCao extends javax.swing.JFrame {
    
    HoaDonService hoaDonService;
    KhachHangService khachHangService;
    SanPhamService sanPhamService;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModel2 = new DefaultTableModel();
    SanPham sanPham = new SanPham();
    /**
     * Creates new form NewJFrame
     */
    public FormBaoCao() {
        initComponents();
        khachHangService = new KhachHangService();
        sanPhamService = new SanPhamService();
        
        //HomNay();
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
            
        };
        defaultTableModel2 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
            
        };
        khachHangTable.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã Khách Hàng");
        defaultTableModel.addColumn("Tên Khách Hàng");
        defaultTableModel.addColumn("Số Lượng Đã Mua");
        defaultTableModel.addColumn("Tổng Tiền");
        khachHangTable.setRowHeight(24);
        khachHangTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        khachHangTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        setKhachHangTableData();
        
        //TongSoL();
        sanPhamTable.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("Mã Sản Phẩm");
        defaultTableModel2.addColumn("Tên Sản Phẩm");
        defaultTableModel2.addColumn("Số Lượng Đã Bán");
        defaultTableModel2.addColumn("Doanh Thu");
        defaultTableModel2.addColumn("Lợi Nhuận");
        sanPhamTable.setRowHeight(24);
        sanPhamTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        sanPhamTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        setSanPhamTableData();
        
        int count=defaultTableModel2.getRowCount();
        double tongDoanhThu=0;
        double tongLoiNhuan=0;
        int tongSoLuong=0;
        if (count==0) 
        {
            tongSoLuongDaBanDisplayLabel.setText("0");
            tongDoanhThuDisplayLabel.setText("0");
            tongLoiNhuanDisplayLabel.setText("0");
        }
        else 
        {
            for (int i=0;i<count;i++)
            {
                tongSoLuong+=Integer.valueOf(defaultTableModel2.getValueAt(i, 2).toString());
                tongDoanhThu+=Double.valueOf(defaultTableModel2.getValueAt(i, 3).toString());
                tongLoiNhuan+=Double.valueOf(defaultTableModel2.getValueAt(i, 4).toString());
            }
            tongSoLuongDaBanDisplayLabel.setText(String.valueOf(tongSoLuong));
            tongDoanhThuDisplayLabel.setText(String.valueOf(tongDoanhThu));
            tongLoiNhuanDisplayLabel.setText(String.valueOf(tongLoiNhuan));
        }
    }
    
 /*   private void HomNay(){
        try {           
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanly_banhang","root","a1a2a3");
            PreparedStatement Pst = Con.prepareStatement("select * from hoa_don where THOIGIAN like N'%"+java.time.LocalDate.now()+"%'");
            ResultSet Rs = Pst.executeQuery();
            defaultTableModel = (DefaultTableModel)HoaDonTbl.getModel();
            defaultTableModel.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MAHD"),Rs.getString("SOHD"), Rs.getString("THOIGIAN"), Rs.getDouble("TONGTIEN")};
                defaultTableModel.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        TongSoL();
    }*/
    private void setKhachHangTableData(){
        
         try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement
            ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
            + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH"
                    + " group by hoadon.MAKH,TENKH "
            );
            ResultSet Rs = Pst.executeQuery();
            
            defaultTableModel.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MAKH"),Rs.getString("TENKH"), Rs.getInt("SOLUONGDAMUA"), Rs.getDouble("TONGTIEN")};
                defaultTableModel.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    

    
    
    private void setSanPhamTableData(){
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement(
            "select chitiethoadon.MASP,chitiethoadon.TENSP,sum(chitiethoadon.SOLUONG) as SOLUONGDABAN, sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG) as DOANHTHU, "
                    + "sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG - chitiethoadon.GIANHAP*chitiethoadon.SOLUONG) as LOINHUAN "
            +" from chitiethoadon join hoadon on chitiethoadon.MAHD = hoadon.MAHD"
            +" group by chitiethoadon.MASP,chitiethoadon.TENSP ");
            ResultSet Rs = Pst.executeQuery();
          
            
            defaultTableModel2.setRowCount(0);
            while (Rs.next()) {
                
                Object objList[] = {Rs.getString("MASP"),Rs.getString("TENSP"),Rs.getInt("SOLUONGDABAN"),Rs.getString("DOANHTHU"),Rs.getDouble("LOINHUAN")};
                defaultTableModel2.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        sapXepTheoLabel = new javax.swing.JLabel();
        tangDanTongTienButton = new javax.swing.JButton();
        giamDanTongTienButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        khachHangTable = new javax.swing.JTable();
        tuKHLabel = new javax.swing.JLabel();
        tuKHTextField = new javax.swing.JTextField();
        denKHLabel = new javax.swing.JLabel();
        denKHTextField = new javax.swing.JTextField();
        khoangThoiGianLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        tuLabel = new javax.swing.JLabel();
        denLabel = new javax.swing.JLabel();
        tuSanPhamTextField = new javax.swing.JTextField();
        denSanPhamTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        sanPhamTable = new javax.swing.JTable();
        khoangThoiGianLabel1 = new javax.swing.JLabel();
        doanhThuRadioButton = new javax.swing.JRadioButton();
        loiNhuanRadioButton = new javax.swing.JRadioButton();
        soLuongBanRadioButton = new javax.swing.JRadioButton();
        thuTuLabel = new javax.swing.JLabel();
        tangDanRadioButton = new javax.swing.JRadioButton();
        giamDanRadioButton = new javax.swing.JRadioButton();
        timKiemButton = new javax.swing.JButton();
        sapXepTheoDoanhThuLabel1 = new javax.swing.JLabel();
        tongDoanhThuLabel = new javax.swing.JLabel();
        tongDoanhThuDisplayLabel = new javax.swing.JLabel();
        tongLoiNhuanDisplayLabel = new javax.swing.JLabel();
        tongLoiNhuanLabel = new javax.swing.JLabel();
        tongSoLuongDaBan = new javax.swing.JLabel();
        tongSoLuongDaBanDisplayLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        nhanVienButton = new javax.swing.JButton();
        hoaDonButton = new javax.swing.JButton();
        khachHangButton = new javax.swing.JButton();
        nhaCungCapButton = new javax.swing.JButton();
        dangXuatButton = new javax.swing.JButton();
        nhapHangButton = new javax.swing.JButton();
        sanPhamButton = new javax.swing.JButton();
        baoCaoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1508, 668));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1508, 668));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(new javax.swing.border.MatteBorder(null));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1505, 580));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        sapXepTheoLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sapXepTheoLabel.setText("Sắp Xếp Theo Tổng Tiền");

        tangDanTongTienButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tangDanTongTienButton.setText("Tăng Dần");
        tangDanTongTienButton.setPreferredSize(new java.awt.Dimension(120, 40));
        tangDanTongTienButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tangDanTongTienButtonMouseClicked(evt);
            }
        });

        giamDanTongTienButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        giamDanTongTienButton.setText("Giảm Dần");
        giamDanTongTienButton.setToolTipText("");
        giamDanTongTienButton.setPreferredSize(new java.awt.Dimension(120, 40));
        giamDanTongTienButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giamDanTongTienButtonMouseClicked(evt);
            }
        });

        khachHangTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        khachHangTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(khachHangTable);

        tuKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tuKHLabel.setText("Từ");
        tuKHLabel.setPreferredSize(new java.awt.Dimension(40, 40));

        tuKHTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tuKHTextField.setPreferredSize(new java.awt.Dimension(120, 40));

        denKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        denKHLabel.setText("Đến");
        denKHLabel.setPreferredSize(new java.awt.Dimension(40, 40));

        denKHTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        denKHTextField.setPreferredSize(new java.awt.Dimension(120, 40));

        khoangThoiGianLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        khoangThoiGianLabel.setText("Khoảng Thời Gian");
        khoangThoiGianLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(denKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tuKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(denKHTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(tuKHTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(sapXepTheoLabel)
                            .addComponent(khoangThoiGianLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(giamDanTongTienButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tangDanTongTienButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(khoangThoiGianLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tuKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tuKHLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(denKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(denKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sapXepTheoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tangDanTongTienButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giamDanTongTienButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Khách Hàng", jPanel4);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tuLabel.setText("Từ:");
        tuLabel.setPreferredSize(new java.awt.Dimension(40, 40));

        denLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        denLabel.setText("Đến:");

        tuSanPhamTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tuSanPhamTextField.setPreferredSize(new java.awt.Dimension(120, 40));

        denSanPhamTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        denSanPhamTextField.setPreferredSize(new java.awt.Dimension(120, 40));

        sanPhamTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(sanPhamTable);

        khoangThoiGianLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        khoangThoiGianLabel1.setText("Khoảng Thời Gian");

        doanhThuRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(doanhThuRadioButton);
        doanhThuRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        doanhThuRadioButton.setSelected(true);
        doanhThuRadioButton.setText("Doanh Thu");

        loiNhuanRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(loiNhuanRadioButton);
        loiNhuanRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loiNhuanRadioButton.setText("Lợi Nhuận");

        soLuongBanRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(soLuongBanRadioButton);
        soLuongBanRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        soLuongBanRadioButton.setText("Số Lượng Bán");

        thuTuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        thuTuLabel.setText("Thứ Tự");

        tangDanRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(tangDanRadioButton);
        tangDanRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tangDanRadioButton.setSelected(true);
        tangDanRadioButton.setText("Tăng Dần");

        giamDanRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(giamDanRadioButton);
        giamDanRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        giamDanRadioButton.setText("Giảm Dần");

        timKiemButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timKiemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Search.png"))); // NOI18N
        timKiemButton.setText("Tìm");
        timKiemButton.setPreferredSize(new java.awt.Dimension(120, 40));
        timKiemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemButtonActionPerformed(evt);
            }
        });

        sapXepTheoDoanhThuLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sapXepTheoDoanhThuLabel1.setText("Sắp Xếp Theo");

        tongDoanhThuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongDoanhThuLabel.setText("Tổng Doanh Thu:");

        tongDoanhThuDisplayLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongDoanhThuDisplayLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tongDoanhThuDisplayLabel.setText("0");

        tongLoiNhuanDisplayLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongLoiNhuanDisplayLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tongLoiNhuanDisplayLabel.setText("0");

        tongLoiNhuanLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongLoiNhuanLabel.setText("Tổng Lợi Nhuận:");

        tongSoLuongDaBan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongSoLuongDaBan.setText("Tổng Số Lượng Đã Bán: ");

        tongSoLuongDaBanDisplayLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongSoLuongDaBanDisplayLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tongSoLuongDaBanDisplayLabel.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(denLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(tuSanPhamTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(denSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(khoangThoiGianLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(doanhThuRadioButton)
                                        .addComponent(loiNhuanRadioButton)
                                        .addComponent(soLuongBanRadioButton)
                                        .addComponent(thuTuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tangDanRadioButton)
                                        .addComponent(giamDanRadioButton))
                                    .addComponent(sapXepTheoDoanhThuLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(timKiemButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(578, Short.MAX_VALUE)
                        .addComponent(tongSoLuongDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tongSoLuongDaBanDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tongLoiNhuanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tongLoiNhuanDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tongDoanhThuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tongDoanhThuDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(khoangThoiGianLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tuSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(denLabel)
                            .addComponent(denSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sapXepTheoDoanhThuLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(doanhThuRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loiNhuanRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(soLuongBanRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuTuLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tangDanRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giamDanRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timKiemButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tongSoLuongDaBanDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tongSoLuongDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tongLoiNhuanDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tongLoiNhuanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tongDoanhThuDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tongDoanhThuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel5);

        jPanel2.setBackground(new java.awt.Color(102, 255, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1508, 80));

        nhanVienButton.setBackground(new java.awt.Color(102, 255, 102));
        nhanVienButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nhanVienButton.setForeground(new java.awt.Color(51, 51, 51));
        nhanVienButton.setText("Nhân Viên");
        nhanVienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhanVienButtonActionPerformed(evt);
            }
        });

        hoaDonButton.setBackground(new java.awt.Color(102, 255, 102));
        hoaDonButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        hoaDonButton.setForeground(new java.awt.Color(51, 51, 51));
        hoaDonButton.setText("Hóa Đơn");
        hoaDonButton.setPreferredSize(new java.awt.Dimension(142, 35));
        hoaDonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoaDonButtonActionPerformed(evt);
            }
        });

        khachHangButton.setBackground(new java.awt.Color(102, 255, 102));
        khachHangButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        khachHangButton.setForeground(new java.awt.Color(51, 51, 51));
        khachHangButton.setText("Khách Hàng");
        khachHangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khachHangButtonActionPerformed(evt);
            }
        });

        nhaCungCapButton.setBackground(new java.awt.Color(102, 255, 102));
        nhaCungCapButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nhaCungCapButton.setForeground(new java.awt.Color(51, 51, 51));
        nhaCungCapButton.setText("Nhà Cung Cấp");
        nhaCungCapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhaCungCapButtonActionPerformed(evt);
            }
        });

        dangXuatButton.setBackground(new java.awt.Color(102, 255, 102));
        dangXuatButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        dangXuatButton.setForeground(new java.awt.Color(51, 51, 51));
        dangXuatButton.setText("Đăng Xuất");
        dangXuatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangXuatButtonActionPerformed(evt);
            }
        });

        nhapHangButton.setBackground(new java.awt.Color(102, 255, 102));
        nhapHangButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nhapHangButton.setForeground(new java.awt.Color(51, 51, 51));
        nhapHangButton.setText("Nhập Hàng");
        nhapHangButton.setToolTipText("");
        nhapHangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapHangButtonActionPerformed(evt);
            }
        });

        sanPhamButton.setBackground(new java.awt.Color(102, 255, 102));
        sanPhamButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        sanPhamButton.setForeground(new java.awt.Color(51, 51, 51));
        sanPhamButton.setText("Sản Phẩm");
        sanPhamButton.setMaximumSize(new java.awt.Dimension(200, 200));
        sanPhamButton.setMinimumSize(new java.awt.Dimension(0, 0));
        sanPhamButton.setPreferredSize(new java.awt.Dimension(10, 10));
        sanPhamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanPhamButtonActionPerformed(evt);
            }
        });

        baoCaoButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        baoCaoButton.setText("Báo Cáo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sanPhamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nhanVienButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(khachHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hoaDonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nhaCungCapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nhapHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(baoCaoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dangXuatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sanPhamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nhanVienButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(khachHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nhaCungCapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hoaDonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nhapHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(baoCaoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dangXuatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void giamDanTongTienButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamDanTongTienButtonMouseClicked
        // TODO add your handling code here:
       try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst;
            ResultSet Rs;
            String string="";
            if (String.valueOf(tuKHTextField.getText()).equals(string) && String.valueOf(denKHTextField.getText()).equals(string))
            {    
                Pst = Con.prepareStatement
                (" select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + " from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN DESC ");
                Rs = Pst.executeQuery();
            }
            else if(!String.valueOf(tuKHTextField.getText()).equals(string) && String.valueOf(denKHTextField.getText()).equals(string)){
                Pst = Con.prepareStatement
                ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + "where date(hoadon.THOIGIAN) >= '"+tuKHTextField.getText()+"' "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN DESC ");
                Rs = Pst.executeQuery();
            }
            else if(String.valueOf(tuKHTextField.getText()).equals(string) && !String.valueOf(denKHTextField.getText()).equals(string)){
                Pst = Con.prepareStatement
                ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + "where date(hoadon.THOIGIAN) <='"+denKHTextField.getText()+"' "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN DESC ");
                Rs = Pst.executeQuery();
            }
            else
            {
                Pst = Con.prepareStatement
                ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + "where hoadon.THOIGIAN between '"+tuKHTextField.getText()+"' and '"+denKHTextField.getText()+"' "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN DESC ");
                Rs = Pst.executeQuery();
            }
            
            
            defaultTableModel.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MAKH"),Rs.getString("TENKH"), Rs.getInt("SOLUONGDAMUA"), Rs.getDouble("TONGTIEN")};
                defaultTableModel.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_giamDanTongTienButtonMouseClicked

    private void tangDanTongTienButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tangDanTongTienButtonMouseClicked
        // TODO add your handling code here:
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst;
            ResultSet Rs;
            String string="";
            if (String.valueOf(tuKHTextField.getText()).equals(string) && String.valueOf(denKHTextField.getText()).equals(string))
            {    
                Pst = Con.prepareStatement
                (" select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + " from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN ASC ");
                Rs = Pst.executeQuery();
            }
            else if(!String.valueOf(tuKHTextField.getText()).equals(string) && String.valueOf(denKHTextField.getText()).equals(string)){
                Pst = Con.prepareStatement
                ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + "where date(hoadon.THOIGIAN) >= '"+tuKHTextField.getText()+"' "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN ASC ");
                Rs = Pst.executeQuery();
            }
            else if(String.valueOf(tuKHTextField.getText()).equals(string) && !String.valueOf(denKHTextField.getText()).equals(string)){
                Pst = Con.prepareStatement
                ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + "where date(hoadon.THOIGIAN) <='"+denKHTextField.getText()+"' "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN ASC ");
                Rs = Pst.executeQuery();
            }
            else
            {
                Pst = Con.prepareStatement
                ("select hoadon.MAKH,TENKH,sum(SOLUONG) as SOLUONGDAMUA, sum(chitiethoadon.SOLUONG*chitiethoadon.GIABAN) as TONGTIEN "
                + "from (hoadon join chitiethoadon on hoadon.MAHD=chitiethoadon.MAHD) left join khachhang on hoadon.MAKH=khachhang.MAKH "
                + "where hoadon.THOIGIAN between '"+tuKHTextField.getText()+"' and '"+denKHTextField.getText()+"' "
                + " group by hoadon.MAKH,TENKH "
                + " order by TONGTIEN ASC ");
                Rs = Pst.executeQuery();
            }
            
            
            defaultTableModel.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MAKH"),Rs.getString("TENKH"), Rs.getInt("SOLUONGDAMUA"), Rs.getDouble("TONGTIEN")};
                defaultTableModel.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_tangDanTongTienButtonMouseClicked

    private void nhanVienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhanVienButtonActionPerformed
        // TODO add your handling code here:
        new FormNhanVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nhanVienButtonActionPerformed

    private void hoaDonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoaDonButtonActionPerformed
        // TODO add your handling code here:
        new FormHoaDon().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_hoaDonButtonActionPerformed

    private void khachHangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khachHangButtonActionPerformed
        // TODO add your handling code here:
        new FormKhachHang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_khachHangButtonActionPerformed

    private void nhaCungCapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhaCungCapButtonActionPerformed
        // TODO add your handling code here:
        new FormNhaCungCap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nhaCungCapButtonActionPerformed

    private void dangXuatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatButtonActionPerformed
        // TODO add your handling code here:
        new FormDangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dangXuatButtonActionPerformed

    private void nhapHangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapHangButtonActionPerformed
        // TODO add your handling code here:
        new FormDonNhapHang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nhapHangButtonActionPerformed

    private void sanPhamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanPhamButtonActionPerformed
        // TODO add your handling code here:
        new FormSanPham().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sanPhamButtonActionPerformed

    private void timKiemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemButtonActionPerformed
        // TODO add your handling code here:
        String sapXepTheo ="";
        String thuTu="";
        if (doanhThuRadioButton.isSelected()) sapXepTheo="DOANHTHU";
        else if (loiNhuanRadioButton.isSelected()) sapXepTheo="LOINHUAN";
        else if (soLuongBanRadioButton.isSelected()) sapXepTheo="SOLUONGDABAN";
        if (tangDanRadioButton.isSelected()) thuTu="ASC";
        else if (giamDanRadioButton.isSelected()) thuTu="DESC";
        try 
        {
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst;
            ResultSet Rs;
            if (tuSanPhamTextField.getText().equals("") && denSanPhamTextField.getText().equals(""))
            {
                Pst = Con.prepareStatement("select chitiethoadon.MASP,chitiethoadon.TENSP,sum(chitiethoadon.SOLUONG) as SOLUONGDABAN, sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG) as DOANHTHU, "
                + "sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG - chitiethoadon.GIANHAP*chitiethoadon.SOLUONG) as LOINHUAN " 
                +" from chitiethoadon join hoadon on chitiethoadon.MAHD=hoadon.MAHD "
                +" group by chitiethoadon.MASP,chitiethoadon.TENSP "
                + "order by "
                + sapXepTheo+" " + thuTu);
                Rs = Pst.executeQuery();
                defaultTableModel2.setRowCount(0);
                while (Rs.next()) 
                {
                    Object objList[] = {Rs.getString("MASP"),Rs.getString("TENSP"),Rs.getInt("SOLUONGDABAN"),Rs.getDouble("DOANHTHU"),Rs.getDouble("LOINHUAN")};
                    defaultTableModel2.addRow(objList);                
                }
            }
            else if(!tuSanPhamTextField.getText().equals("") && denSanPhamTextField.getText().equals(""))
            {
                if(!dateValidation(tuSanPhamTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");
                }
                else
                {
                    Pst = Con.prepareStatement("select chitiethoadon.MASP,chitiethoadon.TENSP,sum(chitiethoadon.SOLUONG) as SOLUONGDABAN, sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG) as DOANHTHU,"
                    + "sum( chitiethoadon.GIABAN*chitiethoadon.SOLUONG - chitiethoadon.GIANHAP*chitiethoadon.SOLUONG) as LOINHUAN "
                    +" from chitiethoadon join hoadon on chitiethoadon.MAHD=hoadon.MAHD"
                    +" where date(hoadon.THOIGIAN) >= '"+tuSanPhamTextField.getText()+"'"
                    +" group by chitiethoadon.MASP,chitiethoadon.TENSP "
                    + "order by "
                    + sapXepTheo+" " + thuTu);
                    Rs = Pst.executeQuery();
                    defaultTableModel2.setRowCount(0);
                    while (Rs.next()) 
                    {
                        Object objList[] = {Rs.getString("MASP"),Rs.getString("TENSP"),Rs.getInt("SOLUONGDABAN"),Rs.getDouble("DOANHTHU"),Rs.getDouble("LOINHUAN")};
                        defaultTableModel2.addRow(objList);                
                    }
                }
            }
            else if (tuSanPhamTextField.getText().equals("") && !denSanPhamTextField.getText().equals(""))
            {
                if(!dateValidation(denSanPhamTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");
                }
                else
                {
                    Pst = Con.prepareStatement("select chitiethoadon.MASP,chitiethoadon.TENSP,sum(chitiethoadon.SOLUONG) as SOLUONGDABAN, sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG) as DOANHTHU,"
                    + "sum( chitiethoadon.GIABAN*chitiethoadon.SOLUONG - chitiethoadon.GIANHAP*chitiethoadon.SOLUONG) as LOINHUAN"
                    +" from chitiethoadon join hoadon on chitiethoadon.MAHD=hoadon.MAHD"
                    +" where date(hoadon.THOIGIAN) <= '"+denSanPhamTextField.getText()+"'"
                    +" group by chitiethoadon.MASP,chitiethoadon.TENSP "
                    + "order by "
                    + sapXepTheo+" " + thuTu);
                    Rs = Pst.executeQuery();
                    defaultTableModel2.setRowCount(0);
                    while (Rs.next()) 
                    {
                        Object objList[] = {Rs.getString("MASP"),Rs.getString("TENSP"),Rs.getInt("SOLUONGDABAN"),Rs.getDouble("DOANHTHU"),Rs.getDouble("LOINHUAN")};
                        defaultTableModel2.addRow(objList);                
                    }
                }
            }
            else {
                if(!dateValidation(tuSanPhamTextField.getText())||!dateValidation(denSanPhamTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");
                }
                else
                {
                    Pst = Con.prepareStatement("select chitiethoadon.MASP,chitiethoadon.TENSP,sum(chitiethoadon.SOLUONG) as SOLUONGDABAN, sum(chitiethoadon.GIABAN*chitiethoadon.SOLUONG) as DOANHTHU,"
                    + "sum( chitiethoadon.GIABAN*chitiethoadon.SOLUONG - chitiethoadon.GIANHAP*chitiethoadon.SOLUONG) as LOINHUAN"
                    +" from chitiethoadon join hoadon on chitiethoadon.MAHD=hoadon.MAHD"
                    +" where date(hoadon.THOIGIAN) between '"+tuSanPhamTextField.getText()+"' and '"+denSanPhamTextField.getText()+"'"
                    +" group by chitiethoadon.MASP,chitiethoadon.TENSP "
                    + "order by "
                    + sapXepTheo+" " + thuTu);
                    Rs = Pst.executeQuery();
                    defaultTableModel2.setRowCount(0);
                    while (Rs.next()) 
                    {
                        Object objList[] = {Rs.getString("MASP"),Rs.getString("TENSP"),Rs.getInt("SOLUONGDABAN"),Rs.getDouble("DOANHTHU"),Rs.getDouble("LOINHUAN")};
                        defaultTableModel2.addRow(objList);                
                    }
                }
            }
            
            
            int count=defaultTableModel2.getRowCount();
            double tongDoanhThu=0;
            double tongLoiNhuan=0;
            int tongSoLuongDaBan=0;
            if (count==0) 
            {
                tongSoLuongDaBanDisplayLabel.setText("0");
                tongDoanhThuDisplayLabel.setText("0");
                tongLoiNhuanDisplayLabel.setText("0");
            }
            else 
            {
                for (int i=0;i<count;i++)
                {
                    tongSoLuongDaBan+=Integer.valueOf(defaultTableModel2.getValueAt(i, 2).toString());
                    tongDoanhThu+=Double.valueOf(defaultTableModel2.getValueAt(i, 3).toString());
                    tongLoiNhuan+=Double.valueOf(defaultTableModel2.getValueAt(i, 4).toString());
                }
                tongSoLuongDaBanDisplayLabel.setText(String.valueOf(tongSoLuongDaBan));
                tongDoanhThuDisplayLabel.setText(String.valueOf(tongDoanhThu));
                tongLoiNhuanDisplayLabel.setText(String.valueOf(tongLoiNhuan));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormBaoCao.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }//GEN-LAST:event_timKiemButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormBaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBaoCao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baoCaoButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton dangXuatButton;
    private javax.swing.JLabel denKHLabel;
    private javax.swing.JTextField denKHTextField;
    private javax.swing.JLabel denLabel;
    private javax.swing.JTextField denSanPhamTextField;
    private javax.swing.JRadioButton doanhThuRadioButton;
    private javax.swing.JRadioButton giamDanRadioButton;
    private javax.swing.JButton giamDanTongTienButton;
    private javax.swing.JButton hoaDonButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton khachHangButton;
    private javax.swing.JTable khachHangTable;
    private javax.swing.JLabel khoangThoiGianLabel;
    private javax.swing.JLabel khoangThoiGianLabel1;
    private javax.swing.JRadioButton loiNhuanRadioButton;
    private javax.swing.JButton nhaCungCapButton;
    private javax.swing.JButton nhanVienButton;
    private javax.swing.JButton nhapHangButton;
    private javax.swing.JButton sanPhamButton;
    private javax.swing.JTable sanPhamTable;
    private javax.swing.JLabel sapXepTheoDoanhThuLabel1;
    private javax.swing.JLabel sapXepTheoLabel;
    private javax.swing.JRadioButton soLuongBanRadioButton;
    private javax.swing.JRadioButton tangDanRadioButton;
    private javax.swing.JButton tangDanTongTienButton;
    private javax.swing.JLabel thuTuLabel;
    private javax.swing.JButton timKiemButton;
    private javax.swing.JLabel tongDoanhThuDisplayLabel;
    private javax.swing.JLabel tongDoanhThuLabel;
    private javax.swing.JLabel tongLoiNhuanDisplayLabel;
    private javax.swing.JLabel tongLoiNhuanLabel;
    private javax.swing.JLabel tongSoLuongDaBan;
    private javax.swing.JLabel tongSoLuongDaBanDisplayLabel;
    private javax.swing.JLabel tuKHLabel;
    private javax.swing.JTextField tuKHTextField;
    private javax.swing.JLabel tuLabel;
    private javax.swing.JTextField tuSanPhamTextField;
    // End of variables declaration//GEN-END:variables
}
