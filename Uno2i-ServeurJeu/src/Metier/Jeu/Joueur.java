
package Metier.Jeu;

import java.net.Socket;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Joueur other = (Joueur) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }        
}
