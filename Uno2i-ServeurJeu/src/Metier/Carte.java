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
    
    public Carte(String id) {
        this.id = id;
        //System.out.println("[Carte] Ajout de la carte \"" + id + "\".");
    }
    
    public String getId() {
        return this.id;
    }
    
}
