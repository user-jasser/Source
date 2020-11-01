package com.mycompany.myappservice.cryptorize;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindByText;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;

public class LayoutClickRestart extends TimerLayout
{
		private FindByText mFindByText;
		private FindClassByName mFindClassByName;
		private List<AccessibilityNodeInfo> mList = null;
		
		public LayoutClickRestart(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);
				
				mFindByText = new FindByText(handle.mRoot);
				mFindClassByName = new FindClassByName(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{	
				if (mHandle.mIsGameFinish)			
						return true;

				return false;
		}

		@Override
		public boolean Action()
		{	
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow( );
				
				if (mNodeInfo == null)
						return true;
		
				if (Condition())
				{					
						if (mList == null)
						{
								List<AccessibilityNodeInfo> list = mFindByText.test(mNodeInfo, "You have earned");
								if (list.size() > 0)
								{
										list = mFindClassByName.test(mNodeInfo, "android.widget.ImageView");
										if (list.size( ) > 0)
										{
												mList = list;
										}
								}
						}
						else
						{
								for (AccessibilityNodeInfo node : mList)
								{
										if (node.isClickable())
										{				
										    mHandle.mIsGameFinish = false;
												node.performAction( AccessibilityNodeInfo.ACTION_CLICK );		
										}
								}
						}
						
				}
				else
				{
						List<AccessibilityNodeInfo> list = mFindByText.test(mNodeInfo, "You have earned");
						if (!(list.size() > 0))
						{
								mList = null;
								return false;
						}
				}
				
				return true;
		}

		@Override
		public void Timeout()
		{
				if(mHandle.mIsGameFinish)
				{
						mHandle.mIsGameFinish = false;
				}
				else
				{
						mHandle.restart();
				}
		}
		
		
}
