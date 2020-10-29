package com.mycompany.myappservice.Layout;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.MyAccessibilityService;

public class MonitorTask
{
		MyAccessibilityService mRoot;
		Thread mMonitorThread;
		Runnable mMonitorRun;
		HandlerThread mHandlerThread;
		Handler mHandler;
		public AccessibilityNodeInfo mNodeInfo;
		public int mCounter;
		
		
		public MonitorTask(MyAccessibilityService root)
		{
				mRoot = root;
			  mHandlerThread = new HandlerThread( "monitor-loop" );
				mHandlerThread.start( );
				mHandler = new Handler( mHandlerThread.getLooper( ) );
							  
				mMonitorRun = new Runnable() 
				{
						@Override
						public void run()
						{
								if (condition())
										mHandler.postDelayed( mMonitorRun, 5000 );				
						}
				};
				
				mHandler.postDelayed( mMonitorRun, 5000 );
		}
		
    public boolean condition()
		{
				//if (mRoot.mCryptorize.mExceededLimitLayout.mActionIsRuning)
				//		return true;
						
				if (mNodeInfo == null)
				{
						mNodeInfo = mRoot.getRootInActiveWindow();
						return true;
				}	
				//Toast.makeText(mRoot.getBaseContext(), String.valueOf(mNodeInfo.getWindowId()), Toast.LENGTH_SHORT).show();
				AccessibilityNodeInfo node = mRoot.getRootInActiveWindow();
				if (mNodeInfo.getWindowId() == node.getWindowId())
				{
						
						mCounter++;
				}
				else
				{		
						mNodeInfo = node;
						mCounter = 0;
				}
				if (mCounter >= 12*3)
				{
						Toast.makeText(mRoot.getBaseContext(), "refresh", Toast.LENGTH_SHORT).show();
						
						mRoot.restart("com.bprogrammers.cryptorize");
						//mRoot.restart("com.mycompany.myappservice");
				}
				return true;
		}
	
}
