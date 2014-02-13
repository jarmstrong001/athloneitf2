package com.athloneitf.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.athloneitf.datatype.*;
import com.athloneitf.main.Common;

public class MemberCheckInInterface extends JFrame {

	private static final long serialVersionUID = 6927324337756533205L;
	private Member instructor;
	private final JLabel clock;
	final JList memberList = new JList();
	
	private ActionListener updateClockAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clock.setText(new Date().toString());
		}
	};

	public MemberCheckInInterface(ClassType classType) {
		setIconImage(CommonUI.getIcon(classType));
		instructor = Common.getLoggedInInstructor();
		setTitle("Athlone ITF - instructor " + instructor.getName());
		setSize(400, 400);
		Timer t = new Timer(1000, updateClockAction);
		t.start();

		final JPanel loginPanel = new JPanel();
		final JPanel listPanel = new JPanel();
		final JLabel scanInLabel = new JLabel(
				"Enter barcode to scan into class");
		final JTextField scanInTextField = new JTextField(10);
		final JLabel resultLabel = new JLabel("                ");
		final JTextArea paymentTextArea = new JTextArea(5,25);
		
		updateMemberList();
		
		listPanel.add(memberList);
		loginPanel.add(scanInLabel);
		loginPanel.add(scanInTextField);
		loginPanel.add(resultLabel);
		loginPanel.add(paymentTextArea);
		add(loginPanel, BorderLayout.CENTER);
		add(listPanel, BorderLayout.EAST);
		File file;
		System.out.println("ClassType=" + classType.name());

		if (classType == ClassType.TAEKWONDO)
			file = new File("images/tkd.png");
		else if (classType == ClassType.KICKBOXING)
			file = new File("images/AthloneKickboxing.jpg");
		else
			file = new File("images/Skyboxing.jpg");
		BufferedImage myPicture = null;
		try {
			BufferedImage myOversizedPicture = ImageIO.read(file);
			myPicture = Common.resize(myOversizedPicture, 300, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
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
						updateMemberList();
					} else {
						Common.memberScanIn(member);
						resultLabel.setText(member.getName()
								+ " scanned into class at "
								+ Common.timeFormat.format(new Date()));
						System.out.println("Payment Status:"
								+ parseStringArrayList(Common
										.getPaymentStatusTkd(member)));
						paymentTextArea.setText(parseStringArrayList(Common
								.getPaymentStatusTkd(member)));
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
		memberList.setListData(Common.getListOfScannedInMembers().toArray());
	}

	private String parseStringArrayList(ArrayList<String> input) {
		String returnValue = "";
		for (String s : input) {
			returnValue+=(s + "\n");
		}
		System.out.println(returnValue + " payment info");
		return returnValue;
	}

}
