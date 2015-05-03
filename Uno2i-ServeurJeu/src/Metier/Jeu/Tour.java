
package Metier.Jeu;

import Outils.Trame;
import java.util.ArrayList;

public class Tour {
    
    private boolean sensNormal;
    private ArrayList<Joueur> ordreJoueurs;
    private int indexJoueurActuel;
    
    public Tour(boolean sensNormal, ArrayList<Joueur> ordreJoueurs, int indexJoueurActuel) {
        this.sensNormal = sensNormal;
        this.ordreJoueurs = ordreJoueurs;
        this.indexJoueurActuel = indexJoueurActuel;
        
        System.out.print("[Tour] Nouveau tour, dans le sens ");
        if(this.sensNormal)
            System.out.print("normal. ");
        else
            System.out.print("inverse. ");
        
        System.out.print("C'est au tour de \"" + this.ordreJoueurs.get(indexJoueurActuel).getNom() + "\".");
        System.out.println("");
        Trame.envoyer("JSTI/" + this.ordreJoueurs.get(this.indexJoueurActuel).getNom());
    }
    
    public Joueur getJoueurSuivant() {
        if((this.indexJoueurActuel+1) != (ordreJoueurs.size()))
            return ordreJoueurs.get(this.indexJoueurActuel+1);
        else
            return this.tourSuivant().getJoueurActuel();
    }  
    
    public void setJoueurSuivant(){
        if((this.indexJoueurActuel+1) != (ordreJoueurs.size()))
            this.indexJoueurActuel++;
        else
            this.indexJoueurActuel = this.tourSuivant().indexJoueurActuel;
        
        this.ordreJoueurs.get(this.indexJoueurActuel).setaPioche(false);
        Trame.envoyer("JSTI/" + this.ordreJoueurs.get(this.indexJoueurActuel).getNom());
    }
    
    public Joueur getJoueurActuel() {
        return ordreJoueurs.get(this.indexJoueurActuel);
    }  
            
    public void inverserSens() {
        this.sensNormal = !this.sensNormal;
    }
    
    public Tour tourSuivant() {
        if(this.sensNormal)
            return new Tour(this.sensNormal, this.ordreJoueurs, 0);
        else
            return new Tour(this.sensNormal, this.ordreJoueurs, this.ordreJoueurs.size());
    }
    
}
