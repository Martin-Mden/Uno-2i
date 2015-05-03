
package Metier.Jeu;

import Outils.Trame;
import java.util.ArrayList;

public class Partie {
    
    private ArrayList<Joueur> listeConnectesNonPrets;
    private ArrayList<Joueur> listeJoueurs;
    private Tour tourActuel;
    private Pioche pioche;
    
    public Partie() {
        this("Sans libellé");                

    }

    public Tour getTourActuel() {
        return tourActuel;
    }
    
    public ArrayList<Joueur> getListeConnectesNonPrets() {
        return this.listeConnectesNonPrets;
    }
    
    public void preparerJoueur(Joueur j) {
        this.listeConnectesNonPrets.remove(j);
        this.listeJoueurs.add(j);
        System.out.println("[Partie] Connexion de \"" + j.getNom() + "\" à la partie.");
    }
    
    public Partie(String libelle) { 
        this.listeJoueurs = new ArrayList<>();
        this.listeConnectesNonPrets = new ArrayList<>();
        //System.out.println("[Partie] Création de la partie \"" + libelle + "\".");        
    }
    
    public ArrayList<Joueur> getJoueurs() {
        return this.listeJoueurs;
    }
    
    public void ajouterJoueur(Joueur j) {
        this.listeConnectesNonPrets.add(j);        
    }
    
    public Pioche getPioche() {
        return this.pioche;
    }
    
    public void initialiser() {
        
        this.pioche = new Pioche();

        System.out.println("[Partie] Distribution des cartes...");

        Carte carte;
        for(int compteur = 0; compteur < 7; compteur++) {
            for(Joueur j : this.getJoueurs()) {
                carte = this.getPioche().piocher();
                j.getMain().ajouterCarte(carte);
                Trame.envoyerPour("JSPI/" + j.getNom() + ";" + carte.getId(), j);
                Trame.envoyerSauf("JSPI/" + j.getNom() + ";", j);
            }
        }
        
        System.out.println("");
        for(Joueur j : this.getJoueurs()) {
            System.out.println("Contenu de la main du joueur \"" + j.getNom() + "\" :");
            for(Carte c : j.getMain().getCartes()) {
                System.out.print(c.getId() + ", ");
            }
            System.out.println("");
        }
        
        System.out.println("");
        
        this.getPioche().setDefausse(this.getPioche().piocher());
        System.out.println("Carte retournée en défausse : " + this.getPioche().getDefausse().getId());
        Trame.envoyer("JSPI/Défausse;" + this.getPioche().getDefausse().getId());
   
        System.out.println("");
        this.tourActuel = new Tour(true, this.getJoueurs(), 0);
    }
    
    public boolean jouer(Carte c, Joueur j){
        //Création des régles sur la partie en cours
        Regles regles = new Regles(this);
        
        //On teste si le joueur peut jouer sa carte
        if(!regles.verifier(j, c)){
            return false;
        }
        
        //Enlever la carte de la main du joueur
        this.getTourActuel().getJoueurActuel().getMain().supprimerCarteMainJoueur(c);
        
        //Poser la carte sur la pioche
        this.pioche.setDefausse(c);
        System.out.println("");
        //On passe au joueur suivant
        this.tourActuel.setJoueurSuivant();
        
        for(Joueur joueur : this.getJoueurs()) {
            System.out.println("Contenu de la main du joueur \"" + joueur.getNom() + "\" :");
            for(Carte cartes : joueur.getMain().getCartes()) {
                System.out.print(cartes.getId() + ", ");
            }
            System.out.println("");
        }
        System.out.println("Carte retournée en défausse : " + this.getPioche().getDefausse().getId());
        System.out.println("");
        System.out.println("C'est au tour de \"" + this.getTourActuel().getJoueurActuel().getNom()+"\"");
        return true;
    }
    
    public void piocher(Joueur joueur){
        if(joueur.getNom().equals(this.getTourActuel().getJoueurActuel().getNom())){
            if(!joueur.isaPioche()){
                Carte cartePiochee = this.getPioche().piocher();
                joueur.getMain().ajouterCarte(cartePiochee);
                Trame.envoyerPour("JSPI/" + joueur.getNom() + ";" + cartePiochee.getId(), joueur);
                Trame.envoyerSauf("JSPI/" + joueur.getNom() + ";", joueur);
                joueur.setaPioche(true);
                System.out.println("");                                

                for(Joueur j : this.getJoueurs()) {
                    System.out.println("Contenu de la main du joueur \"" + j.getNom() + "\" :");
                    for(Carte cartes : j.getMain().getCartes()) {
                        System.out.print(cartes.getId() + ", ");
                    }
                    System.out.println("");
                }
                System.out.println("Carte retournée en défausse : " + this.getPioche().getDefausse().getId());
                System.out.println("");
                System.out.println("C'est au tour de \"" + this.getTourActuel().getJoueurActuel().getNom()+"\"");
            }
        }
    }
    
    public void passerTour(){
        //On passe au joueur suivant
        this.tourActuel.setJoueurSuivant();
        
        for(Joueur joueur : this.getJoueurs()) {
            System.out.println("Contenu de la main du joueur \"" + joueur.getNom() + "\" :");
            for(Carte cartes : joueur.getMain().getCartes()) {
                System.out.print(cartes.getId() + ", ");
            }
            System.out.println("");
        }
        System.out.println("Carte retournée en défausse : " + this.getPioche().getDefausse().getId());
        System.out.println("");
        System.out.println("C'est au tour de \"" + this.getTourActuel().getJoueurActuel().getNom()+"\"");
    }
}
