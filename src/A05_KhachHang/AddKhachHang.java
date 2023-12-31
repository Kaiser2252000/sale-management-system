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
public class AddKhachHang extends javax.swing.JFrame {
    KhachHangService khachHangService;
    KhachHang khachHang;

    /**
     * Creates new form ADDHangHoa
     */
    public AddKhachHang() {
        initComponents();
        khachHangService = new KhachHangService();
        khachHang = new KhachHang();
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
        themKhachHangLabel = new javax.swing.JLabel();
        dongButton = new javax.swing.JButton();
        maKHLabel = new javax.swing.JLabel();
        tenKHLabel = new javax.swing.JLabel();
        sdtLabel = new javax.swing.JLabel();
        diaChiLabel = new javax.swing.JLabel();
        ghiChuLabel = new javax.swing.JLabel();
        maKHTextField = new javax.swing.JTextField();
        tenKHTextField = new javax.swing.JTextField();
        sdtTextField = new javax.swing.JTextField();
        diaChiTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ghiChuTextField = new javax.swing.JTextArea();
        luuButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(700, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));

        jPanel2.setBackground(new java.awt.Color(102, 255, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 80));

        themKhachHangLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        themKhachHangLabel.setForeground(new java.awt.Color(51, 51, 51));
        themKhachHangLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        themKhachHangLabel.setText("Thêm Khách Hàng");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(themKhachHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(themKhachHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dongButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        maKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maKHLabel.setText("Mã Khách Hàng");
        maKHLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        tenKHLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenKHLabel.setText("Tên Khách Hàng");
        tenKHLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        sdtLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sdtLabel.setText("Số Điện Thoại");
        sdtLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        diaChiLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        diaChiLabel.setText("Địa Chỉ");
        diaChiLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        ghiChuLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuLabel.setText("Ghi Chú");
        ghiChuLabel.setPreferredSize(new java.awt.Dimension(170, 40));

        maKHTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maKHTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        tenKHTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenKHTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        sdtTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sdtTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        diaChiTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        diaChiTextField.setPreferredSize(new java.awt.Dimension(450, 40));

        ghiChuTextField.setColumns(20);
        ghiChuTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ghiChuTextField.setRows(5);
        jScrollPane1.setViewportView(ghiChuTextField);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tenKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tenKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(maKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sdtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sdtTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(diaChiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(diaChiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ghiChuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(luuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(diaChiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diaChiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ghiChuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(luuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            if (i!=0)
            {
                 JOptionPane.showMessageDialog(this, "Mã Khách Hàng đã tồn tại");
            }
            else
            {
                khachHang.setMAKH(maKHTextField.getText());
                khachHang.setTENKH(tenKHTextField.getText());
                khachHang.setSDT(sdtTextField.getText());
                khachHang.setDIACHI(diaChiTextField.getText());
                khachHang.setGHICHU(ghiChuTextField.getText());
                khachHangService.addKhachHang(khachHang);
                JOptionPane.showMessageDialog(this, "Lưu thành công!");    
            }
        }
    }//GEN-LAST:event_luuButtonActionPerformed

    
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
            java.util.logging.Logger.getLogger(AddKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel themKhachHangLabel;
    // End of variables declaration//GEN-END:variables
}
