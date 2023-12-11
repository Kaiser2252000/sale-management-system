/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A09_DonNhapHang;

import A00_Connection.JDBCConnection;
import A10_ChiTietDonNhapHang.ChiTietDonNhapHang;
import A00_Connection.MyCombobox;
import A00_Support.IsInt;
import static A00_Support.IsNumeric.isNumeric;
import A03_SanPham.SanPham;
import A03_SanPham.SanPhamService;
import A06_NhaCungCap.AddNhaCungCap;
import A06_NhaCungCap.NhaCungCapDao;
import A00_Threading.ClockThread;
import A00_Threading.DateThread;
import A10_ChiTietDonNhapHang.ChiTietDonNhapHangService;
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
public class AddDonNhapHang extends javax.swing.JFrame {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    DefaultTableModel defaultTableModel2 = new DefaultTableModel();
    AddDonNhapHang addDonNhapHang;  
    DonNhapHangService donNhapHangService = new DonNhapHangService();
    public static int row;
    public static String gio;
    public static String ngay;
    public static String[] tenSP;
    public static String[] giaNhap;
    public static String[] soLuong;
    public static String[] thanhTien;
    public static String tongTien;
    public static String maDonNhapHang;
    public static String tenNCC;
    
    public void initClock(){
        ClockThread th = new ClockThread(hhmmssLabel);
        th.start();
    }
    public void initDate(){
        DateThread th = new DateThread(ddmmyyLabel);
        th.start();
    }
    
    public AddDonNhapHang() {
        addDonNhapHang = this;
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
        donNhapHangTable.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã Sản Phẩm");
        defaultTableModel.addColumn("Tên Sản Phẩm");
        defaultTableModel.addColumn("Loại Sản Phẩm");
        defaultTableModel.addColumn("Giá Nhập");
        defaultTableModel.addColumn("Số Lượng");
        defaultTableModel.addColumn("Thành Tiền"); 
        donNhapHangTable.setRowHeight(24);
        donNhapHangTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        donNhapHangTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        
        sanPhamTable.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("Mã Sản Phẩm");
        defaultTableModel2.addColumn("Tên Sản Phẩm");
        defaultTableModel2.addColumn("Loại Sản Phẩm");
        defaultTableModel2.addColumn("Giá Nhập");
        defaultTableModel2.addColumn("Số Lượng");
        sanPhamTable.setRowHeight(24);
        sanPhamTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        sanPhamTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        setSanPhamTable();
        //Update();
        //PrintHD();
    }
    
    private void setSanPhamTable(){
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement
            ("select MASP,TENSP,LOAISP,GIANHAP,SOLUONG "
            + "from sanpham");
            ResultSet Rs = Pst.executeQuery();
            
            defaultTableModel2.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"), Rs.getString("LOAISP"), Rs.getString("GIANHAP"), Rs.getInt("SOLUONG")};
                defaultTableModel2.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDonNhapHang.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void PrintHD(){
        gio = hhmmssLabel.getText();
        ngay = ddmmyyLabel.getText();
        row = defaultTableModel.getRowCount();
        tenSP = new String[row];
        giaNhap = new String[row];
        soLuong = new String[row];
        thanhTien = new String[row];
        for (int j = 0; j < row; j++) {
            tenSP[j] = String.valueOf(defaultTableModel.getValueAt(j, 1));
            giaNhap[j] = String.valueOf(defaultTableModel.getValueAt(j, 3)); 
            soLuong[j] = String.valueOf(defaultTableModel.getValueAt(j, 4));          
            thanhTien[j] = String.valueOf(defaultTableModel.getValueAt(j, 5));
        }
        tongTien = tongTienTextField.getText();
 
        if (nhaCungCapComboBox.getSelectedItem() == null) {
            tenNCC = "Nhà Cung Cấp Lẻ";
        }else{
            tenNCC = nhaCungCapComboBox.getSelectedItem().toString();
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
                tongSoLuongTextField.setText(tongSoLuong+"");
                tongTienTextField.setText(tongTien+"");
            }
        }
        tienThuaTextField.setText(String.valueOf(Double.valueOf(traNCCTextField.getText())-tongTien));
    }
    
   
String maSP;
int soLuongMoi;  
   /* private void Update(){
        //String upMaHH;
        //int upSOL;
        model = (DefaultTableModel)HoaDonTbl.getModel();   
        for (int j = 0; j < model.getRowCount(); j++) {
            
            upMaHH = String.valueOf(model.getValueAt(j, 1).toString());
            
            try {                              
                               
                Connection Con = JDBCConnection.getJDBCConnection();
                PreparedStatement Pst = Con.prepareStatement("select SOL from hang_hoa where MAHH='"+upMaHH+"'");
                ResultSet Rs = Pst.executeQuery();
                upSOL = Integer.valueOf(Rs.getString("SOL")) - Integer.valueOf(model.getValueAt(j, 3).toString());
                
            } catch (SQLException ex) {
                Logger.getLogger(AddDonNhapHang.class.getName()).log(Level.SEVERE,null,ex);
        }
            
            
            try {
                Connection Con = JDBCConnection.getJDBCConnection();
                String Query = "update hang_hoa set SOL="+upSOL+" where MAHH='"+upMaHH+"'";
                Statement Add = Con.createStatement();
                Add.executeUpdate(Query);
                //JOptionPane.showMessageDialog(this, "Product Update");
                SelectHang_hoa();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }*/
    
   

