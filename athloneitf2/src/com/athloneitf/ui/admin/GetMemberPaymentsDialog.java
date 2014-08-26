package com.athloneitf.ui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.athloneitf.main.Common;
import com.athloneitf.ui.CommonUI;

public class GetMemberPaymentsDialog extends JDialog {
	
	private final JPanel mainPanel=new JPanel(new FlowLayout());
	private final JButton exitButton=new JButton("Exit");
	private final JButton getPaymentsButton=new JButton("Get Payments");
	private final JTextField memberNameField=new JTextField(26);
	private final JLabel memberNameLabel=new JLabel("Member Name");
	private final JPanel paymentsPanel=new JPanel();
	private final JTable paymentsTable=new JTable();
	
	
	
	public GetMemberPaymentsDialog(){
		
		getPaymentsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				paymentsTable.setModel(Common.getPaymentsForMember(Common.getMemberFromName(memberNameField.getText())));
				
			}
			
		});
		
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminFrame();
				
			}
			
		});
		mainPanel.add(memberNameLabel);
		mainPanel.add(memberNameField);
		mainPanel.add(getPaymentsButton);
		mainPanel.add(exitButton);
		
		this.add(mainPanel,BorderLayout.NORTH);
		paymentsTable.setPreferredSize(new Dimension(400,100));
		paymentsPanel.add(paymentsTable);
		this.add(paymentsPanel,BorderLayout.CENTER);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}
}
