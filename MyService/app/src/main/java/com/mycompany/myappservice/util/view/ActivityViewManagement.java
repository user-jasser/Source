package com.mycompany.myappservice.util.view;

import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.mycompany.myappservice.MainActivity;
import com.mycompany.myappservice.ProgramNode;
import com.mycompany.myappservice.util.ServiceDocumentBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.lang.reflect.InvocationTargetException;

public class ActivityViewManagement
{
		private final MainActivity mMainActivity;
		private ServiceDocumentBuilder mServiceConfigBilder;
		private List<ProgramNode> mConfigurationAllPrograms;
		private List<View> mViewProgramList;
		private String mPath;
		
		private void showMessage(String message)
		{
				Toast.makeText( mMainActivity.getBaseContext( ), 
											 message, Toast.LENGTH_LONG ).show( );
		}

		private void addButtonOnClickListener(Button btn)
		{
				btn.setOnClickListener( new View.OnClickListener( ) {
								@Override
								public void onClick(View v)
								{
										//showMessage( String.valueOf( v.getId( ) ) );
										mMainActivity.setIndexProgram( v.getId( ) );
										mMainActivity.StartPrograms( );
								}			

						} );
		}

		private View loadView(ProgramNode node)
		{
				LinearLayout linearByButton = new LinearLayout( mMainActivity );
				linearByButton.setOrientation( LinearLayout.HORIZONTAL );
				linearByButton.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, 
																												 LayoutParams.WRAP_CONTENT ) );
				linearByButton.setGravity( Gravity.CENTER | Gravity.LEFT );			

				ImageView image = new ImageView( mMainActivity );
				image.setImageURI( Uri.parse( mPath + "/" + node.mImage ) );
				image.setScaleX( 2.5f );
				image.setScaleY( 2.5f );
				linearByButton.addView( image, new LayoutParams( LayoutParams.WRAP_CONTENT, 
																												LayoutParams.WRAP_CONTENT ) );																									
				MarginLayoutParams lp = (MarginLayoutParams)image.getLayoutParams( );
				lp.leftMargin = 30;																						

				Button button = new Button( mMainActivity );
				button.setId( node.mId );
				button.setText( node.mTitle );
				addButtonOnClickListener( button );
				linearByButton.addView( button, new LayoutParams( LayoutParams.WRAP_CONTENT, 
																												 LayoutParams.WRAP_CONTENT ) );
				lp = (MarginLayoutParams)button.getLayoutParams( );
				lp.leftMargin = 30;																					 

				LinearLayout linearByCheckBox = new LinearLayout( mMainActivity );
				linearByCheckBox.setOrientation( LinearLayout.HORIZONTAL );
				linearByCheckBox.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, 
																													 LayoutParams.WRAP_CONTENT ) );
				linearByCheckBox.setGravity( Gravity.CENTER | Gravity.RIGHT );
				linearByButton.addView( linearByCheckBox );

				CheckBox check = new CheckBox( mMainActivity );
				check.setChecked( node.mCheckBox );
				linearByCheckBox.addView( check, new LayoutParams( LayoutParams.WRAP_CONTENT, 																					LayoutParams.WRAP_CONTENT ) );

				return linearByButton;
		}

		public ActivityViewManagement(MainActivity activity)
		{
				mMainActivity = activity;
				mPath = activity.getExternalFilesDir( null ).getAbsolutePath( );

				try
				{
						mServiceConfigBilder = new ServiceDocumentBuilder( mMainActivity );					
				}
				catch (IOException e)
				{}
				catch (SAXException e)
				{}
				catch (ParserConfigurationException e)
				{
						showMessage( e.getMessage( ) );
				}
		}

		public void showTitle(TextView view)
		{
				view.setText( mServiceConfigBilder.getTitle( ) );
				view.setScaleX( (float)0.8 );
				view.setScaleY( (float)0.8 );
				view.setTextColor( Color.parseColor( "#FF20468D" ) );
		}

		public void loadAllPrograms(LinearLayout linear)
		{
				mViewProgramList = new ArrayList<>( );
				mConfigurationAllPrograms = mServiceConfigBilder.loadPrograms( );

				if (mConfigurationAllPrograms.size( ) > 0)
				{
						for (ProgramNode node : mConfigurationAllPrograms)
						{
	          		View view = loadView( node );
								mViewProgramList.add( view );

								linear.addView( view );
						}
				}
				else
						showMessage( "Progtams Not found." );
		}


}
