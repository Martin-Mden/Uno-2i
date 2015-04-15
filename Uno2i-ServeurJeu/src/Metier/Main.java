
package Metier;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Main {
    
    private ArrayList<Carte> listeCartesEnMain;
    
    public Main() {
        this.listeCartesEnMain = new ArrayList<>();
    }
    
    public ArrayList<Carte> getCartes() {
        return this.listeCartesEnMain;
    }
    
    public void ajouterCarte(Carte c) {
        this.listeCartesEnMain.add(c);
    }
    
    public boolean possedeCarte(Carte c) {
        for(Carte carteMain: this.getCartes()) {
            if(c.getId().equals(carteMain.getId()))
                return true;
        }
        
        return false;
    }
    
    public void supprimerCarteMainJoueur(Carte c){
        if(!this.listeCartesEnMain.remove(c))
            System.out.println("[Main] ça ne supprime pas");
        else
            System.out.println("[Main] ça supprime");
    }
    
}
