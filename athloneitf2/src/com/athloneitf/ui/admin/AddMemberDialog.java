package com.athloneitf.ui.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddMemberDialog extends JDialog {
	
	private final JLabel firstNameLabel = new JLabel("First Name");
	private final JTextField firstNameTextField = new JTextField(15);
	private final JLabel surnameLabel = new JLabel("Surname");
	private final JTextField surnameTextField = new JTextField(15);
	private final JLabel familyLabel = new JLabel("Family");
	private final JCheckBox familyCheckBox = new JCheckBox();
	private final DateModel utilDateModel=new UtilDateModel();
	private final JDatePanelImpl dobPanel=new JDatePanelImpl(utilDateModel);
	private final JButton addMemeberButton=new JButton("Add Member");
	private final JButton exitButton=new JButton("Exit");
	private final JPanel mainPanel=new JPanel(new BorderLayout());
	private final JPanel formPanel=new JPanel(new GridLayout(3,2));
	private final JPanel buttonPanel=new JPanel();
	private final JPanel datePanel=new JPanel();
	
	public AddMemberDialog(){
		formPanel.add(firstNameLabel);
		formPanel.add(firstNameTextField);
		formPanel.add(surnameLabel);
		formPanel.add(surnameTextField);
		formPanel.add(familyLabel);
		formPanel.add(familyCheckBox);
		mainPanel.add(formPanel,BorderLayout.WEST);
		datePanel.add(dobPanel);
		mainPanel.add(dobPanel,BorderLayout.CENTER);
		buttonPanel.add(addMemeberButton);
		buttonPanel.add(exitButton);
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setVisible(true);
		
	}
	
}
