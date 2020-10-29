import java.io.*;
import java.util.Vector;

public class si_File
{
    static Vector<String> si_line;	
	static si_damp globalDamp;
	
	public static String toString()
	{
		String res = "";
		for(int x = 0; x < si_line.size() ; x++)
			res = res + si_line.get(x);		
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
	
	public static String read(String fileName) throws FileNotFoundException {
        //StringBuilder sb = new StringBuilder();
		si_line = new Vector<String>();
		
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
					  si_line.add(s +"\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
		
		//System.out.print(si_File.toString());
		
		//System.out.print(si_line.get(3).charAt(3));
        return "";//si_line.get(4);
    }
	
}
