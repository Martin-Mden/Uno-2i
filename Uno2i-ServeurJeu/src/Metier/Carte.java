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
public class Carte {
    
    private String id;
    private String couleur;
    private String type;
    private String valeur;
    
    public Carte(String id) {
        this.id = id;
        if(id.substring(0,1).equals("#")){
            this.type="chiffre";
            this.couleur=id.substring(1,2);
            this.valeur=id.substring(2,3);
        }
        //System.out.println("[Carte] Ajout de la carte \"" + id + "\".");
    }
    
    public String getId() {
        return this.id;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getType() {
        return type;
    }

    public String getValeur() {
        return valeur;
    }
    
}
