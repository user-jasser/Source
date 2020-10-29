import java.io.*;
import java.util.Vector;

class xx_call_class
{
	xx_lex_line var;
	xx_call runer;	
	/*
	 enum xx_CALLS {
	 INIT, MAIN, IMP, VAR, ARR, CONST, FUNC, 
	 POP, PUSH, CALL, GET, SET, ADD, LOC, IDX, 
	 POS, GOTO, IF, IFNO, RET, END; } 
	 */
	static  xx_call[] caller = 
	{
		new xx_call_INIT(), new xx_call_NULL(), 
		new xx_call_IMP(),  new xx_call_VAR(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_FUNC(), new xx_call_NULL(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_NULL(), new xx_call_NULL(),
		new xx_call_END() 
	};
		
	public xx_call_class(xx_lexser lex)
	{
		xx_lex_line line = lex.lex_line.get(xx_lex_index.SIGNATURE.ordinal());
		int index = line.signature.ordinal();

		runer = caller[index];
		runer.func(lex);
	}
}

public class xx_damp
{
	String file_name;
	String damp_name;
	boolean is_init = false;
	int damp_index;
	
	int index_lexser;
	Vector<xx_lexser> lex_arr;
	
	public xx_damp(String fname) //throws  FileNotFoundException
	{
		file_name = fname;		
		fname = xx_root.path + fname;
		
		damp_index = xx_root.damp_index;
		xx_root.damp_index++;
		
		System.out.printf(xx_file.read(fname));
		start_lexser();
	}
	
	public xx_lexser next_lex()
	{  
	    xx_lexser lex = lex_arr.get(index_lexser);
		
		index_lexser++;
		return lex;
	}
	
	public void start_lexser()
	{
		index_lexser = 0;
		lex_arr = new Vector<xx_lexser>();
		
		for(int x = 0; x < xx_file.line.size() ; x++)
		{		
			xx_lexser l =  new xx_lexser(this);	
			
			l.set(xx_file.line.get(x));	
			lex_arr.add(l);
		}	
		while(index_lexser < lex_arr.size())
		{
			xx_call_class calls = new xx_call_class(next_lex());
		}
		
	}
	
}
