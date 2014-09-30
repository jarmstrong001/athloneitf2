package com.athloneitf.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.athloneitf.datatype.ClassType;
import com.athloneitf.datatype.Member;
import com.athloneitf.main.Common;

public class AITFListCellRenderer extends DefaultListCellRenderer{

	private ClassType classType;
	
	public AITFListCellRenderer(ClassType ct) {
		// TODO Auto-generated constructor stub
		super();
		classType=ct;
	}

		   
    public Component getListCellRendererComponent( JList list,  
            Object value, int index, boolean isSelected,  
            boolean cellHasFocus )  
    {  
        super.getListCellRendererComponent( list, value, index,  
                isSelected, cellHasFocus ); 
        
        List<String> paymentDefaults=Common.getPaymentStatus((Member)value,classType);
		if(paymentDefaults.get(paymentDefaults.size()-1).endsWith("up to date")){
			 setForeground( Color.BLACK );
		}
		else setForeground( Color.RED);
		
		return this;
		
		
	}

}
