
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lahiru
 */
public class ShowAdmins extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ShowAdmins.class.getName());

    /**
     * Creates new form ShowAdmins
     */
    
    Connection conn = null;
    private DefaultTableModel tableModel;
    
    public ShowAdmins() {
        super("Show Admins");
        initComponents();
        conn = databaseConnection.connection();
        setupTable();
        loadAdminData();
    }

            /**
     * Setup the table with proper column names and model
     */
    private void setupTable() {
        // Define column names to match the database schema
        String[] columnNames = {"ID", "Mail", "Password", "Name"};
        
        // Create table model with column names and no initial data
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make table read-only
                return false;
            }
        };
        
        // Set the model to the table
        jTable1.setModel(tableModel);
        
        // Set column widths for better display
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);  //Mail
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);   // Password
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);  // Name
        
        // Enable auto-resize for better fit
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
    /**
     * Load admin data from database and populate the table
     */
    private void loadAdminData() {
        try {
            // Clear existing data
            tableModel.setRowCount(0);
            
            // SQL query to get all admins
            String sql = "SELECT id, mail, password, name FROM admin ORDER BY id";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("id"),
                        rs.getString("mail"),
                        rs.getString("password"),
                        rs.getString("name")
                    };
                    tableModel.addRow(row);
                }
                
                // Update table display
                jTable1.revalidate();
                jTable1.repaint();
                
                // Show status message
                if (tableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, 
                        "No admins found in database.", 
                        "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Optional: Show count in title
                    setTitle("Show Admins (" + tableModel.getRowCount() + "admins)");
                }
                
            }
            
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Database error while loading admins", e);
            JOptionPane.showMessageDialog(this, 
                "Error loading admin data: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Unexpected error while loading admins", e);
            JOptionPane.showMessageDialog(this, 
                "An unexpected error occurred while loading data.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Refresh the table data (useful if called from other forms)
     */
    public void refreshData() {
        loadAdminData();
    }
    
    /**
     * Search for admins by name (optional enhancement)
     */
    public void searchAdminByName(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            loadAdminData(); // Show all if search is empty
            return;
        }
        
        try {
            tableModel.setRowCount(0);
            
            String sql = "SELECT id, mail, password, name" +
                        "FROM admin WHERE name LIKE ? ORDER BY id";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%" + searchTerm.trim() + "%");
                    
                    while (rs.next()) {
                        Object[] row = {
                            rs.getInt("id"),
                            rs.getString("mail"),
                            rs.getString("password"),
                            rs.getString("name"),
                        };
                        tableModel.addRow(row);
                    }
                }
            
            jTable1.revalidate();
            jTable1.repaint();
            
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "No admins found matching: " + searchTerm, 
                    "Search Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                setTitle("Show admins - Search Results (" + tableModel.getRowCount() + " found)");
            }
            
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Database error during search", e);
            JOptionPane.showMessageDialog(this, 
                "Error searching admins: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Get selected student ID (useful for edit/delete operations)
     */
    public int getSelectedId() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
    
    /**
     * Override setVisible to refresh data when window is shown
     */
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            refreshData();
        }
        super.setVisible(visible);
    }
    
    // [Keep all your existing initComponents() and event handler methods here]
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button3 = new java.awt.Button();
        jPanel1 = new javax.swing.JPanel();
        sims = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();

        button3.setBackground(new java.awt.Color(255, 255, 0));
        button3.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        button3.setLabel("Admin");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Admins", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        sims.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        sims.setForeground(new java.awt.Color(51, 0, 153));
        sims.setText("Student Information Management System");

        home.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        home.setForeground(new java.awt.Color(0, 0, 255));
        home.setText("Lrschools");

        logo.setFont(new java.awt.Font("Cooper Black", 0, 48)); // NOI18N
        logo.setForeground(new java.awt.Color(204, 0, 51));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mail", "Password", "Name"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(153, 153, 255));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png"))); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home)
                    .addComponent(sims))
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sims, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Home");
        jCheckBoxMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home.png"))); // NOI18N
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Logout");
        jCheckBoxMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png"))); // NOI18N
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("About");
        jCheckBoxMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/about.png"))); // NOI18N
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(742, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Home object = new Home();
        object.setVisible(true);
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Home object = new Home();
        object.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Login object = new Login();
        object.setVisible(true);
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        About object = new About();
        object.setVisible(true);
    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ShowAdmins().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button3;
    private javax.swing.JLabel home;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel sims;
    // End of variables declaration//GEN-END:variables
}
