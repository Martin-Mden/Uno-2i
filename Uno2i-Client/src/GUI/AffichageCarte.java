/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Guillaume
 */
public class AffichageCarte extends JPanel {
    private Image image;

    public AffichageCarte(String image) {
        this.image = getToolkit().getImage(getClass().getClassLoader().getResource(image));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0,100,100,this);
    }
}
