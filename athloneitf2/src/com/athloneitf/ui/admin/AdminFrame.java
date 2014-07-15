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
package com.athloneitf.ui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.athloneitf.datatype.Member;

public class AdminFrame extends JFrame {

	private final JButton addMemberButton=new JButton("Add Member");
	private final JPanel adminOptionsPanel=new JPanel();
	
	public AdminFrame(){
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMemberDialog am=new AddMemberDialog();
			}
		});
		adminOptionsPanel.add(addMemberButton);
		this.add(adminOptionsPanel);
		this.setVisible(true);
	}
}
