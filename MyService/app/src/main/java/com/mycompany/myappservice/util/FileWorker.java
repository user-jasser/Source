package com.mycompany.myappservice.util;

import java.io.*;
import android.util.Log;
import android.widget.Toast;

public class FileWorker
{  
		private String Path;
		private String FileName;
		private String AbsolutePath;
		private File ThisFile;

		public FileWorker(String patch, String name)
		{
				Path = patch;
				FileName = name;	
				AbsolutePath = Path + "/" + FileName;

				ThisFile = new File( AbsolutePath );

				if (!ThisFile.exists( ))
						write( "" );
		}

		public void write(String text)
		{
				try
				{
						BufferedWriter bw = new BufferedWriter( new FileWriter( AbsolutePath ) );	   
						try
						{
								bw.write( text );
						}
						finally
						{
								bw.close( );
						}
				}
				catch (IOException e)
				{
						throw new RuntimeException( e );
				}
    }

		public String read()
		{
        StringBuilder sb = new StringBuilder( );

        try
				{            
            BufferedReader in = new BufferedReader( new FileReader( AbsolutePath ) );
            try
						{
                String s;
                while ((s = in.readLine( )) != null)
								{
                    sb.append( s );
                    sb.append( "\n" );
                }
            }
						finally
						{
                in.close( );
            }
        }
				catch (IOException e)
				{
            throw new RuntimeException( e );
        }
        return sb.toString( );
    }

		public void update(String newText)
		{    	 	
        StringBuilder sb = new StringBuilder( );
        String oldFile = read( );
        sb.append( oldFile );
        sb.append( newText );

        write( sb.toString( ) );		
    }

		public File getFile()
		{ return ThisFile; }

		public String getFileName()
		{ return FileName; }

		public String getAbsolutePath()
		{ return AbsolutePath; }

}
