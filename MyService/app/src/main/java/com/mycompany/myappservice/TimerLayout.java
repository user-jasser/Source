package com.mycompany.myappservice;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.PushbackInputStream;

public abstract class TimerLayout
{
		public IntervalRunable mAction; 
		public HandlerThread mHandlerThread;
		public Handler mHandler;

		public ProgramsBuilder mHandle;
		public AccessibilityNodeInfo mNodeInfo;
		public boolean mActionIsRuning;
		public String mStringMessage;
		public long mDelay;
		public long mTimeOut;
		
		private long delay;
		private long timeout;
		
		public abstract boolean Condition();

		public  abstract boolean Action();

		public abstract void Timeout();

		public class IntervalRunable implements Runnable
		{
				@Override
				public void run()
				{
						if (mAction == null)
								return;

						if (mTimeOut > 0)
						{
								if (Action( ))
								{			
										mHandler.postDelayed( mAction, mDelay );
										mTimeOut = mTimeOut - mDelay;
								}
								else
								{
										//Toast.makeText( mHandle.mRoot.getBaseContext( ), "destry - " + mStringMessage, Toast.LENGTH_SHORT ).show( );

										removeTimer( );
										mActionIsRuning = false;		
								}
						}
						else 
						{
								Toast.makeText( mHandle.mRoot.getBaseContext( ), "TIMER OUTE - " + mStringMessage, Toast.LENGTH_SHORT ).show( );

								Timeout( );
								removeTimer( );
								mActionIsRuning = false;
						}
				}

		}

		public TimerLayout(ProgramsBuilder handle, String massege, long delay, long timeout)
		{  
		    this.delay = delay;
				this.timeout = timeout;
				mHandle = handle;
				mActionIsRuning = false;
				mStringMessage = massege;
				
				mHandlerThread = new HandlerThread( mStringMessage );
				mHandlerThread.start( );
				mHandler = new Handler( mHandlerThread.getLooper( ) );
		}
		
		public void start()
		{
				
		}

		public boolean CreateTimer(long delay, long timeout)
		{
				if (mActionIsRuning)
						return false;

				if (Condition( ))
				{
						mDelay = delay;
						mTimeOut = TimeUnit.SECONDS.toMillis( timeout );
						removeTimer( );
						mActionIsRuning = true;
						if (mAction == null)
								mAction = new IntervalRunable( );

						mHandler.postDelayed( mAction, mDelay );
						return mActionIsRuning;
				}
				return false;
		}

		public void actionClick(List<AccessibilityNodeInfo> list)
		{
				for (AccessibilityNodeInfo node : list)
				{
						if (node != null && node.isClickable( ))
						{	
								node.performAction( AccessibilityNodeInfo.ACTION_CLICK );
						}
				}
		}

		public boolean actionLoopClick(List<AccessibilityNodeInfo> list) //throws InterruptedException
		{	
				try
				{
						boolean result = true;

						while (mTimeOut > 0)
						{
								Thread.sleep( mDelay );
								mTimeOut = mTimeOut - mDelay;

								actionClick( list );

								if (!Condition( ))
								{
										Toast.makeText( mHandle.mRoot.getBaseContext( ), "!!!STOPER!!! = " + mStringMessage, Toast.LENGTH_SHORT ).show( );				

										result = false;
										break;
								}
						}

						return result;	
				}
				catch (InterruptedException e)
				{
						throw new RuntimeException( e );
				}	
		}

		public void removeTimer()
		{
				if (mHandler != null)
				{
						mHandler.removeCallbacksAndMessages(null);
						mAction = null;
				}
		}

		public boolean serferMechanism(AccessibilityNodeInfo node)
		{		
				mNodeInfo = node;
				return CreateTimer( this.delay, this.timeout );
		}

}
