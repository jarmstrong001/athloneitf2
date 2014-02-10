package com.athloneitf.ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
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

	static {
		try {
			tkdIcon = ImageIO.read(new File("images/tkd.png"));
			tkdIcon = Common.resize(tkdIcon, 100, 100);
			skyIcon = ImageIO.read(new File("images/Skyboxing.jpg"));
			skyIcon = Common.resize(skyIcon, 100, 100);
			kickIcon = ImageIO.read(new File("images/AthloneKickboxing.jpg"));
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


}
