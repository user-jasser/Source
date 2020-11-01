package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.util.finder.FindByText;
import java.util.List;


public class CryptorizeExceededLimitLayout extends LayoutTimer
{	
		private FindByText mFindByText;

		@Override
		public CryptorizeExceededLimitLayout(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );
				
				mFindByText = new FindByText(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;
						
				List<AccessibilityNodeInfo> list = mFindByText.test( mNodeInfo, "Oops, you've exceeded the limit");
				if (list.size( ) > 0)
				{
						return true;
				}
				return false;
		}

		@Override
		public boolean Action()
		{
				Toast.makeText(mHandle.mRoot.getBaseContext(), "TIMER "+ String.valueOf(mTimeOut), Toast.LENGTH_SHORT).show();			
				
				return true;
		}

		public void Timeout()
		{
				mHandle.mRoot.restart("com.bprogrammers.cryptorize");
		}
}
