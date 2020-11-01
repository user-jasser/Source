package com.mycompany.myappservice.BfastBfree;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindByText;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;

public class LayoutFacebookBaner extends TimerLayout
{
		private FindByText mFindByText;
		private FindClassByName mFindClassByName;
		private List<AccessibilityNodeInfo> mList;

		public LayoutFacebookBaner(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super( handle, massege, delay, timeout );

				mList = null;
				mFindByText = new FindByText( handle.mRoot );
				mFindClassByName = new FindClassByName( handle.mRoot );
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;

				List<AccessibilityNodeInfo> list = mFindByText.test( mNodeInfo, "Our Facebook. To Check Promotional Codes" );
				if (list.size( ) > 0)
				{				
						return true;
				}

				return false;
		}

		@Override
		public boolean Action()
		{
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow( );

				if (Condition( ))
				{
						if (mList == null)
						{
								List<AccessibilityNodeInfo> list = mFindClassByName.test( mNodeInfo, "android.widget.Button" );
								if (list.size( ) > 0)
								{
										mList = list;
								}
						}
						else
						{
								for (AccessibilityNodeInfo node : mList)
								{
										CharSequence text = node.getText( );
										if (text != null)
										{
												if (text.equals( "OK" ))
												{
														node.performAction( AccessibilityNodeInfo.ACTION_CLICK );
														return true;
												}
										}
								}
						}
				}
				else
				{								
						if (mHandle.mRoot.mPrograms.refresh(mNodeInfo))
						{
								Toast.makeText( mHandle.mRoot.getBaseContext( ), "condition true", Toast.LENGTH_SHORT ).show( );
								
								mList = null;
								return false;
						}
				}
				
				return true;
		}

		@Override
		public void Timeout()
		{
				mHandle.restart();
		}


}
