import java.io.*;
import java.util.Vector;

public class xx_root
{
	static Vector<xx_damp> global_damp;
	static int damp_index = 0;
	
	static String global_name;
	static String path;
		
	public xx_root(String fname) //throws FileNotFoundException
	{
		int bindex = fname.lastIndexOf('/');
		path = fname.substring(0, bindex+1);
		global_name = fname.substring(bindex+1);
		
		start_damp();
	}
	
	public void start_damp() //throws FileNotFoundException
	{
		global_damp = new Vector<xx_damp>();
		global_damp.add(new xx_damp(global_name));
	}
	
}
