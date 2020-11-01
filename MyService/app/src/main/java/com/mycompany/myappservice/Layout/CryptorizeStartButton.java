package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import java.util.List;


public class CryptorizeStartButton extends LayoutTimer
{
		@Override
		public CryptorizeStartButton(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;
				
				List<AccessibilityNodeInfo> list = mNodeInfo.findAccessibilityNodeInfosByViewId( "com.bprogrammers.cryptorize:id/bVideoContinue" );
				if (list.size( ) > 0)
				{			
						//mHandle.mRoot.restart("hotspotshield.android.vpn");
						return true;
				}

				return false;
		}

		@Override
		public boolean Action()
		{
				boolean result = true;
				mNodeInfo.refresh( );

				List<AccessibilityNodeInfo> list = mNodeInfo.findAccessibilityNodeInfosByViewId( "com.bprogrammers.cryptorize:id/bVideoContinue" );
				if (list.size( ) > 0)
				{					
						result = actionLoopClick( list );
						if (!result)
						{ 
						
						}
				}
				else
				{
						result = false;
				}
								
				return result;
		}

		@Override
		public void Timeout()
		{
				mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );
		}

}
