
package Metier.Jeu;

import java.net.Socket;

public class Joueur {
    
    private String nom;
    private Socket socketClient;
    
    private Main mainJoueur;
    private boolean aPioche;
    
    public Joueur(String nom) {
        //System.out.println("[Joueur] Création du joueur \"" + nom + "\".");
        this.nom = nom;
        this.mainJoueur = new Main();
        this.aPioche = false;
    }
    
    public Joueur(Socket socket) {
        this("Connexion...");
        this.socketClient = socket;
    }
    
    public Main getMain() {
        return this.mainJoueur;
    }
    
    public String getNom() {
        return this.nom;
    }

    public boolean isaPioche() {
        return aPioche;
    }

    public void setaPioche(boolean aPioche) {
        this.aPioche = aPioche;
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}
