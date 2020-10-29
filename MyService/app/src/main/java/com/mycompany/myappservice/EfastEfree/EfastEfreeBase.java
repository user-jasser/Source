package com.mycompany.myappservice.EfastEfree;

import com.mycompany.myappservice.ImportLayout;
import com.mycompany.myappservice.ProgramsBuilder;
import java.util.ArrayList;

public class EfastEfreeBase extends ImportLayout
{
		public EfastEfreeBase(ProgramsBuilder builder, String page)
		{
				super( builder, page );
		}

		@Override
		public void createLayout()
		{
				mLayout = new ArrayList<>( );

				mLayout.add( new LayoutStart( mBuilder, "LayoutStart", 1000, 15 ) );		
				mLayout.add( new LayoutStartButton( mBuilder, "LayoutStartButton", 1000, 60 ) );
				mLayout.add( new LayoutAdvertisingBanner( mBuilder, "LayoutAdvertisingBanner", 1000, 90 ) );
				mLayout.add( new LayoutFacebookBaner( mBuilder, "LayoutFacebookBaner", 1000, 10 ) );
				mLayout.add( new LayoutEnergyBaner( mBuilder, "LayoutEnergyBaner", 1000, 20 ) );
				mLayout.add( new LayoutExceededLimit( mBuilder, "LayoutExceededLimit", 1000, 5 ) );			
				//mLayout.add( new LayoutButtonOK(mBuilder, "LayoutButtonOK", 1000, 20) );

		}

		public static EfastEfreeBase imports(ProgramsBuilder builder)
		{				
				return new EfastEfreeBase( builder, "com.efast.efree" );
		}
}
