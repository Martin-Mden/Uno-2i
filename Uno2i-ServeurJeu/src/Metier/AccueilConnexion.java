
package Metier;

import Outils.Trame;
import Metier.Jeu.Joueur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AccueilConnexion extends Thread {
    
    private ServerSocket socketServeur;
    private Serveur serveur;
    
    public AccueilConnexion(ServerSocket socketServeur, Serveur serveur) {
        this.socketServeur = socketServeur;
        this.serveur = serveur;
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
                Trame.addConnexion((Connexion)t);
                t.start();  
            }
            catch(IOException e) {
                System.err.println("[AccueilConnexion] Une tentative de connexion à échoué.");
            }
        }       
        
    }
}
