package com.mycompany.myappservice.util;

import android.content.Context;
import com.mycompany.myappservice.ProgramNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
  
public class ServiceDocumentBuilder
{
		public static final String CONFIG_NAME = "config.xml";
		private Document mDocument;

		public ServiceDocumentBuilder( Context context ) throws IOException,  SAXException, ParserConfigurationException
		{
				File path = context.getExternalFilesDir( null );
				File config = new File( path + "/" + CONFIG_NAME );

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance( );
				DocumentBuilder builder = factory.newDocumentBuilder( );
				Document document = builder.parse( config );	

				document.getDocumentElement( ).normalize( );
				mDocument = document;			
		}

		public String getTitle() 
		{
				String version = mDocument.getDocumentElement( ).getAttribute( "version" );
				NodeList list = mDocument.getElementsByTagName( "prog_manager" );
				String title = list.item( 0 ).getAttributes( ).getNamedItem( "title" ).getNodeValue( );

				return title + " " + version;		
		}
		
		public List<String> getIgnore()
		{
				List<String> result = new ArrayList<>();
				NodeList list = mDocument.getElementsByTagName( "ignore" );
				
				for (int index = 0; index < list.getLength( ); index++)
				{
						NamedNodeMap map = list.item( index ).getAttributes( );
						result.add(map.getNamedItem( "page" ).getNodeValue( ));
				}
				
				return result;
		}

		public List<ProgramNode> loadPrograms()
		{
				List<ProgramNode> result = new ArrayList<>( );
				NodeList list = mDocument.getElementsByTagName( "programs" );

				for (int index = 0; index < list.getLength( ); index++)
				{
						ProgramNode item = new ProgramNode( );
						NamedNodeMap map = list.item( index ).getAttributes( );

						item.mTitle = map.getNamedItem( "title" ).getNodeValue( );
						item.mPage = map.getNamedItem( "page" ).getNodeValue( );
						item.mImage = map.getNamedItem( "image" ).getNodeValue( );
						String boolStr = map.getNamedItem( "check_box" ).getNodeValue( );					
						item.mCheckBox = boolStr.equals( "true" );
						
						item.mId = index;
						result.add( item );				
				}

				return result;
		}

		public Node findByPage(String page)
		{
				NodeList list = mDocument.getElementsByTagName( "programs" );
				for (int index = 0; index < list.getLength( ); index++)
				{
						NamedNodeMap map = list.item( index ).getAttributes( );
						if (map.getNamedItem( "page" ).getNodeValue( ).equals(page))
						{
								return list.item( index );
						}
				}

				return null;
		}


		public Document getDocument()
		{
				return mDocument;
		}
}
