/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Metier.Connexion;
import javax.swing.JOptionPane;
/**
 *
 * @author Guillaume
 */
public class Client extends javax.swing.JFrame {

    Connexion con;
    
    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
        
        this.setLocationRelativeTo(this.getParent()); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saisiePseudo = new javax.swing.JTextField();
        boutonConnexion = new javax.swing.JButton();
        boutonDeconnexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        boutonConnexion.setText("Connexion");
        boutonConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonConnexionActionPerformed(evt);
            }
        });

        boutonDeconnexion.setText("Déconnecté");
        boutonDeconnexion.setEnabled(false);
        boutonDeconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonDeconnexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(saisiePseudo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boutonConnexion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boutonDeconnexion)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saisiePseudo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boutonConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boutonDeconnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boutonConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonConnexionActionPerformed
        if(!this.saisiePseudo.getText().isEmpty()) {
            con = new Connexion(this.saisiePseudo.getText());
            con.start();
            
            // Empêcher de nouvelles connexion
            this.boutonConnexion.setEnabled(false);            
            this.saisiePseudo.setEnabled(false);
            
            this.boutonConnexion.setText("Connecté");
            this.boutonDeconnexion.setText("Déconnexion");
            
            this.boutonDeconnexion.setEnabled(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un pseudo.");
        }
    }//GEN-LAST:event_boutonConnexionActionPerformed

    private void boutonDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonDeconnexionActionPerformed
                
        con.deconnecter();
        
        this.boutonDeconnexion.setEnabled(false);
        this.saisiePseudo.setEnabled(true);

        this.boutonConnexion.setText("Connecté");
        this.boutonDeconnexion.setText("Déconnexion");
        
        this.boutonConnexion.setEnabled(true);        
    }//GEN-LAST:event_boutonDeconnexionActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonConnexion;
    private javax.swing.JButton boutonDeconnexion;
    private javax.swing.JTextField saisiePseudo;
    // End of variables declaration//GEN-END:variables
}
