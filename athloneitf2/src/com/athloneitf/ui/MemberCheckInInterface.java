package com.athloneitf.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
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
	private final JPanel listPanel = new JPanel(new BorderLayout());
	private final JLabel scanInLabel = new JLabel(
			"Enter barcode to scan into class");	
	private final JLabel resultLabel = new JLabel("                ");
	private final JTextArea paymentTextArea = new JTextArea(5,25);
	private final Timer paymentAreaTimer;
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
			resultLabel.setText("All members"
					+ " auto scanned out of class by Instructor at "
					+ Common.timeFormat.format(new Date()));
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
		paymentAreaTimer=new Timer(5000,blankPaymentTextAreaAction);
		paymentAreaTimer.start();

		
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
					PaymentDialog pd=new PaymentDialog(selectedMember,globalClassType,"");
					pd.setVisible(true);
					MemberCheckInInterface.this.dispose();
				}
			}
		};
		memberList.addMouseListener(mouseListenerSingleClick);
		memberList.addMouseListener(mouseListenerDoubleClick);
		
		updateMemberList();
		JScrollPane memberListScrollPane=new JScrollPane(memberList);
		listPanel.add(memberListScrollPane,BorderLayout.CENTER);
		memberList.setPreferredSize(new Dimension(150,200));
		listPanel.add(scanOutAllButton,BorderLayout.SOUTH);
		scanOutAllButton.addActionListener(scanOutAllAction);
		scanOutAllButton.setSize(new Dimension(150,30));
		loginPanel.add(scanInLabel);
		loginPanel.add(scanInTextField);
		loginPanel.add(resultLabel); 
		paymentTextArea.setEnabled(false);
		loginPanel.add(paymentTextArea);
		endClassButton.addActionListener(endClassAction);
		loginPanel.add(endClassButton);
		add(loginPanel, BorderLayout.CENTER);
		add(listPanel, BorderLayout.EAST);
		File file;
		System.out.println("ClassType=" + classType.name());

		
		JLabel picLabel = new JLabel(new ImageIcon(CommonUI.getPic(classType)));
		add(picLabel, BorderLayout.NORTH);

		clock = new JLabel(new Date().toString());

		add(clock, BorderLayout.SOUTH);

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
						resultLabel.setText(member.getName()
								+ " scanned out of class at "
								+ Common.timeFormat.format(new Date()));
						paymentTextArea.setText("");
						updateMemberList();
					} else {
						Common.memberScanIn(member,ClassType.TAEKWONDO);
						resultLabel.setText(member.getName()
								+ " scanned into class at "
								+ Common.timeFormat.format(new Date()));
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
		paymentAreaTimer.start();
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
