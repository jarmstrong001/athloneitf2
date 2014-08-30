/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.athloneitf.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sourceforge.jdatepicker.*;
import net.sourceforge.jdatepicker.impl.*;

import com.athloneitf.datatype.*;
import com.athloneitf.main.Common;

public class PaymentDialog extends JDialog {

	private final JButton makePaymentButton;
	private final JButton exitButton;
	private final JPanel panel = new JPanel(new BorderLayout());
	private final JPanel listPanel = new JPanel();
	private final JPanel buttonPanel = new JPanel(new BorderLayout());
	private final JList<PaymentType> paymentTypeList = new JList<PaymentType>();
	private final JTextField paymentAmountTextField = new JTextField("");
	private final JLabel paymentAmountLabel = new JLabel("Payment Amount");
	private final JPanel paymentAmountPanel = new JPanel(new GridLayout(1, 2));
	private final JTextArea paymentStatusTextArea = new JTextArea(5, 25);
	private final JTextArea messageTextArea = new JTextArea(5, 25);
	private final JPanel paymentStatusPanel = new JPanel(new BorderLayout());
	private final DateModel utilDateModel = new UtilDateModel();
	private final JDatePanelImpl paymentToPanel = new JDatePanelImpl(
			utilDateModel);
	private final Member globalMember;
	private final ClassType globalClassType;
	private boolean dateSelected = false;
	private boolean paymentAmountSelected = false;

	public PaymentDialog(Member member, ClassType ct) {
		globalMember = member;
		globalClassType = ct;
		makePaymentButton = new JButton("Make Payment");
		makePaymentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Common.makePayment(paymentTypeList.getSelectedValue(),
						globalMember, (Date) utilDateModel.getValue(),
						getPaymentAmount());
				updatePaymentStatus(globalMember, globalClassType);
				messageTextArea.setText("Payment made for "
						+ globalMember.getName()
						+ "\n of "
						+ getPaymentAmount()
						+ " for "
						+ paymentTypeList.getSelectedValue()
								.getPaymentTypeName() + "\n up to date: "
						+ ((Date) utilDateModel.getValue()).toString());
			}

		});
		makePaymentButton.setEnabled(false);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MemberCheckInInterface(globalClassType);
			}
		});
		BufferedImage tkdIcon = CommonUI.getTkdIcon();
		BufferedImage skyIcon = CommonUI.getSkyIcon();
		BufferedImage kickIcon = CommonUI.getKickIcon();

		Image img = null;
		switch (ct) {
		case TAEKWONDO:
			img = new ImageIcon(tkdIcon).getImage();
			break;
		case SKYBOXING:
			img = new ImageIcon(skyIcon).getImage();
			break;
		case KICKBOXING:
			img = new ImageIcon(kickIcon).getImage();
			break;
		case OTHER:
			img = new ImageIcon(tkdIcon).getImage();
		default:
			img = new ImageIcon(tkdIcon).getImage();
		}

		setIconImage(img);
		this.setTitle("Enter Payment for " + member.getName());

		utilDateModel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("DateModel changed");
				setDateSelected();
				if (paymentAmountSelected && dateSelected) {
					makePaymentButton.setEnabled(true);
				}
			}
		});
		panel.add(paymentToPanel, BorderLayout.CENTER);

		paymentTypeList.setListData(Common.getPaymentTypes(ct).toArray(
				new PaymentType[1]));
		paymentTypeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				JList<PaymentType> tempList = (JList<PaymentType>) arg0
						.getSource();
				PaymentType pt = tempList.getSelectedValue();
				paymentAmountTextField.setText(String.format("%9.2f",
						pt.getPaymentAmount()));
				setPaymentAmountFieldSelected();
				if (paymentAmountSelected && dateSelected) {
					makePaymentButton.setEnabled(true);
				}
			}
		});
		listPanel.add(paymentTypeList);
		panel.add(listPanel, BorderLayout.WEST);

		paymentAmountPanel.add(paymentAmountLabel);
		paymentAmountPanel.add(paymentAmountTextField);
		buttonPanel.add(paymentAmountPanel, BorderLayout.NORTH);

		buttonPanel.add(makePaymentButton, BorderLayout.WEST);
		buttonPanel.add(exitButton, BorderLayout.SOUTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		paymentStatusPanel.add(paymentStatusTextArea, BorderLayout.NORTH);
		paymentStatusPanel.add(messageTextArea, BorderLayout.SOUTH);
		updatePaymentStatus(member, ct);
		panel.add(paymentStatusPanel, BorderLayout.EAST);

		this.getContentPane().add(panel);
		this.setSize(CommonUI.FULLSCREEN);
		this.setVisible(true);
	}

	private void setPaymentAmountFieldSelected() {
		if (!getPaymentAmount().equals(0.0)) {
			paymentAmountSelected = true;
		}
	}

	private void setDateSelected() {
		dateSelected = true;
	}

	private Double getPaymentAmount() {
		Double returnValue = 0.0;
		try {
			returnValue = Double.valueOf(paymentAmountTextField.getText());
		} catch (NullPointerException pe) {
			paymentStatusTextArea
					.append("\nINVALID DATA IN PAYMENT AMOUNT BOX");
		} finally {
			if (returnValue.isNaN() || returnValue.isInfinite()) {
				returnValue = 0.0;
			}
		}
		return returnValue;
	}

	private void updatePaymentStatus(Member member, ClassType ct) {

		paymentStatusTextArea.setText(parseStringArrayList(Common
				.getPaymentStatus(member, globalClassType)));

	}

	private String parseStringArrayList(ArrayList<String> input) {
		String returnValue = "";
		for (String s : input) {
			returnValue += (s + "\n");
		}
		System.out.println(returnValue + " payment info");
		return returnValue;
	}

}
