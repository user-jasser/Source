package com.mycompany.myappservice.BfastBfree;

import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.Network;

public class LayoutStart extends TimerLayout
{

		public LayoutStart(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);
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

		@Override
		public void Timeout()
		{
				if (Network.isNetworkAvailable(mHandle.mRoot))
				{
						Toast.makeText(mHandle.mRoot.getBaseContext(), "Network TRUE", Toast.LENGTH_SHORT).show();

						mHandle.failureCounterStart();
				}
				else
						Toast.makeText(mHandle.mRoot.getBaseContext(), "Network FALSE", Toast.LENGTH_SHORT).show();			
		}
}
