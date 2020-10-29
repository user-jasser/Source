package com.mycompany.myappservice.cryptorize;

import android.widget.Toast;
import com.mycompany.myappservice.ImportLayout;
import com.mycompany.myappservice.ProgramsBuilder;
import java.util.ArrayList;

public class CryptorizeBase extends ImportLayout
{
		public CryptorizeBase(ProgramsBuilder builder, String page)
		{
				super(builder, page);
		}

		@Override
		public void createLayout()
		{
				mLayout = new ArrayList<>();
				
				mLayout.add( new LayoutStart(mBuilder, "LayoutStart", 1000, 15) );
				mLayout.add( new LayoutStartButton(mBuilder, "LayoutStartButton", 1000, 30) );
				mLayout.add( new LayoutAdvertisingBanner(mBuilder, "LayoutAdvertisingBanner", 1000, 90) );
				mLayout.add( new LayoutClickRestart(mBuilder, "LayoutClickRestart", 1000, 15) );
				mLayout.add( new LayoutGame(mBuilder, "LayoutGame", 1000, 60) );
				mLayout.add( new LayoutExceededLimit(mBuilder, "LayoutExceededLimit", 1000, 5) );
				mLayout.add( new LayoutButtonOK(mBuilder, "LayoutButtonOK", 1000, 5) );
				
		}
		
		public static CryptorizeBase imports(ProgramsBuilder builder)
		{				
				return new CryptorizeBase(builder, "com.bprogrammers.cryptorize");
		}
}
