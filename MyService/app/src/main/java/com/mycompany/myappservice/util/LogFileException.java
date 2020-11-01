package com.mycompany.myappservice.util;
   
import java.io.*;
import com.mycompany.myappservice.util.FileWorker;

public class LogFileException extends Exception
{
	private static final String mStrFile =  "LogFile.txt";

	public LogFileException(android.content.Context thisContext, String message)
	{
		super(message);
		
		File path = thisContext.getExternalFilesDir(null);
		FileWorker fileWorker = new FileWorker(path.getAbsolutePath(), mStrFile);
		
		fileWorker.update(message + "\n");	
	}
	
}
