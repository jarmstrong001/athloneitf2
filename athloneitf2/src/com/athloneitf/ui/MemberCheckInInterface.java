package com.athloneitf.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import com.athloneitf.datatype.*;
import com.athloneitf.main.Common;

public class MemberCheckInInterface extends JFrame {

	private static final long serialVersionUID = 6927324337756533205L;
	private Member instructor;
	private final JLabel clock;
	final JList memberList = new JList();
	private final JButton scanOutAllButton=new JButton("Scan Out All");
	private final JButton endClassButton=new JButton("Leave Class Checkin Screen");
	private final JTextField scanInTextField = new JTextField(10);
	private final JPanel loginPanel = new JPanel();
	private final JPanel listPanel = new JPanel();
	private final JPanel rightPanel =new JPanel();
	private final JPanel leftPanel = new JPanel();
	private final JLabel scanInLabel = new JLabel(
			"Enter barcode to scan into class");	
	private final JLabel resultLabel = new JLabel("                ");
	private final JTextArea paymentTextArea = new JTextArea(5,25);
	private final ClassType globalClassType;

	
	private ActionListener updateClockAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clock.setText(new Date().toString());
		}
	};
	
	private ActionListener blankPaymentTextAreaAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			paymentTextArea.setText("");
			paymentTextArea.setEnabled(false);
		}
	};
	
	private ActionListener scanOutAllAction=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Common.autoScanOutAll();
			resultLabel.setText("<html>All members"
					+ " auto scanned out of class by Instructor<br>at "
					+ Common.timeFormat.format(new Date())+"<html>");
			paymentTextArea.setText("");
			updateMemberList();
		}
	};
	
	private ActionListener endClassAction=new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ClassSelectDialog(Common.getLoggedInInstructor());
			
		}
		
	};

	public MemberCheckInInterface(ClassType classType) {
		globalClassType=classType;
		setIconImage(CommonUI.getIcon(classType));
		instructor = Common.getLoggedInInstructor();
		setTitle("Athlone ITF - instructor " + instructor.getName());
		setSize(CommonUI.FULLSCREEN);
		Timer updateClockTimer = new Timer(1000, updateClockAction);
		updateClockTimer.start();

		
		MouseListener mouseListenerSingleClick=new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					showPayment((Member)((JList)e.getSource()).getSelectedValue());
				}
			}
		};
		MouseListener mouseListenerDoubleClick=new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					Member selectedMember=(Member)memberList.getSelectedValue();
					PaymentDialog pd=new PaymentDialog(selectedMember,globalClassType,"",0);
					pd.setVisible(true);
					MemberCheckInInterface.this.dispose();
				}
			}
		};
		memberList.addMouseListener(mouseListenerSingleClick);
		memberList.addMouseListener(mouseListenerDoubleClick);
		
		updateMemberList();
		JScrollPane memberListScrollPane=new JScrollPane(memberList);
		listPanel.setLayout(new BorderLayout());
		listPanel.add(memberListScrollPane,BorderLayout.CENTER);
		memberList.setCellRenderer(new AITFListCellRenderer(globalClassType));
		listPanel.add(scanOutAllButton,BorderLayout.SOUTH);
		scanOutAllButton.addActionListener(scanOutAllAction);
		scanOutAllButton.setSize(new Dimension(180,20));
		clock = new JLabel(new Date().toString());
		listPanel.add(clock,BorderLayout.NORTH);
		loginPanel.setLayout(new GridBagLayout());

		scanInTextField.setPreferredSize(new Dimension(200,20));
		scanInLabel.setPreferredSize(new Dimension(200,20));
		GridBagConstraints scanLabelc=new GridBagConstraints();
		scanLabelc.gridx=0;
		scanLabelc.gridy=0;
		scanLabelc.gridwidth=2;
		scanLabelc.gridheight=1;
		scanLabelc.anchor=GridBagConstraints.FIRST_LINE_START;
		loginPanel.add(scanInLabel,scanLabelc);
		GridBagConstraints scanFieldc=new GridBagConstraints();
		scanFieldc.gridx=2;
		scanFieldc.gridy=0;
		scanFieldc.gridwidth=2;
		scanFieldc.gridheight=1;
		scanFieldc.anchor=GridBagConstraints.PAGE_START;
		scanInTextField.setMinimumSize(new Dimension(100,20));
		loginPanel.add(scanInTextField,scanFieldc);
		
		endClassButton.addActionListener(endClassAction);
		GridBagConstraints endClassc=new GridBagConstraints();
		endClassc.gridx=4;
		endClassc.gridy=0;
		endClassc.gridwidth=2;
		endClassc.gridheight=1;
		endClassc.weightx=0.1;
		endClassc.weighty=0.1;
		endClassc.anchor=GridBagConstraints.FIRST_LINE_END;
		endClassButton.setPreferredSize(new Dimension(200,20));
		loginPanel.add(endClassButton,endClassc);
		
		resultLabel.setMinimumSize(new Dimension(350,20));
		GridBagConstraints resultLabelc=new GridBagConstraints();
		resultLabelc.gridx=4;
		resultLabelc.gridy=2;
		resultLabelc.gridwidth=2;
		resultLabelc.gridheight=1;
		resultLabelc.weightx=1.0;
		resultLabelc.weighty=1.0;
		resultLabelc.anchor=GridBagConstraints.LAST_LINE_END;
		loginPanel.add(resultLabel,resultLabelc); 
		paymentTextArea.setEnabled(false);
		paymentTextArea.setMinimumSize(new Dimension(390,80));
		GridBagConstraints paymentAreac=new GridBagConstraints();
		paymentAreac.gridx=0;
		paymentAreac.gridy=1;
		paymentAreac.gridwidth=3;
		paymentAreac.gridheight=2;
		paymentAreac.weightx=1.0;
		paymentAreac.weighty=1.0;
		paymentAreac.anchor=GridBagConstraints.LINE_START;
		loginPanel.add(paymentTextArea,paymentAreac);
		
		
		JLabel picLabel = new JLabel(new ImageIcon(CommonUI.getPic(classType)));
		picLabel.setPreferredSize(new Dimension(700,500));
		leftPanel.setLayout(new GridBagLayout());
		GridBagConstraints plc=new GridBagConstraints();
		plc.gridx=0;
		plc.gridy=0;
		plc.gridheight=6;
		plc.gridwidth=10;
		plc.weightx=1.0;
		plc.weighty=1.0;
		plc.fill=GridBagConstraints.BOTH;
		plc.anchor=GridBagConstraints.CENTER;
		leftPanel.add(picLabel,plc);
		
		
		GridBagConstraints lpc=new GridBagConstraints();
		lpc.gridx=0;
		lpc.gridy=3;
		lpc.gridheight=3;
		lpc.gridwidth=6;
		lpc.weightx=1.0;
		lpc.weighty=0.1;
		lpc.fill=GridBagConstraints.HORIZONTAL;
		lpc.anchor=GridBagConstraints.PAGE_END;
		leftPanel.add(loginPanel,lpc);
		//loginPanel.setPreferredSize(new Dimension(700,180));
		loginPanel.setBackground(Color.WHITE);
		
		rightPanel.setBackground(Color.BLACK);
		listPanel.setPreferredSize(new Dimension(150,660));
		rightPanel.add(listPanel);
		rightPanel.setMaximumSize(new Dimension(150,800));
		rightPanel.setMinimumSize(new Dimension(150,800));
		//System.out.println("ClassType=" + classType.name());
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints rightPanelConstraints=new GridBagConstraints();
		rightPanelConstraints.gridx=3;
		rightPanelConstraints.gridy=0;
		rightPanelConstraints.gridwidth=1;
		rightPanelConstraints.gridheight=GridBagConstraints.REMAINDER;
		rightPanelConstraints.weightx=0.1;
		rightPanelConstraints.weighty=0.1;
		rightPanelConstraints.fill=GridBagConstraints.BOTH;

		GridBagConstraints leftPanelConstraints=new GridBagConstraints();
		leftPanelConstraints.gridx=0;
		leftPanelConstraints.gridy=0;
		leftPanelConstraints.gridwidth=2;
		leftPanelConstraints.gridheight=GridBagConstraints.REMAINDER;
		leftPanelConstraints.weightx=1.0;
		leftPanelConstraints.weighty=1.0;
		leftPanelConstraints.fill=GridBagConstraints.BOTH;
		add(leftPanel,leftPanelConstraints);
		add(rightPanel,rightPanelConstraints);
		this.setSize(CommonUI.FULLSCREEN);
		setVisible(true);

		scanInTextField.requestFocusInWindow();
		scanInTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String memberBarCode = scanInTextField.getText();
				Member member = Common.getMember(memberBarCode);
				if (member != null) {
					System.out.println(member.toString());
					if (Common.isMemberScannedIn("" + member.getMemberCode())) {
						Common.memberScanOut(member, ScanOutType.NORMAL);
						resultLabel.setText("<html>"+member.getName()
								+ " scanned out of class<br>at "
								+ Common.timeFormat.format(new Date())+"<html>");
						paymentTextArea.setText("");
						//paymentEditorPane.setText(paymentTextArea.getText());
						updateMemberList();
					} else {
						Common.memberScanIn(member,ClassType.TAEKWONDO);
						resultLabel.setText("<html>"+member.getName()
								+ " scanned into class<br>at "
								+ Common.timeFormat.format(new Date())+"<html>");
						/*System.out.println("Payment Status:"
								+ parseStringArrayList(Common
										.getPaymentStatusTkd(member)));*/
						showPayment(member);
						
						updateMemberList();
					}
				} else {
					resultLabel.setText("No Member in database for barcode "
							+ memberBarCode);

				}
				scanInTextField.setText("");
			}

		});

	}
	
	private void updateMemberList(){
		List<Member> tempList=Common.getListOfScannedInMembers();
		Collections.sort((List<Member>)tempList);
		memberList.setListData(tempList.toArray());
		
	}
	
	private void showPayment(Member member){
		paymentTextArea.setText(parseStringArrayList(Common
				.getPaymentStatus(member,globalClassType)));
		paymentTextArea.setEnabled(true);
		//paymentEditorPane.setText(paymentTextArea.getText());

		//paymentAreaTimer.start();
	}

	private String parseStringArrayList(ArrayList<String> input) {
		String returnValue = "";
		for (String s : input) {
			returnValue+=(s + "\n");
		}
		//System.out.println(returnValue + " payment info");
		return returnValue;
	}

}