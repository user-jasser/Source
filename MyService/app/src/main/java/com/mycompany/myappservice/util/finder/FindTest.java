package com.mycompany.myappservice.util.finder;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.MyAccessibilityService;

public class FindTest extends FinderSuper
{  
		@Override
		public FindTest(MyAccessibilityService context)
		{
				super( context );
		}

		@Override
		public void founder(AccessibilityNodeInfo node)
		{
				if (!mIsDebaging)
						return;
				
				Rect rect = new Rect( );
				Rect bounds = new Rect( );
				node.getBoundsInScreen( rect );
				node.getBoundsInParent( bounds );
				String str = node.getClassName( ) + 
						" Text: " + node.getText( ) + 
						" ViewId: " + node.getViewIdResourceName( ) + 
						" Click: " + node.isClickable( ) +
						" [" + rect.flattenToString( ) + "]" +
						" [" + bounds.flattenToString( ) + "]";
						
				String str1 = node.getClassName( ) + 
						" Text: " + node.getText( ) + 
						" ViewId: " + node.getViewIdResourceName( ) + 
						" Click: " + node.isClickable( ) +
						" window " + node.getWindowId() +
						" disc " + node.describeContents() +
						" res " + node;
			  String str2 = node +"\n";

				mLogFile.update( getMTestPrefix() + "+" + str + "\n" );
		}
		
		public void test(AccessibilityNodeInfo node)
		{		
				if (mIsDebaging)
						mLogFile.update( "========>>>" + "\n" );
						
				setMTestPrefix("");
				
				find(node);
		}
	
}
