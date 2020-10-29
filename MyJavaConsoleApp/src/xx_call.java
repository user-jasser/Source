import java.util.*;
import java.io.*;

interface xx_call
{
	public void func(xx_lexser lex);
}

class xx_call_NULL implements xx_call
{
	@Override
	public void func(xx_lexser lex)
	{
		//xx_type_bass[] b = {new xx_int(), new xx_str()};
		//System.out.println(b[1].getType());
		
		// TODO: Implement this method
		System.out.print("call NULL function "+"\n");
	}
}

class xx_call_INIT implements xx_call
{
	@Override
	public void func(xx_lexser lex)
	{
		// TODO: Implement this method
		System.out.print("call INIT function "+"\n");
		System.out.print("=======?????????======="+"\n");
		
		int index = xx_lex_index.FERST_INDEX.ordinal();
		lex.lex_damp.damp_name = lex.lex_line.get(index).lex_string;
		lex.lex_damp.is_init = true;
		
		Varable.init(lex.lex_damp);
	}
}

class xx_call_IMP implements xx_call
{
	@Override
	public void func(xx_lexser lex)
	{
		// TODO: Implement this method
		System.out.print("call IMP function "+"\n");
		
		if(lex.lex_damp.is_init)
		{
			int index = xx_lex_index.FERST_INDEX.ordinal();
			xx_lex_line l = lex.lex_line.get(index);
			if(l.lex_type == xx_lex_type.CONST_STR)
			{
				xx_root.global_damp.add(new xx_damp(l.lex_bank.const_str));
			}
		}
	}
}

class xx_call_VAR implements xx_call
{
	@Override
	public void func(xx_lexser lex)
	{
		// TODO: Implement this method
		System.out.print("call VAR function "+"\n");
		
		int index = xx_lex_index.FERST_INDEX.ordinal();
		xx_lex_line l = lex.lex_line.get(index);
		
		if(lex.lex_damp.is_init)
		{
			Varable.addGlobalItem(lex);
		}
		else
		{
			
		}
		
	}
}

class xx_call_FUNC implements xx_call
{
	@Override
	public void func(xx_lexser lex)
	{
		// TODO: Implement this method
		System.out.print("call FUNC function "+"\n");
	}
}

// ===================================

class xx_call_END implements xx_call
{
	@Override
	public void func(xx_lexser lex)
	{
		// TODO: Implement this method
		System.out.print("call END function "+"\n");
		
		if(lex.lex_damp.is_init)
		{
			lex.lex_damp.is_init = false;
			System.out.print("========================="+"\n");
		}
	}
}
