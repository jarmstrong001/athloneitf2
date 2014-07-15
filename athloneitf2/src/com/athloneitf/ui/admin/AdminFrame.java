package com.athloneitf.ui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.athloneitf.datatype.Member;
import com.athloneitf.ui.CommonUI;

public class AdminFrame extends JFrame {

	private final JButton addMemberButton=new JButton("Add Member");
	private final JPanel adminOptionsPanel=new JPanel();
	
	public AdminFrame(){
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMemberDialog am=new AddMemberDialog();
			}
		});
		adminOptionsPanel.add(addMemberButton);
		this.add(adminOptionsPanel);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}
}
