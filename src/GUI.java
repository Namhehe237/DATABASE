import Attribute.RENT;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.DropMode.INSERT;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    private ArrayList<RENT> listRent;
    private DefaultTableModel tmRent;
    private String sqlInsert,sqlSelect ;
    public GUI() throws ClassNotFoundException, SQLException {
        initComponents();
        String url = "jdbc:mysql://localhost:3306/btl?autoReconnect=true&&useSSL=false";
        String username = "root";
        String password = "Thanhnam@2307";
        Class.forName("com.mysql.jdbc.Driver");
        //Connection
        Connection connect = DriverManager.getConnection(url, username, password);
        if (connect != null) {
            tmRent = (DefaultTableModel) table.getModel();
            listRent = new ArrayList<>();
            
            
            addBTN.addActionListener((e) -> {
               try {
                int rID = Integer.parseInt(rentidTXT.getText());
                int cID = Integer.parseInt(cusidTXT.getText());
                int mID = Integer.parseInt(movieidTXT.getText());
                int reID = Integer.parseInt(remployeeidTXT.getText());
                String day = daytimeTXT.getText();
                int fee = Integer.parseInt(feeTXT.getText());
                Statement stmm=(Statement) connect.createStatement();
                ResultSet rs = stmm.executeQuery("SELECT * FROM Rents");
                boolean check=true;
                while (rs.next()){
                    int imTMP = rs.getInt(1);
                   
                    if (imTMP == rID){
                        check= false;
                        break;
                    }
                } 
                   
                if (check){
                    PreparedStatement stm;
                    try {
                    sqlInsert = "INSERT INTO Rents VALUES (?,?,?,?,?,?)";
                    stm = (PreparedStatement) connect.prepareStatement(sqlInsert);
                    stm.setInt(1, rID);
                    stm.setInt(2, cID);
                    stm.setInt(3, mID);
                    stm.setInt(4, reID);
                    stm.setString(5, day);
                    stm.setInt(6, fee);
                    stm.executeUpdate();
                    
                    } catch (SQLException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    RENT a = new RENT(rID, cID, mID,reID, day, fee);
                    tmRent.addRow(a.toObject());
                }  else {
                    JOptionPane.showMessageDialog(this, "ID DA CO");
                }
                    rentidTXT.setText("");
                    movieidTXT.setText("");
                    cusidTXT.setText("");
                    daytimeTXT.setText("");
                    feeTXT.setText("");
                } catch (Exception ex){
                   JOptionPane.showMessageDialog(this, "NHAP DAY DU THONG TIN");
                }
                
            });
            
            searchBTN.addActionListener((e) -> {
                try{
                    tmRent.setRowCount(0);
                    sqlSelect="SELECT * FROM rents where ";
                    try {
                        Statement stm= (Statement) connect.createStatement();
                        ArrayList <String> word = new ArrayList<>();

                        if (!rentidTXT.getText().equals("")) word.add("Rent_id="+rentidTXT.getText());
                        if (!cusidTXT.getText().equals("")) word.add("Citizen_id="+cusidTXT.getText());
                        if (!movieidTXT.getText().equals("")) word.add("Film_id="+movieidTXT.getText());
                        if (!daytimeTXT.getText().equals(""))word.add("dayTime ="+daytimeTXT.getText());
                        if (!remployeeidTXT.getText().equals("")) word.add("R_Employee_ID ="+remployeeidTXT.getText());
                        if (!feeTXT.getText().equals("")) word.add("Fee ="+feeTXT.getText());

                        if (word.size()==1) sqlSelect+=word.get(0);
                        else {
                            sqlSelect+=word.get(0);
                            for (int i=1;i<word.size();i++) sqlSelect+=" and "+word.get(i);
                        }


                        ResultSet rs = stm.executeQuery(sqlSelect);
                        while (rs.next()){
                            RENT a = new RENT(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getInt(6));
                            tmRent.addRow(a.toObject());
                        }
                     
                        rentidTXT.setText("");
                        movieidTXT.setText("");
                        cusidTXT.setText("");
                        daytimeTXT.setText("");
                        feeTXT.setText("");
                        remployeeidTXT.setText("");

                    } catch (SQLException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }catch (Exception exx){
                    JOptionPane.showMessageDialog(this, "NHAP THONG TIN VAO IT NHAT 1 O TRONG");
                }
                    
            });
            
            updateBTN.addActionListener((e) -> {
                try{
                    String updateSQL = "UPDATE Rents SET ";
                    if (!rentidTXT.getText().equals("")){
                        Statement stm= (Statement) connect.createStatement();
                        ArrayList <String> word = new ArrayList<>();
                        boolean check =true;
                        int cusidTMP=0,filmidTMP=0,reidTMP=0;
                        
                        if (!cusidTXT.getText().equals("") && check==true){
                            ResultSet rs = stm.executeQuery("SELECT * FROM Rents");
                            while (rs.next()){
                                if (rs.getInt(1) == Integer.parseInt(rentidTXT.getText())){
                                    cusidTMP=rs.getInt(2);
                                    break;
                                }
                            }
                            
                            rs = stm.executeQuery("SELECT * FROM Customer");
                            while (rs.next()){
                                if (rs.getInt(1)==Integer.parseInt(cusidTXT.getText())){
                                    check=false;
                                    break;
                                }
                            }
                            
    
                            
                        }
                        
                        if (!movieidTXT.getText().equals("") && check==true){
                            
                            ResultSet rs = stm.executeQuery("SELECT * FROM Rents");
                            while (rs.next()){
                                if (rs.getInt(1)== Integer.parseInt(rentidTXT.getText())){
                                    filmidTMP=rs.getInt(3);
                                    break;
                                }
                            }
                            
                            rs = stm.executeQuery("SELECT * FROM Movie");
                            while (rs.next()){
                                if (rs.getInt(1)==Integer.parseInt(movieidTXT.getText())){
                                    check=false;
                                    break;
                                }
                            }                                    
                          
                        }
                            
                        if (!remployeeidTXT.getText().equals("") && check ==true){
                            
                            ResultSet rs = stm.executeQuery("SELECT * FROM Rents");
                            while (rs.next()){
                                if (rs.getInt(1)==Integer.parseInt(rentidTXT.getText())){
                                    reidTMP=rs.getInt(4);
                                    break;
                                }
                            }
                         
                            rs = stm.executeQuery("SELECT * FROM rent_manager");
                            while (rs.next()){
                                if (rs.getInt(1)==Integer.parseInt(remployeeidTXT.getText())){
                                    check=false;
                                    break;
                                }
                            }          
                        }
                        
                        
                        if (!daytimeTXT.getText().equals("")&& check==true) word.add("dayTime="+daytimeTXT.getText());
                        
                        if (!feeTXT.getText().equals("")&& check==true) word.add("Fee="+feeTXT.getText());
                        
                       
                        if (check) {
                            stm.executeUpdate("UPDATE Customer SET Citizen_id="+cusidTXT.getText() +" WHERE Citizen_id="+String.valueOf(cusidTMP));
                            stm.executeUpdate("UPDATE Movie SET Film_id ="+movieidTXT.getText()+" WHERE Film_ID="+String.valueOf(filmidTMP));
                            stm.executeUpdate("UPDATE rent_manager SET R_Employee_ID="+remployeeidTXT.getText()+"WHERE R_Employee_ID="+String.valueOf(reidTMP));
                            if (word.size()==1) updateSQL+=word.get(0);
                            else {
                                updateSQL+=word.get(0);
                                for (int i=1;i<word.size();i++) updateSQL+=","+word.get(i);
                            }
                            updateSQL+="WHERE Rent_ID= "+rentidTXT.getText();
                            stm.executeUpdate(updateSQL);                           
                        }else {
                            JOptionPane.showMessageDialog(this, "ID NAY DA CO VUI LONG DOI ID");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "DIEN ID VA THONG TIN MUON THAY DOI");
                    }    
                } catch (SQLException exc){
                    
                }
            });
            
            deleteBTN.addActionListener((e) -> {
                try {
                    if (!rentidTXT.getText().equals("")){
                        String rent_id= rentidTXT.getText();
                        Statement stm= (Statement) connect.createStatement();
                        String deleteSQL ="DELETE FROM rents WHERE Rent_ID= ";
                    try {
                        deleteSQL+=rent_id+';';
                        stm.executeUpdate(deleteSQL);
                        } catch (SQLException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }else {
                        JOptionPane.showMessageDialog(this, "NHAP ID DE XOA");
                    }
                    
                    rentidTXT.setText("");
                    movieidTXT.setText("");
                    cusidTXT.setText("");
                    daytimeTXT.setText("");
                    remployeeidTXT.setText("");
                    feeTXT.setText("");
                } catch (MySQLSyntaxErrorException exx) {
                    
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }                
            });
            
            getBTN.addActionListener((e) -> {
                try {
                    rentidTXT.setText("");
                    movieidTXT.setText("");
                    cusidTXT.setText("");
                    daytimeTXT.setText("");
                    feeTXT.setText("");
                    remployeeidTXT.setText("");
                    tmRent.setRowCount(0);
                    Statement stm= (Statement) connect.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM Rents");
                    while (rs.next()){
                        RENT a = new RENT(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getInt(6));
                        tmRent.addRow(a.toObject());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
            totaldBTN.addActionListener((e) -> {
                  Total1Day a = null;
                try {
                    a = new Total1Day();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                  a.show();
                  dispose();
            });
            
            avgmBTN.addActionListener((e) -> {
                avgYear a =null;
                try {
                    a = new avgYear();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                  a.show();
                  dispose();
            });
        } else {

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

        cusidTXT = new javax.swing.JTextField();
        movieidTXT = new javax.swing.JTextField();
        addBTN = new javax.swing.JButton();
        searchBTN = new javax.swing.JButton();
        daytimeTXT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        feeTXT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rentidTXT = new javax.swing.JTextField();
        updateBTN = new javax.swing.JButton();
        deleteBTN = new javax.swing.JButton();
        getBTN = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        remployeeidTXT = new javax.swing.JTextField();
        totalmBTN = new javax.swing.JButton();
        totaldBTN = new javax.swing.JButton();
        avgmBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addBTN.setText("ADD");
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });

        searchBTN.setText("SEARCH");
        searchBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBTNActionPerformed(evt);
            }
        });

        jLabel4.setText("Day");

        jLabel5.setText("Fee");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rent ID", "Citizen ID", "Film ID", "R_Employee_ID", "Day", "Fee"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Rent ID");

        jLabel2.setText("Citizen_ID");

        jLabel3.setText("Film ID");

        rentidTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentidTXTActionPerformed(evt);
            }
        });

        updateBTN.setText("UPDATE");
        updateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTNActionPerformed(evt);
            }
        });

        deleteBTN.setText("DELETE");
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });

        getBTN.setText("GET DATA");

        jLabel6.setText("R_Employee_ID");

        remployeeidTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remployeeidTXTActionPerformed(evt);
            }
        });

        totalmBTN.setText("TOTAL BILL IN MONTH");
        totalmBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalmBTNActionPerformed(evt);
            }
        });

        totaldBTN.setText("TOTAL INCOME IN DAY");

        avgmBTN.setText("AVG INCOME IN MONTH");
        avgmBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avgmBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(movieidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rentidTXT)
                                        .addComponent(daytimeTXT)
                                        .addComponent(feeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(remployeeidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cusidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(252, 252, 252)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(searchBTN)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deleteBTN)
                                            .addComponent(updateBTN))
                                        .addComponent(addBTN, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(getBTN)))
                                .addGap(115, 115, 115)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalmBTN)
                                    .addComponent(totaldBTN)
                                    .addComponent(avgmBTN))))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBTN)
                            .addComponent(totalmBTN))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(searchBTN))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(totaldBTN)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rentidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cusidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(movieidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(remployeeidTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(daytimeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(feeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(updateBTN)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteBTN)
                            .addComponent(avgmBTN))
                        .addGap(32, 32, 32)
                        .addComponent(getBTN)))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBTNActionPerformed

    private void updateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateBTNActionPerformed

    private void searchBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBTNActionPerformed

    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBTNActionPerformed

    private void rentidTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentidTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rentidTXTActionPerformed

    private void avgmBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avgmBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_avgmBTNActionPerformed

    private void totalmBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalmBTNActionPerformed
        try {
            // TODO add your handling code here:
            TotalMonth a = new TotalMonth();
            a.show();
            dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_totalmBTNActionPerformed

    private void remployeeidTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remployeeidTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_remployeeidTXTActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBTN;
    private javax.swing.JButton avgmBTN;
    private javax.swing.JTextField cusidTXT;
    private javax.swing.JTextField daytimeTXT;
    private javax.swing.JButton deleteBTN;
    private javax.swing.JTextField feeTXT;
    private javax.swing.JButton getBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField movieidTXT;
    private javax.swing.JTextField remployeeidTXT;
    private javax.swing.JTextField rentidTXT;
    private javax.swing.JButton searchBTN;
    private javax.swing.JTable table;
    private javax.swing.JButton totaldBTN;
    private javax.swing.JButton totalmBTN;
    private javax.swing.JButton updateBTN;
    // End of variables declaration//GEN-END:variables
}
