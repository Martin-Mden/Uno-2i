
package Outils;

import Outils.Images;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageCartes {
	
    private static final int NOMBRE_IMAGES = 16, LARGEUR = 285, HAUTEUR = 435;
	private static ImageCartes instance;
	private final BufferedImage[] images;

	public static ImageCartes getInstance() {
		synchronized(Images.class) {
			if(instance == null)
				instance = new ImageCartes();
			return instance;
		}
	}

	private ImageCartes() {
		BufferedImage sprite = Images.getInstance().getImage("cartes.png");
		images = new BufferedImage[NOMBRE_IMAGES];
		for(int i=0 ; i<images.length ; i++)
			images[i] = sprite.getSubimage((i % 4) * LARGEUR, (i/4) * HAUTEUR, LARGEUR, HAUTEUR);
	}
	
	public BufferedImage getImage(String type, int numero) {
		return images[getRangImage(type, numero)];
	}

	public BufferedImage getImage(String id) {
		//return getImage(carte.getType(), carte.getType() == TypeCarte.NUMERO ? ((CarteNumero) carte).getNumero() : 0);
            
            return getImage(String.valueOf(id.charAt(0)), id.charAt(0) == '#' ? Character.getNumericValue(id.charAt(2)):0);
	}

	public void dessiner(Graphics g, int width, int height, String id, boolean visible) {
		dessiner(g, 0, 0, width, height, id, visible);
	}

	public void dessiner(Graphics g, int x, int y, int width, int height, String id, boolean visible) {
            if(id != null)
		dessiner(g, visible ? getImage(id) : getImage(null, 0), x, y, width, height, String.valueOf(id.charAt(1)));
	}

	public static void dessiner(Graphics g, BufferedImage image, int x, int y, int width, int height, String couleur) {
            Color color;
            if(couleur.equals("V"))
                color = Color.GREEN;
            else if(couleur.equals("J"))
                color= Color.YELLOW;
            else if(couleur.equals("B"))
                color= Color.BLUE;
            else if(couleur.equals("R"))
                color = Color.RED;
            else
                color = Color.BLACK;
            
            g.setColor(color);
            g.fillRoundRect(x + width/75, y + height/55, width - width/33, height - height/33, width/5, height/5);
            g.drawImage(image, x, y, width, height, null);
	}
	
	public static int getRangImage(String type, int numero) {
		/*if(type != null) switch(type) {
		case "#": return numero;
		/*case PLUS_DEUX: return 10;
		case INVERSION: return 11;
		case PASSE_TOUR: return 12;
		case PLUS_QUATRE: return 13;
		case JOKER: return 14;
		}
		return 15;*/
                
            if(type.equals("#")) return numero;
            else return 15;
	}
	
}