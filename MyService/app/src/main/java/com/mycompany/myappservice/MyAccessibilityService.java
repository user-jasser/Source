package com.mycompany.myappservice;

//import org.reflections.Reflections;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;
//import com.mycompany.myappservice.Layout.AllLayoutCryptorize;

public class MyAccessibilityService  extends AccessibilityService
{
		public static final boolean DEBUG = BuildConfig.DEBUG;
		
		public boolean mIsActive = false;
		
		public TimerMonitor mTimerMonitor;
		public ProgramsBuilder mPrograms;
		public String mPackageName;
		public int mWidth;
		public int mHeight;

		public void setHeight(int mHeight)
		{			
				this.mHeight = mHeight;
		}

		public int getHeight()
		{
				return mHeight;
		}

		public void setWidth(int mWidth)
		{
				this.mWidth = mWidth;
		}

		public int getWidth()
		{
				return mWidth;
		}
		
		public void restart(String packageName)//Intent.FLAG_ACTIVITY_CLEAR_TASK
		{
				Intent launchIntent = this.getPackageManager( ).getLaunchIntentForPackage( packageName );
				launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				
			  startActivity( launchIntent ); 
		}
		
		public void refocuse(String packageName)
		{
				Intent launchIntent  = this.getPackageManager().getLaunchIntentForPackage( packageName );
				launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				startActivity(launchIntent); 
		}

		@Override
		public void onCreate() 
		{   
				super.onCreate( );
				
				mPrograms = new ProgramsBuilder(this);
		}

		@Override
		public int onStartCommand(Intent intent, int flags, int startId)
		{
				if (isAccessibilitySettingsOn( this ))
				{	 
						Toast.makeText( getBaseContext( ), "AccessibilityEvent True", Toast.LENGTH_SHORT ).show( );
						if (intent == null)
						{
								Toast.makeText( getBaseContext( ), "restard cromand!!!!", Toast.LENGTH_SHORT ).show( );					
								restart( "com.mycompany.myappservice");
						}
						else
						{
								String action = intent.getStringExtra( "servise" );
								if (action.equals( "start" ))
								{
										if (!mIsActive)
										{
												mIsActive = true;
												mTimerMonitor = new TimerMonitor(this);
										}										
										mWidth = intent.getIntExtra("getWidth", 0);
										mHeight = intent.getIntExtra("getHeight", 0);
										
										//mPrograms.loadProgramId();
									//	if (mPrograms.getUsed() == null)
									//	{											
												mPrograms.setUsed(intent.getIntExtra("indexProg", 0));	
												mPrograms.start();
												
												
									//  }
										
									  mPrograms.refocuse();
								}
						}
				}	
				else
						Toast.makeText( getBaseContext( ), "AccessibilityEvent False", Toast.LENGTH_SHORT ).show( );

				return super.onStartCommand( intent, flags, startId );
		}	

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent)
		{
				if (!mIsActive)
						return;

				if (accessibilityEvent.getEventType( ) == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
				{
						if (mPrograms.IgnoreEventPackage( accessibilityEvent ))
						{					
							  mPrograms.refocuse();
						}	
						
						if ( !mPrograms.refresh( this.getRootInActiveWindow()) )
						{
								Toast.makeText( getBaseContext( ), "NOT FOUND LAYOUT !!!", Toast.LENGTH_SHORT ).show( );
						}
				}

				if (accessibilityEvent.getEventType( ) == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED){
				} 
				if (accessibilityEvent.getEventType( ) == AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE){
				}

		}

		@Override
    public void onInterrupt() 
		{
				if (!mIsActive)
						return;
    } 

		@Override
		protected void onServiceConnected()
		{
				super.onServiceConnected( );
				AccessibilityServiceInfo info = new AccessibilityServiceInfo( );
				//info.flags = AccessibilityServiceInfo.DEFAULT |
        //    AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS |
        //    AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;      
				info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
				info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
				
				info.packageNames = new String[mPrograms.getAllPackageName().size()];
				for (int index = 0; index < mPrograms.getAllPackageName().size(); index++)
						info.packageNames[index] = mPrograms.getAllPackageName().get(index);
										
				setServiceInfo( info );	
		}


		public static boolean isAccessibilitySettingsOn(Context mContext)
		{
        int accessibilityEnabled = 0;
        //your package/accesibility service path/class
        final String service = "com.mycompany.myappservice/com.mycompany.myappservice.MyAccessibilityService";	

        boolean accessibilityFound = false;
        try
				{
            accessibilityEnabled = Settings.Secure.getInt(
								mContext.getApplicationContext( ).getContentResolver( ),
								android.provider.Settings.Secure.ACCESSIBILITY_ENABLED );
            Log.v( "TAG", "accessibilityEnabled = " + accessibilityEnabled );
        }
				catch (Settings.SettingNotFoundException e)
				{
            Log.e( "TAG", "Error finding setting, default accessibility to not found: "
									+ e.getMessage( ) );
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter( ':' );

        if (accessibilityEnabled == 1)
				{
            Log.v( "TAG", "***ACCESSIBILIY IS ENABLED*** -----------------" );
            String settingValue = Settings.Secure.getString(
								mContext.getApplicationContext( ).getContentResolver( ),
								Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES );
            if (settingValue != null)
						{
                TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
                splitter.setString( settingValue );
                while (splitter.hasNext( ))
								{
                    String accessabilityService = splitter.next( );

                    Log.v( "TAG", "-------------- > accessabilityService :: " + accessabilityService );
                    if (accessabilityService.equalsIgnoreCase( service ))
										{
                        Log.v( "TAG", "We've found the correct setting - accessibility is switched on!" );
                        return true;
                    }
                }
            }
        }
				else
				{
            Log.v( "TAG", "***ACCESSIBILIY IS DISABLED***" );
        }
        return accessibilityFound;
    }

}
