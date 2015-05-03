package Outils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelImage extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Rectangle bounds;
	private BufferedImage image;
	private boolean max;


	public PanelImage() {
		setOpaque(false);
		bounds = new Rectangle();
	}

	public PanelImage(BufferedImage image) {
		this();
		setImage(image);
	}

	public PanelImage(BufferedImage image, boolean max) {
		this(image);
		setMax(max);
	}

	public PanelImage(LayoutManager layout) {
		this();
		setLayout(layout);
	}

	public Rectangle getZone() {
		return bounds;
	}

	@Override
	public void paintComponent(Graphics g) {
		if(image!=null) {
			setZone(image);
			g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
		}
		super.paintComponent(g);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public PanelImage tailleImage() {
		if(image != null)
			setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		return this;
	}

	public void setMax(boolean max) {
		this.max = max;
	}

	public void setZone(BufferedImage image) {
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = getWidth();
		bounds.height = getHeight();
		if(!max) {
			if(getHeight() - image.getHeight() > getWidth() - image.getWidth()) {
				bounds.height = (image.getHeight()*bounds.width)/image.getWidth();
				bounds.y = (getHeight()-bounds.height)/2;
			}
			else {
				bounds.width = (image.getWidth()*bounds.height)/image.getHeight();
				bounds.x = (getWidth()-bounds.width)/2;
			}
		}
	}
	

}
