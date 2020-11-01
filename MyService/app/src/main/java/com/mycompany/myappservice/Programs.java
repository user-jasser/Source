package com.mycompany.myappservice;

import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;
import com.mycompany.myappservice.util.ServiceDocumentBuilder;
import com.mycompany.myappservice.util.finder.FindTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Programs
{
		public MyAccessibilityService mRoot;
		public List<ProgramNode> mProgStack;
		public List<String> mProgIgnore;
		public List<String> mAllPackageName;
		public ProgramNode mUsedProg;
		public FindTest mFindTest;
		
		public Programs(MyAccessibilityService service)
		{
				mRoot = service;
				mUsedProg = null;
				mFindTest = new FindTest( mRoot );			
				
				try
				{
						ServiceDocumentBuilder bilder = new ServiceDocumentBuilder( mRoot );
						mProgIgnore = bilder.getIgnore();
						mProgStack = bilder.loadPrograms();
						
						allPackageName();
						
				}
				catch (IOException e)
				{}
				catch (SAXException e)
				{}
				catch (ParserConfigurationException e)
				{}
		}
		
		public void allPackageName()
		{
				mAllPackageName = new ArrayList<>();

				for (String list : mProgIgnore)
				{
						mAllPackageName.add(list);
				}	
			  for (ProgramNode node : mProgStack)	
				{
						mAllPackageName.add(node.mPage);
				}
		}
		
		public boolean IgnoreEventPackage(AccessibilityEvent accessibilityEvent)
		{
				for (String list : mProgIgnore)
				{
						if ( accessibilityEvent.getPackageName( ).equals( list )  )
								return true;
				}
				
				return false;
		}
		
		public List<String> getAllPackageName()
		{
				return mAllPackageName;
		}
		
		public void restart()
		{
				Intent launchIntent = mRoot.getPackageManager( ).getLaunchIntentForPackage( mUsedProg.mPage );
				launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

			  mRoot.startActivity( launchIntent ); 
		}

		public void refocuse()
		{
				Intent launchIntent  = mRoot.getPackageManager().getLaunchIntentForPackage( mUsedProg.mPage );
				launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				mRoot.startActivity(launchIntent); 
		}
}
