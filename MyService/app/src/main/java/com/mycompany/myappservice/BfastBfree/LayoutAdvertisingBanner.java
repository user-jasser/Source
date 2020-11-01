package com.mycompany.myappservice.BfastBfree;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindLableClose;
import java.util.List;

public class LayoutAdvertisingBanner extends TimerLayout
{
		private FindLableClose mFindLableClose;
		private boolean mIsNotLoadChild;

		public LayoutAdvertisingBanner(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);

				mFindLableClose = new FindLableClose(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{			
				if (mNodeInfo == null)
						return false;

				if (mNodeInfo.getChildCount( ) > 0)
				{
						AccessibilityNodeInfo child = mNodeInfo.getChild( 0 );

						if (child != null)
						{
								if (child.getClassName( ).equals( "android.webkit.WebView" ) || 
										child.getClassName( ).equals( "android.widget.VideoView" ))
								{
										mHandle.failureCounterFree();
										mIsNotLoadChild = false;
										
										return true;
								}
						}
				}
				else
				{
						mIsNotLoadChild = true;
						return true;
				}

				return false;
		}

		@Override
		public boolean Action()
		{
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow( );

				if (Condition())
				{
						if (mIsNotLoadChild)
								return true;

						List<AccessibilityNodeInfo> list = mFindLableClose.test(mNodeInfo);
						if (list.size( ) > 0)
						{
								for (AccessibilityNodeInfo node : list)
								{
										if (node.isClickable())
										{
												node.performAction( AccessibilityNodeInfo.ACTION_CLICK );		
										}
								}
						}
				}
				else
				{
						mHandle.failureEnrichmentStart();
				
						return false;
				}


				return true;
		}

		@Override
		public void Timeout()
		{
				mHandle.restart();
		}

}
