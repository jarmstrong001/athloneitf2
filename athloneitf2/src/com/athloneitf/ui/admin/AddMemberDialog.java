package com.athloneitf.ui.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import com.athloneitf.datatype.Center;
import com.athloneitf.datatype.Member;
import com.athloneitf.main.Common;
import com.athloneitf.ui.ClassSelectDialog;
import com.athloneitf.ui.CommonUI;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddMemberDialog extends JDialog {
	
	private final JLabel firstNameLabel = new JLabel("First Name");
	private final JTextField firstNameTextField = new JTextField(15);
	private final JLabel surnameLabel = new JLabel("Surname");
	private final JTextField surnameTextField = new JTextField(15);
	private final JLabel centerLabel = new JLabel("Center");
	private final JLabel memberCodeLabel = new JLabel("Member Code");
	private final JTextField memberCodeTextField = new JTextField(8);
	private final JComboBox centerComboBox = new JComboBox(Center.values());
	private DateModel utilDateModel=new UtilDateModel();
	private JDatePanelImpl dobPanel=new JDatePanelImpl(utilDateModel);
	private final JButton addMemberButton=new JButton("Add Member");
	private final JButton exitButton=new JButton("Exit");
	private final JTextField messageLabel=new JTextField(20);
	private final JPanel mainPanel=new JPanel(new BorderLayout());
	private final JPanel formPanel=new JPanel(new GridLayout(4,2));
	private final JPanel buttonPanel=new JPanel(new BorderLayout());
	private final JPanel datePanel=new JPanel();
	private final JPanel centrePanel=new JPanel();
	private final JLabel emailLabel=new JLabel("Email");
	private final JTextField emailTextField=new JTextField(30);
	private final JLabel phoneNumberLabel=new JLabel("Phone Number");
	private final JTextField phoneNumberTextField=new JTextField(20);
	
	public AddMemberDialog(String message){
		messageLabel.setText(message);
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminFrame();
				
			}
			
		});
		
		addMemberButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Member m=validateInputs();
				if(m==(null)){
					//messageLabel.setText("Invalid inputs");
				}
				else {
					Common.addMember(m);
					//messageLabel.setText("Member "+m.getName()+" added");
					dispose();
					new AddMemberDialog("Member "+m.getName()+" added");
					/* utilDateModel=new UtilDateModel();
					dobPanel = new JDatePanelImpl(utilDateModel);
					*/
				}
				
			}
			
		});
		formPanel.setLayout(new GridLayout(6,2));
		formPanel.add(firstNameLabel);
		formPanel.add(firstNameTextField);
		formPanel.add(surnameLabel);
		formPanel.add(surnameTextField);
		formPanel.add(centerLabel);
		formPanel.add(centerComboBox);
		formPanel.add(memberCodeLabel);
		formPanel.add(memberCodeTextField);
		formPanel.add(emailLabel);
		formPanel.add(emailTextField);
		formPanel.add(phoneNumberLabel);
		formPanel.add(phoneNumberTextField);
		centrePanel.setLayout(new GridLayout(1,2));
		centrePanel.add(formPanel);
		datePanel.add(dobPanel);
		//mainPanel.add(dobPanel,BorderLayout.CENTER);
		centrePanel.add(datePanel);
		mainPanel.add(centrePanel,BorderLayout.CENTER);
		buttonPanel.add(messageLabel,BorderLayout.CENTER);
		buttonPanel.add(addMemberButton,BorderLayout.WEST);
		buttonPanel.add(exitButton,BorderLayout.EAST);
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
		
	}
	
	private Member validateInputs(){
		String firstName=firstNameTextField.getText();
		String surname=surnameTextField.getText();
		Date dob=(Date)utilDateModel.getValue();
		Center center=(Center)centerComboBox.getSelectedItem();
		String memberCode=memberCodeTextField.getText();
		String email=emailTextField.getText();
		String phoneNumber=phoneNumberTextField.getText();
		String fieldsRequired="";
		if(firstName==null||firstName.equals("")){fieldsRequired+="First Name ";}
		if(surname==null||surname.equals("")){fieldsRequired+="Surname ";}
		if(dob==null){fieldsRequired+="Date Of Birth ";}
		if(center==null){fieldsRequired+="Center";}
		if(!(memberCode.length()==8)){ fieldsRequired+=" Member Code must be EXACTLY 8 numbers";}
		if(firstName==null||surname==null||dob==null||center==null){messageLabel.setText("Required fields:"+fieldsRequired);}
		
		if(!(Common.getMember(memberCode)==null)){
			messageLabel.setText("Unique barcode required. "+memberCode+" already in use.");
			return null;
		}
		if(firstName.length()>0&&surname.length()>0&&memberCode.length()==8&&!(dob==(null))&&!(center==(null))){
			Member m=new Member();
			m.setFirstName(firstName);
			m.setSurname(surname);
			m.setMemberCode(Integer.valueOf(memberCode));
			m.setMemberDob(dob);
			m.setCenter(center);
			if(!(email==null)) { m.setEmail(email);}
			if(!(phoneNumber==null)) {m.setPhoneNumber(phoneNumber);}
			return m;
		}
		else return null;
	}
	
}
