import java.io.*;
import java.util.Vector;
//import java.lang.annotation.*;

public class xx_lexser
{
	int lex_index;
	xx_damp lex_damp;
	static char[] arref = {'+', '-', '/', '*'};
	static xx_ARREF[] enum_arref = {xx_ARREF.PLUS, xx_ARREF.MINUS, 
	                                xx_ARREF.DELL, xx_ARREF.DIV};
	Vector<xx_lex_line> lex_line;
	
	public xx_lexser(xx_damp damp)
	{
		lex_index = 0;
		lex_damp = damp;
	    lex_line = new Vector<xx_lex_line>();
		
		for(int x = 0; x <  xx_lex_index.MAX.ordinal(); x++)
		    lex_line.add(new xx_lex_line());
	}
	
	public int set_const_str(String txt, int y)
	{
		String res = "";
		char ch ;		
		
	    while(true)
		{  
		    y++;
		    if(y >= txt.length())
				break;
			ch = txt.charAt(y);
			if(ch == '"')
			{   
				y++; 			
				break;
			}
			res = res + ch;

			if(y >= txt.length()-1)
			{
				System.out.print(";!!! LINE ERROR !!! : no autintification string needet '\"'");
			}
		}
		lex_line.get(lex_index).index = lex_index;
		lex_line.get(lex_index).lex_string = res;
		lex_line.get(lex_index).lex_type = xx_lex_type.CONST_STR;
		lex_line.get(lex_index).lex_bank.const_str = res;
			
		lex_index ++;		
		return y;
	}
	
	public boolean set_signature(String res)
	{
		if(lex_index == 0)
		{
			lex_line.get(lex_index).signature = xx_CALLS.valueOf(res);
			return true;
		}
		return false;
	}
	
	public boolean set_const_int(String res)
	{
		if(xx_file.isNumber(res))
		{
			Integer x = Integer.valueOf(res);
			lex_line.get(lex_index).lex_type = xx_lex_type.CONST_INT;	
			lex_line.get(lex_index).lex_bank.const_int = x;
			return true;
		}
		return false;
	}
	
	public int set_arref(char ch, int y)
	{	
		for(int t = 0; t < arref.length; t++)
			if(ch == arref[t])
			{		
				lex_line.get(lex_index).lex_bank.lex_arref = enum_arref[t];

				y++;
			} 
		return y;
	}
	
	public void set_word(String str)
	{
		String pref = "";
		boolean b = false;
		
		int bindex = str.lastIndexOf(':');
		if(bindex > 0)
		{
			pref = str.substring(0, bindex);
			str = str.substring(bindex+1);
			b = true;
		}
		if(xx_file.isWord(str))
		{
			lex_line.get(lex_index).lex_type = xx_lex_type.CONST_ID;
			lex_line.get(lex_index).lex_bank.const_id = new xx_const_id(b, pref, str);
		}
	}
	
	public void set_all(String res)
	{
		if(!set_signature(res))
		{
			if(!set_const_int(res))
				set_word(res);
		}	
		lex_line.get(lex_index).index = lex_index;
		lex_line.get(lex_index).lex_string = res;
	}
	
	public void set(String line)
	{
		String res = "";
		boolean is_str = false;
		
		for(int y = 0; y < line.length() ; y++)
		{		
		    y = set_arref(line.charAt(y), y);
		    char ch = line.charAt(y);
			//char ch = line.charAt(y);	
		/*	
			for(int t = 0; t < arref.length; t++)
			    if(ch == arref[t])
				{		
					lex_line.get(lex_index).lex_bank.lex_arref = enum_arref[t];
	!!!	лутьше оставить это тело в место функуии		
					y++;
					ch = line.charAt(y);
				} */
			if(ch == '"')
			{
				y = set_const_str(line, y);
				is_str = true;
			}
			else
			if(ch == ' ')
			{
				set_all(res);
				
				res = "";
				lex_index++;
			}
			else
				res = res + ch;
		}	
		if(!is_str)
		{
			set_all(res);
		}
		else
		   is_str = false;
	}
	
}

class xx_lex_line
{
	int index;
	String lex_string;
	xx_lex_type lex_type;
	xx_lex_bank lex_bank;
	xx_CALLS signature;
	
	public xx_lex_line()
	{
		index = 0;
		lex_string = "";
		lex_type = xx_lex_type.NULL;
		lex_bank = new xx_lex_bank();
		lex_bank.lex_arref = xx_ARREF.MAX;
	}
}

class xx_const_id
{
	boolean is_pref;
	String id_prefics;
	String id_str;
	
	public xx_const_id(boolean b, String pref, String id)
	{
		id_prefics = pref;
		id_str = id;
		is_pref = b;
	}
}

class xx_lex_bank
{
	String const_str;
	Integer const_int;
	double const_double;
	xx_const_id const_id;
	xx_ARREF lex_arref;
}

enum xx_ARREF {PLUS, MINUS, DELL, DIV, MAX;
}

enum xx_CALLS {
	INIT, MAIN, IMP, VAR, ARR, CONST, FUNC, 
	POP, PUSH, CALL, GET, SET, ADD, LOC, IDX, 
	POS, GOTO, IF, IFNO, RET, END;
}

enum xx_lex_type {
	CONST_STR, CONST_INT, CONST_DOBLE, CONST_ID , NULL, MAX; 
}

enum xx_lex_index { 
     SIGNATURE, FERST_INDEX, SECOND_INDEX, MAX; 
}
