package com.hjin.ipscan;

import android.widget.TextView;

public class ipScanThread extends Thread {
	String[] ipSplit;
	TextView tV;
	
	public ipScanThread(TextView _tV, String[] _ipSplit)
	{
		ipSplit = _ipSplit;
		tV = _tV;
	}
	
	public void run(){
		String[] newIpSplit = new String[4];
		newIpSplit[0] = ipSplit[0];
		newIpSplit[1] = ipSplit[1];
		newIpSplit[2] = ipSplit[2];
		
		for(int i = 0; i < 256; i++)
		{
			newIpSplit[3] = i + "";
			tV.setText(tV.getText()+ "" + i + " ");
			//IPScan.getIpInfo(tV, newIpSplit);							
		}
	}
}
