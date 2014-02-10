package com.athloneitf.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.hibernate.Session;

import com.athloneitf.datatype.*;
import com.athloneitf.main.Common;
import com.athloneitf.main.Main;

public class ClassSelectDialog extends JDialog {

	private static final long serialVersionUID = -705825594149403522L;
	// private Session session=Common.startSession();

	private final JButton selectTaekwondoClassButton;
	private final JButton selectSkyboxingClassButton;
	private final JButton selectKickboxingClassButton;
	private final JPanel panel = new JPanel(new BorderLayout());

	public ClassSelectDialog(Member instructor) {

		BufferedImage tkdIcon = CommonUI.getTkdIcon();
		BufferedImage skyIcon = CommonUI.getSkyIcon();
		BufferedImage kickIcon = CommonUI.getKickIcon();
		
		Image img = new ImageIcon(tkdIcon).getImage();
		setIconImage(img);
		this.setTitle("Choose class type");

		selectTaekwondoClassButton = new JButton(new ImageIcon(tkdIcon));
		selectTaekwondoClassButton.setBorder(BorderFactory.createEmptyBorder());
		selectTaekwondoClassButton.setContentAreaFilled(false);
		selectSkyboxingClassButton = new JButton(new ImageIcon(skyIcon));
		selectSkyboxingClassButton.setBorder(BorderFactory.createEmptyBorder());
		selectSkyboxingClassButton.setContentAreaFilled(false);
		selectKickboxingClassButton = new JButton(new ImageIcon(kickIcon));
		selectKickboxingClassButton
				.setBorder(BorderFactory.createEmptyBorder());
		selectKickboxingClassButton.setContentAreaFilled(false);

		JLabel instructorLabel = new JLabel(instructor.getName()
				+ " is logged in. Please select class type");
		panel.add(instructorLabel, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel();
		GridLayout gl = new GridLayout(1, 3);
		buttonPanel.setLayout(gl);

		buttonPanel.add(selectTaekwondoClassButton);
		buttonPanel.add(selectSkyboxingClassButton);
		buttonPanel.add(selectKickboxingClassButton);
		panel.add(buttonPanel);
		JButton logoutButton = new JButton("Logout");
		panel.add(logoutButton, BorderLayout.SOUTH);

		selectTaekwondoClassButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MemberCheckInInterface mcii = new MemberCheckInInterface(
						ClassType.TAEKWONDO);
				mcii.setVisible(true);
			}

		});
		selectSkyboxingClassButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MemberCheckInInterface mcii = new MemberCheckInInterface(
						ClassType.SKYBOXING);
				mcii.setVisible(true);
			}

		});
		selectKickboxingClassButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MemberCheckInInterface mcii = new MemberCheckInInterface(
						ClassType.KICKBOXING);
				mcii.setVisible(true);
			}

		});

		this.add(panel);
		this.setSize(400, 300);
		this.setVisible(true);

	}
}
