package com.athloneitf.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.athloneitf.datatype.ClassType;
import com.athloneitf.datatype.Member;
import com.athloneitf.main.Common;

public class AITFListCellRenderer extends JLabel implements ListCellRenderer<Member> {

	private ClassType classType;
	
	public AITFListCellRenderer(ClassType ct) {
		// TODO Auto-generated constructor stub
		super();
		classType=ct;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Member> list,
			Member value, int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		if(Common.getPaymentStatus(value,classType).size()>0){
			this.setBackground(Color.RED);
		}
		setText(value.getName());
		return this;
		
	}

}
