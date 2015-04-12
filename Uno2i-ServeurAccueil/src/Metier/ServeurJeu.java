/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author Martin
 */
public class ServeurJeu {
    
    private String adresseIp;
    private int port;
    private String nom;
    private String etat;
    
    public ServeurJeu(String adresseIp, int port) {
        this.adresseIp = adresseIp;
        this.port = port;
        this.nom = "Récupération en cours...";
        this.etat = "Récupération en cours...";
    }
    
    public String getAdresseIp() {
        return this.adresseIp;
    }

    public int getPort() {
        return port;
    }

    public String getNom() {
        return nom;
    }  

    public String getEtat() {
        return etat;
    }    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}
