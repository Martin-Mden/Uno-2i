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
    
    public ServeurJeu(String adresseIp, int port) {
        this.adresseIp = adresseIp;
        this.port = port;
        this.nom = "Sans nom";
    }
    
    @Override
    public String toString() {
        return this.adresseIp + ":" + String.valueOf(this.port) + " - En attente de joueurs...";
    }
    
}
