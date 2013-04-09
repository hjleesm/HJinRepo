package com.hjin.ipscan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IPScan extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ipscan);

		Button scanBT = (Button)findViewById(R.id.scanBT);

		//hjlee: 2013-03-29, Button Click Event
		scanBT.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				TextView tV = (TextView)findViewById(R.id.displayTV);
				tV.setText("Local ip: " + getLocalIpAddress() + "\n");
				EditText eT = (EditText)findViewById(R.id.inputET);
				String inputIP = eT.getText().toString();

				//hjlee: 2013-03-30, 입력된 ip가 존재해야 해당 ip 검색 수행.
				//eT.setText("192.168.0.83");
				String ipStr = eT.getText().toString();

				if(!inputIP.equals("")) {
					String[] ipSplit = ipStr.split("\\.");
					if(ipSplit.length == 4)
					{
						getIpInfo(tV, ipSplit);
					}
					else if (ipSplit.length == 3)
					{
						ipScanThread ipST = new ipScanThread(tV, ipSplit);
						ipST.start();
					}
				}
			}
		});
	}
	
	public static void getIpInfo(TextView tV, String[] ipSplit)
	{
		try {
			tV.setText(tV.getText() + "Input ip: " + ipSplit[0] + "." + ipSplit[1] + "." + ipSplit[2] + "." + ipSplit[3] + "." + "\n");
			byte[] ipByte = new byte[] {(byte) Integer.parseInt(ipSplit[0]), (byte) Integer.parseInt(ipSplit[1]), 
					(byte) Integer.parseInt(ipSplit[2]), (byte) Integer.parseInt(ipSplit[3])};
			InetAddress inetAddr = InetAddress.getByAddress(ipByte);
			tV.setText(tV.getText() + "HostName: " +inetAddr.getHostName() + "\n");

			tV.setText(tV.getText() + "Reachable: ");
			if(inetAddr.isReachable(2000))
				tV.setText(tV.getText() + "true\n");
			else
				tV.setText(tV.getText() + "false\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//hjlee: 2013-03-29, Get Local Ip Address
	public String getLocalIpAddress()
	{
		final String IP_NONE = "N/A";
		final String WIFI_DEVICE_PREFIX = "eth";

		String LocalIP = IP_NONE;
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();           
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						if( LocalIP.equals(IP_NONE) )
							LocalIP = inetAddress.getHostAddress().toString();
						else if( intf.getName().startsWith(WIFI_DEVICE_PREFIX) )
						{
							LocalIP = inetAddress.getHostAddress().toString();
							if(inetAddress.getByAddress(LocalIP.getBytes()) != null)
								LocalIP += "," + inetAddress.getHostName();

						}
					}
				}
			}
		} catch (SocketException e) {
			//error
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			LocalIP += " null";
			e.printStackTrace();
		}
		return LocalIP;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ipscan, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
