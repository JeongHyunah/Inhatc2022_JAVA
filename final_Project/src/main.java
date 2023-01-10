import java.awt.Color;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

public class main extends javax.swing.JFrame {
    DB_MAN DBM = new DB_MAN();
    String SQL_SELECT_bookDB = "SELECT * FROM book_db ";
    String SQL_SELECT_userDB = "SELECT * FROM user_db ";
    String SQL_SELECT_loanDB = "SELECT * FROM loan_db ";
    
    public main() {
        initComponents();
        colorChange();
        
         try {
            DBM.dbOpen();
            GetBookInfoData_Basic(SQL_SELECT_bookDB);
            GetLoanInfoData_Basic(SQL_SELECT_loanDB);
            GetUserInfoData_Basic(SQL_SELECT_userDB);
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
        }
    }
    private void colorChange() {
        Color panel = new Color(237,246,255);
        Color btn = new Color(211, 211, 211);
        Color combo = new Color(109, 184, 255);

        pSearch.setBackground(panel);
        pBorrow.setBackground(panel);
        pUser.setBackground(panel);
        pBook.setBackground(panel);
        jDialog_add.setBackground(panel);
        jDialog_update.setBackground(panel);
        
        btnBookSearch.setBackground(btn);
        btnLoanSearch.setBackground(btn);
        btnBorrow.setBackground(btn);
        btnReturn.setBackground(btn);
        btnAdd.setBackground(btn);
        btnDelete.setBackground(btn);
        btnUserDelete.setBackground(btn);
        btnUpdate.setBackground(btn);
        jDiaAdd_btnAdd.setBackground(btn);
        jDiaUpdate_btnSearch.setBackground(btn);
        jDiaUpdate_btnUpdate.setBackground(btn);
        
        cboBookSearch.setBackground(combo);
        cboLoanSearch.setBackground(combo);
        
        Color frameBacgColor = new Color(237,246,255);
        this.getContentPane().setBackground(frameBacgColor);
    }
    
    public String MakeBookSQL_WHERE(String strQuery) {
        switch(cboBookSearch.getSelectedIndex()) {
            case 0:
                strQuery = SQL_SELECT_bookDB;
                break;
            case 1:
                strQuery += "WHERE bNo = '" + txtSearch.getText() + "'";
                break;
            case 2:
                strQuery += "WHERE bName = '" + txtSearch.getText() + "'";
                break;
            case 3:
                strQuery += "WHERE bAuthor = '" + txtSearch.getText() + "'";
                break;
            case 4:
                strQuery += "WHERE bPub = '" + txtSearch.getText() + "'";
                break;
            default:
                strQuery = SQL_SELECT_bookDB;
                break;
        }
        return strQuery;
    }
    
    public void InitTableBookSearch() {
        DefaultTableModel bookInfoModel = (DefaultTableModel)table_bookSearch.getModel();
        bookInfoModel.setNumRows(0);
    }
    
    public void InitTableBookManagement() {
        DefaultTableModel bookInfoModel = (DefaultTableModel)table_bookManagement.getModel();
        bookInfoModel.setNumRows(0);
    }
    
    public void InitTableLoanSearch() {
        DefaultTableModel bookInfoModel = (DefaultTableModel)table_LoanSearch.getModel();
        bookInfoModel.setNumRows(0);
    }
    
    public void InitTableUserManagement() {
        DefaultTableModel bookInfoModel = (DefaultTableModel)table_User.getModel();
        bookInfoModel.setNumRows(0);
    }
    
