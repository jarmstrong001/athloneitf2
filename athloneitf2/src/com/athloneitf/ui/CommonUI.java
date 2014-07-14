package com.athloneitf.ui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.athloneitf.datatype.ClassType;
import com.athloneitf.main.Common;

public class CommonUI {

	private static BufferedImage tkdIcon = new BufferedImage(1, 1, 1);
	private static BufferedImage skyIcon = new BufferedImage(1, 1, 1);
	private static BufferedImage kickIcon = new BufferedImage(1, 1, 1);

	/*static {
			tkdIcon= toBufferedImage(Toolkit.getDefaultToolkit().getImage(CommonUI.class.getClassLoader().getResource("images\\tkd.png")));
			tkdIcon = Common.resize(tkdIcon, 100, 100);
			skyIcon = toBufferedImage(Toolkit.getDefaultToolkit().getImage(CommonUI.class.getClassLoader().getResource("images\\Skyboxing.jpg")));
			skyIcon = Common.resize(skyIcon, 100, 100);
			kickIcon = toBufferedImage(Toolkit.getDefaultToolkit().getImage(CommonUI.class.getClassLoader().getResource("images\\AthloneKickboxing.jpg")));
			kickIcon = Common.resize(kickIcon, 100, 100);
	}*/
	
	static {
		try {
			tkdIcon = ImageIO.read(new File("images\\tkd.png"));
			tkdIcon = Common.resize(tkdIcon, 100, 100);
			skyIcon = ImageIO.read(new File("images\\Skyboxing.jpg"));
			skyIcon = Common.resize(skyIcon, 100, 100);
			kickIcon = ImageIO.read(new File("images\\AthloneKickboxing.jpg"));
			kickIcon = Common.resize(kickIcon, 100, 100);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}	
	
	public static BufferedImage getTkdIcon(){
		return tkdIcon;
	}
	
	public static BufferedImage getSkyIcon(){
		return skyIcon;
	}

	public static BufferedImage getKickIcon(){
		return kickIcon;
	}
	
	public static Image getIcon(ClassType classType){
		BufferedImage icon = new BufferedImage(1, 1, 1);
		switch(classType){
		case TAEKWONDO: icon=tkdIcon;
		break;
		case SKYBOXING: icon=skyIcon;
		break;
		case KICKBOXING: icon=kickIcon; 
		break;
		}
		return new ImageIcon(icon).getImage();
		
	}
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}


}
