package com.mycompany.myappservice.cryptorize;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;

public class LayoutGame extends TimerLayout
{
		private FindClassByName mFindClassByName;

		@Override
		public LayoutGame(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);

				mFindClassByName = new FindClassByName( handle.mRoot );
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;

				if (mNodeInfo.getChildCount( ) > 0)
				{
						AccessibilityNodeInfo child = mNodeInfo.getChild( 0 );
						if (child.getClassName( ).equals( "android.widget.RelativeLayout" ))
								if (child.getChildCount( ) > 0)
								{
										if (!child.getChild( 0 ).getClassName( ).equals( "android.widget.ProgressBar" ))
										{
												Toast.makeText( mHandle.mRoot.getBaseContext( ), "START GAME", Toast.LENGTH_SHORT ).show( );			

												return true;
										}
								}							
				}
				return false;
		}

		@Override
		public boolean Action()
		{
				Toast.makeText( mHandle.mRoot.getBaseContext( ), "START", Toast.LENGTH_SHORT ).show( );

				List<AccessibilityNodeInfo> list = mFindClassByName.test( mNodeInfo, "android.widget.ScrollView" );
				if (list.size( ) > 0)
				{
						AccessibilityNodeInfo parent = list.get( 0 );

						for (int index = 0; index < parent.getChildCount( ); index++) 
						{
								try
								{
										Thread.sleep( 150 );
										if (parent.getChild( index ) != null)
										{
												parent.getChild( index ).performAction( AccessibilityNodeInfo.ACTION_CLICK );
										}
								}
								catch (InterruptedException e) 
								{
										throw new RuntimeException( e );
								}
						}

						if (parent.getChildCount( ) == 0)
						{
								Toast.makeText( mHandle.mRoot.getBaseContext( ), "FINISH", Toast.LENGTH_SHORT ).show( );

								mHandle.mIsGameFinish = true;
								mHandle.mRoot.mPrograms.refresh( mHandle.mRoot.getRootInActiveWindow());
								
								return false;
						}
				}
				return true;
		}

		public void Timeout()
		{
				mHandle.restart();
		}
}
