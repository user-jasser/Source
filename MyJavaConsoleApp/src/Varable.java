import java.util.*;
import java.io.*;

interface TypeBass
{
	Vector<String> id_name = new Vector<String>();
	//public xx_lex_type getType();
	public void setValue(xx_lex_line lex);
}

class TypeInt implements TypeBass
{
	Vector var;

	@Override
	public void setValue(xx_lex_line lex)
	{
		// TODO: Implement this method
		System.out.println(id_name.get(id_name.size()-1));
		System.out.println(lex.lex_bank.const_int);
	}


}

class TypeStr implements TypeBass
{
	Vector<String> var ;
	
	@Override
	public void setValue(xx_lex_line lex)
	{
		// TODO: Implement this method
		System.out.println(id_name.get(id_name.size()-1));
		System.out.println(lex.lex_bank.const_str);
	}
}

class TypeNull implements TypeBass
{
	Vector<String> var ;
	
	@Override
	public void setValue(xx_lex_line lex)
	{
		// TODO: Implement this method
		System.out.println(id_name.get(id_name.size()-1));
		System.out.println(lex.lex_type);
	}
}

class TypeAll
{
	TypeBass[] allVar;
	
	public TypeAll()
	{
		allVar = new TypeBass[xx_lex_type.MAX.ordinal()];
		
		allVar[xx_lex_type.CONST_INT.ordinal()] = new TypeInt();
		allVar[xx_lex_type.CONST_STR.ordinal()] = new TypeStr();
		allVar[xx_lex_type.NULL.ordinal()] = new TypeNull();
		//...
	}
	
	public void addItem(xx_lex_line lex1, xx_lex_line lex2)
	{
		int i = lex2.lex_type.ordinal();
		String name = lex1.lex_bank.const_id.id_str;
		for(int x = 0; x < allVar[i].id_name.size() ; x++)
		{
			System.out.println(allVar[1].id_name +"!!!!!!!!!!!!!!!!! "+ allVar[i].id_name.get(x));
			if(allVar[i].id_name.get(x) == name)
			{
				System.out.println("SSSSSSSSSSS");
			}
		}
		
		allVar[i].id_name.add(name);
		allVar[i].setValue(lex2);
	}
}

class VarLib
{
	String nameLib;
	int indexLib;
	TypeAll varLib;
	
	public VarLib(String name, int index)
	{
		nameLib = name;
		indexLib = index;
		varLib = new TypeAll();
		
		System.out.println("#### "+nameLib+ " = "+ indexLib);
	}
}

public class Varable
{
	static Vector<VarLib> varable = new Vector<VarLib>();
	
	static public void init(xx_damp damp)
	{
		varable.add(new VarLib(damp.damp_name, damp.damp_index));
	}
	
	static public void addGlobalItem(xx_lexser lex)
	{
		xx_lex_line lex1 = lex.lex_line.get(xx_lex_index.FERST_INDEX.ordinal());
		xx_lex_line lex2 = lex.lex_line.get(xx_lex_index.SECOND_INDEX.ordinal());
		
		if(lex1.lex_type == xx_lex_type.CONST_ID)
		{
			//lex.lex_damp.damp_index
			System.out.println("## "+lex.lex_damp.damp_name+ " = "+ Varable.varable.get(lex.lex_damp.damp_index).varLib);
			Varable.varable.get(lex.lex_damp.damp_index).varLib.addItem(lex1, lex2);
			
		    //tp.addItem(lex1, lex2);
			
		}
	}
	
}
