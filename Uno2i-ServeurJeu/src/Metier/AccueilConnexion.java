
package Metier;

import Metier.Jeu.Joueur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AccueilConnexion extends Thread {
    
    private ServerSocket socketServeur;
    private Serveur serveur;
    private ArrayList<Connexion> listeConnexions;
    
    public AccueilConnexion(ServerSocket socketServeur, Serveur serveur) {
        this.socketServeur = socketServeur;
        this.serveur = serveur;
        this.listeConnexions = new ArrayList<>();
    }
    
    public void desactiverConnexion() {
        this.socketServeur = null;
    }
    
    @Override
    public void run() {
      
        System.out.println("[AccueilConnexion] En attente de connexion des joueurs...");
        Socket socketClient;
        while(this.socketServeur != null && !Thread.currentThread().isInterrupted()) {
            try {
                socketClient = socketServeur.accept();                         
                Thread t = new Connexion(socketClient, new Joueur(socketClient), serveur);
                this.listeConnexions.add((Connexion)t);
                t.start();  
            }
            catch(IOException e) {
                System.err.println("[AccueilConnexion] Une tentative de connexion à échoué.");
            }
        }       
        
    }
    
    public void envoyerTrame(String trame) {
        for(Connexion c : this.listeConnexions) {
            c.envoyerTrame(trame);
        }
    }
    
}
