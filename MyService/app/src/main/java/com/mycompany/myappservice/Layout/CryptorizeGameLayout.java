package com.mycompany.myappservice.Layout;


import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;



public class CryptorizeGameLayout extends LayoutTimer
{
		public boolean mIsGame = false;
		private FindClassByName mFindClassByName;

		@Override
		public CryptorizeGameLayout(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );

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
						Toast.makeText( mHandle.mRoot.getBaseContext( ), list.get( 0 ).getClassName( ), Toast.LENGTH_SHORT ).show( );
						AccessibilityNodeInfo parent = list.get( 0 );
						int count = parent.getChildCount( );

						for (int index = 0; index < parent.getChildCount( ); index++) 
						{
								try
								{
										Thread.sleep( 150 );
										if (parent.getChild( index ) != null)
										{
												//if (parent.getChild(index).performAction(AccessibilityNodeInfo.ACTION_SELECT))
												parent.getChild( index ).performAction( AccessibilityNodeInfo.ACTION_CLICK );
										}
										//parent.refresh();
										//if(parent.getChildCount() == 0)
										//	return false;
								}
								catch (InterruptedException e) 
								{
										throw new RuntimeException( e );
								}
						}

						if (parent.getChildCount( ) == 0)
						{
								Toast.makeText( mHandle.mRoot.getBaseContext( ), "FINISH", Toast.LENGTH_SHORT ).show( );

								mIsGame = true;
								mHandle.FindLayoute( mHandle.mRoot.getRootInActiveWindow( ) );

								return false;
						}
				}
				return true;
		}

		public void Timeout()
		{
				mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );
		}
}
