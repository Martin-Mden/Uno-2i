
package Metier;

import Outils.CarteGraphique;
import Outils.Outils;
import Outils.Trame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConnexionJeu extends Thread {
    
    private Socket socketServeurJeu;
    private BufferedReader in;
    private PrintWriter out;
    private Joueur joueur;
    private JFrame fenetre;
    
    public ConnexionJeu(Socket socketServeurJeu, Joueur joueur, JFrame fenetre) {
        this.joueur = joueur;
        this.socketServeurJeu = socketServeurJeu;
        this.fenetre = fenetre;        
    }
    
    @Override
    public void run() {
        
        try {
            this.out = new PrintWriter(this.socketServeurJeu.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socketServeurJeu.getInputStream()));
            Trame.initialiser(this);
        }
        catch(UnknownHostException e) {
            System.err.println("[ConnexionJeu] Impossible de se connecter au serveur de jeu (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[ConnexionJeu] Impossible de se connecter au serveur de jeu : " + e.getMessage());
        }

        // Envoi du pseudo
        String trame = "CCCI/" + joueur.getPseudo();
        this.out.println(trame);
        System.out.println("[ConnexionJeu] Trame envoyée : " + trame);
        
        // Communication avec le jeu
        try {
            String trameEnTete, trameContenu;                
            while((trame = this.in.readLine()) != null) {                
                trameEnTete = trame.split("/")[0];

                System.out.println("[ConnexionJeu] Trame reçu : " + trame);
                
                trameContenu = "";
                if(trame.split("/").length != 1)
                    trameContenu = trame.split("/")[1];
                else
                    System.out.println("[ActualisationServeurs] Trame sans contenu.");
                
                if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'D' && trameEnTete.charAt(3) == 'I') {
                    JLabel decompteLabel = Outils.getComponentByName(fenetre, "infoLabel");
                    if(Integer.valueOf(trameContenu) == -1) {
                        decompteLabel.setText("En attente d'autres joueurs...");
                    }
                    else if(Integer.valueOf(trameContenu) == 1) {
                        decompteLabel.setText("1 seconde avant le début de la partie...");
                    }
                    else if(Integer.valueOf(trameContenu) == 0) {
                        decompteLabel.setText("La partie va commencer...");
                    }
                    else {
                        decompteLabel.setText(trameContenu + " secondes avant le début de la partie...");
                    }
                    
                }
                else if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'T' && trameEnTete.charAt(3) == 'I') {
                    JLabel infoLabel = Outils.getComponentByName(fenetre, "infoLabel");
                    if(trameContenu.equals(joueur.getPseudo())) {
                        infoLabel.setText("C'est à vous de jouer !");
                        JButton bouton = Outils.getComponentByName(fenetre, "pretBouton");
                        bouton.setText("FINIR TOUR");
                        bouton.setEnabled(false);
                        
                        JButton bouton2 = Outils.getComponentByName(fenetre, "piocherBouton");
                        bouton2.setEnabled(true);
                    }
                    else {
                        infoLabel.setText("C'est au tour de " + trameContenu + ".");
                    }                                         
                }
                else if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'P' && trameEnTete.charAt(3) == 'I') { // Pioche d'une carte
                    System.out.println("[ActualisationServeurs] Trame reçu : " + trame);
                    if(trameContenu.split(";")[0].equals(joueur.getPseudo())) {
                        joueur.addCarteEnMain(trameContenu.split(";")[1]);
                        
                        // Organisation des cartes en main
                        JPanel mainJoueurPanel = Outils.getComponentByName(fenetre, "mainJoueurPanel");
                        mainJoueurPanel.removeAll();
                        LayoutManager lay = new BoxLayout(mainJoueurPanel, BoxLayout.LINE_AXIS);
                        mainJoueurPanel.setLayout(lay);
                        mainJoueurPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                        mainJoueurPanel.add(Box.createHorizontalGlue());
                        
                        for(String carte : this.joueur.getListeCartesEnMain()) {
                            mainJoueurPanel.add(new CarteGraphique(carte, true));
                            mainJoueurPanel.add(Box.createRigidArea(new Dimension((120 / this.joueur.getListeCartesEnMain().size()) - ((this.joueur.getListeCartesEnMain().size() - 7) * 10), 0)));
                        }                        
                        
                        mainJoueurPanel.revalidate();
                        mainJoueurPanel.repaint();
                    }
                    else if(trameContenu.split(";")[0].equals("Défausse")) {
                        joueur.setCarteDefausse(trameContenu.split(";")[1]);
                        System.out.println("[ConnexionJeu] Affiche défausse"+joueur.getCarteDefausse());
                        LayoutManager lay = new BorderLayout();
                        JPanel defaussePanel = Outils.getComponentByName(fenetre, "defaussePanel");
                        defaussePanel.setLayout(lay);
                        CarteGraphique ca = new CarteGraphique(trameContenu.split(";")[1], true);      
                        defaussePanel.add(ca, BorderLayout.CENTER);
                        
                        defaussePanel.revalidate();
                        defaussePanel.repaint();
                    }
                }
                else if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'J' && trameEnTete.charAt(3) == 'R') {
                    // Contenu : CARTE JOUEE (à enlever à la main) ; NOUVELLE CARTE DEFAUSSE (à updater sur l'interface)
                    System.out.println("[ActualisationServeurs] Trame reçu : " + trame);
                                        
                    joueur.removeCarteEnMain(trameContenu.split(";")[0]);
                    joueur.setCarteDefausse(trameContenu.split(";")[1]);
                    
                    // Organisation des cartes en main
                    JPanel mainJoueurPanel = Outils.getComponentByName(fenetre, "mainJoueurPanel");
                    mainJoueurPanel.removeAll();
                    LayoutManager lay = new BoxLayout(mainJoueurPanel, BoxLayout.LINE_AXIS);
                    mainJoueurPanel.setLayout(lay);
                    mainJoueurPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                    mainJoueurPanel.add(Box.createHorizontalGlue());

                    for(String carte : this.joueur.getListeCartesEnMain()) {
                        mainJoueurPanel.add(new CarteGraphique(carte, true));
                        mainJoueurPanel.add(Box.createRigidArea(new Dimension((120 / this.joueur.getListeCartesEnMain().size()) - ((this.joueur.getListeCartesEnMain().size() - 7) * 10), 0)));
                    }                        

                    mainJoueurPanel.revalidate();
                    mainJoueurPanel.repaint();
                    
                    // Update de la carte de défausse
                    lay = new BorderLayout();
                    JPanel defaussePanel = Outils.getComponentByName(fenetre, "defaussePanel");
                    defaussePanel.removeAll();
                    defaussePanel.setLayout(lay);
                    CarteGraphique ca = new CarteGraphique(trameContenu.split(";")[1], true);      
                    defaussePanel.add(ca, BorderLayout.CENTER);
                    
                    defaussePanel.revalidate();
                    defaussePanel.repaint();
                    
                    JButton bouton = Outils.getComponentByName(fenetre, "pretBouton");
                    bouton.setEnabled(false);
                    
                    JButton piocheBouton = Outils.getComponentByName(fenetre, "piocherBouton");
                    piocheBouton.setEnabled(false);
                }
                else if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'J' && trameEnTete.charAt(3) == 'E') {
                    if(trameContenu.split(";")[0].equals("Message")) {
                        JOptionPane.showMessageDialog(fenetre, trameContenu.split(";")[1]);
                    }
                }
                else if(trameEnTete.charAt(0) == 'M' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'R' && trameEnTete.charAt(3) == 'I') {
                    JTextArea jta = Outils.getComponentByName(fenetre, "chatWindow");
                    jta.append(trameContenu.split(";")[0] + "> " + trameContenu.split(";")[1] + "\n");
                }
                else if(trameEnTete.equals("Defausse")) {
                    LayoutManager lay = new BorderLayout();
                    JPanel defaussePanel = Outils.getComponentByName(fenetre, "defaussePanel");
                    defaussePanel.removeAll();
                    defaussePanel.setLayout(lay);
                    CarteGraphique ca = new CarteGraphique(trameContenu, true);      
                    defaussePanel.add(ca, BorderLayout.CENTER);
                    
                    defaussePanel.revalidate();
                    defaussePanel.repaint();
                }
                
            }
        }
        catch(IOException e) {
            System.err.println("[ConnexionJeu] Erreur de récupération des trames.");
        }
    }
    
    public void deconnecter() {
        // Envoi d'une trame de déconnexion
        this.out.println("CCDI/");
    }
    
    public void indiquerPret() {
        this.out.println("JCPI/");
    }
    
    public void envoyerMessageChat(String message) {
        //TODO
    }
    
    public void envoyerTrame(String trame) {
        this.out.println(trame);
    }
    
}
