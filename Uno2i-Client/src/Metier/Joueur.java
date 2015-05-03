
package Metier;

import java.util.ArrayList;

public class Joueur {
    
    private ArrayList<String> listeCartesEnMain;
    private String carteDefausse;
    private String pseudo;
    
    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.listeCartesEnMain = new ArrayList<>();
    }
    
    public ArrayList<String> getListeCartesEnMain() {
        return this.listeCartesEnMain;
    }

    public String getPseudo() {
        return pseudo;
    }    
    
    public void addCarteEnMain(String carte) {
        this.listeCartesEnMain.add(carte);
    }
    
    public void removeCarteEnMain(String carte) {
        this.listeCartesEnMain.remove(carte);
    }

    public String getCarteDefausse() {
        return carteDefausse;
    }

    public void setCarteDefausse(String carteDefausse) {
        this.carteDefausse = carteDefausse;
    }
    
    
    
}
