package com.mycompany.myappservice.util.finder;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.MyAccessibilityService;
import java.util.ArrayList;
import java.util.List;
   
public class FindLableClose extends FinderSuper
{
		public List<AccessibilityNodeInfo> mNodeList;
		public Rect mRectControl;

		@Override
		public FindLableClose(MyAccessibilityService context)
		{
				super( context );
		}

		@Override
		public void founder(AccessibilityNodeInfo node)
		{
				Rect rect = new Rect( );
				Rect bounds = new Rect( );
				node.getBoundsInScreen( rect );
				node.getBoundsInParent( bounds );

				if (mIsDebaging)
				{
						String str = "bottom " + String.valueOf( rect.bottom ) +
								" left " + String.valueOf( rect.left ) +
								" right " + String.valueOf( rect.right ) +
								" top " + String.valueOf( rect.top );
						
						mLogFile.update( str + "\n" );
				}
				if (rect.top < mRectControl.top && rect.left > mRectControl.left)
				{
						if (bounds.bottom < mRectControl.bottom && bounds.right < mRectControl.right)
						{
								if(mIsDebaging)
								{
										String text = node.getClassName( ) + 
												" Text: " + node.getText( ) + 
												" ViewId: " + node.getViewIdResourceName( ) + 
												" Click: " + node.isClickable( ) +
												" [" + rect.flattenToString( ) + "]" +
												" [" + bounds.flattenToString( ) + "]";
												
										mLogFile.update( "FIND !!!" + text + "\n" );
								}
								
								mNodeList.add( node );
						}
				}
		}

		public List<AccessibilityNodeInfo> test(AccessibilityNodeInfo node)
		{
				mNodeList = new ArrayList<>( );
				mRectControl = new Rect( );

				int x = mRootContext.getWidth( );
				int y = mRootContext.getHeight( );

				mRectControl.left = 900 * x / 1080;
				mRectControl.top = 300 * y / 2340;
				mRectControl.right = 300 * x / 1080;
				mRectControl.bottom = 300 *	y / 2340;

				find( node );

				return mNodeList;
		} 
}
