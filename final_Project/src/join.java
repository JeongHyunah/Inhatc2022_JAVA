import java.awt.Color;
import static javax.swing.JOptionPane.showMessageDialog;


public class join extends javax.swing.JFrame {
    DB_MAN DBM = new DB_MAN();
    
    public join() {
        initComponents();
        colorChange();
    }
    
    private void colorChange() {
        Color btnback = new Color(211, 211, 211);

        btnBack.setBackground(btnback);
        btnOverlap.setBackground(btnback);
        btnjoin.setBackground(btnback);
       
        Color frameBacgColor = new Color(237,246,255);
        this.getContentPane().setBackground(frameBacgColor);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbljoin = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        txtIDCheck = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnjoin = new javax.swing.JButton();
        btnOverlap = new javax.swing.JButton();
        txtPWCheck = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtPasswordCheck = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        lbljoin.setText("회원가입");

        lblPassword.setText("비밀번호");

        lblID.setText("아이디");

        txtIDCheck.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        txtIDCheck.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtIDCheck.setText("아이디 중복확인을 해주세요");

        btnBack.setText("돌아가기");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnjoin.setText("회원가입");
        btnjoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjoinActionPerformed(evt);
            }
        });

        btnOverlap.setText("중복확인");
        btnOverlap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOverlapActionPerformed(evt);
            }
        });

        txtPWCheck.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        txtPWCheck.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtPWCheck.setText("비밀번호 확인을 위해 한번 더 입력하세요");
        txtPWCheck.setToolTipText("");

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        txtPasswordCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordCheckKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIDCheck)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPasswordCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPWCheck)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnOverlap)
                            .addComponent(btnjoin)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(lbljoin)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbljoin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOverlap, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(txtPasswordCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPWCheck)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnjoin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean GetAdminID(String adminID) {
        try {
            String strSQL = "SELECT * FROM admin_db WHERE aId = '" + adminID + "';";
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strSQL);
            while(DBM.DB_rs.next()) {
                return adminID.equals(DBM.DB_rs.getString("aId"));
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetBookNumber");
        }
        return false;
    }
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        new login().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnOverlapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOverlapActionPerformed
        String adminID = txtID.getText().trim();
        boolean adminIDCheck = GetAdminID(adminID);
        
        if(!adminIDCheck) {
            txtIDCheck.setText("사용 가능한 아이디입니다.");
        } else {
            txtIDCheck.setText("중복된 아이디입니다.");
            //showMessageDialog(null, "중복된 아이디입니다.");
        }
    }//GEN-LAST:event_btnOverlapActionPerformed

    private void btnjoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnjoinActionPerformed
        String adminID = txtID.getText().trim();
        String adminPW = txtPassword.getText().trim();
        String adminPWCheck = txtPasswordCheck.getText().trim();
        
        if(!adminID.equals("") && !adminPW.equals("") && !adminPWCheck.equals("")) {
            boolean adminIDCheck = GetAdminID(adminID);
            if(!adminIDCheck) {
                txtIDCheck.setText("사용 가능한 아이디입니다.");
                if(adminPW.equals(adminPWCheck)) {
                    try {
                        String strQuery = "INSERT INTO admin_db (`aId`, `aPw`) VALUES ('" + adminID + "', '" + adminPW + "');";
                        DBM.dbOpen();
                        DBM.DB_stmt.executeUpdate(strQuery);
                        DBM.dbClose();
                
                        showMessageDialog(null, "회원가입이 완료되었습니다.");
                        txtID.setText("");
                        txtPassword.setText("");
                        txtPasswordCheck.setText("");
                    }
                    catch(Exception e) {
                        System.out.println("SQLException : " + e.getMessage());
                        System.out.println("btnOverlapActionPerformed");
                    }
                } else {
                    txtPWCheck.setText("비밀번호가 일치하지 않습니다.");
                }
            } else {
                txtIDCheck.setText("중복된 아이디입니다.");
                //showMessageDialog(null, "중복된 아이디입니다.");
            }
        } else {
            showMessageDialog(null, "사용하실 아이디와 비밀번호를 입력하세요.");
        }
    }//GEN-LAST:event_btnjoinActionPerformed

    private void txtPasswordCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordCheckKeyReleased
        String adminPW = txtPassword.getText().trim();
        String adminPWCheck = txtPasswordCheck.getText().trim();

        if(adminPW.equals(adminPWCheck)) {
            txtPWCheck.setText("비밀번호가 일치합니다.");
        } else if(adminPW.equals("") || adminPWCheck.equals("")) {
            txtPWCheck.setText("제대로 입력해주세요.");
        } else {
            txtPWCheck.setText("비밀번호가 일치하지 않습니다.");
        }
    }//GEN-LAST:event_txtPasswordCheckKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        String adminPW = txtPassword.getText().trim();
        String adminPWCheck = txtPasswordCheck.getText().trim();

        if(adminPW.equals(adminPWCheck)) {
            txtPWCheck.setText("비밀번호가 일치합니다.");
        } else if(adminPW.equals("") || adminPWCheck.equals("")) {
            txtPWCheck.setText("제대로 입력해주세요.");
        } else {
            txtPWCheck.setText("비밀번호가 일치하지 않습니다.");
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

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
            java.util.logging.Logger.getLogger(join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new join().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnOverlap;
    private javax.swing.JButton btnjoin;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lbljoin;
    private javax.swing.JTextField txtID;
    private javax.swing.JLabel txtIDCheck;
    private javax.swing.JLabel txtPWCheck;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordCheck;
    // End of variables declaration//GEN-END:variables
}