   public void updateSoLuong(){
                  
                            
            for (int j = 0; j < i; j++) {
                int soLuongNhap = Integer.valueOf(defaultTableModel.getValueAt(j, 4).toString());
                String MASP = defaultTableModel.getValueAt(j, 0).toString(); 
                SanPham sanPham = new SanPham();
                SanPhamService sanPhamService = new SanPhamService();
                sanPham = sanPhamService.getSanPhamByMASP(MASP);
                int soLuongSanPhamCu = sanPham.getSOLUONG();
                int soLuongSanPhamMoi = soLuongSanPhamCu + soLuongNhap;
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
        timNCCLabel = new javax.swing.JLabel();
        timNCCTextField = new javax.swing.JTextField();
        tongSoLuongLabel = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        traNCCLabel = new javax.swing.JLabel();
        tienThuaLabel = new javax.swing.JLabel();
        ghiChuLabel = new javax.swing.JLabel();
        tongSoLuongTextField = new javax.swing.JTextField();
        tongTienTextField = new javax.swing.JTextField();
        traNCCTextField = new javax.swing.JTextField();
        tienThuaTextField = new javax.swing.JTextField();
        thanhToanButton = new javax.swing.JButton();
        hhmmssLabel = new javax.swing.JLabel();
        ddmmyyLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ghiChuTextField = new javax.swing.JTextArea();
        themNCCButton = new javax.swing.JButton();
        nhaCungCapLabel = new javax.swing.JLabel();
        nhaCungCapComboBox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        dongButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sanPhamTable = new javax.swing.JTable();
        xoaButton = new javax.swing.JButton();
        RefreshBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        donNhapHangTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1600, 1000));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        jPanel1.setBackground(new java.awt.Color(102, 255, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(344, 900));

        ngayLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ngayLabel.setText("Ngày:");

        gioLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        gioLabel.setText("Giờ:");

        timNCCLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timNCCLabel.setText("Tìm Nhà Cung Cấp");
        timNCCLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        timNCCTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timNCCTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        timNCCTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                timNCCTextFieldCaretUpdate(evt);
            }
        });
        timNCCTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timNCCTextFieldActionPerformed(evt);
            }
        });
        timNCCTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timNCCTextFieldKeyReleased(evt);
            }
        });

        tongSoLuongLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongSoLuongLabel.setText("Tổng Số Lượng");
        tongSoLuongLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        tongTienLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongTienLabel.setText("Tổng Tiền");
        tongTienLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        traNCCLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        traNCCLabel.setText("Trả Nhà Cung Cấp");
        traNCCLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        tienThuaLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tienThuaLabel.setText("Tiền Thừa");
        tienThuaLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        ghiChuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuLabel.setText("Ghi Chú");
        ghiChuLabel.setPreferredSize(new java.awt.Dimension(150, 40));

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
        tongTienTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tongTienTextField.setForeground(new java.awt.Color(255, 0, 0));
        tongTienTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tongTienTextField.setText("0");
        tongTienTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        tongTienTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tongTienTextFieldActionPerformed(evt);
            }
        });

        traNCCTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        traNCCTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        traNCCTextField.setText("0");
        traNCCTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        traNCCTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                traNCCTextFieldCaretUpdate(evt);
            }
        });

        tienThuaTextField.setEditable(false);
        tienThuaTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tienThuaTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tienThuaTextField.setText("0");
        tienThuaTextField.setPreferredSize(new java.awt.Dimension(150, 40));

        thanhToanButton.setBackground(new java.awt.Color(51, 255, 0));
        thanhToanButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
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

        ghiChuTextField.setColumns(20);
        ghiChuTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuTextField.setRows(5);
        jScrollPane4.setViewportView(ghiChuTextField);

        themNCCButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        themNCCButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Add.png"))); // NOI18N
        themNCCButton.setText("Thêm");
        themNCCButton.setPreferredSize(new java.awt.Dimension(120, 40));
        themNCCButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themNCCButtonMouseClicked(evt);
            }
        });

        nhaCungCapLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nhaCungCapLabel.setText("Nhà Cung Cấp");
        nhaCungCapLabel.setPreferredSize(new java.awt.Dimension(150, 40));

        nhaCungCapComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nhaCungCapComboBox.setMaximumRowCount(20);
        nhaCungCapComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        nhaCungCapComboBox.setSelectedIndex(-1);
        nhaCungCapComboBox.setPreferredSize(new java.awt.Dimension(150, 40));
        nhaCungCapComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nhaCungCapComboBoxItemStateChanged(evt);
            }
        });
        nhaCungCapComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhaCungCapComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ngayLabel)
                        .addGap(18, 18, 18)
                        .addComponent(ddmmyyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gioLabel)
                        .addGap(4, 4, 4)
                        .addComponent(hhmmssLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(thanhToanButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(timNCCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(themNCCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nhaCungCapComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(timNCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(nhaCungCapLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tongSoLuongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tongSoLuongTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tongTienTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(traNCCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(traNCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tienThuaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tienThuaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ghiChuLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayLabel)
                    .addComponent(hhmmssLabel)
                    .addComponent(ddmmyyLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timNCCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timNCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(themNCCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nhaCungCapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhaCungCapComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tongSoLuongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongSoLuongTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongTienTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(traNCCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(traNCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tienThuaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tienThuaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ghiChuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(thanhToanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 0));

        dongButton.setBackground(new java.awt.Color(255, 255, 255));
        dongButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dongButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Delete.png"))); // NOI18N
        dongButton.setText("Đóng");
        dongButton.setPreferredSize(new java.awt.Dimension(120, 40));
        dongButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dongButtonMouseClicked(evt);
            }
        });
        dongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dongButtonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Thêm Đơn Nhập Hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(456, 456, 456)
                .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(dongButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
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
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        xoaButton.setBackground(new java.awt.Color(0, 255, 0));
        xoaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        xoaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/icons8-delete-bin-32.png"))); // NOI18N
        xoaButton.setText("Xóa");
        xoaButton.setPreferredSize(new java.awt.Dimension(120, 40));
        xoaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xoaButtonMouseClicked(evt);
            }
        });
        xoaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaButtonActionPerformed(evt);
            }
        });

        RefreshBtn.setBackground(new java.awt.Color(0, 255, 0));
        RefreshBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RefreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Refresh.png"))); // NOI18N
        RefreshBtn.setText("Refresh");
        RefreshBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        RefreshBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RefreshBtnMouseClicked(evt);
            }
        });

        donNhapHangTable.setModel(new javax.swing.table.DefaultTableModel(
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
        donNhapHangTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        donNhapHangTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                donNhapHangTablePropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(donNhapHangTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(timSanPhamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(timSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                        .addComponent(RefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(xoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, 897, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(timSanPhamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(timSanPhamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tongSoLuongTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongSoLuongTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tongSoLuongTextFieldActionPerformed

    private void timNCCTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timNCCTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timNCCTextFieldActionPerformed

    private void tongTienTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongTienTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tongTienTextFieldActionPerformed
int idHH;
int i=0;
int sol;
Double tong;

    private void xoaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaButtonMouseClicked
        // TODO add your handling code here:
        int index = donNhapHangTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(AddDonNhapHang.this, "Vui lòng chọn Sản Phẩm cần xóa","Lỗi", JOptionPane.ERROR_MESSAGE);
        }else{
        defaultTableModel.removeRow(index);
        i--;
        defaultTableModel.setRowCount(i);
        /*for (int j = 1; j <= i; j++) {
            defaultTableModel.setValueAt(j, j-1, 0);
        }*/
        hienThiTongSoLuongVaTongTien();
        }
    }//GEN-LAST:event_xoaButtonMouseClicked

    private void traNCCTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_traNCCTextFieldCaretUpdate
        // TODO add your handling code here:
        Double traNCC;
        Double tongTien;
        Double tienThua;        
        if (traNCCTextField.getText().isEmpty()) {
            tienThuaTextField.setText("0");
        }else{
            traNCC = Double.valueOf(traNCCTextField.getText());
            tongTien = Double.valueOf(tongTienTextField.getText());
            tienThua = traNCC - tongTien;
            tienThuaTextField.setText(tienThua+"");
        }
    }//GEN-LAST:event_traNCCTextFieldCaretUpdate

    private void thanhToanButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thanhToanButtonMouseClicked
        // TODO add your handling code here:
         for (int j = 0; j < i; j++) {
                int soLuongNhap = Integer.valueOf(defaultTableModel.getValueAt(j, 4).toString());
           
                double thanhTien= soLuongNhap * Double.valueOf(defaultTableModel.getValueAt(j, 3).toString());
                defaultTableModel.setValueAt(thanhTien, j, 5);
                           
            }

        hienThiTongSoLuongVaTongTien();
        
        
        if (defaultTableModel.getRowCount()==0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
        }
        else{
        DonNhapHang donNhapHang = new DonNhapHang();
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        maDonNhapHang= "DNH"+String.valueOf(formatter.format(date));
        donNhapHang.setMADNH("DNH"+String.valueOf(formatter.format(date)));
               
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        donNhapHang.setTHOIGIAN(sqlDate);
            
        //hoaDon.setMaKhachHang(2); //// Chưa code 
        MyCombobox cbb = (MyCombobox) nhaCungCapComboBox.getSelectedItem();
        if (cbb == null) {
            donNhapHang.setMANCC("0");
        }else{
            donNhapHang.setMANCC(cbb.Value.toString());
        }               
        
        donNhapHang.setTONGTIEN(Double.valueOf(tongTienTextField.getText()));       
        donNhapHang.setGHICHU(ghiChuTextField.getText());
        
        //Thêm hóa đơn vào database
        //SetHoaDon.ThemHoaDon(hoaDon);
        donNhapHangService.addDonNhapHang(donNhapHang);
        
        //Set MaNH mới thêm dc vào Nhập hàng nhapHang
       //nhapHang.setMaNH(DonNhapHangDao.GetMaNHBySoNH(nhapHang.getSoNH()));
        
        //Thêm tất cả chi tiết hóa đơn theo MaHD vừa thêm
        addChiTietDonNhapHang(donNhapHang.getMADNH());
        
        //gọi hàm xuất hóa đơn
        //XuatHoaDon(hd.getMaHD());
        PrintHD();
        new PrintDonNhapHang().setVisible(true);
        
        updateSoLuong();
        //tạo lại hóa đơn mới
        
        
        // khách hàng về null
        nhaCungCapComboBox.setSelectedIndex(-1);
        }
        //Update Kho
        //UpdateKho();
    }//GEN-LAST:event_thanhToanButtonMouseClicked

   
    
    
    
        /*Hàm thêm tất cả các dòng trong Table vào bảng chi tiết hóa đơn*/
    private void addChiTietDonNhapHang(String MADNH) {
        for (int j = 0; j < donNhapHangTable.getRowCount(); j++) {
            String maDNH = MADNH;
            String maSP = donNhapHangTable.getValueAt(j, 0).toString();
            Double giaNhap = Double.valueOf(donNhapHangTable.getValueAt(j, 3).toString());
            int soLuong = Integer.valueOf(donNhapHangTable.getValueAt(j, 4).toString());
            ChiTietDonNhapHang chiTietDonNhapHang = new ChiTietDonNhapHang();
            ChiTietDonNhapHangService chiTietDonNhapHangService = new ChiTietDonNhapHangService();
            chiTietDonNhapHang.setMADNH(maDNH);
            chiTietDonNhapHang.setMASP(maSP);
            chiTietDonNhapHang.setGIANHAP(giaNhap);
            chiTietDonNhapHang.setSOLUONG(soLuong);
            chiTietDonNhapHangService.addChiTietDonNhapHang(chiTietDonNhapHang);
        }
    }
    
       /*private void ThemChiTietNhapHang(int MaNH, int IDHH, int SoLuong, Double GiaV,
            Double ThanhTien, String GhiChu) {

        ChiTietDonNhapHang ctnh = new ChiTietDonNhapHang();
        ctnh.setMaNH(MaNH);
        ctnh.setIDHH(IDHH);
        ctnh.setSoL(SoLuong);
        ctnh.setGiaV(ThanhTien); 
        ctnh.setThanhTien(ThanhTien); 
        ctnh.setGhiChu(GhiChu); 
        

        ChiTietDonNhapHangDao.addCTNH(ctnh);
    }*/
       
    private void RefreshBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RefreshBtnMouseClicked
        // TODO add your handling code here:
        int rows = defaultTableModel.getRowCount();
        for(int a = rows - 1; a >=0; a--)
        {
            defaultTableModel.removeRow(a); 
        }
        i=0;
        
        nhaCungCapComboBox.setSelectedIndex(-1);
        tongSoLuongTextField.setText("0");
        tongTienTextField.setText("0");
        traNCCTextField.setText("0");
        tienThuaTextField.setText("0");
        ghiChuTextField.setText("");
    }//GEN-LAST:event_RefreshBtnMouseClicked

    private void themNCCButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themNCCButtonMouseClicked
        // TODO add your handling code here:
        new AddNhaCungCap().setVisible(true);
        
        String keyword = "";
        NhaCungCapDao.DoDuLieuVaoCBBNhaCungCap(nhaCungCapComboBox, keyword);
    }//GEN-LAST:event_themNCCButtonMouseClicked

    private void timNCCTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timNCCTextFieldKeyReleased
        // TODO add your handling code here:
        String keyword = timNCCTextField.getText();
       NhaCungCapDao.DoDuLieuVaoCBBNhaCungCap(nhaCungCapComboBox, keyword);
    }//GEN-LAST:event_timNCCTextFieldKeyReleased

    private void nhaCungCapComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nhaCungCapComboBoxItemStateChanged
        // TODO add your handling code here:
        /*if (cbbKH.getItemCount() > 0) {
            MyCombobox cbb = (MyCombobox) cbbKH.getSelectedItem();
            KhachHang kh = SetKhachHang.GetKHByMaKH(cbb.Value.toString());
            if (kh != null) {
                txtDiaChi.setText(kh.getDiaChi());
                txtDienThoai.setText(kh.getSoDienThoai());

            }
        }*/
    }//GEN-LAST:event_nhaCungCapComboBoxItemStateChanged

    private void dongButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dongButtonMouseClicked
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_dongButtonMouseClicked

    private void nhaCungCapComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhaCungCapComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhaCungCapComboBoxActionPerformed

    private void timNCCTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_timNCCTextFieldCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_timNCCTextFieldCaretUpdate

    private void xoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xoaButtonActionPerformed

    private void dongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dongButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_dongButtonActionPerformed

    private void sanPhamTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanPhamTableMouseClicked
        // TODO add your handling code here:
        try {      
            int index = sanPhamTable.getSelectedRow();
            String MASP = defaultTableModel2.getValueAt(index, 0).toString();
            //tong = 1 * Double.valueOf(model.getValueAt(Myindex, 3).toString());
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement("select MASP,TENSP,LOAISP,GIANHAP,SOLUONG from sanpham where MASP='"+MASP+"'");
            ResultSet Rs = Pst.executeQuery();

            int g=defaultTableModel.getRowCount();
            if (g==0){
                while (Rs.next()) {
                Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"),Rs.getString("LOAISP"),Rs.getDouble("GIANHAP"),1,Rs.getDouble("GIANHAP")};
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
                    JOptionPane.showMessageDialog(this,"Sản Phẩm đã tồn tại trong Đơn Nhập Hàng");
                }
                else
                {
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"),Rs.getString("LOAISP"),Rs.getDouble("GIANHAP"),1,Rs.getDouble("GIANHAP")};
                    defaultTableModel.addRow(objList); 
                    i++;
                    } 
                }   
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDonNhapHang.class.getName()).log(Level.SEVERE,null,ex);
        }
        hienThiTongSoLuongVaTongTien();
    }//GEN-LAST:event_sanPhamTableMouseClicked

    private void timSanPhamTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_timSanPhamTextFieldCaretUpdate
        // TODO add your handling code here:
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement("select MASP,TENSP,LOAISP,GIANHAP,SOLUONG from sanpham where TENSP like N'%"+timSanPhamTextField.getText()+"%' or MASP like N'%"+timSanPhamTextField.getText()+"%'");
            ResultSet Rs = Pst.executeQuery();
           
            defaultTableModel2.setRowCount(0);
            while (Rs.next()) {
                Object objList[] = {Rs.getString("MASP"), Rs.getString("TENSP"), Rs.getString("LOAISP"), Rs.getDouble("GIANHAP"), Rs.getInt("SOLUONG")};
                defaultTableModel2.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDonNhapHang.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_timSanPhamTextFieldCaretUpdate

    private void donNhapHangTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_donNhapHangTablePropertyChange
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
                int soLuongNhap = Integer.valueOf(defaultTableModel.getValueAt(j, 4).toString());
                double thanhTien = soLuongNhap * Double.valueOf(defaultTableModel.getValueAt(j, 3).toString());
                defaultTableModel.setValueAt(thanhTien, j, 5);
                            
            }
         
            hienThiTongSoLuongVaTongTien();
            PrintHD();
    }//GEN-LAST:event_donNhapHangTablePropertyChange

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
            java.util.logging.Logger.getLogger(AddDonNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDonNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDonNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDonNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDonNhapHang().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RefreshBtn;
    private javax.swing.JLabel ddmmyyLabel;
    private javax.swing.JTable donNhapHangTable;
    private javax.swing.JButton dongButton;
    private javax.swing.JLabel ghiChuLabel;
    private javax.swing.JTextArea ghiChuTextField;
    private javax.swing.JLabel gioLabel;
    private javax.swing.JLabel hhmmssLabel;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel ngayLabel;
    private javax.swing.JComboBox<String> nhaCungCapComboBox;
    private javax.swing.JLabel nhaCungCapLabel;
    private javax.swing.JTable sanPhamTable;
    private javax.swing.JButton thanhToanButton;
    private javax.swing.JButton themNCCButton;
    private javax.swing.JLabel tienThuaLabel;
    private javax.swing.JTextField tienThuaTextField;
    private javax.swing.JLabel timNCCLabel;
    private javax.swing.JTextField timNCCTextField;
    private javax.swing.JLabel timSanPhamLabel;
    private javax.swing.JTextField timSanPhamTextField;
    private javax.swing.JLabel tongSoLuongLabel;
    private javax.swing.JTextField tongSoLuongTextField;
    private javax.swing.JLabel tongTienLabel;
    private javax.swing.JTextField tongTienTextField;
    private javax.swing.JLabel traNCCLabel;
    private javax.swing.JTextField traNCCTextField;
    private javax.swing.JButton xoaButton;
    // End of variables declaration//GEN-END:variables
}
