package com.athloneitf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.athloneitf.database.DatabaseMySQL;
import com.athloneitf.datatype.Member;
import com.athloneitf.main.Common;

public class UserInterface extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static DatabaseMySQL database;

	static {
		database=new DatabaseMySQL();

	}
		
	public UserInterface(){
		
        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Athlone ITF Instructor Login");
        setSize(300,250);
        
        final JPanel loginPanel = new JPanel();
        final JLabel loginLabel = new JLabel("Enter barcode to login");
        final JTextField loginTextField = new JTextField(10);
        final JLabel resultLabel = new JLabel("                ");
        loginPanel.add(loginLabel);
        loginPanel.add(loginTextField);
        loginPanel.add(resultLabel);
        add(loginPanel);
        setVisible(true);
        
        loginTextField.requestFocusInWindow();
        loginTextField.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent event)
        	{
        		Member instructor=database.loginInstructor(loginTextField.getText());
        		if(instructor!=null) {
        			System.out.println(instructor.toString());
        			ClassSelectDialog csd=new ClassSelectDialog(instructor);
        			csd.setVisible(true);
        			UserInterface.this.dispose();
        		}
        		else {
        			resultLabel.setText("Invalid Login");
        			loginTextField.setText("");
        		}
        		
        	}
        	
        	
        });
	}
	
	
	
}
