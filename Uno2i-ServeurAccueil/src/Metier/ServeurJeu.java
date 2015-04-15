/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "<html><b>" + this.getNom() + "</b> (" + this.getAdresseIp() + ":" + String.valueOf(this.getPort()) + ") - <i><font color=gray>" + this.getEtat() + "</font></i></html>";
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.adresseIp);
        hash = 61 * hash + this.port;
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
        final ServeurJeu other = (ServeurJeu) obj;
        if (!Objects.equals(this.adresseIp, other.adresseIp)) {
            return false;
        }
        if (this.port != other.port) {
            return false;
        }
        return true;
    }

}
