package com.mycompany.myappservice.Layout;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.MyAccessibilityService;
import com.mycompany.myappservice.util.finder.FindTest;

public class AllLayoutCryptorize
{
		public MyAccessibilityService mRoot;
		public MonitorTask mMonitorTask;
		public boolean mIsNotFocuse = false;
		public String mStatusText;
		public CryptorizeStartLayout mCryptorizeStartLayout;
		public CryptorizeStartButton mCryptorizeStartButton;
		public CryptorizeAdvertisingBanner mCryptorizeAdvertisingBanner;
		public CryptorizeGameLayout mCryptorizeGameLayout;
		public CryptorizeLebleRestart mCryptorizeLebleRestart;
		public CryptorizeMSLayout mCryptorizeMSLayout;
		public CryptorizeExceededLimitLayout mExceededLimitLayout;

		public HotspotshieldStart mHotspotshieldStart;

		public FindTest mFindTest;

		public AllLayoutCryptorize(MyAccessibilityService thisContext)
		{
				mRoot = thisContext;		
				mMonitorTask = new MonitorTask( mRoot );

				mFindTest = new FindTest( mRoot );
				mFindTest.getLogFile( ).write( "TEST_NODE_INFO" + "\n" );
				mCryptorizeAdvertisingBanner = new CryptorizeAdvertisingBanner( this, "CryptorizeAdvertisingBanner" );
				mCryptorizeStartButton = new CryptorizeStartButton( this, "CryptorizeStartButton" );
				mCryptorizeStartLayout = new CryptorizeStartLayout( this, "CryptorizeStartLayout" );
				mCryptorizeGameLayout = new CryptorizeGameLayout( this, "CryptorizeGameLayout" );
				mCryptorizeLebleRestart = new CryptorizeLebleRestart( this, "CryptorizeLebleRestart" );
				mCryptorizeMSLayout = new CryptorizeMSLayout( this, "CryptorizeMSLayout" );
				mExceededLimitLayout = new CryptorizeExceededLimitLayout( this, "ExceededLimitLayout" );

				mHotspotshieldStart = new HotspotshieldStart( this, "HotspotshieldStart" );
		}

		public boolean FindLayoute(AccessibilityNodeInfo node)
		{
				//Toast.makeText( mRoot.getBaseContext( ), "+++++++", Toast.LENGTH_SHORT ).show( );
				
				if (mIsNotFocuse)
				{
						//Toast.makeText( mRoot.getBaseContext( ), "=========", Toast.LENGTH_SHORT ).show( );
						
						mIsNotFocuse = false;
						return mIsNotFocuse;
				}
//				if (node.getPackageName( ).equals( "com.bling.bitcoinblocks" ))
//				{
//						Toast.makeText( mRoot.getBaseContext( ), "blocssss", Toast.LENGTH_SHORT ).show( );
//						
//				}
				//Toast.makeText( mRoot.getBaseContext( ), "----------", Toast.LENGTH_SHORT ).show( );
				

//				if (node.getPackageName( ).equals( "hotspotshield.android.vpn" ))
//				{				
//						if (mHotspotshieldStart.serferMechanism( node, 1000, 20 ))
//						{
//								mStatusText = mHotspotshieldStart.mStringMessage + " FIND";
//								return true;
//						}
//				}
				
				mFindTest.test(node);
		
				
				//if (node.getPackageName( ).equals( "com.bprogrammers.cryptorize" ))
				//{
						if (mCryptorizeLebleRestart.serferMechanism( node, 1000, 15 ))
						{
								mStatusText = mCryptorizeLebleRestart.mStringMessage + " FIND";
								return true;
						}
						if (mCryptorizeStartLayout.serferMechanism( node, 1000, 40 ))
						{
								mStatusText = mCryptorizeStartLayout.mStringMessage + " FIND";
								return true;
						}
						if (mCryptorizeStartButton.serferMechanism( node, 1000, 30 ))
						{
								mStatusText = mCryptorizeStartButton.mStringMessage + " FIND";
								return true;
						}
						if (mCryptorizeAdvertisingBanner.serferMechanism( node, 1000, 120 ))
						{
								mStatusText = mCryptorizeAdvertisingBanner.mStringMessage + " FIND";
								return true;
						}	
						if (mCryptorizeGameLayout.serferMechanism( node, 1000, 60 ))
						{
								mStatusText = mCryptorizeGameLayout.mStringMessage + " FIND";
								return true;
						}
						if (mExceededLimitLayout.serferMechanism( node, 1000, 60 * 30 ))
						{
								mStatusText = mExceededLimitLayout.mStringMessage + " FIND";
								return true;
						}
						if (mCryptorizeMSLayout.serferMechanism( node, 1000, 10 ))
						{
								mStatusText = mCryptorizeMSLayout.mStringMessage + " FIND";
								return true;
						}
						
				//}
				return false;
		}

		public void ToastMessage()
		{
				Toast.makeText( mRoot.getBaseContext( ), mStatusText, Toast.LENGTH_SHORT ).show( );
		}

		public void quit()
		{

		}
}
