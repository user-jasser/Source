import java.io.*;
import java.util.Vector;

class si_varable
{
	static enum Autin {INT, STR, BOOL, GOTO};

	Autin getAutin;
	String stringValue;
	int intValue;
	boolean boolValue;
	si_callDamp jampValue;
}

class si_callDamp
{
	//lecser lex;
	si_varable var;
	
	call runer;
	
	public si_callDamp(String autin, String name, call f)
	{
		runer = f;
		
		runer.func(this);
	}
	
	public si_callDamp(String name, call f)
	{
		runer = f;

		runer.func(this);
	}
}

public class si_damp
{
	String name;
	int indexLex = 0;
    Vector<lecser> si_dampArr;
	Vector<si_callDamp> callDamp;	
	Vector<si_damp> localDamp;
	
	static call[] caller = {new call_MAIN(),
	       new call_IMP(), new call_VAR(), 
		   new call_CONST(), new call_FUNC(),
		   new call_POP(), new call_LOC() };
		   
	static enum CALLS {MAIN, IMP, VAR, 
	       CONST, FUNC, POP, LOC}

	public lecser getNextLex()
	{  
	    lecser ret = si_dampArr.get(indexLex);
		indexLex++;
		
		return ret;
	}
	
	public si_damp(String fname)
	{
		System.out.print("000000000000000000000000000000000");
		run(fname);
		while(indexLex < si_dampArr.size())
		{
			lecser lex = getNextLex();
			String str = lex.lexString.get(lexIndex.FERST_INDEX.ordinal());
		    int bindex = str.indexOf(':');
		    if(bindex > 0)
			{
			   String left = str.substring(0, bindex);
			   String rath = str.substring(bindex +1);
			   String name = lex.lexString.get(lexIndex.SECOND_INDEX.ordinal());
			   si_callDamp damp = new si_callDamp(rath, name, caller[CALLS.valueOf(left).ordinal()]);
			} 
		else		   
			{
				System.out.print(" ["+str+"] ");
				//si_callDamp damp = new si_callDamp(name, caller[CALLS.valueOf(str).ordinal()]);
			}
			
		}
	}
	
	public void run(String fname)
	{
		System.out.print(fname);
		name = fname;
		
		si_dampArr = new Vector<lecser>();
		for(int x = 1; x < si_File.si_line.size() ; x++)
		{
			String res = "";
			int indexLex = 0;
			lecser l =  new lecser();
			l.lexString = new Vector<String>(lexIndex.MAX.ordinal());
			si_dampArr.add(l);
			
			for(int y = 0; y < si_File.si_line.get(x).length() ; y++)
			{
				char ch = si_File.si_line.get(x).charAt(y);		
				if(ch == '"')
				{  		  
					while(true)
					{   y++;
						ch = si_File.si_line.get(x).charAt(y);
						if(ch == '"')
						{   
						    y++; 
							ch = si_File.si_line.get(x).charAt(y);
							break;
						}
						res = res + ch;
						
						if(y >= si_File.si_line.get(x).length()-1)
						{
							System.out.print(";!!! LINE ERROR !!! : no autintification string needet '\"'");
						}
					}
				}
				if(ch == ' ')
				{
					l.lexString.add(res);
					indexLex++;
					res = "";
				}
				else
				res = res + ch;
			}
			l.lexString.add(res);
		}
		//System.out.print(si_dampArr.get(3).lexString.get(lexIndex.FERST_INDEX.ordinal()));
		//System.out.print(lexIndex.MAX.ordinal());
	}
}
