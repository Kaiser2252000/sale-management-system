/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A07_HoaDon;



import A00_Connection.JDBCConnection;
import static A00_Support.DateValidation.dateValidation;
import A01_DangNhap.FormDangNhap;
import A11_BaoCao.FormBaoCao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import A03_SanPham.FormSanPham;
import A05_KhachHang.FormKhachHang;
import A06_NhaCungCap.FormNhaCungCap;
import A04_NhanVien.FormNhanVien;
import A08_ChiTietHoaDon.ChiTietHoaDonService;
import A09_DonNhapHang.FormDonNhapHang;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;


/**
 *
 * @author ADMIN
 */
public class FormHoaDon extends javax.swing.JFrame {
    HoaDonService hoaDonService;
    ChiTietHoaDonService chiTietHoaDonService;
    DefaultTableModel defaultTableModel;

    /**
     * Creates new form HangHoa
     */
    public FormHoaDon() {
        initComponents();
        hoaDonService = new HoaDonService();
        chiTietHoaDonService = new ChiTietHoaDonService();
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        // Đổ dữ liệu vào jtable  
        hoaDonTable.setModel(defaultTableModel);
        
        defaultTableModel.addColumn("Mã Hóa Đơn");
        defaultTableModel.addColumn("Thời Gian");
        defaultTableModel.addColumn("Mã Khách Hàng");
        defaultTableModel.addColumn("Mã Nhân Viên");
        defaultTableModel.addColumn("Tổng Tiền");
        defaultTableModel.addColumn("Ghi Chú");
        hoaDonTable.setRowHeight(24);
        hoaDonTable.setFont(new Font("Segoe UI",Font.PLAIN,18));
        hoaDonTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,18));
        setTableData(hoaDonService.getAllHoaDons());
       //Douple click
        hoaDonTable.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me) 
            {
                if (me.getClickCount() == 2) 
                {     // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    String MAHD = String.valueOf(hoaDonTable.getValueAt(row, 0));
                    new A08_ChiTietHoaDon.FormChiTietHoaDon(MAHD).setVisible(true);
               //int column = target.getSelectedColumn(); // select a column
              //JOptionPane.showMessageDialog(null, HoaDonTbl.getValueAt(row, column)); // get the value of a row and column.
                }
            }
        }
        );
    }
    private void setTableData(List<HoaDon> hoaDons){
        for(HoaDon hoaDon : hoaDons){

            defaultTableModel.addRow(new Object[]{hoaDon.getMAHD(),hoaDon.getTHOIGIAN(), hoaDon.getMAKH(),hoaDon.getMANV(), hoaDon.getTONGTIEN(),
            hoaDon.getGHICHU()});       
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
        jPanel3 = new javax.swing.JPanel();
        hoaDonLabel = new javax.swing.JLabel();
        timKiemTextField = new javax.swing.JTextField();
        xoaButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        timKiemLabel = new javax.swing.JLabel();
        thoiGianTextField = new javax.swing.JLabel();
        tuLabel = new javax.swing.JLabel();
        denLabel = new javax.swing.JLabel();
        timKiemButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        sanPhamButton = new javax.swing.JButton();
        nhanVienButton = new javax.swing.JButton();
        nhapHangButton = new javax.swing.JButton();
        nhaCungCapButton = new javax.swing.JButton();
        baoCaoButton = new javax.swing.JButton();
        khachHangButton = new javax.swing.JButton();
        dangXuatButton = new javax.swing.JButton();
        hoaDonButton = new javax.swing.JButton();
        tuTextField = new javax.swing.JTextField();
        denTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1508, 668));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(1508, 668));

        hoaDonLabel.setBackground(new java.awt.Color(60, 63, 100));
        hoaDonLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        hoaDonLabel.setForeground(new java.awt.Color(51, 51, 51));
        hoaDonLabel.setText("Hóa Đơn");

        timKiemTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timKiemTextField.setPreferredSize(new java.awt.Dimension(350, 40));
        timKiemTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                timKiemTextFieldCaretUpdate(evt);
            }
        });
        timKiemTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemTextFieldActionPerformed(evt);
            }
        });

        xoaButton.setBackground(new java.awt.Color(0, 255, 0));
        xoaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        xoaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/icons8-delete-bin-32.png"))); // NOI18N
        xoaButton.setText("Xóa");
        xoaButton.setPreferredSize(new java.awt.Dimension(120, 40));
        xoaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaButtonActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(0, 255, 0));
        refreshButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Refresh.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.setPreferredSize(new java.awt.Dimension(120, 40));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        timKiemLabel.setBackground(new java.awt.Color(204, 204, 255));
        timKiemLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timKiemLabel.setText("Tìm kiếm");

        thoiGianTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        thoiGianTextField.setText("Thời gian");

        tuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tuLabel.setText("Từ");
        tuLabel.setPreferredSize(new java.awt.Dimension(40, 40));

        denLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        denLabel.setText("Đến:");
        denLabel.setPreferredSize(new java.awt.Dimension(40, 40));

        timKiemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Search.png"))); // NOI18N
        timKiemButton.setPreferredSize(new java.awt.Dimension(120, 40));
        timKiemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timKiemButtonMouseClicked(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(102, 255, 0));
        jPanel9.setPreferredSize(new java.awt.Dimension(1508, 80));

        sanPhamButton.setBackground(new java.awt.Color(102, 255, 102));
        sanPhamButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        sanPhamButton.setForeground(new java.awt.Color(51, 51, 51));
        sanPhamButton.setText("Sản Phẩm");
        sanPhamButton.setPreferredSize(new java.awt.Dimension(170, 50));
        sanPhamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanPhamButtonActionPerformed(evt);
            }
        });

        nhanVienButton.setBackground(new java.awt.Color(102, 255, 102));
        nhanVienButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nhanVienButton.setForeground(new java.awt.Color(51, 51, 51));
        nhanVienButton.setText("Nhân Viên");
        nhanVienButton.setPreferredSize(new java.awt.Dimension(170, 50));
        nhanVienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhanVienButtonActionPerformed(evt);
            }
        });

        nhapHangButton.setBackground(new java.awt.Color(102, 255, 102));
        nhapHangButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nhapHangButton.setForeground(new java.awt.Color(51, 51, 51));
        nhapHangButton.setText("Nhập Hàng");
        nhapHangButton.setPreferredSize(new java.awt.Dimension(170, 50));
        nhapHangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapHangButtonActionPerformed(evt);
            }
        });

        nhaCungCapButton.setBackground(new java.awt.Color(102, 255, 102));
        nhaCungCapButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nhaCungCapButton.setForeground(new java.awt.Color(51, 51, 51));
        nhaCungCapButton.setText("Nhà Cung Cấp");
        nhaCungCapButton.setPreferredSize(new java.awt.Dimension(170, 50));
        nhaCungCapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhaCungCapButtonActionPerformed(evt);
            }
        });

        baoCaoButton.setBackground(new java.awt.Color(102, 255, 102));
        baoCaoButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        baoCaoButton.setForeground(new java.awt.Color(51, 51, 51));
        baoCaoButton.setText("Báo Cáo");
        baoCaoButton.setPreferredSize(new java.awt.Dimension(170, 50));
        baoCaoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baoCaoButtonActionPerformed(evt);
            }
        });

        khachHangButton.setBackground(new java.awt.Color(102, 255, 102));
        khachHangButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        khachHangButton.setForeground(new java.awt.Color(51, 51, 51));
        khachHangButton.setText("Khách Hàng");
        khachHangButton.setPreferredSize(new java.awt.Dimension(170, 50));
        khachHangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khachHangButtonActionPerformed(evt);
            }
        });

        dangXuatButton.setBackground(new java.awt.Color(102, 255, 102));
        dangXuatButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        dangXuatButton.setForeground(new java.awt.Color(51, 51, 51));
        dangXuatButton.setText("Đăng Xuất");
        dangXuatButton.setPreferredSize(new java.awt.Dimension(170, 50));
        dangXuatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangXuatButtonActionPerformed(evt);
            }
        });

        hoaDonButton.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        hoaDonButton.setText("Hóa Đơn");
        hoaDonButton.setPreferredSize(new java.awt.Dimension(170, 50));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sanPhamButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nhanVienButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(khachHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hoaDonButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nhaCungCapButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nhapHangButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(baoCaoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dangXuatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sanPhamButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhanVienButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baoCaoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(khachHangButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dangXuatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoaDonButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhaCungCapButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhapHangButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tuTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tuTextField.setPreferredSize(new java.awt.Dimension(120, 40));

        denTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        denTextField.setPreferredSize(new java.awt.Dimension(120, 40));

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
        jScrollPane2.setViewportView(hoaDonTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(tuTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(thoiGianTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hoaDonLabel)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(denLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(denTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(timKiemButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(timKiemLabel)
                                .addGap(28, 28, 28)
                                .addComponent(timKiemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(739, 739, 739)
                                .addComponent(xoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1293, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addComponent(jPanel9, 1506, 1506, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(hoaDonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thoiGianTextField)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tuTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(denLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(denTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(timKiemButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timKiemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timKiemLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void timKiemTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timKiemTextFieldActionPerformed

    private void xoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaButtonActionPerformed
        // TODO add your handling code here:
        int row = hoaDonTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(FormHoaDon.this, "Vui lòng chọn Hóa Đơn cần xóa","Lỗi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(FormHoaDon.this, "Bạn chắc chắn muốn xóa không?");
            
            if (confirm == JOptionPane.YES_OPTION) {
                String MAHD = String.valueOf( hoaDonTable.getValueAt(row, 0));
                chiTietHoaDonService.deleteChiTietHoaDon(MAHD);
                hoaDonService.deleteHoaDon(MAHD);
                
                defaultTableModel.setRowCount(0);
                setTableData(hoaDonService.getAllHoaDons());
            }
        }
            
    }//GEN-LAST:event_xoaButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        defaultTableModel.setRowCount(0);
        setTableData(hoaDonService.getAllHoaDons());
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void timKiemTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_timKiemTextFieldCaretUpdate
        // TODO add your handling code here:
        try {
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst;
            ResultSet Rs ;
            if (tuTextField.getText().equals("")&&denTextField.getText().equals(""))
            {
                Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%'" );
                Rs = Pst.executeQuery();
                defaultTableModel.setRowCount(0);
                while (Rs.next()) {
                Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                defaultTableModel.addRow(objList);
                }                
            }
            else if(!tuTextField.getText().equals("")&&denTextField.getText().equals(""))
            {
                if(!dateValidation(tuTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");        
                }
                else
                {
                    Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%' and date(hoadon.THOIGIAN)>='"+tuTextField.getText()+"'");
                    Rs = Pst.executeQuery();
                    defaultTableModel.setRowCount(0);
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                    defaultTableModel.addRow(objList);
                    }
                }
                    
            }
            else if (tuTextField.getText().equals("")&&!denTextField.getText().equals(""))
            {
                if(!dateValidation(denTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");        
                }
                else
                {
                    Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%' and date(hoadon.THOIGIAN)<='"+denTextField.getText()+"'");
                    Rs = Pst.executeQuery();
                    defaultTableModel.setRowCount(0);
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                    defaultTableModel.addRow(objList);
                    }
                }
                    
            } 
            else 
            {
                Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%'and (date(hoaDon.THOIGIAN) BETWEEN '"+tuTextField.getText()+"' AND '"+denTextField.getText()+"')");
                Rs = Pst.executeQuery();
                defaultTableModel.setRowCount(0);
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                    defaultTableModel.addRow(objList);
                    }
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(FormKhachHang.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_timKiemTextFieldCaretUpdate

    private void timKiemButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timKiemButtonMouseClicked
        // TODO add your handling code here:
      
        try {
            Connection Con = JDBCConnection.getJDBCConnection();
            PreparedStatement Pst;
            ResultSet Rs ;
            if (tuTextField.getText().equals("")&&denTextField.getText().equals(""))
            {
                Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%'" );
                Rs = Pst.executeQuery();
                defaultTableModel.setRowCount(0);
                while (Rs.next()) {
                Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                defaultTableModel.addRow(objList);
                }                
            }
            else if(!tuTextField.getText().equals("")&&denTextField.getText().equals(""))
            {
                if(!dateValidation(tuTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");        
                }
                else
                {
                    Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%' and date(hoadon.THOIGIAN)>='"+tuTextField.getText()+"'");
                    Rs = Pst.executeQuery();
                    defaultTableModel.setRowCount(0);
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                    defaultTableModel.addRow(objList);
                    }
                }
                    
            }
            else if (tuTextField.getText().equals("")&&!denTextField.getText().equals(""))
            {
                if(!dateValidation(denTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Nhập ngày định dạng yyyy-mm-dd");        
                }
                else
                {
                    Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%' and date(hoadon.THOIGIAN)<='"+denTextField.getText()+"'");
                    Rs = Pst.executeQuery();
                    defaultTableModel.setRowCount(0);
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                    defaultTableModel.addRow(objList);
                    }
                }
                    
            } 
            else 
            {
                Pst = Con.prepareStatement("select * from hoadon where MAHD like '%"+timKiemTextField.getText()+"%'and (date(hoaDon.THOIGIAN) BETWEEN '"+tuTextField.getText()+"' AND '"+denTextField.getText()+"')");
                Rs = Pst.executeQuery();
                defaultTableModel.setRowCount(0);
                    while (Rs.next()) {
                    Object objList[] = {Rs.getString("MAHD"), Rs.getString("THOIGIAN"), Rs.getString("MAKH"), Rs.getString("MANV"), Rs.getDouble("TONGTIEN"), Rs.getString("GHICHU")};
                    defaultTableModel.addRow(objList);
                    }
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(FormKhachHang.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_timKiemButtonMouseClicked

    private void sanPhamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanPhamButtonActionPerformed
        // TODO add your handling code here:
        new FormSanPham().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sanPhamButtonActionPerformed

    private void nhanVienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhanVienButtonActionPerformed
        // TODO add your handling code here:
        new FormNhanVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nhanVienButtonActionPerformed

    private void nhapHangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapHangButtonActionPerformed
        // TODO add your handling code here:
        new FormDonNhapHang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nhapHangButtonActionPerformed

    private void nhaCungCapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhaCungCapButtonActionPerformed
        // TODO add your handling code here:
        new FormNhaCungCap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_nhaCungCapButtonActionPerformed

    private void khachHangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khachHangButtonActionPerformed
        // TODO add your handling code here:
        new FormKhachHang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_khachHangButtonActionPerformed

    private void dangXuatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatButtonActionPerformed
        // TODO add your handling code here:
        new FormDangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dangXuatButtonActionPerformed

    private void baoCaoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baoCaoButtonActionPerformed
        // TODO add your handling code here:
        new FormBaoCao().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_baoCaoButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FormHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baoCaoButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton dangXuatButton;
    private javax.swing.JLabel denLabel;
    private javax.swing.JTextField denTextField;
    private javax.swing.JButton hoaDonButton;
    private javax.swing.JLabel hoaDonLabel;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton khachHangButton;
    private javax.swing.JButton nhaCungCapButton;
    private javax.swing.JButton nhanVienButton;
    private javax.swing.JButton nhapHangButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton sanPhamButton;
    private javax.swing.JLabel thoiGianTextField;
    private javax.swing.JButton timKiemButton;
    private javax.swing.JLabel timKiemLabel;
    private javax.swing.JTextField timKiemTextField;
    private javax.swing.JLabel tuLabel;
    private javax.swing.JTextField tuTextField;
    private javax.swing.JButton xoaButton;
    // End of variables declaration//GEN-END:variables
}
