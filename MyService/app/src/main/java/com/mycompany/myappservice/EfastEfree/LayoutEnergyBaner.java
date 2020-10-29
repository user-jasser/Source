package com.mycompany.myappservice.EfastEfree;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindByText;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;

public class LayoutEnergyBaner extends TimerLayout
{
		private FindByText mFindByText;
		private FindClassByName mFindClassByName;

		public LayoutEnergyBaner(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);

				mFindByText = new FindByText( handle.mRoot );
				mFindClassByName = new FindClassByName( handle.mRoot );
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;

				List<AccessibilityNodeInfo> list = mFindByText.test( mNodeInfo, "To spin the roulette you need" );
				if (list.size( ) > 0)
				{		
						return true;
				}

				return false;
		}

		@Override
		public boolean Action()
		{
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow();

				if (Condition())
				{
						List<AccessibilityNodeInfo> list = mFindClassByName.test( mNodeInfo, "android.widget.Button" );
						if (list.size( ) > 0)
						{
								for (AccessibilityNodeInfo node : list)
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
