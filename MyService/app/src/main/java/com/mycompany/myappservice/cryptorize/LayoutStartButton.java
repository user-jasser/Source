package com.mycompany.myappservice.cryptorize;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import java.util.List;

public class LayoutStartButton extends TimerLayout
{
		List<AccessibilityNodeInfo> mClickList; 
		
		public LayoutStartButton(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;

				List<AccessibilityNodeInfo> list = mNodeInfo.findAccessibilityNodeInfosByViewId( "com.bprogrammers.cryptorize:id/bVideoContinue" );
				if (list.size( ) > 0)
				{				
				    //mHandle.startNext();
				    mClickList = list;
						return true;
				}

				return false;
		}

		@Override
		public boolean Action()
		{
				if (Condition( ))
				{
						for (AccessibilityNodeInfo node : mClickList)
						{
								node.performAction( AccessibilityNodeInfo.ACTION_CLICK );							
						}
				}
				else
				{
						return false;
				}
				
				return true;
		}

		@Override
		public void Timeout()
		{
				mHandle.failureCounterStart();
		}		
		
}
