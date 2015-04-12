/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import Metier.ServeurJeu;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ServeurListCellRenderer extends JLabel implements ListCellRenderer<ServeurJeu>
{
    @Override
    public Component getListCellRendererComponent(JList<? extends ServeurJeu> jlist, ServeurJeu serveur, int cellIndex, boolean isSelected, boolean cellHasFocus)
    {
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("Images/icon-serveur.png")); 
        
        setIcon(imageIcon); 
        setText("<html><b>" + serveur.getNom() + "</b> (" + serveur.getAdresseIp() + ":" + String.valueOf(serveur.getPort()) + ") - <i><font color=gray>" + serveur.getEtat() + "</font></i></html>"); 
        
        return this;
    }
}
