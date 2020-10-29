package com.mycompany.myappservice.util;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class Network
{  
		public static boolean isNetworkAvailable(AccessibilityService activity)
	{
		ConnectivityManager connect = (ConnectivityManager)
			activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo active = connect.getActiveNetworkInfo();
		return active != null && active.isConnected();
	}
	
	public static String getIPAddress(boolean useIPv4)
	{
			try {
					List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
					for (NetworkInterface intf : interfaces) 
					{
							List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
							for(InetAddress addr : addrs)
							{
									if (!addr.isLoopbackAddress())
									{
											String sAddr = addr.getHostAddress();
											boolean isIPv4 = sAddr.indexOf(':') < 0;
											
											if (useIPv4)
											{
													if (isIPv4)
															return sAddr;											
											}
											else
											{
													if (!isIPv4)
													{
															int delim = sAddr.indexOf('%');
															return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
													}
											}
									}
							}
					}
					
					
			} catch (Exception ignored) {}
			return "";
	}
	
		public static boolean isVPNConnected(AccessibilityService activity)
	{
			boolean result = false;
			ConnectivityManager conMan = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] infoList = conMan.getAllNetworkInfo();

			if (infoList.length > 0)
			{
					for(NetworkInfo addr : infoList)
					{
							if (addr.getTypeName().equals("VPN"))
							{
									if (addr.isConnected())
									{
											result = true;
									}
									else
									{
											result = false;
	  	          	}
							}
					}
			}			
		  return result;
	}
	
	
}
