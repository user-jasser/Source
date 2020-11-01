import java.io.*;
import java.util.Vector;

public class xx_file
{
    static Vector<String> line;	

	public static String toStrings()
	{
		String res = "";
		for(int x = 0; x < line.size() ; x++)
			res = res + line.get(x)+"\n";		
		return res;
	}


	public static String compact(String line)
	{
		String res = "";
		boolean corr = true;

		for(int x = 0; x < line.length() ; x++)
		{
			char ch = line.charAt(x);
			if(ch == ';')
				break;
			if(ch == ' ')
				corr = false;
			else
			{
				if(!corr && res != "")
					res = res + ' ';
				res = res + ch;
				corr = true;
			}
			if(ch == '"')
			{  		  
				while(true)
				{
					x = x + 1;
					ch = line.charAt(x);
					res = res + ch;
					if(ch == '"')
						break;
					if(x >= line.length()-1)
					{
						return ";!!! LINE ERROR !!! : no autintification string needet '\"'";
					}
				}
			}
		}
		return res;
	}
	
	public static String read(String fileName) 
	{

		line = new Vector<String>();

        File file = new File(fileName);
        if (!file.exists()){
            System.out.print("FileNotFoundException "+file.getName());
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) 
				{
					s = si_File.compact(s);
					if(s != "")
						line.add(s);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

		System.out.print(xx_file.toStrings());

		//System.out.print(si_line.get(3).charAt(3));
        return "";
    }

	public static String read1(String fileName) throws FileNotFoundException {
		
		line = new Vector<String>();

        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) 
				{
					s = si_File.compact(s);
					if(s != "")
						line.add(s);
                } 
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

		System.out.print(xx_file.toStrings());

		//System.out.print(si_line.get(3).charAt(3));
        return "";
    }
	
	public static boolean isNumber(String str) 
	{
		if (str == null || str.isEmpty()) 
			return false;
		for (int i = 0; i < str.length(); i++) 
		{   
			if (!Character.isDigit(str.charAt(i))) 
				return false;
			}
		return true;
	}
	
	public static boolean isWord(String str)
	{
		if( str == null || str.isEmpty() ) 
			return false;
		if( !Character.isLetter(str.charAt(0)) ) 
			return false;
		for( int i = 1; i < str.length(); i++ ) 
		{   
		    if (str.charAt(i) == '_') 
				i++;
			if (!Character.isLetterOrDigit(str.charAt(i))) 
				return false;
		}
		return true;
	}

}
