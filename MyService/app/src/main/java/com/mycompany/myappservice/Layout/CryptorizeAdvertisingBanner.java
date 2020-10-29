package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.util.finder.FindLableClose;
import java.util.List;

public class CryptorizeAdvertisingBanner extends LayoutTimer
{
		private FindLableClose mFindLableClose;
		
		@Override
		public CryptorizeAdvertisingBanner(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );
				
				mFindLableClose = new FindLableClose(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{
				boolean result = false;

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
										result = true;
								}
						}
				}
				else
				{
						mNodeInfo.refresh( );
						result = Condition( );
				}

				return result;
		}

		@Override
		public boolean Action()
		{   
				mNodeInfo = mHandle.mRoot.getRootInActiveWindow( );
				if (mNodeInfo == null)
						return false;

				boolean result = true;
				//mNodeInfo.refresh( );

				List<AccessibilityNodeInfo> list = mFindLableClose.test(mNodeInfo);
				if (list.size( ) > 0 && !mHandle.mIsNotFocuse)
				{
						result = actionLoopClick( list );

						if (!result)
						{
								return false;
						}
				}
				return true;
		}

		public void Timeout()
		{
				mHandle.mRoot.refocuse( "com.mycompany.myappservice" );
		}

}
