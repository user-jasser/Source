package com.mycompany.myappservice.util.finder;

import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.MyAccessibilityService;
import java.util.ArrayList;
import java.util.List;

public class FindClassByName extends FinderSuper
{  
		public List<AccessibilityNodeInfo> mNodeList;
		public String mClassFound;
		
		@Override
		public FindClassByName(MyAccessibilityService context)
		{
				super( context );
		}

		@Override
		public void founder(AccessibilityNodeInfo node)
		{
				if (node == null)
						return;
						
				if (node.getClassName( ).equals( mClassFound ))
				{
						if (mIsDebaging)
								mLogFile.update( "FIND CLASS:  " + mClassFound + " = " + node.getClassName( ) + "\n" );
								
						mNodeList.add( node );
				}
		}

		public List<AccessibilityNodeInfo> test(AccessibilityNodeInfo node, String name)
		{
				mClassFound = name;
				mNodeList = new ArrayList<>( );

				find( node );

				return mNodeList;
		}
}
