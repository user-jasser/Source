package com.mycompany.myappservice.MathCash;

		import com.mycompany.myappservice.ImportLayout;
		import com.mycompany.myappservice.ProgramsBuilder;
		import java.util.ArrayList;

		public class MathCashBase extends ImportLayout
		{
				public MathCashBase(ProgramsBuilder builder, String page)
				{
						super( builder, page );
				}

				@Override
				public void createLayout()
				{
						mLayout = new ArrayList<>( );
	
						//mLayout.add( new LayoutButtonOK(mBuilder, "LayoutButtonOK", 1000, 20) );

				}

		public static MathCashBase imports(ProgramsBuilder builder)
				{				
						return new MathCashBase( builder, "com.philtechie.mathcash" );
				}
		}
