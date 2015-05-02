
package Metier.Jeu;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Carte other = (Carte) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
