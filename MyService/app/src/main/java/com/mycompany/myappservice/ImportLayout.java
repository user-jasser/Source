package com.mycompany.myappservice;

import android.view.accessibility.AccessibilityNodeInfo;
import java.util.HashMap;
import java.util.List;

public abstract class ImportLayout
{
		public String mTitle;
		public ProgramsBuilder mBuilder;
		public List<TimerLayout> mLayout;
		static HashMap<String, ImportLayout> mAll = new HashMap<>();
		
		public abstract void createLayout();
		
		public ImportLayout(ProgramsBuilder builder, String page)
		{
				mTitle = page;
				mAll.put(page, this);
				mBuilder = builder;
		}
		
		public boolean streamLayout(AccessibilityNodeInfo node)
		{
				for (TimerLayout layout : mLayout )
				{
						if (layout.serferMechanism( node ))
						{
								mBuilder.mMsg = layout.mStringMessage + " FIND";
								return true;
						}
				}
				
				return false;
		}
		
		public void destroyLayout()
		{
				if (mLayout.size() > 0)
				{
						for (TimerLayout item : mLayout)
						{
								item.removeTimer();
								//item.mHandlerThread.quit();
								
								//item.mHandler = null;						
								//item = null;
						}
						//mLayout = null;
				}
		}
		
		
}
