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
package com.athloneitf.datatype;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import com.athloneitf.main.Common;

public class MemberScanInTableModel extends AbstractTableModel  {

	private String[] columnNames={"Unique Dates","Scan In Date","Scan In Time","ClassType"};
	public Object[][] rowData;
	
	public MemberScanInTableModel(List<MemberScanIn> scans){
		rowData=new Object[scans.size()][4];
		int i=0;
		int uniqueDates=0;
		Date previousDate=new Date(0);
		for(MemberScanIn s:scans){
			String classType=s.getClassType().toString();
			String date=Common.dobDateFormat.format(s.getScanInTime());
			String time=Common.timeFormat.format(s.getScanInTime());
			if(!((s.getScanInTime().getDate()==(previousDate.getDate()))&&(s.getScanInTime().getMonth()==(previousDate.getMonth()))))
					{
						uniqueDates++;
					}
			previousDate=s.getScanInTime();
			rowData[i]=new String[]{""+uniqueDates,date,time,classType};
			i++;
		}
	}
	
	public String getColumnName(int i){
		return columnNames[i];
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return rowData[arg0][arg1];
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
