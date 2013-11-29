package com.lausdahl.ast.creator.methods;

import com.lausdahl.ast.creator.definitions.Field;
import com.lausdahl.ast.creator.env.Environment;

public class ParentSetMethod extends Method
{
	Field f;
	String structureClassName;
	public ParentSetMethod(String structureClassName, Field f,Environment env)
	{
		super(null,env);
		this.f = f;
		this.structureClassName = structureClassName;
	}
	
	@Override
	protected void prepare()
	{
		name = "parent";
		arguments.add(new Argument(structureClassName, "value"));
		body = "\t\tthis." + f.getName() + " = value;";
	}

}
