package com.mycompany.myappservice.util.finder;

import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.MyAccessibilityService;
import java.util.ArrayList;
import java.util.List;

public class FindByText extends FinderSuper
{   
		public List<AccessibilityNodeInfo> mNodeList;
		public String mSubstring;

		@Override
		public FindByText(MyAccessibilityService context)
		{
				super( context );
		}

		@Override
		public void founder(AccessibilityNodeInfo node)
		{
				if (node == null)
						return;
						
				String source = String.valueOf(node.getText());
				if (source == null)
						return;
				if (mIsDebaging)	
						mLogFile.update( "source:  " + source + "\n" );
						
				if (source.indexOf(mSubstring) != -1)
				{
						if(mIsDebaging)
								mLogFile.update( "FIND SUB!!!" + source + "\n" );
								
						mNodeList.add( node );
				}
		}

		public List<AccessibilityNodeInfo> test(AccessibilityNodeInfo node, String substring)
		{
				mSubstring = substring;
				mNodeList = new ArrayList<>( );

				find( node );

				return mNodeList;
		}
}
