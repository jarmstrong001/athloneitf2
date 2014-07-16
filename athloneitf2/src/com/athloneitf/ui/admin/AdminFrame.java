package com.athloneitf.ui.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.athloneitf.datatype.Member;
import com.athloneitf.main.Common;
import com.athloneitf.ui.ClassSelectDialog;
import com.athloneitf.ui.CommonUI;

public class AdminFrame extends JFrame {

	private final JButton addMemberButton=new JButton("Add Member");
	private final JButton getMemberPayments=new JButton("Get Member Payments");
	private final JPanel adminOptionsPanel=new JPanel();
	private final JLabel messageLabel=new JLabel();
	private final JButton exitButton=new JButton("Exit");
	private final Member instructor;
	
	public AdminFrame(){
		instructor=Common.getLoggedInInstructor();
		messageLabel.setText(instructor.getName()+" logged in");
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddMemberDialog am=new AddMemberDialog();
			}
		});
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ClassSelectDialog(instructor);
				
			}
			
		});
		adminOptionsPanel.add(messageLabel,BorderLayout.NORTH);
		adminOptionsPanel.add(addMemberButton,BorderLayout.CENTER);
		adminOptionsPanel.add(exitButton,BorderLayout.SOUTH);
		
		this.add(adminOptionsPanel);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}
}
