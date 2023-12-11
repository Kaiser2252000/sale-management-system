/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A05_KhachHang;


import javax.swing.JOptionPane;


/**
 *
 * @author ADMIN
 */
public class EditKhachHang extends javax.swing.JFrame {  
    private KhachHang khachHang;
    KhachHangService khachHangService;
    String MAKHdangsua;

    /**
     * Creates new form ADDHangHoa
     */
    public EditKhachHang(String MAKH) {
        khachHang = new KhachHang();
        khachHangService = new KhachHangService();
        khachHang = khachHangService.getKhachHangByMAKH(MAKH);
        initComponents();
        MAKHdangsua=khachHang.getMAKH();
        maKHTextField.setText(khachHang.getMAKH());
        tenKHTextField.setText(khachHang.getTENKH());                         
        sdtTextField.setText(khachHang.getSDT());
        diaChiTextField.setText(khachHang.getDIACHI());
        ghiChuTextField.setText(khachHang.getGHICHU());
                
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
        chinhSuaKhachHangLabel = new javax.swing.JLabel();
        dongButton = new javax.swing.JButton();
        luuButton = new javax.swing.JButton();
        ghiChuLabel = new javax.swing.JLabel();
        maKHTextField = new javax.swing.JTextField();
        tenKHTextField = new javax.swing.JTextField();
        sdtTextField = new javax.swing.JTextField();
        diaChiTextField = new javax.swing.JTextField();
        maKHLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ghiChuTextField = new javax.swing.JTextArea();
        tenKHLabel = new javax.swing.JLabel();
        sdtLabel = new javax.swing.JLabel();
        diaChiLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(700, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));

        jPanel2.setBackground(new java.awt.Color(102, 255, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 80));

        chinhSuaKhachHangLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        chinhSuaKhachHangLabel.setForeground(new java.awt.Color(51, 51, 51));
        chinhSuaKhachHangLabel.setText("Chỉnh Sửa Khách Hàng");

        dongButton.setBackground(new java.awt.Color(0, 255, 0));
        dongButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dongButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Delete.png"))); // NOI18N
        dongButton.setText("Đóng");
        dongButton.setPreferredSize(new java.awt.Dimension(120, 40));
        dongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dongButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chinhSuaKhachHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chinhSuaKhachHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        luuButton.setBackground(new java.awt.Color(0, 255, 0));
        luuButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        luuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/A00_Icon/Save.png"))); // NOI18N
        luuButton.setText("Lưu");
        luuButton.setPreferredSize(new java.awt.Dimension(120, 40));
        luuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luuButtonActionPerformed(evt);
            }
        });

        ghiChuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuLabel.setText("Ghi Chú");

        maKHTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maKHTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        tenKHTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenKHTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        sdtTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sdtTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        diaChiTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        diaChiTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        maKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maKHLabel.setText("Mã Khách Hàng");
        maKHLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        ghiChuTextField.setColumns(20);
        ghiChuTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuTextField.setRows(5);
        jScrollPane1.setViewportView(ghiChuTextField);

        tenKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenKHLabel.setText("Tên Khách Hàng");
        tenKHLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        sdtLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sdtLabel.setText("Số Điện Thoại");
        sdtLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        diaChiLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        diaChiLabel.setText("Địa Chỉ");
        diaChiLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sdtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sdtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tenKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(maKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diaChiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ghiChuLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(diaChiTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(luuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sdtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(diaChiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ghiChuLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(diaChiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(luuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dongButtonActionPerformed
        // TODO add your handling code here:
       
        this.dispose();
    }//GEN-LAST:event_dongButtonActionPerformed

    private void luuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuButtonActionPerformed
        // TODO add your handling code here:
        if (maKHTextField.getText().isEmpty()||tenKHTextField.getText().isEmpty())    
        {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ Mã và Tên Khách Hàng");
        }
        else
        {
            int i=khachHangService.getCountMAKH(maKHTextField.getText());
            if (i!=0 && !MAKHdangsua.equals(maKHTextField.getText()))
            {
                JOptionPane.showMessageDialog(this, "Mã Khách Hàng bị trùng lặp");
                maKHTextField.setText(MAKHdangsua);
            }
            else
            {
                khachHang.setMAKH(maKHTextField.getText());
                khachHang.setTENKH(tenKHTextField.getText());       
                khachHang.setSDT(sdtTextField.getText());
                khachHang.setDIACHI(diaChiTextField.getText());
                khachHang.setGHICHU(ghiChuTextField.getText());

                khachHangService.updateKhachHang(khachHang);
                JOptionPane.showMessageDialog(this, "Lưu thành công!");   
            }
        }
    }//GEN-LAST:event_luuButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chinhSuaKhachHangLabel;
    private javax.swing.JLabel diaChiLabel;
    private javax.swing.JTextField diaChiTextField;
    private javax.swing.JButton dongButton;
    private javax.swing.JLabel ghiChuLabel;
    private javax.swing.JTextArea ghiChuTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton luuButton;
    private javax.swing.JLabel maKHLabel;
    private javax.swing.JTextField maKHTextField;
    private javax.swing.JLabel sdtLabel;
    private javax.swing.JTextField sdtTextField;
    private javax.swing.JLabel tenKHLabel;
    private javax.swing.JTextField tenKHTextField;
    // End of variables declaration//GEN-END:variables
}