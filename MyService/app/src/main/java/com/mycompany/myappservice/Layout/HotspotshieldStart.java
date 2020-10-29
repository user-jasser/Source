package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.util.Network;
import com.mycompany.myappservice.util.finder.FindClassByName;
import com.mycompany.myappservice.util.finder.FindTest;
import java.util.List;

public class HotspotshieldStart extends LayoutTimer
{
		public FindTest mFindTest;
		public FindClassByName mFindClassByName;
		public List<AccessibilityNodeInfo> mListInfo;
		
		public HotspotshieldStart(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );
				
				mFindClassByName = new FindClassByName(handle.mRoot);
				mFindTest = new FindTest(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{
				return true;
		}

		@Override
		public boolean Action()
		{				
				if (Network.isVPNConnected(mHandle.mRoot))
				{				
						mHandle.mRoot.restart("com.bling.bitcoinblocks");
						return false;
				}				
				
				AccessibilityNodeInfo node = null;
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow();			
				List<AccessibilityNodeInfo> list = mFindClassByName.test(mNodeInfo, "androidx.viewpager.widget.ViewPager");
				if (list.size( ) > 0)
				{
					  node = list.get(0);
				}
				else return true;
				
				int count = node.getChildCount( );
				
				for (int index = 0; index < count; index++) 
				{
						AccessibilityNodeInfo child = node.getChild(index);
						if (child.getClassName().equals("android.widget.ImageView"))
						{
								if (child.isClickable())
								{										
										child.performAction( AccessibilityNodeInfo.ACTION_CLICK );								
								}
						}
				}
				return true;
		}

		@Override
		public void Timeout()
		{
				mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );
				
		}
		
}
