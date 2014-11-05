package com.athloneitf.datatype;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.athloneitf.main.Common;

public class MemberPaymentTableModel extends AbstractTableModel {

	private String[] columnNames={"Payment Type","Payment Amount","Payment Date","Payment Valid To"};
	public Object[][] rowData;
	
	public MemberPaymentTableModel(List<Payment> payments){
		rowData=new Object[payments.size()+1][4];
		int i=0;
		double paymentAmountTotal=0.0;
		for(Payment p:payments){
			String paymentName=Common.getPaymentTypeName(p.getPaymentTypeId());
			String paymentAmount="€"+p.getPaymentAmount();
			String paymentDate=Common.dobDateFormat.format(p.getPaymentDate());
			String paymentValidTo=Common.dobDateFormat.format(p.getPaymentTo());
			//System.out.println("PN="+paymentName+"\nPA"+paymentAmount+"\nPD"+paymentDate);
			rowData[i]= new String[]{paymentName,paymentAmount,paymentDate,paymentValidTo};
			i++;
			paymentAmountTotal+=p.getPaymentAmount();
		}
		rowData[payments.size()]=new String[]{"Total","€"+paymentAmountTotal,"",""};

	}
	
	public String getColumnName(int i){
		return columnNames[i];
	}
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return rowData[arg0][arg1];
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
}
