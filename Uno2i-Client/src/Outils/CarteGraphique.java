package Outils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class CarteGraphique extends JPanel {
    private static final long serialVersionUID = 1L;
    private final String carte;
    private boolean survol, actif, visible;


    public CarteGraphique(String carte, boolean visible) {      
        this.carte = carte;
        this.visible = visible;
        actif = true;
        setOpaque(false);
        setPreferredSize(new Dimension(76, 112));
    }

    public void setAffiche(boolean visible) {
        this.visible = visible;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
        repaint();
    }

    public void setSurvol(boolean survol) {
        this.survol = survol;
        repaint();
    }

    public void dessine(Graphics2D g, Color couleur) {
        Composite tmp = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));
        g.setColor(couleur);
        g.fillRoundRect(0, 0, getWidth() - getWidth()/40, getHeight() - getHeight()/40, getWidth()/7, getHeight()/7);
        g.setComposite(tmp);
    }

    public String getCarte() {
        return carte;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageCartes.getInstance().dessiner(g, getWidth(), getHeight(), carte, visible);
        if(!actif)
            dessine((Graphics2D) g, Color.BLACK);
        else if(survol)
            dessine((Graphics2D) g, Color.WHITE);
        
        repaint();
    }
}