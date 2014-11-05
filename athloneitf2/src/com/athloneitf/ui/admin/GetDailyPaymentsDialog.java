package com.athloneitf.ui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.athloneitf.main.Common;
import com.athloneitf.ui.CommonUI;

public class GetDailyPaymentsDialog extends JDialog {
	
	private final JPanel mainPanel=new JPanel(new FlowLayout());
	private final JButton exitButton=new JButton("Exit");
	private final JButton getPaymentsButton=new JButton("Get Payments");
	private final JLabel dateLabel=new JLabel("Date");
	private final JPanel paymentsPanel=new JPanel();
	private final JTable paymentsTable=new JTable();
	private DateModel utilDateModel=new UtilDateModel();
	private JDatePanelImpl datePanel=new JDatePanelImpl(utilDateModel);
	
	
	
	
	public GetDailyPaymentsDialog(){
		
		getPaymentsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(utilDateModel.getValue()==null){}
				else{
					paymentsTable.setModel(Common.getPaymentsForDate((Date)utilDateModel.getValue()));
				}
			}
			
		});
		
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminFrame();
				
			}
			
		});
		mainPanel.add(dateLabel);
		mainPanel.add(datePanel);
		mainPanel.add(getPaymentsButton);
		mainPanel.add(exitButton);
		
		this.add(mainPanel,BorderLayout.NORTH);
		paymentsTable.setSize(new Dimension(600,300));
		JScrollPane tableScrollPane=new JScrollPane(paymentsTable);
		tableScrollPane.setSize(new Dimension(600,300));
		tableScrollPane.setLocation(100,100);
		this.add(tableScrollPane,BorderLayout.CENTER);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}
}
