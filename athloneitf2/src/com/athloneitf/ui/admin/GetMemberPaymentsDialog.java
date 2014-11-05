package com.athloneitf.ui.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.athloneitf.datatype.Member;
import com.athloneitf.datatype.ClassType;
import com.athloneitf.main.Common;
import com.athloneitf.ui.CommonUI;
import com.athloneitf.ui.PaymentDialog;

public class GetMemberPaymentsDialog extends JDialog {
	
	private final JPanel mainPanel=new JPanel(new GridLayout(2,4));
	private final JButton exitButton=new JButton("Exit");
	private final JButton getPaymentsButton=new JButton("Get Payments");
	private final JButton addPaymentsButton=new JButton("Add Payments");
	private final JComboBox classTypeComboBox=new JComboBox(ClassType.values());
	private final JTextField memberNameField=new JTextField(26);
	private final JLabel memberNameLabel=new JLabel("Member Name");
	private final JTextField memberCodeField=new JTextField(8);
	private final JLabel memberCodeLabel=new JLabel("Member Barcode");
	private final JPanel paymentsPanel=new JPanel();
	private final JTable paymentsTable=new JTable();
	private final JLabel messageLabel=new JLabel();
	
	
	public GetMemberPaymentsDialog(){
		
		addPaymentsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0){
				Member member=null;
				if(memberNameField.getText().length()>0){
					member=Common.getMemberFromName(memberNameField.getText());
					memberNameField.setText("");
					
				}
				else if(memberCodeField.getText().length()>0){
					member=Common.getMember(memberCodeField.getText());
				    memberCodeField.setText("");
				    
				}
				else messageLabel.setText("Please enter member name or barcode");
				if(!(member==null)){new PaymentDialog(member,(ClassType)classTypeComboBox.getSelectedItem(),"",1);}
				
			}
		});
		
		getPaymentsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Member member=null;
				if(memberCodeField.getText().length()>0){
					member=Common.getMember(memberCodeField.getText());
					memberCodeField.setText("");
				}
				else if(memberNameField.getText().length()>0){
					member=Common.getMemberFromName(memberNameField.getText());
				    memberNameField.setText("");
				}
				else messageLabel.setText("Please enter member name or barcode");
				if(!(member==null)){paymentsTable.setModel(Common.getPaymentsForMember(member));}
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
		mainPanel.add(memberCodeLabel);
		mainPanel.add(memberCodeField);
		mainPanel.add(classTypeComboBox);
		mainPanel.add(addPaymentsButton);
		mainPanel.add(getPaymentsButton);
		mainPanel.add(exitButton);
		
		this.add(mainPanel,BorderLayout.NORTH);
		paymentsTable.setSize(new Dimension(600,300));
		JScrollPane tableScrollPane=new JScrollPane(paymentsTable);
		tableScrollPane.setSize(new Dimension(600,300));
		tableScrollPane.setLocation(100,100);
		this.add(tableScrollPane,BorderLayout.CENTER);
		this.add(messageLabel,BorderLayout.SOUTH);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}
}