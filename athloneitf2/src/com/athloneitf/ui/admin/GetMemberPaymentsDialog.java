package com.athloneitf.ui.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.athloneitf.ui.CommonUI;

public class GetMemberPaymentsDialog extends JDialog {
	
	private final JPanel mainPanel=new JPanel(new BorderLayout());
	private final JButton exitButton=new JButton("Exit");
	
	
	public GetMemberPaymentsDialog(){
		
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminFrame();
				
			}
			
		});
		mainPanel.add(exitButton);
		this.add(mainPanel);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}
}
