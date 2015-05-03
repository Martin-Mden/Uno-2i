
package Outils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Singleton permettant de charger des images a partir du dossier "images" de l'archive jar, et de les conserver en memoire
 */
public class Images {
	private static final String PATH = "/Images/";
	private static Images instance;
	private final Map<String, BufferedImage> images;


	/**
	 * Design-pattern singleton. Cree l'unique instance de cette classe si elle n'existe pas encore
	 * @return l'unique instance de cette classe
	 */
	public static Images getInstance() {
		synchronized(Images.class) {
			if(instance == null)
				instance = new Images();
			return instance;
		}
	}

	/**
	 * Constructeur prive, selon le design-pattern singleton
	 */
	private Images() {
		images = new HashMap<>();
	}

	/**
	 * Charge l'image a partir du dossier "images" de l'archive jar et la memorise
	 * @param image le nom de l'image a charger a partir du dossier "images" de l'archive jar et la memorise
	 * @see BufferedImage
	 */
	public void chargeImage(String image) {
		try {
			images.put(image, ImageIO.read(getClass().getResource(PATH + image)));
		} catch(Exception e) {
			System.err.println("Impossible de trouver l'image " + image);
			images.put(image, new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY));
		}
	}

	/**
	 * Dans le cas ou l'image correspondant au nom n'a pas encore ete chargee, charge l'image via la methode {@link #chargeImage(String)}
	 * @param image le nom de l'image a obtenir
	 * @return l'image correspondant au nom specifie, depuis le dossier "images" de l'archive jar
	 */
	public BufferedImage getImage(String image) {
		if(!images.containsKey(image))
			chargeImage(image);
		return images.get(image);
	}

}