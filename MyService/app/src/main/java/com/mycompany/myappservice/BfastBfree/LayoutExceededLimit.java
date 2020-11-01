package com.mycompany.myappservice.BfastBfree;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.ProgramsBuilder;
import com.mycompany.myappservice.TimerLayout;
import com.mycompany.myappservice.util.finder.FindByText;
import java.util.List;

public class LayoutExceededLimit extends TimerLayout
{
		private FindByText mFindByText;

		public LayoutExceededLimit(ProgramsBuilder handle, String massege, long delay, long timeout)
		{
				super(handle, massege, delay, timeout);

				mFindByText = new FindByText(handle.mRoot);
		}

		@Override
		public boolean Condition()
		{
				if (mNodeInfo == null)
						return false;

				List<AccessibilityNodeInfo> list = mFindByText.test( mNodeInfo, "Oops, you've exceeded the limit");
				List<AccessibilityNodeInfo> energy = mFindByText.test( mNodeInfo, "At this moment you do not have energy");
				if (list.size( ) > 0 || energy.size( ) > 0)
				{
						//mHandle.startNext();
						return true;
				}
				return false;
		}

		@Override
		public boolean Action()
		{
				//Toast.makeText(mHandle.mRoot.getBaseContext(), "TIMER "+ String.valueOf(mTimeOut), Toast.LENGTH_SHORT).show();			
				mHandle.startNext();

				return false;
		}

		@Override
		public void Timeout()
		{

		}


}
