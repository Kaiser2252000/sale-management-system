/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A02_BanHang;

import A00_Connection.JDBCConnection;
import A01_DangNhap.FormDangNhap;
import A08_ChiTietHoaDon.ChiTietHoaDonService;
import A08_ChiTietHoaDon.ChiTietHoaDon;
import A00_Connection.MyCombobox;
import A00_Support.IsInt;
import A00_Support.IsNumeric;
import static A00_Support.IsNumeric.isNumeric;
import A03_SanPham.SanPham;
import A03_SanPham.SanPhamService;
import A07_HoaDon.HoaDon;
import A07_HoaDon.HoaDonService;
import A05_KhachHang.AddKhachHang;
import A05_KhachHang.KhachHangDao;
import A00_Threading.ClockThread;
import A00_Threading.DateThread;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class FormBanHang extends javax.swing.JFrame {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    DefaultTableModel defaultTableModel2 = new DefaultTableModel();
    FormBanHang formBanHang;  
    HoaDonService hoaDonService = new HoaDonService();
    public static int row;
    public static String gio;
    public static String ngay;
    public static String[] tenSP;
    public static String[] giaBan;
    public static String[] soLuong;
    public static String[] thanhTien;
    public static String tongTien;
    public static String maHoaDon;
    public static String tenKH;
 
    public void initClock(){
        ClockThread th = new ClockThread(hhmmssLabel);
        th.start();
    }
    
    public void initDate(){
        DateThread th = new DateThread(ddmmyyLabel);
        th.start();
    }
    
    public FormBanHang() {
        formBanHang = this;
        initComponents();
        initClock();
        initDate();
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                if (col==4) return true;
                else return false;
            }
        };
        defaultTableModel2 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
               return false;
            }
        };
        hoaDonTable.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã Sản Phẩm");
        defaultTableModel.addColumn("Tên Sản Phẩm");
        defaultTableModel.addColumn("Loại Sản Phẩm");
        defaultTableModel.addColumn("Giá Bán");
        defaultTableModel.addColumn("Số Lượng");
        defaultTableModel.addColumn("Thành Tiền");   
        hoaDonTable.setRowHeight(24);
        hoaDonTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        hoaDonTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        sanPhamTable.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("Mã Sản Phẩm");
        defaultTableModel2.addColumn("Tên Sản Phẩm");
        defaultTableModel2.addColumn("Loại Sản Phẩm");
        defaultTableModel2.addColumn("Giá Bán");
        defaultTableModel2.addColumn("Số Lượng");
        
        
        sanPhamTable.setRowHeight(24);
        sanPhamTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        sanPhamTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        setSanPhamTable();
        maNVLabel2.setText(FormDangNhap.nhanVien.getMANV());
        nhanVienLabel2.setText(FormDangNhap.nhanVien.getTENNV());
    }
    
    private void setSanPhamTable(){
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement
            ("select MASP,TENSP,LOAISP,GIABAN,SOLUONG "
            + "from sanpham");
            ResultSet Rs = Pst.executeQuery();
           
            defaultTableModel2.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"), Rs.getString("LOAISP"), Rs.getString("GIABAN"), Rs.getInt("SOLUONG")};
                defaultTableModel2.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    
    
    public void PrintHD(){
        gio = hhmmssLabel.getText();
        ngay = ddmmyyLabel.getText();
        row = defaultTableModel.getRowCount();
        tenSP = new String[row];
        giaBan = new String[row];
        soLuong = new String[row];
        thanhTien = new String[row];
        for (int j = 0; j < row; j++) {
            tenSP[j] = String.valueOf(defaultTableModel.getValueAt(j, 1));
            giaBan[j] = String.valueOf(defaultTableModel.getValueAt(j, 3)); 
            soLuong[j] = String.valueOf(defaultTableModel.getValueAt(j, 4));          
            thanhTien[j] = String.valueOf(defaultTableModel.getValueAt(j, 5));
        }
        tongTien = tongTienTextField.getText();
 
        if (khachHangComboBox.getSelectedItem() == null) {
            tenKH = "Khách Lẻ";
        }else{
            tenKH = khachHangComboBox.getSelectedItem().toString();
        }
    }
    
   
    
    
    private void hienThiTongSoLuongVaTongTien(){
        int soLuong = 0;
        int tongSoLuong = 0;
        Double thanhTien = 0.0;
        Double tongTien = 0.0;
       
        
        
        int a = defaultTableModel.getRowCount();
        if (a == 0){
            tongSoLuongTextField.setText("0");
            tongTienTextField.setText("0");
        }else{
            for (int j = 0; j < a; j++) {
                soLuong = Integer.valueOf(String.valueOf(defaultTableModel.getValueAt(j, 4)));
                thanhTien = Double.valueOf(String.valueOf(defaultTableModel.getValueAt(j, 5)));
                tongSoLuong += soLuong;
                tongTien += thanhTien;
                tongSoLuongTextField.setText(String.valueOf(tongSoLuong));
                tongTienTextField.setText(String.valueOf(tongTien));
            }
        }
        tienThuaTextField.setText(String.valueOf(Double.valueOf(khachTraTextField.getText())-tongTien));
    }
    
    
   
    String maSP;
    int soLuongMoi;  
    
    public void updateSoLuong(){
                  
                            
            for (int j = 0; j < i; j++) {
                int soLuongMua = Integer.valueOf(defaultTableModel.getValueAt(j, 4).toString());
                String MASP = defaultTableModel.getValueAt(j, 0).toString(); 
                SanPham sanPham = new SanPham();
                SanPhamService sanPhamService = new SanPhamService();
                sanPham = sanPhamService.getSanPhamByMASP(MASP);
                int soLuongSanPhamCu = sanPham.getSOLUONG();
                int soLuongSanPhamMoi = soLuongSanPhamCu - soLuongMua;
                SanPhamService serviceHangHoa = new SanPhamService();
                serviceHangHoa.updateSOLUONG(MASP,soLuongSanPhamMoi);         
            }
            setSanPhamTable();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        timSanPhamLabel = new javax.swing.JLabel();
        timSanPhamTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        ngayLabel = new javax.swing.JLabel();
        gioLabel = new javax.swing.JLabel();
        timKhachHangLabel = new javax.swing.JLabel();
        timKhachHangTextField = new javax.swing.JTextField();
        tongSoLuongLabel = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        khachTraLabel = new javax.swing.JLabel();
        tienThuaLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tongSoLuongTextField = new javax.swing.JTextField();
        tongTienTextField = new javax.swing.JTextField();
        khachTraTextField = new javax.swing.JTextField();
        tienThuaTextField = new javax.swing.JTextField();
        thanhToanButton = new javax.swing.JButton();
        hhmmssLabel = new javax.swing.JLabel();
        ddmmyyLabel = new javax.swing.JLabel();
        nhanVienLabel = new javax.swing.JLabel();
        nhanVienLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ghiChuTextField = new javax.swing.JTextArea();
        themKhachHangButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        khachHangComboBox = new javax.swing.JComboBox<>();
        maNVLabel = new javax.swing.JLabel();
        maNVLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        OutBtn = new javax.swing.JButton();
        tapHoaLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sanPhamTable = new javax.swing.JTable();
        xoaButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1600, 1000));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1600, 1000));

        timSanPhamLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timSanPhamLabel.setText("Tìm Sản Phẩm");
        timSanPhamLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        timSanPhamTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timSanPhamTextField.setPreferredSize(new java.awt.Dimension(350, 40));
        timSanPhamTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                timSanPhamTextFieldCaretUpdate(evt);
            }
        });
        timSanPhamTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timSanPhamTextFieldActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 255, 0));

        ngayLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ngayLabel.setText("Ngày:");

        gioLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        gioLabel.setText("Giờ:");

        timKhachHangLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timKhachHangLabel.setText("Tìm Khách Hàng");
        timKhachHangLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        timKhachHangTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timKhachHangTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        timKhachHangTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKhachHangTextFieldActionPerformed(evt);
            }
        });
        timKhachHangTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timKhachHangTextFieldKeyReleased(evt);
            }
        });

        tongSoLuongLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongSoLuongLabel.setText("Tổng Số Lượng");
        tongSoLuongLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        tongTienLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongTienLabel.setText("Tổng Tiền");
        tongTienLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        khachTraLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        khachTraLabel.setText("Khách Trả");
        khachTraLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        tienThuaLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tienThuaLabel.setText("Tiền Thừa ");
        tienThuaLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Ghi Chú");

        tongSoLuongTextField.setEditable(false);
        tongSoLuongTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongSoLuongTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tongSoLuongTextField.setText("0");
        tongSoLuongTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        tongSoLuongTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tongSoLuongTextFieldActionPerformed(evt);
            }
        });

        tongTienTextField.setEditable(false);
        tongTienTextField.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tongTienTextField.setForeground(new java.awt.Color(255, 0, 0));
        tongTienTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tongTienTextField.setText("0");
        tongTienTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        tongTienTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tongTienTextFieldActionPerformed(evt);
            }
        });

        khachTraTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        khachTraTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        khachTraTextField.setText("0");
        khachTraTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        khachTraTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                khachTraTextFieldCaretUpdate(evt);
            }
        });

        tienThuaTextField.setEditable(false);
        tienThuaTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tienThuaTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tienThuaTextField.setText("0");
        tienThuaTextField.setPreferredSize(new java.awt.Dimension(150, 40));

        thanhToanButton.setBackground(new java.awt.Color(102, 255, 0));
        thanhToanButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        thanhToanButton.setForeground(new java.awt.Color(51, 51, 51));
        thanhToanButton.setText("Thanh Toán");
        thanhToanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thanhToanButtonMouseClicked(evt);
            }
        });

        hhmmssLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        hhmmssLabel.setForeground(new java.awt.Color(255, 51, 0));
        hhmmssLabel.setText("hh:mm:ss");

        ddmmyyLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ddmmyyLabel.setForeground(new java.awt.Color(255, 51, 0));
        ddmmyyLabel.setText("dd/mm/yy");

        nhanVienLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nhanVienLabel.setText("Nhân Viên:");
        nhanVienLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        nhanVienLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nhanVienLabel2.setPreferredSize(new java.awt.Dimension(150, 40));

        ghiChuTextField.setColumns(20);
        ghiChuTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuTextField.setRows(5);
        jScrollPane4.setViewportView(ghiChuTextField);

        themKhachHangButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        themKhachHangButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Add.png"))); // NOI18N
        themKhachHangButton.setText("Thêm");
        themKhachHangButton.setPreferredSize(new java.awt.Dimension(120, 40));
        themKhachHangButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themKhachHangButtonMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Khách Hàng");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 40));

        khachHangComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        khachHangComboBox.setMaximumRowCount(20);
        khachHangComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        khachHangComboBox.setSelectedIndex(-1);
        khachHangComboBox.setPreferredSize(new java.awt.Dimension(150, 40));
        khachHangComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                khachHangComboBoxItemStateChanged(evt);
            }
        });
        khachHangComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khachHangComboBoxActionPerformed(evt);
            }
        });

        maNVLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maNVLabel.setText("Mã Nhân Viên:");
        maNVLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        maNVLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maNVLabel2.setPreferredSize(new java.awt.Dimension(150, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(themKhachHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ngayLabel)
                                .addGap(18, 18, 18)
                                .addComponent(ddmmyyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gioLabel)
                                .addGap(4, 4, 4)
                                .addComponent(hhmmssLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(thanhToanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(maNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(maNVLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(nhanVienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nhanVienLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(timKhachHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(timKhachHangTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tongTienTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tienThuaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tienThuaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(khachTraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(khachTraTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(tongSoLuongLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tongSoLuongTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(khachHangComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maNVLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nhanVienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhanVienLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayLabel)
                    .addComponent(hhmmssLabel)
                    .addComponent(ddmmyyLabel))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timKhachHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKhachHangTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(themKhachHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(khachHangComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tongSoLuongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongSoLuongTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongTienTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(khachTraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(khachTraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienThuaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tienThuaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(thanhToanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 0));

        OutBtn.setBackground(new java.awt.Color(255, 255, 255));
        OutBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Delete.png"))); // NOI18N
        OutBtn.setText("Đóng");
        OutBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        OutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OutBtnMouseClicked(evt);
            }
        });

        tapHoaLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        tapHoaLabel.setForeground(new java.awt.Color(51, 51, 51));
        tapHoaLabel.setText("Bán Hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tapHoaLabel)
                .addGap(554, 554, 554)
                .addComponent(OutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tapHoaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

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
        sanPhamTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sanPhamTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanPhamTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sanPhamTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        xoaButton.setBackground(new java.awt.Color(102, 255, 0));
        xoaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        xoaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/icons8-delete-bin-32.png"))); // NOI18N
        xoaButton.setText("Xóa");
        xoaButton.setPreferredSize(new java.awt.Dimension(120, 40));
        xoaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xoaButtonMouseClicked(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(102, 255, 0));
        refreshButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Refresh.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.setPreferredSize(new java.awt.Dimension(120, 40));
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });

        hoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
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
        hoaDonTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        hoaDonTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                hoaDonTablePropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(hoaDonTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(timSanPhamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(timSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 443, Short.MAX_VALUE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(xoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(timSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(timSanPhamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(xoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tongSoLuongTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongSoLuongTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tongSoLuongTextFieldActionPerformed

    private void timKhachHangTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKhachHangTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timKhachHangTextFieldActionPerformed

    private void tongTienTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongTienTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tongTienTextFieldActionPerformed

    private void timSanPhamTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timSanPhamTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timSanPhamTextFieldActionPerformed
int idHH;
int i=0;
int sol;
Double tong;

    private void xoaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaButtonMouseClicked
        // TODO add your handling code here:
        
        int index = hoaDonTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(FormBanHang.this, "Vui lòng chọn Sản Phẩm cần xóa","Lỗi", JOptionPane.ERROR_MESSAGE);
        }else{
        defaultTableModel.removeRow(index);
        i--;
        defaultTableModel.setRowCount(i);
        hienThiTongSoLuongVaTongTien();
        }
    }//GEN-LAST:event_xoaButtonMouseClicked

    private void khachTraTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_khachTraTextFieldCaretUpdate
        // TODO add your handling code here:
        Double khachTra;
        Double tongTien;
        Double tienThua;        
        if (khachTraTextField.getText().isEmpty()) {
            tienThuaTextField.setText("0");
        }
        else{
            khachTra = Double.valueOf(khachTraTextField.getText());
            tongTien = Double.valueOf(tongTienTextField.getText());
            tienThua = khachTra - tongTien;
            tienThuaTextField.setText(tienThua+"");
        }
    }//GEN-LAST:event_khachTraTextFieldCaretUpdate

    private void thanhToanButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thanhToanButtonMouseClicked
        // TODO add your handling code here:   
        if (defaultTableModel.getRowCount()==0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
        }else{
        
        HoaDon hoaDon = new HoaDon();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        maHoaDon = "HD"+String.valueOf(formatter.format(date));
        hoaDon.setMAHD("HD"+String.valueOf(formatter.format(date)));
      
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        hoaDon.setTHOIGIAN(sqlDate);
            
        //hoaDon.setMaKhachHang(2); //// Chưa code 
        MyCombobox cbb = (MyCombobox) khachHangComboBox.getSelectedItem();
        if (cbb == null) {
            hoaDon.setMAKH("0");
        }else{
            hoaDon.setMAKH(cbb.Value.toString());
        }
        
              
        hoaDon.setMANV(maNVLabel2.getText());
        
        
        hoaDon.setTONGTIEN(Double.valueOf(tongTienTextField.getText()));       
        hoaDon.setGHICHU(ghiChuTextField.getText());

        hoaDonService.addHoaDon(hoaDon);
        addChiTietHoaDon(hoaDon.getMAHD());

        PrintHD();
        new PrintHoaDon().setVisible(true);
        
        updateSoLuong();

        
        

        khachHangComboBox.setSelectedIndex(-1);
        }

    }//GEN-LAST:event_thanhToanButtonMouseClicked

    
    
    

    private void addChiTietHoaDon(String MAHD) {
        
        for (int j = 0; j < hoaDonTable.getRowCount(); j++) {
            String maHD = MAHD;
            String maSP = hoaDonTable.getValueAt(j, 0).toString();
            String tenSP = hoaDonTable.getValueAt(j, 1).toString();
            SanPham sanPham = new SanPham();
            SanPhamService sanPhamService = new SanPhamService();
            sanPham=sanPhamService.getSanPhamByMASP(maSP);
            Double giaNhap = sanPham.getGIANHAP();
            Double giaBan = Double.valueOf(hoaDonTable.getValueAt(j, 3).toString());
            int soLuong = Integer.valueOf(hoaDonTable.getValueAt(j, 4).toString());
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonService();
            chiTietHoaDon.setMAHD(maHD);
            chiTietHoaDon.setMASP(maSP);
            chiTietHoaDon.setTENSP(tenSP);
            chiTietHoaDon.setGIANHAP(giaNhap);
            chiTietHoaDon.setGIABAN(giaBan);
            chiTietHoaDon.setSOLUONG(soLuong);
            chiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);
        }
    }
    
     
       
    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        // TODO add your handling code here:
        
        int rows = defaultTableModel.getRowCount();
        for(int a = rows - 1; a >=0; a--)
        {
            defaultTableModel.removeRow(a); 
        }
        i=0;
        
        khachHangComboBox.setSelectedIndex(-1);
        tongSoLuongTextField.setText("0");
        tongTienTextField.setText("0");
        khachTraTextField.setText("0");
        tienThuaTextField.setText("0");
        ghiChuTextField.setText("");
    }//GEN-LAST:event_refreshButtonMouseClicked

    private void timSanPhamTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_timSanPhamTextFieldCaretUpdate
        // TODO add your handling code here:
         // TODO add your handling code here:
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement("select MASP,TENSP,LOAISP,GIABAN,SOLUONG from sanpham where TENSP like N'%"+timSanPhamTextField.getText()+"%' or MASP like N'%"+timSanPhamTextField.getText()+"%'");
            ResultSet Rs = Pst.executeQuery();
           
            defaultTableModel2.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"), Rs.getString("LOAISP"), Rs.getDouble("GIABAN"), Rs.getInt("SOLUONG")};
                defaultTableModel2.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_timSanPhamTextFieldCaretUpdate

    private void themKhachHangButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themKhachHangButtonMouseClicked
        // TODO add your handling code here:
        new AddKhachHang().setVisible(true);
        String keyword = "";
        KhachHangDao.DoDuLieuVaoCBBKhachHang(khachHangComboBox, keyword);
    }//GEN-LAST:event_themKhachHangButtonMouseClicked

    private void timKhachHangTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timKhachHangTextFieldKeyReleased
        // TODO add your handling code here:
        String keyword = timKhachHangTextField.getText();
        KhachHangDao.DoDuLieuVaoCBBKhachHang(khachHangComboBox, keyword);
    }//GEN-LAST:event_timKhachHangTextFieldKeyReleased

    private void khachHangComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_khachHangComboBoxItemStateChanged

    }//GEN-LAST:event_khachHangComboBoxItemStateChanged

    private void OutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutBtnMouseClicked
        // TODO add your handling code here:
        new FormDangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_OutBtnMouseClicked

    private void khachHangComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khachHangComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_khachHangComboBoxActionPerformed

    private void sanPhamTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanPhamTableMouseClicked
        // TODO add your handling code here:
        try {      
            int index = sanPhamTable.getSelectedRow();
            String MASP = defaultTableModel2.getValueAt(index, 0).toString();
            int SOLUONG = Integer.valueOf(defaultTableModel2.getValueAt(index,4).toString());
            //tong = 1 * Double.valueOf(model.getValueAt(Myindex, 3).toString());
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement("select MASP,TENSP,LOAISP,GIABAN,SOLUONG from sanpham where MASP='"+MASP+"'");
            ResultSet Rs = Pst.executeQuery();
            if (SOLUONG<=0) JOptionPane.showMessageDialog(this, "Sản phẩm đã hết");
            else{
            int g=defaultTableModel.getRowCount();
            if (g==0){
                while (Rs.next()) {
                Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"),Rs.getString("LOAISP"),Rs.getDouble("GIABAN"),1,Rs.getDouble("GIABAN")};
                defaultTableModel.addRow(objList); 
                i++;
                }    
            }
            else
            {
                int count=0;
                for (int j=0;j<g;j++)
                {
                    String ss1=String.valueOf(defaultTableModel.getValueAt(j, 0));
                    String ss2=String.valueOf(defaultTableModel2.getValueAt(index,0));
                   
                    if (ss1.equals(ss2))
                    {
                        count++;
                        break;
                    }    
                }
                if (count!=0)
                {
                    JOptionPane.showMessageDialog(this,"Sản Phẩm đã có trong Hoá Đơn");
                }
                else
                {
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"),Rs.getString("LOAISP"),Rs.getDouble("GIABAN"),1,Rs.getDouble("GIABAN")};
                    defaultTableModel.addRow(objList); 
                    i++;
                    } 
                }   
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE,null,ex);
        }
        hienThiTongSoLuongVaTongTien();
    }//GEN-LAST:event_sanPhamTableMouseClicked

    private void hoaDonTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_hoaDonTablePropertyChange
        // TODO add your handling code here:
            for (int j=0;j<i;j++){
                if (!isNumeric(defaultTableModel.getValueAt(j, 4).toString()))
                {
                    JOptionPane.showMessageDialog(this, "Số Lượng phải nhập định dạng số");
                    defaultTableModel.setValueAt(1, j, 4);
                }
                else if (!IsInt.isStringInt(defaultTableModel.getValueAt(j, 4).toString()))
                {
                    JOptionPane.showMessageDialog(this, "Số Lượng phải là số nguyên");
                    defaultTableModel.setValueAt(1, j, 4);
                }
                else if (Integer.valueOf(defaultTableModel.getValueAt(j, 4).toString())<=0)
                {
                    JOptionPane.showMessageDialog(this, "Số Lượng phải lớn hơn 0");
                    defaultTableModel.setValueAt(1, j, 4);
                }
            }      
                       
            for (int j = 0; j < i; j++) {
                int soLuongMua = Integer.valueOf(defaultTableModel.getValueAt(j, 4).toString());
                String MASP = defaultTableModel.getValueAt(j, 0).toString();               
                SanPham sanPham = new SanPham();
                SanPhamService sanPhamService = new SanPhamService();
                sanPham = sanPhamService.getSanPhamByMASP(MASP);
                int soLuongSanPhamCu = sanPham.getSOLUONG();              
                if (soLuongMua > soLuongSanPhamCu) {
                    JOptionPane.showMessageDialog(this, "Số lượng "+defaultTableModel.getValueAt(j, 1)+" trong kho còn là: "+soLuongSanPhamCu);
                    soLuongMua=soLuongSanPhamCu;
                    defaultTableModel.setValueAt(soLuongMua, j, 4);
                    double thanhTien = soLuongMua * Double.valueOf(defaultTableModel.getValueAt(j, 3).toString());
                    defaultTableModel.setValueAt(thanhTien, j, 5);
                }else{
                    double thanhTien = soLuongMua * Double.valueOf(defaultTableModel.getValueAt(j, 3).toString());
                    defaultTableModel.setValueAt(thanhTien, j, 5);
                }               
            }
         
            hienThiTongSoLuongVaTongTien();
            PrintHD();
    }//GEN-LAST:event_hoaDonTablePropertyChange

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
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBanHang().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OutBtn;
    private javax.swing.JLabel ddmmyyLabel;
    private javax.swing.JTextArea ghiChuTextField;
    private javax.swing.JLabel gioLabel;
    private javax.swing.JLabel hhmmssLabel;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> khachHangComboBox;
    private javax.swing.JLabel khachTraLabel;
    private javax.swing.JTextField khachTraTextField;
    private javax.swing.JLabel maNVLabel;
    private javax.swing.JLabel maNVLabel2;
    private javax.swing.JLabel ngayLabel;
    private javax.swing.JLabel nhanVienLabel;
    private javax.swing.JLabel nhanVienLabel2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable sanPhamTable;
    private javax.swing.JLabel tapHoaLabel;
    private javax.swing.JButton thanhToanButton;
    private javax.swing.JButton themKhachHangButton;
    private javax.swing.JLabel tienThuaLabel;
    private javax.swing.JTextField tienThuaTextField;
    private javax.swing.JLabel timKhachHangLabel;
    private javax.swing.JTextField timKhachHangTextField;
    private javax.swing.JLabel timSanPhamLabel;
    private javax.swing.JTextField timSanPhamTextField;
    private javax.swing.JLabel tongSoLuongLabel;
    private javax.swing.JTextField tongSoLuongTextField;
    private javax.swing.JLabel tongTienLabel;
    private javax.swing.JTextField tongTienTextField;
    private javax.swing.JButton xoaButton;
    // End of variables declaration//GEN-END:variables
}
