package com.athloneitf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import org.hibernate.Session;

import com.athloneitf.datatype.*;
import com.athloneitf.main.Common;
import com.athloneitf.main.Main;

public class ClassSelectDialog extends JDialog {
	
	private static final long serialVersionUID = -705825594149403522L;
	private Session session=Common.startSession();
	
	private final JButton selectTaekwondoClassButton=new JButton("Taekwondo Class");
	private final JButton selectSkyboxingClassButton=new JButton("Skyboxing Class");
	private final JButton selectKickboxingClassButton=new JButton("Kickboxing Class");
	private final JPanel panel=new JPanel();
	
	
	public ClassSelectDialog(Member instructor) {
		
	
	panel.add(selectTaekwondoClassButton);
	panel.add(selectSkyboxingClassButton);
	panel.add(selectKickboxingClassButton);
	selectTaekwondoClassButton.addActionListener(new ActionListener()
			{
				
				public void actionPerformed(ActionEvent e){
					MemberCheckInInterface mcii=new MemberCheckInInterface(ClassType.TAEKWONDO);
        			mcii.setVisible(true);
				}
			
			}
			);
	
	
	
	this.add(panel);
	this.setSize(400,500);
	this.setVisible(true);
	
	
			
	}
}
