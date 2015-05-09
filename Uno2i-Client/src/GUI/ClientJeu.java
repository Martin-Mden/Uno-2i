
package GUI;

import Metier.ConnexionJeu;
import Metier.Joueur;
import Metier.ServeurJeu;
import Outils.Trame;
import java.io.IOException;
import java.net.Socket;

public class ClientJeu extends javax.swing.JFrame {

    private ClientLobby clientLobby;
    private ConnexionJeu connexionJeu;
    
    public ClientJeu(ClientLobby clientLobby, ServeurJeu srv) {
        initComponents();
        
        this.clientLobby = clientLobby;
        
        this.setLocationRelativeTo(this.getParent()); 
        this.setIconImage(getToolkit().getImage(getClass().getClassLoader().getResource("Images/icon_uno.png")));
        System.out.println("Client de jeu démarré. Tentative de connexion au serveur de jeu...");
        
        Socket socketServeurJeu = null;
        try {
            socketServeurJeu = new Socket(srv.getAdresseIp(), srv.getPort());
            System.out.println("[ClientJeu] Connecté au serveur de jeu.");
        } 
        catch (IOException e) {
            System.err.println("[ClientJeu] Impossible de se connecter au serveur de jeu. " + e.getMessage());
        }
        
        connexionJeu = new ConnexionJeu(socketServeurJeu, new Joueur(this.clientLobby.c.getPseudo()), this);
        connexionJeu.start();
        
    
        

        //mainJoueurPanel.setLayout(lay);
        /*
        LayoutManager lay2 = new BorderLayout(100, 100);
        JPanel carte = new JPanel(lay2);
           
        mainJoueurPanel.add(carte, BorderLayout.LINE_END);       
        CarteGraphique ca = new CarteGraphique("#V8", true);      
        carte.add(ca, BorderLayout.CENTER);
        
        lay2 = new BorderLayout(100, 100);        
        carte = new JPanel(lay2);
           
        mainJoueurPanel.add(carte, BorderLayout.LINE_END);
        ca = new CarteGraphique("#J0", true);      
        carte.add(ca, BorderLayout.CENTER);*/

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pretBouton = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatWindow = new javax.swing.JTextArea();
        chatInput = new javax.swing.JTextField();
        envoyerMessageBouton = new javax.swing.JButton();
        defaussePanel = new javax.swing.JPanel();
        mainJoueurPanel = new javax.swing.JPanel();
        piocherBouton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UNO - Plateau de jeu");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pretBouton.setText("PRET");
        pretBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pretBoutonActionPerformed(evt);
            }
        });

        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        chatWindow.setEditable(false);
        chatWindow.setColumns(20);
        chatWindow.setRows(5);
        jScrollPane1.setViewportView(chatWindow);

        chatInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatInputActionPerformed(evt);
            }
        });

        envoyerMessageBouton.setText(">");
        envoyerMessageBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoyerMessageBoutonActionPerformed(evt);
            }
        });

        defaussePanel.setMaximumSize(new java.awt.Dimension(76, 112));
        defaussePanel.setMinimumSize(new java.awt.Dimension(76, 112));
        defaussePanel.setPreferredSize(new java.awt.Dimension(76, 112));

        javax.swing.GroupLayout defaussePanelLayout = new javax.swing.GroupLayout(defaussePanel);
        defaussePanel.setLayout(defaussePanelLayout);
        defaussePanelLayout.setHorizontalGroup(
            defaussePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );
        defaussePanelLayout.setVerticalGroup(
            defaussePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainJoueurPanelLayout = new javax.swing.GroupLayout(mainJoueurPanel);
        mainJoueurPanel.setLayout(mainJoueurPanelLayout);
        mainJoueurPanelLayout.setHorizontalGroup(
            mainJoueurPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );
        mainJoueurPanelLayout.setVerticalGroup(
            mainJoueurPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        piocherBouton.setText("PIOCHER");
        piocherBouton.setEnabled(false);
        piocherBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piocherBoutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(pretBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(piocherBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chatInput, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(envoyerMessageBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addComponent(defaussePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(mainJoueurPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chatInput)
                                .addComponent(piocherBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(envoyerMessageBouton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pretBouton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(defaussePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainJoueurPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
        this.clientLobby.setVisible(true);        
    }//GEN-LAST:event_formWindowClosing

    private void pretBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pretBoutonActionPerformed
        if(this.pretBouton.getText().equals("PRET")) {
            this.pretBouton.setEnabled(false);
            this.pretBouton.setText("ATTENTE");
            this.connexionJeu.indiquerPret();
        }
        else if(this.pretBouton.getText().equals("FINIR TOUR")) {
            this.pretBouton.setEnabled(false);
            this.piocherBouton.setEnabled(false);
            this.pretBouton.setText("ATTENTE");
            Trame.envoyer("JCTR/");
        }        
    }//GEN-LAST:event_pretBoutonActionPerformed

    private void piocherBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piocherBoutonActionPerformed
        Trame.envoyer("JCPI/");
        piocherBouton.setEnabled(false);
        pretBouton.setEnabled(true);
    }//GEN-LAST:event_piocherBoutonActionPerformed

    private void envoyerMessageBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envoyerMessageBoutonActionPerformed
        // TODO add your handling code here:
        String laTrame = "MCEI/"+this.clientLobby.c.getPseudo()+";"+chatInput.getText();
        Trame.envoyer(laTrame);
        System.out.println("[ClientJeu] La trame envoyée: "+laTrame);
    }//GEN-LAST:event_envoyerMessageBoutonActionPerformed

    private void chatInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chatInputActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chatInput;
    private javax.swing.JTextArea chatWindow;
    private javax.swing.JPanel defaussePanel;
    private javax.swing.JButton envoyerMessageBouton;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainJoueurPanel;
    private javax.swing.JButton piocherBouton;
    private javax.swing.JButton pretBouton;
    // End of variables declaration//GEN-END:variables
}
