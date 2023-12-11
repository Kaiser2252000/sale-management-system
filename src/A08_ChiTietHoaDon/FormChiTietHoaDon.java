/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A08_ChiTietHoaDon;

import A00_Connection.JDBCConnection;
import A03_SanPham.SanPham;
import A03_SanPham.SanPhamService;
import A07_HoaDon.HoaDon;
import A07_HoaDon.HoaDonService;
import A05_KhachHang.FormKhachHang;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class FormChiTietHoaDon extends javax.swing.JFrame {

    HoaDon hoaDon = new HoaDon();
    SanPham sanPham = new SanPham();
    ChiTietHoaDon chiTietHoaDon =new ChiTietHoaDon();
    HoaDonService hoaDonService = new HoaDonService();
    SanPhamService sanPhamService = new SanPhamService();
    ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonService();
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    /**
     * Creates new form FormChiTietHoaDon
     */
    public FormChiTietHoaDon(String MAHD) {
        
        initComponents();
        
        hoaDon = hoaDonService.getHoaDonByMAHD(MAHD);
        maHDTextField.setText(hoaDon.getMAHD());
        thoiGianTextField.setText(hoaDon.getTHOIGIAN().toString());                         
        maKHTextField.setText(hoaDon.getMAKH());
        maNVTextField.setText(String.valueOf(hoaDon.getMANV()));
        tongTienTextField.setText(String.valueOf(hoaDon.getTONGTIEN()));
        ghiChuTextField.setText(hoaDon.getGHICHU());
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
        };
        //chiTietHoaDon = bllcthd.getCTHDByMaHD(MaHD);
        chiTietHoaDonTable.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã Sản Phẩm");
        defaultTableModel.addColumn("Tên Sản Phẩm");
        defaultTableModel.addColumn("Giá Bán");
        defaultTableModel.addColumn("Số Lượng");
        defaultTableModel.addColumn("Thành Tiền");
        chiTietHoaDonTable.setRowHeight(24);
        chiTietHoaDonTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        chiTietHoaDonTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        SetData(MAHD);
    }
    
    public void SetData(String MAHD){
        try {           
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst = Con.prepareStatement("select MASP,TENSP,GIABAN,SOLUONG,SOLUONG*GIABAN as THANHTIEN from chitiethoadon  where MAHD = '"+MAHD+"'");
            ResultSet Rs = Pst.executeQuery();
            defaultTableModel = (DefaultTableModel)chiTietHoaDonTable.getModel();
            defaultTableModel.setRowCount(0);
            while (Rs.next()) {
                sanPham = sanPhamService.getSanPhamByMASP(Rs.getString("MASP"));
                Object objList[] = {Rs.getString("MASP"),Rs.getString("TENSP"),Rs.getDouble("GIABAN"),Rs.getInt("SOLUONG"),Rs.getDouble("THANHTIEN")};
                defaultTableModel.addRow(objList);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormKhachHang.class.getName()).log(Level.SEVERE,null,ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chiTietHoaDonLabel = new javax.swing.JLabel();
        dongButton = new javax.swing.JButton();
        maHDTextField = new javax.swing.JTextField();
        maHDLabel = new javax.swing.JLabel();
        thoiGianLabel = new javax.swing.JLabel();
        thoiGianTextField = new javax.swing.JTextField();
        maKHLabel = new javax.swing.JLabel();
        maKHTextField = new javax.swing.JTextField();
        maNVLabel = new javax.swing.JLabel();
        maNVTextField = new javax.swing.JTextField();
        tongTienLabel = new javax.swing.JLabel();
        tongTienTextField = new javax.swing.JTextField();
        ghiChuLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ghiChuTextField = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        chiTietHoaDonTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel2.setBackground(new java.awt.Color(102, 255, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 80));

        chiTietHoaDonLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        chiTietHoaDonLabel.setForeground(new java.awt.Color(51, 51, 51));
        chiTietHoaDonLabel.setText("Chi Tiết Hóa Đơn");

        dongButton.setBackground(new java.awt.Color(0, 255, 0));
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
        dongButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dongButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(chiTietHoaDonLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chiTietHoaDonLabel))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        maHDTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maHDTextField.setForeground(new java.awt.Color(204, 0, 153));
        maHDTextField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        maHDTextField.setEnabled(false);
        maHDTextField.setPreferredSize(new java.awt.Dimension(200, 40));

        maHDLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maHDLabel.setText("Mã Hóa Đơn");
        maHDLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        thoiGianLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        thoiGianLabel.setText("Thời Gian");
        thoiGianLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        thoiGianTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        thoiGianTextField.setForeground(new java.awt.Color(204, 0, 153));
        thoiGianTextField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        thoiGianTextField.setEnabled(false);
        thoiGianTextField.setPreferredSize(new java.awt.Dimension(200, 40));

        maKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maKHLabel.setText("Mã Khách Hàng");
        maKHLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        maKHTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maKHTextField.setForeground(new java.awt.Color(204, 0, 153));
        maKHTextField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        maKHTextField.setEnabled(false);
        maKHTextField.setPreferredSize(new java.awt.Dimension(200, 40));

        maNVLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maNVLabel.setText("Mã Nhân Viên");
        maNVLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        maNVTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maNVTextField.setForeground(new java.awt.Color(204, 0, 153));
        maNVTextField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        maNVTextField.setEnabled(false);
        maNVTextField.setPreferredSize(new java.awt.Dimension(200, 40));

        tongTienLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongTienLabel.setText("Tổng Tiền");
        tongTienLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        tongTienTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tongTienTextField.setForeground(new java.awt.Color(204, 0, 153));
        tongTienTextField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tongTienTextField.setEnabled(false);
        tongTienTextField.setPreferredSize(new java.awt.Dimension(200, 40));

        ghiChuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuLabel.setText("Ghi Chú");

        ghiChuTextField.setColumns(20);
        ghiChuTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuTextField.setForeground(new java.awt.Color(204, 0, 153));
        ghiChuTextField.setRows(5);
        ghiChuTextField.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        ghiChuTextField.setEnabled(false);
        jScrollPane1.setViewportView(ghiChuTextField);

        chiTietHoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
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
        chiTietHoaDonTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(chiTietHoaDonTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(maHDLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(thoiGianLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ghiChuLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(thoiGianTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tongTienTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maHDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(maNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(maKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(maNVTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane1))
                        .addGap(75, 75, 75))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maHDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maHDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(thoiGianLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tongTienTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(thoiGianTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maNVTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ghiChuLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dongButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dongButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dongButtonKeyPressed

    private void dongButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dongButtonMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_dongButtonMouseClicked

    private void dongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dongButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_dongButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chiTietHoaDonLabel;
    private javax.swing.JTable chiTietHoaDonTable;
    private javax.swing.JButton dongButton;
    private javax.swing.JLabel ghiChuLabel;
    private javax.swing.JTextArea ghiChuTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel maHDLabel;
    private javax.swing.JTextField maHDTextField;
    private javax.swing.JLabel maKHLabel;
    private javax.swing.JTextField maKHTextField;
    private javax.swing.JLabel maNVLabel;
    private javax.swing.JTextField maNVTextField;
    private javax.swing.JLabel thoiGianLabel;
    private javax.swing.JTextField thoiGianTextField;
    private javax.swing.JLabel tongTienLabel;
    private javax.swing.JTextField tongTienTextField;
    // End of variables declaration//GEN-END:variables
}