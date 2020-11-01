package com.mycompany.myappservice.Layout;

import android.content.Intent;
import android.widget.Toast;
import com.mycompany.myappservice.util.Network;


public class CryptorizeStartLayout extends LayoutTimer
{	

		@Override
		public CryptorizeStartLayout(AllLayoutCryptorize handle, String massege)
		{
				super( handle, massege );
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;
						
				if (mNodeInfo.getChildCount() > 0)
				{ 
						if (mNodeInfo.getChild(0) != null)
						{
								if (mNodeInfo.getChild(0).getClassName().equals("android.widget.ProgressBar"))
								{
										return true;
								}
						}
				}
				return false;
		}

		@Override
		public boolean Action()
		{
				if (!Condition())
				{
						return false;
				}
				
				return true;
		}

		public void Timeout()
		{
				if (Network.isNetworkAvailable(mHandle.mRoot))
				{
						Toast.makeText(mHandle.mRoot.getBaseContext(), "Network TRUE", Toast.LENGTH_SHORT).show();
						
						mHandle.mRoot.restart("com.bprogrammers.cryptorize");
				}
				else
						Toast.makeText(mHandle.mRoot.getBaseContext(), "Network FALSE", Toast.LENGTH_SHORT).show();			
		}
}
