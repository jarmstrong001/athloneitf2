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
	private final DateModel utilDateModel=new UtilDateModel();
	private final JDatePanelImpl dobPanel=new JDatePanelImpl(utilDateModel);
	private final JButton addMemberButton=new JButton("Add Member");
	private final JButton exitButton=new JButton("Exit");
	private final JLabel messageLabel=new JLabel("");
	private final JPanel mainPanel=new JPanel(new BorderLayout());
	private final JPanel formPanel=new JPanel(new GridLayout(4,2));
	private final JPanel buttonPanel=new JPanel(new BorderLayout());
	private final JPanel datePanel=new JPanel();
	
	public AddMemberDialog(){
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
				if( m.equals(null)){
					messageLabel.setText("Invalid inputs");
				}
				else {
					Common.addMember(m);
				}
				
			}
			
		});
		formPanel.add(firstNameLabel);
		formPanel.add(firstNameTextField);
		formPanel.add(surnameLabel);
		formPanel.add(surnameTextField);
		formPanel.add(centerLabel);
		formPanel.add(centerComboBox);
		formPanel.add(memberCodeLabel);
		formPanel.add(memberCodeTextField);
		mainPanel.add(formPanel,BorderLayout.WEST);
		datePanel.add(dobPanel);
		mainPanel.add(dobPanel,BorderLayout.CENTER);
		buttonPanel.add(messageLabel,BorderLayout.NORTH);
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
		if(firstName.length()>0&&surname.length()>0&&memberCode.length()==8&&!dob.equals(null)&&!center.equals(null)){
			Member m=new Member();
			m.setFirstName(firstName);
			m.setSurname(surname);
			m.setMemberCode(Integer.valueOf(memberCode));
			m.setMemberDob(dob);
			m.setCenter(center);
			return m;
		}
		else return null;
	}
	
}
