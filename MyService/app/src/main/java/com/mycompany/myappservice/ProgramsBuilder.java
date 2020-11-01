package com.mycompany.myappservice;

import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.mycompany.myappservice.cryptorize.CryptorizeBase;
import com.mycompany.myappservice.BfastBfree.BfastBfreeBase;
import com.mycompany.myappservice.EfastEfree.EfastEfreeBase;
import com.mycompany.myappservice.MathCash.MathCashBase;

public class ProgramsBuilder extends Programs
{
		public String mMsg;
		public boolean mIsGameFinish = false;
		private int mCounterTimeout = 0;
		
		private int mComparisonCounter = 0;
		private CharSequence mComparison = "";
		private CharSequence mComparisonLast = "";
		
		public ProgramsBuilder(MyAccessibilityService service)
		{
				super(service);
				
				CryptorizeBase.imports(this);
				BfastBfreeBase.imports(this);
				EfastEfreeBase.imports(this);
				MathCashBase.imports(this);
		}
		
		public boolean refresh(AccessibilityNodeInfo node)
		{
				mFindTest.test(node);
				
				if (ImportLayout.mAll.get(mUsedProg.mPage).streamLayout(node))
				{
						Toast.makeText( mRoot.getBaseContext( ), mMsg, Toast.LENGTH_SHORT ).show( );
						return true;			
				}
				
				return false;
		}
		
		public void startNext()
		{		
				Toast.makeText(mRoot.getBaseContext( ), mUsedProg.mPage , Toast.LENGTH_SHORT ).show( );												
				
				setUsed(getUsed().mId + 1);
			  start();
				restart();
		}
		
		public void setUsed(int index)
		{ 
		    destroy();
				
				for (;index < mProgStack.size(); index++)
				{
						if (mProgStack.get(index).mCheckBox)
						{
								Toast.makeText(mRoot.getBaseContext( ),mProgStack.get(index).mPage , Toast.LENGTH_SHORT ).show( );												
								mFindTest.getLogFile( ).write( "TEST_NODE_INFO = "+ mProgStack.get(index).mPage + "\n" );

								mUsedProg = mProgStack.get(index);
								return;
						}
				}

				setUsed(0);
		}
		
		public void start()
		{		
				if (mUsedProg != null) 
        {
						ImportLayout.mAll.get(mUsedProg.mPage).createLayout();
				}
		}

		public void destroy()
		{
				if (mUsedProg != null)
				{
						ImportLayout.mAll.get(mUsedProg.mPage).destroyLayout();
						
				}
		}
		
		public void failureCounterStart()
		{
				if (mCounterTimeout >= 3)
				{
						mCounterTimeout = 0;
						startNext();
				}
				else
				{
						mCounterTimeout++;
						restart();
				}
		}
		
		public void failureEnrichmentStart()
		{
				if (mComparison.equals(mComparisonLast))
				{
						if (mComparisonCounter >= 2)
						{
								mComparisonCounter = 0;
								mComparison = "";
								mComparisonLast = "";	
								
								restart();
						}
						else mComparisonCounter++;				
				}
				else
				{
						mComparisonLast = mComparison;
				}
		}
		
		public void failureEnrichmentFree(CharSequence str)
		{	
				if (!str.equals(mComparison))
				{
						mComparison = str;
						mComparisonCounter = 0;
				}
		}
		
		public void failureCounterFree()
		{
				mCounterTimeout = 0;
		}
		
		public ProgramNode getUsed()
		{
				return mUsedProg;
		}
		
}