    public void SQL_UPDATE_bookDB(String strQuery) {
        try {
            DBM.dbOpen();
            DBM.DB_stmt.executeUpdate(strQuery);
            InitTableBookSearch();
            InitTableBookManagement();  
            GetBookInfoData_Basic(SQL_SELECT_bookDB);
            DBM.dbClose();
        }
        catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQL_UPDATE_bookDB");
        }
    }
    
    public void SQL_UPDATE_loanDB(String strQuery) {
        try {
            DBM.dbOpen();
            DBM.DB_stmt.executeUpdate(strQuery);
            InitTableLoanSearch();
            GetLoanInfoData_Basic(SQL_SELECT_loanDB);
            DBM.dbClose();
        }
        catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQL_UPDATE_loanDB");
        }
    }
    
    public void SQL_UPDATE_userDB(String strQuery) {
        try {
            DBM.dbOpen();
            DBM.DB_stmt.executeUpdate(strQuery);
            InitTableUserManagement();
            GetUserInfoData_Basic(SQL_SELECT_userDB);
            DBM.dbClose();
        }
        catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQL_UPDATE_userDB");
        }
    }
    
    public void GetBookInfoData_Basic(String strQuery) {
        String strSQL = "SELECT COUNT(bNO) as count FROM book_db;";
        int iCntRow = 0;
        try {
            DefaultTableModel bookInfoSearch = (DefaultTableModel)table_bookSearch.getModel();
            DefaultTableModel bookInfoModelManagement = (DefaultTableModel)table_bookManagement.getModel();
            ResultSet DB_CntRow = DBM.DB_stmt.executeQuery(strSQL);
            while(DB_CntRow.next()) {
                int CntRow = DB_CntRow.getInt("count");
                bookInfoSearch.setNumRows(CntRow++);
                bookInfoModelManagement.setRowCount(CntRow);
            }
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strQuery);
            while(DBM.DB_rs.next()) {
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bNo"), iCntRow, 0);
                table_bookManagement.setValueAt(DBM.DB_rs.getString("bNo"), iCntRow, 0);
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bName"), iCntRow, 1);
                table_bookManagement.setValueAt(DBM.DB_rs.getString("bName"), iCntRow, 1);             
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bAuthor"), iCntRow, 2);
                table_bookManagement.setValueAt(DBM.DB_rs.getString("bAuthor"), iCntRow, 2);  
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bPub"), iCntRow, 3);
                table_bookManagement.setValueAt(DBM.DB_rs.getString("bPub"), iCntRow, 3);
                if(DBM.DB_rs.getString("bLoan").equals("1")) 
                    table_bookSearch.setValueAt("대출 가능", iCntRow, 4);
                else 
                    table_bookSearch.setValueAt("대출 불가", iCntRow, 4);
                if(DBM.DB_rs.getString("bLoan").equals("1")) 
                    table_bookManagement.setValueAt("대출 가능", iCntRow, 4);
                else 
                    table_bookManagement.setValueAt("대출 불가", iCntRow, 4);
                iCntRow++;
            }
            DBM.DB_rs.close();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetBookInfoData_Basic");
        }
    }
    
    public void GetBookInfoData_Serach(String strQuery) {
        int iCntRow = 0;
        int tCntRow = 0;
        
        try {
            int tableCntRow = table_bookSearch.getRowCount();
            while(tCntRow < tableCntRow) {
                table_bookSearch.setValueAt("", iCntRow, 0);
                table_bookSearch.setValueAt("", iCntRow, 1);          
                table_bookSearch.setValueAt("", iCntRow, 2);
                table_bookSearch.setValueAt("", iCntRow, 3);
                table_bookSearch.setValueAt("", iCntRow, 4);
                iCntRow++;
                tCntRow++;
            }
            iCntRow = 0;
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strQuery);
            while(DBM.DB_rs.next()) {
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bNo"), iCntRow, 0);
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bName"), iCntRow, 1);          
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bAuthor"), iCntRow, 2);
                table_bookSearch.setValueAt(DBM.DB_rs.getString("bPub"), iCntRow, 3);
                if(DBM.DB_rs.getString("bLoan").equals("1")) 
                    table_bookSearch.setValueAt("대출 가능", iCntRow, 4);
                else 
                    table_bookSearch.setValueAt("대출 불가", iCntRow, 4);
                iCntRow++;
            }
            DBM.DB_rs.close();
            
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetBookInfoData_Serach");
        }
    }
    
    public void GetLoanInfoData_Basic(String strQuery) {
        String strSQL = "SELECT COUNT(bNo) as count FROM loan_db;";
        int iCntRow = 0;
        try {
            DefaultTableModel bookLoanSearch = (DefaultTableModel)table_LoanSearch.getModel();
            ResultSet DB_CntRow = DBM.DB_stmt.executeQuery(strSQL);
            while(DB_CntRow.next()) {
                int CntRow = DB_CntRow.getInt("count");
                bookLoanSearch.setNumRows(CntRow++);
            }
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strQuery);
            while(DBM.DB_rs.next()) {
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("bNo"), iCntRow, 0);
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("lUid"), iCntRow, 1);        
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("lDate"), iCntRow, 2);
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("lRDate"), iCntRow, 3);
                if(DBM.DB_rs.getString("lOdue").equals("1")) 
                    table_LoanSearch.setValueAt("연체", iCntRow, 4);
                else 
                    table_LoanSearch.setValueAt("정상", iCntRow, 4);
                iCntRow++;
            }
            DBM.DB_rs.close();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetLoanInfoData_Basic");
        }
    }
    
    public void GetLoanInfoData_Serach(String strQuery) {
        int iCntRow = 0;
        int tCntRow = 0;
        
        try {
            int tableCntRow = table_LoanSearch.getRowCount();
            while(tCntRow < tableCntRow) {
                table_LoanSearch.setValueAt("", iCntRow, 0);
                table_LoanSearch.setValueAt("", iCntRow, 1);          
                table_LoanSearch.setValueAt("", iCntRow, 2);
                table_LoanSearch.setValueAt("", iCntRow, 3);
                table_LoanSearch.setValueAt("", iCntRow, 4);
                iCntRow++;
                tCntRow++;
            }
            iCntRow = 0;
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strQuery);
            while(DBM.DB_rs.next()) {
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("bNo"), iCntRow, 0);
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("lUid"), iCntRow, 1);        
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("lDate"), iCntRow, 2);
                table_LoanSearch.setValueAt(DBM.DB_rs.getString("lRDate"), iCntRow, 3);
                if(DBM.DB_rs.getString("lOdue").equals("1")) 
                    table_LoanSearch.setValueAt("연체", iCntRow, 4);
                else 
                    table_LoanSearch.setValueAt("정상", iCntRow, 4);
                iCntRow++;
            }
            DBM.DB_rs.close();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetLoanInfoData_Serach");
        }
    }
    
    public void GetUserInfoData_Basic(String strQuery) {
        String strSQL = "SELECT COUNT(lUid) as count FROM user_db;";
        int iCntRow = 0;
        try {
            DefaultTableModel userInfo = (DefaultTableModel)table_User.getModel();
            ResultSet DB_CntRow = DBM.DB_stmt.executeQuery(strSQL);
            while(DB_CntRow.next()) {
                int CntRow = DB_CntRow.getInt("count");
                userInfo.setNumRows(CntRow++);
            }
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strQuery);
            while(DBM.DB_rs.next()) {
                table_User.setValueAt(DBM.DB_rs.getString("lUid"), iCntRow, 0);
                iCntRow++;
            }
            DBM.DB_rs.close();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetUserInfoData_Basic");
        }
    }
    
    public boolean GetBookNumber(String bookNum) {
        String strSQL = SQL_SELECT_bookDB;
        try {
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strSQL);
            while(DBM.DB_rs.next()) {
                if(bookNum.equals(DBM.DB_rs.getString("bNo"))) {
                    return true;
                }
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetBookNumber");
        }
        return false;
    }
    
    public boolean GetBookLoan(String bookNum) {
        String strSQL = "SELECT bLoan FROM book_db WHERE bNo = '" + bookNum + "';";
        try {
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strSQL);
            while(DBM.DB_rs.next()) {
                if(DBM.DB_rs.getString("bLoan").equals("1")) {
                    return true;
                }
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetBookLoan");
        }
        return false;
    }
    
    public boolean GetUser(String userID) {
        String strSQL = SQL_SELECT_userDB;
        try {
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strSQL);
            while(DBM.DB_rs.next()) {
                if(userID.equals(DBM.DB_rs.getString("lUid"))) {
                    return true;
                }
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetUser");
        }
        return false;
    }
    
    public boolean GetUserOdue(String userID) {
        String strSQL = SQL_SELECT_loanDB + "WHERE lUid = '" + userID + "';";
        try {
            DBM.dbOpen();
            DBM.DB_rs = DBM.DB_stmt.executeQuery(strSQL);
            while(DBM.DB_rs.next()) {
                if((DBM.DB_rs.getString("lOdue").equals("1"))) {
                    return false;
                }
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetUser");
        }
        return true;
    }
    
    public boolean GetUserLoan(String userID) {
        String strSQL = "SELECT COUNT(CASE WHEN lUid = '" + userID + "' THEN 1 END) AS 'count' FROM loan_db;";
        try {
            DBM.dbOpen();
            ResultSet DB_CntRow = DBM.DB_stmt.executeQuery(strSQL);
            while(DB_CntRow.next()) {
                int CntRow = DB_CntRow.getInt("count");
                return CntRow > 0;
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetUserLoan");
        }
        return true;
    }
    
    public boolean GetLoanCurrect(String bookNum, String userID) {
        String strSQL = "SELECT COUNT(CASE WHEN bNo = '" + bookNum + "' AND lUid = '" + userID +"' THEN 1 END) AS 'count' FROM loan_db;";
        try {
            DBM.dbOpen();
            ResultSet DB_CntRow = DBM.DB_stmt.executeQuery(strSQL);
            while(DB_CntRow.next()) {
                int CntRow = DB_CntRow.getInt("count");
                return CntRow > 0;
            }
            DBM.DB_rs.close();
            DBM.dbClose();
        } catch(Exception e) {
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("GetLoanCurrect");
        }
        return false;
    }
    
    public String SetBookNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); 
        return df.format(cal.getTime());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_add = new javax.swing.JDialog();
        lblTitle = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        lblPublisher = new javax.swing.JLabel();
        add_txtTitle = new javax.swing.JTextField();
        add_txtAuthor = new javax.swing.JTextField();
        add_txtPublisher = new javax.swing.JTextField();
        jDiaAdd_btnAdd = new javax.swing.JButton();
        jDialog_update = new javax.swing.JDialog();
        lblPublisher1 = new javax.swing.JLabel();
        update_txtTitle = new javax.swing.JTextField();
        update_txtAuthor = new javax.swing.JTextField();
        update_txtPublisher = new javax.swing.JTextField();
        jDiaUpdate_btnUpdate = new javax.swing.JButton();
        lblNum1 = new javax.swing.JLabel();
        update_txtNum = new javax.swing.JTextField();
        lblTitle1 = new javax.swing.JLabel();
        lblAuthor1 = new javax.swing.JLabel();
        jDiaUpdate_btnSearch = new javax.swing.JButton();
        jTable = new javax.swing.JTabbedPane();
        pSearch = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnBookSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bookSearch = new javax.swing.JTable();
        cboBookSearch = new javax.swing.JComboBox<>();
        pBorrow = new javax.swing.JPanel();
        txtLoanSearch = new javax.swing.JTextField();
        btnBorrow = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_LoanSearch = new javax.swing.JTable();
        btnLoanSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        cboLoanSearch = new javax.swing.JComboBox<>();
        txtBNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pUser = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_User = new javax.swing.JTable();
        btnUserDelete = new javax.swing.JButton();
        pBook = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_bookManagement = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        jDialog_add.setLocation(new java.awt.Point(0, 0));
        jDialog_add.setModal(true);

        lblTitle.setText("제목");

        lblAuthor.setText("작가");

        lblPublisher.setText("출판사");

        jDiaAdd_btnAdd.setText("추가");
        jDiaAdd_btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDiaAdd_btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog_addLayout = new javax.swing.GroupLayout(jDialog_add.getContentPane());
        jDialog_add.getContentPane().setLayout(jDialog_addLayout);
        jDialog_addLayout.setHorizontalGroup(
            jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_addLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDiaAdd_btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog_addLayout.createSequentialGroup()
                        .addGroup(jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPublisher)
                            .addComponent(lblAuthor)
                            .addComponent(lblTitle))
                        .addGap(35, 35, 35)
                        .addGroup(jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(add_txtAuthor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_txtTitle, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jDialog_addLayout.setVerticalGroup(
            jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_addLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addGap(22, 22, 22)
                .addGroup(jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAuthor))
                .addGap(22, 22, 22)
                .addGroup(jDialog_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublisher))
                .addGap(28, 28, 28)
                .addComponent(jDiaAdd_btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jDialog_update.setLocation(new java.awt.Point(0, 0));
        jDialog_update.setModal(true);

        lblPublisher1.setText("출판사");

        jDiaUpdate_btnUpdate.setText("수정");
        jDiaUpdate_btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDiaUpdate_btnUpdateActionPerformed(evt);
            }
        });

        lblNum1.setText("도서 번호");

        lblTitle1.setText("제목");

        lblAuthor1.setText("작가");

        jDiaUpdate_btnSearch.setText("검색");
        jDiaUpdate_btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDiaUpdate_btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog_updateLayout = new javax.swing.GroupLayout(jDialog_update.getContentPane());
        jDialog_update.getContentPane().setLayout(jDialog_updateLayout);
        jDialog_updateLayout.setHorizontalGroup(
            jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_updateLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jDialog_updateLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPublisher1)
                            .addComponent(lblAuthor1))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(update_txtAuthor)
                            .addComponent(update_txtPublisher)))
                    .addGroup(jDialog_updateLayout.createSequentialGroup()
                        .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNum1)
                            .addComponent(lblTitle1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(update_txtNum)
                            .addComponent(update_txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDiaUpdate_btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jDiaUpdate_btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jDialog_updateLayout.setVerticalGroup(
            jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_updateLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNum1)
                    .addComponent(update_txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDiaUpdate_btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle1))
                .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_updateLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(update_txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jDialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update_txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPublisher1)))
                    .addGroup(jDialog_updateLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblAuthor1)))
                .addGap(12, 12, 12)
                .addComponent(jDiaUpdate_btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        jTable.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N

        pSearch.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N

        btnBookSearch.setText("검색");
        btnBookSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookSearchActionPerformed(evt);
            }
        });

        table_bookSearch.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        table_bookSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "도서 번호", "제목", "작가", "출판사", "대출 여부"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_bookSearch);

        cboBookSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "전체", "도서 번호", "제목", "작가", "출판사" }));

        javax.swing.GroupLayout pSearchLayout = new javax.swing.GroupLayout(pSearch);
        pSearch.setLayout(pSearchLayout);
        pSearchLayout.setHorizontalGroup(
            pSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSearchLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(pSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pSearchLayout.createSequentialGroup()
                        .addComponent(cboBookSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnBookSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        pSearchLayout.setVerticalGroup(
            pSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSearchLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboBookSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnBookSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable.addTab("도서 검색", pSearch);

        btnBorrow.setText("대출");
        btnBorrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrowActionPerformed(evt);
            }
        });

        btnReturn.setText("반납");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        table_LoanSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "도서 번호", "아이디", "대출일", "반납일", "연체 여부"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_LoanSearch.setToolTipText("");
        table_LoanSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_LoanSearchMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_LoanSearch);

        btnLoanSearch.setText("검색");
        btnLoanSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoanSearchActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("아이디");

        cboLoanSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "전체", "도서 번호", "아이디" }));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("대출 도서 번호");

        javax.swing.GroupLayout pBorrowLayout = new javax.swing.GroupLayout(pBorrow);
        pBorrow.setLayout(pBorrowLayout);
        pBorrowLayout.setHorizontalGroup(
            pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBorrowLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pBorrowLayout.createSequentialGroup()
                        .addGroup(pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboLoanSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pBorrowLayout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBNo)
                                .addGap(18, 18, 18)
                                .addComponent(btnBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pBorrowLayout.createSequentialGroup()
                                .addComponent(txtLoanSearch)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoanSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pBorrowLayout.setVerticalGroup(
            pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBorrowLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoanSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoanSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoanSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBorrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBNo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTable.addTab("대출 및 반납", pBorrow);

        table_User.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "아이디"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table_User);

        btnUserDelete.setText("삭제");
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pUserLayout = new javax.swing.GroupLayout(pUser);
        pUser.setLayout(pUserLayout);
        pUserLayout.setHorizontalGroup(
            pUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pUserLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(pUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        pUserLayout.setVerticalGroup(
            pUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pUserLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTable.addTab("유저 관리", pUser);

        table_bookManagement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "도서 번호", "제목", "작가", "출판사", "대출 여부"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(table_bookManagement);

        btnAdd.setText("추가");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("삭제");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("수정");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pBookLayout = new javax.swing.GroupLayout(pBook);
        pBook.setLayout(pBookLayout);
        pBookLayout.setHorizontalGroup(
            pBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBookLayout.createSequentialGroup()
                .addGroup(pBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pBookLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pBookLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pBookLayout.setVerticalGroup(
            pBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBookLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTable.addTab("도서 관리", pBook);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTable)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTable)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBookSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookSearchActionPerformed
        String strSQL = SQL_SELECT_bookDB;
        strSQL = MakeBookSQL_WHERE(strSQL);
        GetBookInfoData_Serach(strSQL);
    }//GEN-LAST:event_btnBookSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String strSQL = "DELETE FROM book_db ";
        int iCntRow;
        String iCntRow_bNo;
        iCntRow = table_bookManagement.getSelectedRow();
        if(iCntRow != -1) {
            iCntRow_bNo = table_bookSearch.getValueAt(iCntRow, 0).toString();
            if(GetBookLoan(iCntRow_bNo)) {
                strSQL += ("WHERE bNo = " + "'" + iCntRow_bNo + "';");
                SQL_UPDATE_bookDB(strSQL);
                showMessageDialog(null, "삭제가 완료되었습니다.");
            }
            else {
                showMessageDialog(null, "선택한 도서가 대출 중입니다. \n 도서가 반납된 후 삭제하십시오."); 
            }
        }
        else {
            showMessageDialog(null, "삭제할 도서를 선택하세요.");
        }        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        jDialog_add.setSize(550,400);
        jDialog_add.show();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        jDialog_update.setSize(550,400);
        jDialog_update.show();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jDiaAdd_btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiaAdd_btnAddActionPerformed
        String strSQL = "INSERT INTO book_db (`bNo`, `bName`, `bAuthor`, `bPub`, `bLoan`) VALUES (";
        String txtTitle = add_txtTitle.getText();
        String txtAuthor = add_txtAuthor.getText();
        String txtPublisher = add_txtPublisher.getText();
        
        if(txtTitle != null && txtAuthor != null && txtPublisher != null &&
                !txtTitle.equals("") && !txtAuthor.equals("") && !txtPublisher.equals("")) {
            String bNum = SetBookNumber();
            strSQL += "'" + bNum + "',";
            strSQL += "'" + txtTitle + "',";
            strSQL += "'" + txtAuthor + "',";
            strSQL += "'" + txtPublisher + "',";
            strSQL += "'1');";         
            SQL_UPDATE_bookDB(strSQL);
            
            add_txtTitle.setText("");
            add_txtAuthor.setText("");
            add_txtPublisher.setText("");
            showMessageDialog(null, "추가가 완료되었습니다.");
        }
        else {
            showMessageDialog(null, "입력을 완료하고 추가하십시오.");
        }
    }//GEN-LAST:event_jDiaAdd_btnAddActionPerformed

    private void jDiaUpdate_btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiaUpdate_btnSearchActionPerformed
        String strSQL = SQL_SELECT_bookDB;
        update_txtTitle.setText("");
        update_txtAuthor.setText("");
        update_txtPublisher.setText("");
        
        String bookNum = update_txtNum.getText();
        if(GetBookNumber(bookNum)) {
            try {
                strSQL += "WHERE bNO = " + "'" + bookNum + "';";
                DBM.dbOpen();
                DBM.DB_rs = DBM.DB_stmt.executeQuery(strSQL);
                while(DBM.DB_rs.next()) {
                    update_txtNum.setText(DBM.DB_rs.getString("bNo"));
                    update_txtTitle.setText(DBM.DB_rs.getString("bName"));
                    update_txtAuthor.setText(DBM.DB_rs.getString("bAuthor"));
                    update_txtPublisher.setText(DBM.DB_rs.getString("bPub"));
                }
                DBM.DB_rs.close();
                DBM.dbClose();
            } catch(Exception e) {
                System.out.println("SQLException : " + e.getMessage());
                System.out.println("jDiaUpdate_btnSearchActionPerformed");
            }
        }
        else {
            showMessageDialog(null, "수정할 대상이 존재하지 않습니다.");
        }
    }//GEN-LAST:event_jDiaUpdate_btnSearchActionPerformed

    private void jDiaUpdate_btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiaUpdate_btnUpdateActionPerformed
        String strSQL = "UPDATE book_db SET ";
        String txtNum = update_txtNum.getText();
        String txtTitle = update_txtTitle.getText();
        String txtAuthor = update_txtAuthor.getText();
        String txtPublisher = update_txtPublisher.getText();   
        if(txtNum != null && txtTitle != null && txtAuthor != null && txtPublisher != null &&
                !txtNum.equals("") && !txtTitle.equals("") && !txtAuthor.equals("") && !txtPublisher.equals("")) {
            if(GetBookNumber(txtNum)) {
                strSQL += "bName = '" + txtTitle + "', ";
                strSQL += "bAuthor = '" + txtAuthor + "', ";
                strSQL += "bPub = '" + txtPublisher + "' ";
                strSQL += "WHERE bNo = '" + txtNum + "';";
                SQL_UPDATE_bookDB(strSQL);
                showMessageDialog(null, "수정이 완료되었습니다.");
            }
            else {
                showMessageDialog(null, "수정할 대상이 존재하지 않습니다.");
            }
        }
        else {
            showMessageDialog(null, "입력을 완료하고 추가하십시오.");
        }
    }//GEN-LAST:event_jDiaUpdate_btnUpdateActionPerformed

    private void btnLoanSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoanSearchActionPerformed
        boolean lUidCheck;
        boolean bNoCheck;
        String loanSearch = txtLoanSearch.getText().trim();
        if(!loanSearch.equals("")) {
            switch(cboLoanSearch.getSelectedIndex()) {
            case 0:
                String strQuery = SQL_SELECT_loanDB;
                GetLoanInfoData_Serach(strQuery);
                break;
            case 1:
                bNoCheck = GetBookNumber(loanSearch);
                if(bNoCheck) {
                    strQuery = SQL_SELECT_loanDB + "WHERE bNo = '" + loanSearch + "';";
                    GetLoanInfoData_Serach(strQuery);
                }
                else {
                    showMessageDialog(null, "검색한 도서가 존재하지 않습니다.");
                }
                break;    
            case 2:
                lUidCheck = GetUser(loanSearch);
                if(lUidCheck) {
                    strQuery = SQL_SELECT_loanDB + "WHERE lUid = '" + loanSearch + "';";
                    GetLoanInfoData_Serach(strQuery);
                }
                else {
                    showMessageDialog(null, "검색한 아이디가 존재하지 않습니다.");
                }
                break;
            default:
                 break;
            }
        }
        else {
            showMessageDialog(null, "입력을 완료해주십시오.");
        }   
    }//GEN-LAST:event_btnLoanSearchActionPerformed

    private void btnBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrowActionPerformed
        String userID = txtID.getText().trim();
        String bookNum = txtBNo.getText().trim();
        boolean lUidCheck = GetUser(userID);
        boolean bNoCheck = GetBookNumber(bookNum);
        boolean lUidOdueCheck = GetUserOdue(userID);
        boolean bLoanCheck = GetBookLoan(bookNum);
        
        if(!userID.equals("") && !bookNum.equals("")) {
            if(lUidCheck && bNoCheck) {
                if(bLoanCheck && lUidOdueCheck) {    
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date SQLlDate = java.sql.Date.valueOf(df.format(cal.getTime()));
                    cal.add(Calendar.DATE, 14);
                    java.sql.Date SQLlRDate = java.sql.Date.valueOf(df.format(cal.getTime()));
                    
                    String strSQL = "INSERT INTO loan_db (`bNo`, `lUid`, `lDate`, `lRDate`, `lOdue`) VALUES (";
                    strSQL += "'" + bookNum + "', '" + userID + "', '" + SQLlDate + "', '" + SQLlRDate + "', '0');";
                    SQL_UPDATE_loanDB(strSQL);
                    
                    strSQL = "UPDATE book_db SET bLoan = '0' WHERE bNo = '" + bookNum + "';";
                    SQL_UPDATE_bookDB(strSQL);
                    
                    txtID.setText("");
                    txtBNo.setText("");
                    showMessageDialog(null, "대출이 완료되었습니다.");
                } else if(!bLoanCheck) {
                    showMessageDialog(null, "해당 도서가 대출 중입니다.");
                } else {
                    showMessageDialog(null, "연체된 유저입니다. \n 대출이 불가능합니다.");
                }
            } else if(!lUidCheck) {
                showMessageDialog(null, "입력한 유저가 존재하지 않습니다.");
            } else {
                showMessageDialog(null, "입력한 도서가 존재하지 않습니다.");
            }
        } else {
            showMessageDialog(null, "입력을 완료해주십시오.");
        }
    }//GEN-LAST:event_btnBorrowActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        String userID = txtID.getText().trim();
        String bookNum = txtBNo.getText().trim();
        boolean lUidOdueCheck = GetUserOdue(userID);
        boolean bLoanCheck = GetBookLoan(bookNum);
        boolean loanCurrectCheck = GetLoanCurrect(bookNum, userID);
        
        if(!userID.equals("") && !bookNum.equals("")) {
            if(!bLoanCheck && loanCurrectCheck) {
            String strSQL = "DELETE FROM loan_db WHERE bNo = '" + bookNum + "'AND lUid = '" + userID + "';"; 
            SQL_UPDATE_loanDB(strSQL);
            
            strSQL = "UPDATE book_db SET bLoan = '1' WHERE bNo = '" + bookNum + "';";
            SQL_UPDATE_bookDB(strSQL);
            
            txtID.setText("");
            txtBNo.setText("");
            showMessageDialog(null, "반납이 완료되었습니다.");
            } else {
                showMessageDialog(null, "반납에 필요한 정보가 잘못되었습니다 \n 다시 확인하십시오.");
            }
            if(!lUidOdueCheck) {
                showMessageDialog(null, "연체된 유저입니다. \n 연체 기간동안 대출 이용이 제한됩니다.");
                txtID.setText("");
                txtBNo.setText("");
                showMessageDialog(null, "반납이 완료되었습니다.");
            }
        } else {
            showMessageDialog(null, "입력을 완료해주십시오.");
        }
    }//GEN-LAST:event_btnReturnActionPerformed

    private void table_LoanSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_LoanSearchMouseClicked
        int iCntRow;
        String iCntRow_bNo;
        String iCntRow_lUid;
        iCntRow = table_LoanSearch.getSelectedRow();
        iCntRow_bNo = table_LoanSearch.getValueAt(iCntRow, 0).toString();
        iCntRow_lUid = table_LoanSearch.getValueAt(iCntRow, 1).toString();
        txtID.setText(iCntRow_lUid);
        txtBNo.setText(iCntRow_bNo);
    }//GEN-LAST:event_table_LoanSearchMouseClicked

    private void btnUserDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeleteActionPerformed
        String strSQL = "DELETE FROM user_db ";
        int iCntRow;
        String iCntRow_lUid;
        iCntRow = table_User.getSelectedRow();
        if(iCntRow != -1) {
            iCntRow_lUid = table_User.getValueAt(iCntRow, 0).toString();
            if(GetUserLoan(iCntRow_lUid.trim())) {
                showMessageDialog(null, "해당 유저는 대출 중입니다 \n 도서가 반납된 후 삭제하십시오."); 
            }
            else {
                strSQL += "WHERE lUid = '" + iCntRow_lUid + "';";
                SQL_UPDATE_userDB(strSQL);
                showMessageDialog(null, "삭제가 완료되었습니다.");
            }
        }
        else {
            showMessageDialog(null, "삭제할 유저를 선택하세요.");
        }
    }//GEN-LAST:event_btnUserDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField add_txtAuthor;
    private javax.swing.JTextField add_txtPublisher;
    private javax.swing.JTextField add_txtTitle;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBookSearch;
    private javax.swing.JButton btnBorrow;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoanSearch;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JComboBox<String> cboBookSearch;
    private javax.swing.JComboBox<String> cboLoanSearch;
    private javax.swing.JButton jDiaAdd_btnAdd;
    private javax.swing.JButton jDiaUpdate_btnSearch;
    private javax.swing.JButton jDiaUpdate_btnUpdate;
    private javax.swing.JDialog jDialog_add;
    private javax.swing.JDialog jDialog_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTable;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblAuthor1;
    private javax.swing.JLabel lblNum1;
    private javax.swing.JLabel lblPublisher;
    private javax.swing.JLabel lblPublisher1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JPanel pBook;
    private javax.swing.JPanel pBorrow;
    private javax.swing.JPanel pSearch;
    private javax.swing.JPanel pUser;
    private javax.swing.JTable table_LoanSearch;
    private javax.swing.JTable table_User;
    private javax.swing.JTable table_bookManagement;
    private javax.swing.JTable table_bookSearch;
    private javax.swing.JTextField txtBNo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLoanSearch;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField update_txtAuthor;
    private javax.swing.JTextField update_txtNum;
    private javax.swing.JTextField update_txtPublisher;
    private javax.swing.JTextField update_txtTitle;
    // End of variables declaration//GEN-END:variables
}
