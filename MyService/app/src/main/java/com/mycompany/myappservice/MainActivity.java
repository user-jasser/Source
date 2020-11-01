package com.mycompany.myappservice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.mycompany.myappservice.util.ServiceDocumentBuilder;
import com.mycompany.myappservice.util.view.ActivityViewManagement;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class MainActivity extends Activity implements View.OnClickListener
{	
    private int mIndexProgram = 0;
		private Button mStart;
		private RadioButton mStartLoop;
		private ActivityViewManagement mConfigView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
		{
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

				mStart = findViewById( R.id.StartButton );
				mStartLoop = findViewById( R.id.StartRadioButton );
				mStart.setOnClickListener(this);
				
				mConfigView = new ActivityViewManagement(this);
				mConfigView.showTitle((TextView)findViewById( R.id.mainTextView ));			
				mConfigView.loadAllPrograms((LinearLayout)findViewById( R.id.AddLinearLayout ));
    }
		
		public void StartPrograms()
		{
				Display display =  getWindowManager( ).getDefaultDisplay( );

				Intent servise = new Intent( this, MyAccessibilityService.class );
				servise.putExtra( "servise", "start" );
				servise.putExtra( "getWidth", display.getWidth( ) );
				servise.putExtra( "getHeight", display.getHeight( ) );
				servise.putExtra( "indexProg", mIndexProgram);

				startService( servise );
		}

		@Override
		protected void onResume()
		{
				super.onResume( );

				Toast.makeText( getBaseContext( ), "Main Activity", Toast.LENGTH_SHORT ).show( );				
				
				if (mStartLoop.isChecked())
				{
						StartPrograms();	
				}
		}

		@Override
		public void onClick(View v)
		{
				switch (v.getId())
				{
						case R.id.StartButton:
								mIndexProgram = 0;
								StartPrograms();
								break;
				}
		}

		@Override
		protected void onPause()
		{
				super.onPause( );			
      
				//finish();
				//Toast.makeText(getBaseContext(), "PAUSE", Toast.LENGTH_SHORT).show();	
		}

		public void setIndexProgram(int index)
		{
				mIndexProgram = index;
		}
     
}	


