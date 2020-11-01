package com.mycompany.myappservice.util.finder;

import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.myappservice.MyAccessibilityService;
import com.mycompany.myappservice.util.FileWorker;
import java.io.File;

public abstract class FinderSuper
{   
		public static final String FILE_NAME = "testFile.txt";
		protected MyAccessibilityService mRootContext;
		protected FileWorker mLogFile;
		protected boolean mIsDebaging;
		private String mTestPrefix = "";
		
		public abstract void founder(AccessibilityNodeInfo node);
		
		public FinderSuper(MyAccessibilityService context)
		{
				mRootContext = context;
				mIsDebaging = mRootContext.DEBUG;
				File path = mRootContext.getExternalFilesDir( null );
				mLogFile = new FileWorker( path.getAbsolutePath( ), FILE_NAME );
				//mLogFile.write( "TEST_NODE_INFO" + "\n" );
		}
		
		private void findChild(AccessibilityNodeInfo node, int count)
		{
				for (int index = 0; index < count; index++) 
				{
						AccessibilityNodeInfo child = node.getChild( index );
						if (child != null)
						{
								if (child.getChildCount( ) > 0)
										find( child );
								else
								{		
										founder( child );
								}
						}
				} 
		}

		public void find(AccessibilityNodeInfo node)
		{
				if (node == null)
						return;
        if (mIsDebaging)
						deletePrefix( );
						
				founder( node );

				int count = node.getChildCount( );
				if (count > 0)
				{
						if (mIsDebaging)
								addPrefix( );

						findChild(node, count);
        }		
		}
		
		public void deletePrefix()
		{	
				int count = mTestPrefix.length( );
				if (count > 0)
						mTestPrefix = mTestPrefix.substring( count - 2 );
		}

		public void addPrefix()
		{
				mTestPrefix = mTestPrefix + "  ";
		}
		
		public void setMTestPrefix(String mTestPrefix)
		{
				this.mTestPrefix = mTestPrefix;
		}

		public String getMTestPrefix()
		{
				return mTestPrefix;
		}
		
		public void fprint(String str)
		{
				mLogFile.update(str);
		}
		
		public FileWorker getLogFile()
		{
				return mLogFile;
		}
		
}
