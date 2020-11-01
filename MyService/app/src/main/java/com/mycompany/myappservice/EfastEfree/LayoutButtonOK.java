package com.mycompany.myappservice.EfastEfree;

import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindByText;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;

public class LayoutButtonOK extends TimerLayout
{
		private FindByText mFindByText;
		private FindClassByName mFindClassByName;
		private AccessibilityNodeInfo mClickNode;


		public LayoutButtonOK(ProgramsBuilder handle, String massege, long delay, long timeout)
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
												mClickNode = node;
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
				if (Condition( ))
				{
						if (mClickNode != null)
								mClickNode.performAction( AccessibilityNodeInfo.ACTION_CLICK );

						return true;
				}
				else
				{
						if ( mHandle.mRoot.mPrograms.refresh(mHandle.mRoot.getRootInActiveWindow()) )
						{
								mClickNode = null;
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
