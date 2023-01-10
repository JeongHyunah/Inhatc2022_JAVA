import java.awt.Color;
import java.sql.ResultSet;
import static javax.swing.JOptionPane.showMessageDialog;

public class login extends javax.swing.JFrame {
    DB_MAN DBM = new DB_MAN();
    
    public login() {
        initComponents();
        colorChange();
    }
    
    private void colorChange() {
        Color btnback = new Color(211, 211, 211);

        btnlogin.setBackground(btnback);
        btnjoin.setBackground(btnback);
       
        Color frameBacgColor = new Color(237,246,255);
        this.getContentPane().setBackground(frameBacgColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblID = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnlogin = new javax.swing.JButton();
        lbllogin = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnjoin = new javax.swing.JButton();
        txtPW = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        lblID.setText("아이디");

        lblPassword.setText("비밀번호");

        btnlogin.setText("로그인");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        lbllogin.setText("로그인");

        btnjoin.setText("회원가입");
        btnjoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjoinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnlogin)
                        .addGap(18, 18, 18)
                        .addComponent(btnjoin))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblID)
                            .addComponent(lblPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addComponent(txtPW))))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(232, Short.MAX_VALUE)
                .addComponent(lbllogin)
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lbllogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPW, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnjoin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        String adminID = txtID.getText().trim();
        String adminPW = txtPW.getText().trim();
        if(!adminID.equals("") && !adminPW.equals("")) {
            try {
                String strQuery = "SELECT COUNT(CASE WHEN aId = '" + adminID + "' AND aPw = '" + adminPW +"' THEN 1 END) AS 'count' FROM admin_db;";
                DBM.dbOpen();
                ResultSet adminCheck = DBM.DB_stmt.executeQuery(strQuery);
                while(adminCheck.next()) {
                    int adminCount = adminCheck.getInt("count");
                    if(adminCount > 0) {
                        showMessageDialog(null, "로그인 성공.");
                        this.setVisible(false);
                        new main().setVisible(true);
                        return;
                    }
                    else {
                        showMessageDialog(null, "아이디와 비밀번호를 확인하십시오.");
                    }
                }
                DBM.DB_rs.close();
                DBM.dbClose();
            } catch(Exception e) {
                System.out.println("SQLException : " + e.getMessage());
                System.out.println("btnloginActionPerformed");
            }
        } else {
            showMessageDialog(null, "입력을 완료하십시오.");
        }
    }//GEN-LAST:event_btnloginActionPerformed

    private void btnjoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnjoinActionPerformed
       this.setVisible(false);
       new join().setVisible(true);
    }//GEN-LAST:event_btnjoinActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnjoin;
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lbllogin;
    private javax.swing.JTextField txtID;
    private javax.swing.JPasswordField txtPW;
    // End of variables declaration//GEN-END:variables
}
