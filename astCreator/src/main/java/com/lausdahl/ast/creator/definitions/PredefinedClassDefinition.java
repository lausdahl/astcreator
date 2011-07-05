package com.lausdahl.ast.creator.definitions;

import java.util.List;
import java.util.Vector;

public class PredefinedClassDefinition implements IClassDefinition
{
	private String packageName;
	private String name;
	private String namePostfix = "";
	private String tag = "";
	private boolean frozenName = false;

	public PredefinedClassDefinition(String packageName, String name)
	{
		this.packageName = packageName;
		this.name = name;
	}
	
	public PredefinedClassDefinition(String packageName, String name,boolean frozenName)
	{
		this.packageName = packageName;
		this.name = name;
		this.frozenName = frozenName;
	}

	
	public List<String> getImports()
	{
		return new Vector<String>();
	}

	
	public boolean isFinal()
	{
		return false;
	}

	
	public boolean isAbstract()
	{
		return false;
	}

	
	public String getPackageName()
	{
		return packageName;
	}

	
	public void setPackageName(String packageName)
	{

	}

	
	public String getJavaSourceCode()
	{
		return "";
	}

	
	public String getVdmSourceCode()
	{
		return "";
	}

	
	public String getName()
	{
		return name + (frozenName?"":getNamePostfix());
	}

	
	public String getSignatureName()
	{
		return getName();
	}

	
	public String getSuperSignatureName()
	{
		return null;
	}

	
	public List<Field> getFields()
	{
		return null;
	}

	
	public boolean hasSuper()
	{
		return false;
	}

	
	public void addField(Field field)
	{
	}

	
	public void setNamePostfix(String postfix)
	{
		this.namePostfix = postfix;
	}

	
	public String getNamePostfix()
	{
		return namePostfix;
	}

	
	public IClassDefinition getSuperDef()
	{
		return null;
	}

	
	public void setTag(String tag)
	{
		this.tag = tag;
	}

	
	public String getTag()
	{
		return this.tag;
	}

	
	public void setGenericArguments(IInterfaceDefinition... arguments)
	{
		
	}

	
	public List<IInterfaceDefinition> getGenericArguments()
	{
		return new Vector<IInterfaceDefinition>();
	}

	
	public void setGenericArguments(List<IInterfaceDefinition> arguments)
	{
		
	}

	
	public void setAnnotation(String annotation)
	{
		
	}

	
	public List<IInterfaceDefinition> getInterfaces()
	{
		return new Vector<IInterfaceDefinition>();
	}
}