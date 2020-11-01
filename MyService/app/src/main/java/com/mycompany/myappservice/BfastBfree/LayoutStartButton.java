package com.mycompany.myappservice.BfastBfree;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;

public class LayoutStartButton extends TimerLayout
{
		private FindClassByName mFindClassByName;
		private List<AccessibilityNodeInfo> mList;
		
		public LayoutStartButton(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super( handle, massege, delay, timeout );
				
				mList = null;
				mFindClassByName = new FindClassByName( handle.mRoot );
		}

		@Override
		public boolean Condition()
		{				
				if (mNodeInfo == null)
						return false;
				
				List<AccessibilityNodeInfo> list = mFindClassByName.test( mNodeInfo, "android.widget.TextView" );
				if (list.size( ) > 0)
				{						
						for (AccessibilityNodeInfo node : list)
						{
								CharSequence text = node.getText( );
								if (text != null)
								{
										if (text.equals("+ "))
										{
														
												//Toast.makeText( mHandle.mRoot.getBaseContext( ), "condition start!!!!!!", Toast.LENGTH_SHORT ).show( );
												
												return true;
										}
								}
						}
				}    
				
				return false;
		}

		@Override
		public boolean Action()
		{
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow();
				
				if (Condition())
				{
						if (mList == null)
						{
								List<AccessibilityNodeInfo> list = mFindClassByName.test( mNodeInfo, "android.widget.Button" );
								if (list.size( ) > 0)
								{
										mList = list;
										
										list = mFindClassByName.test( mNodeInfo, "android.widget.TextView" );
										if (list.size( ) > 0)
										{	
												//Toast.makeText( mHandle.mRoot.getBaseContext( ), list.get(0).getText(), Toast.LENGTH_SHORT ).show( );
												CharSequence str = list.get(0).getText();
												if (str != null)
												{
														mHandle.failureEnrichmentFree(str);
												}
												
										}						
								}
						}
						else
						{
								for (AccessibilityNodeInfo node : mList)
								{
										CharSequence text = node.getText( );
										if (text != null)
										{
												if (text.equals("GO"))
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
						mList = null;
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
