
package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.util.finder.FindByText;
import com.mycompany.myappservice.util.finder.FindClassByName;
import com.mycompany.myappservice.util.finder.FindTest;
import java.util.List;


public class CryptorizeMSLayout extends LayoutTimer
{	
		private FindByText mFindByText;
		private FindClassByName mFindClassByName;
		private AccessibilityNodeInfo mClickNode;
		private boolean mIsClicked;

		@Override
		public CryptorizeMSLayout(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );

				mFindByText = new FindByText( handle.mRoot );
				mFindClassByName = new FindClassByName( handle.mRoot );
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;
				Toast.makeText( mHandle.mRoot.getBaseContext( ), "!!!!!!", Toast.LENGTH_SHORT ).show( );

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
												mIsClicked = false;
												mClickNode = node;
												return true;
										}
								}
						}
				}
				return false;
				/*List<AccessibilityNodeInfo> listMSJ = mFindByText.test( mNodeInfo, "MSJ:" );
				 List<AccessibilityNodeInfo> listDMJ = mFindByText.test( mNodeInfo, "DMJ:" );

				 if (listMSJ.size( ) > 0 || listDMJ.size( ) > 0)
				 {
				 new FindTest(mHandle.mRoot).fprint("MSJ or DMJ find !!!");
				 return true;
				 }
				 return false;*/
		}

		@Override
		public boolean Action()
		{
				//mNodeInfo.refresh();
				//Toast.makeText( mHandle.mRoot.getBaseContext( ), "!!!!!!", Toast.LENGTH_SHORT ).show( );

				if (Condition( ))
				{
						//Toast.makeText( mHandle.mRoot.getBaseContext( ), "++++++++1", Toast.LENGTH_SHORT ).show( );

						//mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );

						//Toast.makeText( mHandle.mRoot.getBaseContext( ), "---------1", Toast.LENGTH_SHORT ).show( );
						mIsClicked = true;
						mClickNode.performAction( AccessibilityNodeInfo.ACTION_CLICK );

						//mHandle.FindLayoute(mNodeInfo);
						return true;			
				}
				else
				{
						if (mIsClicked && mHandle.FindLayoute( mHandle.mRoot.getRootInActiveWindow( ) ))
						{
								Toast.makeText( mHandle.mRoot.getBaseContext( ), "is clicked", Toast.LENGTH_SHORT ).show( );

								//mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );
								//mHandle.FindLayoute(mNodeInfo);

								return false;
						}
				}
				return true;
				/*boolean result = true;
				 List<AccessibilityNodeInfo> list = mFindClassByName.test( mNodeInfo, "android.widget.Button");
				 if (list.size( ) > 0)
				 {
				 result = actionLoopClick( list );
				 if (!result)
				 {
				 mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );
				 return false;
				 }
				 }

				 return result;*/
		}

		public void Timeout()
		{
				mHandle.mRoot.restart( "com.bprogrammers.cryptorize" );
		}
}
