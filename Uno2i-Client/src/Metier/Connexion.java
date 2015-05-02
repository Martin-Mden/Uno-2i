
package Metier;

import GUI.ClientLobby;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;

public class Connexion extends Thread {
    
    private Socket socketServeurAccueil;
    private DefaultListModel listeServeursJeu;
    private BufferedReader in;
    private PrintWriter out;
    private String pseudo;
    private boolean actualisable;
    private boolean connected;
    
    public void stopper() {
        this.connected = false;
    }
    
    public Connexion(String pseudo) {
        this.pseudo = pseudo;
        this.connected = true;
    }
    
    @Override
    public void run() {
        
        try {
            // Connexion au serveur d'accueil
            this.socketServeurAccueil = new Socket("127.0.0.1", 2000);
            this.out = new PrintWriter(this.socketServeurAccueil.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socketServeurAccueil.getInputStream()));
        }
        catch(UnknownHostException e) {
            System.err.println("[Connexion] Impossible de se connecter au serveur d'accueil (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[Connexion] Impossible de se connecter au serveur d'accueil : " + e.getMessage());
        }

        this.out.println("CCCI/" + this.pseudo);
        
        while(this.connected) {
            if(actualisable && listeServeursJeu != null) {
                this.out.println("CCCI/" + this.pseudo);

                // Initiation de la communication avec le serveur central
                this.out.println("CCAI/");
                System.out.println("[ActualisationServeurs] Trame de demande d'actualisation envoyée. Attente de la réponse...");

                // Attente d'une réponse
                try {
                    String trame, trameEnTete, trameContenu;                
                    while((trame = this.in.readLine()) != null) { // Trame attendue : CLAR/
                        System.out.println("[ActualisationServeurs] Réponse reçu.");
                        System.out.println("[ActualisationServeurs] Trame reçu : " + trame);

                        trameEnTete = trame.split("/")[0];

                        trameContenu = "";
                        if(trame.split("/").length != 1)
                            trameContenu = trame.split("/")[1];
                        else
                            System.out.println("[ActualisationServeurs] Trame sans contenu.");
                        
                        if(trameEnTete.charAt(0) == 'C' && trameEnTete.charAt(1) == 'L' && trameEnTete.charAt(2) == 'A' && trameEnTete.charAt(3) == 'R') {
                            if(!trameContenu.isEmpty()) {
                                for(String serveur : trameContenu.split(";")) {
                                    ServeurJeu s = new ServeurJeu(serveur.split(":")[0], Integer.parseInt(serveur.split(":")[1]));
                                    s.setNom(serveur.split(":")[2]);
                                    s.setEtat(serveur.split(":")[3]);

                                    if(this.listeServeursJeu.contains(s)) {
                                        ((ServeurJeu)this.listeServeursJeu.getElementAt(this.listeServeursJeu.indexOf(s))).setEtat(s.getEtat());
                                        ((ServeurJeu)this.listeServeursJeu.getElementAt(this.listeServeursJeu.indexOf(s))).setNom(s.getNom());
                                    }
                                    else
                                        this.listeServeursJeu.addElement(s);

                                    ClientLobby.actualiser();
                                }
                            }
                        }
                        break;


                    }
                }
                catch(IOException e) {
                    System.err.println("[ActualisationServeurs] Erreur lors de la réception de trames : " + e.getMessage());
                }
            }
            
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.err.println("[ActualisationServeurs] Erreur lors de l'attente de rafraîchissement : " + e.getMessage());
            }
        }
    }
    
    public void deconnecter() {
        // Envoi d'une trame de déconnexion
        this.out.println("CCDI/");
    }
    
    public void setListModel(DefaultListModel l) {
        this.listeServeursJeu = l;
    }
    
    public void setActualisation(boolean b) {
        this.actualisable = b;
    }
    
    public String getPseudo() {
        return this.pseudo;
    }
    
}
