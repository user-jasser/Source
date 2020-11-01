package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.util.finder.FindByText;
import com.mycompany.myappservice.util.finder.FindClassByName;
import java.util.List;


public class CryptorizeLebleRestart extends LayoutTimer
{			
		private FindByText mFindByText;
		private FindClassByName mFindClassByName;

		@Override
		public CryptorizeLebleRestart(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );		
				
				mFindByText = new FindByText(handle.mRoot);
				mFindClassByName = new FindClassByName(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;
						
				if (mHandle.mCryptorizeGameLayout.mIsGame)
				{
						mHandle.mCryptorizeGameLayout.mIsGame = false;
						return true;
				}
				return false;
		}

		@Override
		public boolean Action()
		{
				mNodeInfo.refresh();
				
				List<AccessibilityNodeInfo> list = mFindByText.test(mNodeInfo, "You have earned");
				if (list.size() > 0)
				{
						list = mFindClassByName.test(mNodeInfo, "android.widget.ImageView");
						if (list.size( ) > 0)
						{
								Toast.makeText(mHandle.mRoot.getBaseContext(), "222222", Toast.LENGTH_SHORT).show();
								
							  return  actionLoopClick( list );
						}
        }
				return true;
		}

		public void Timeout()
		{
				mHandle.mRoot.restart("com.bprogrammers.cryptorize");
		}
}
