package org.overture.tools.astcreator.definitions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.overture.tools.astcreator.env.Environment;
import org.overture.tools.astcreator.java.definitions.JavaName;
import org.overture.tools.astcreator.methods.Method;

public class InterfaceDefinition implements IInterfaceDefinition
{
	private boolean isBase;
	private boolean isExt;
	public List<Method> methods = new Vector<Method>();
	public Set<IInterfaceDefinition> imports = new HashSet<IInterfaceDefinition>();
	List<String> genericArguments = new Vector<String>();
	public Set<IInterfaceDefinition> supers = new HashSet<IInterfaceDefinition>();
	protected JavaName name;
	public static boolean VDM = false;
	private String tag = "";
	protected String annotation = "";
	protected String extJavaDoc = "";
	// private String javaDoc =
	private String astPackage;
	public boolean filterMethodsIfInherited = false;
	private boolean isFinal = false;
	private boolean isAbstract = false;
	private boolean isWritten = false;

	public String getJavaDoc()
	{
		return "/**\n" + "* Generated file by AST Creator\n"
				+ "* @author Kenneth Lausdahl\n" + extJavaDoc + "*\n" + "*/\n";
	}

	public void setExtJavaDoc(String extJavaDoc)
	{
		this.extJavaDoc = extJavaDoc;
	}

	public InterfaceDefinition(JavaName name, String astPackage)
	{
		this.name = name;
		this.astPackage = astPackage;
	}

	public JavaName getName()
	{
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#getName()
	 */

	public String getNameWithGenericArguments()
	{
		String tmp = name.getPrefix() + this.name.getRawName();
		if (tmp.contains("<"))
		{
			tmp = tmp.replace("<", name.getPostfix() + "<");
		} else if (genericArguments.isEmpty())
		{
			tmp += name.getPostfix();
		} else
		{
			String tmp1 = tmp + name.getPostfix() + "<";
			for (String arg : genericArguments)
			{
				tmp1 += arg + ", ";
			}
			if (!genericArguments.isEmpty())
			{
				tmp1 = tmp1.substring(0, tmp1.length() - 2);
			}
			tmp = tmp1 + ">";
		}
		return tmp;
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#getImports()
	 */

	public Set<String> getImports(Environment env)
	{
		Set<String> imports = new HashSet<String>();

		// for (IInterfaceDefinition i : this.imports)
		// {
		// imports.add(i.getName().getCanonicalName());
		// }
		// imports.addAll(this.imports);
		for (Method m : filter(methods))
		{
			if (m.isConstructor)
			{
				continue;
			}
			for (String string : m.getRequiredImportsSignature(env))
			{
				imports.add(string);
			}
		}

		for (IInterfaceDefinition i : supers)
		{
			imports.add(i.getName().getCanonicalName());
		}

		return imports;
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#isFinal()
	 */

	public boolean isFinal()
	{
		return isFinal;
	}

	public void setFinal(boolean isFinal)
	{
		this.isFinal = isFinal;
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#isAbstract()
	 */

	public boolean isAbstract()
	{
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract)
	{
		this.isAbstract = isAbstract;
	}

	@Override
	public String toString()
	{
		return getName().getName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#getSignatureName()
	 */

	public String getSignatureName()
	{
		return getName().getName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#getJavaSourceCode()
	 */

	public String getJavaSourceCode(StringBuilder sb, Environment env)
	{

		sb.append(IInterfaceDefinition.copyrightHeader + "\n");
		sb.append(IClassDefinition.classHeader + "\n");

		if (getName().getPackageName() != null)
		{
			sb.append("\npackage " + getName().getPackageName() + ";\n\n\n");
		}

		for (String importName : getImports(env))
		{
			sb.append("import " + importName + ";\n");
		}

		sb.append("\n\n" + getJavaDoc());
		sb.append("public interface " + getName().getName());

		sb.append(getGenericsString());

		if (!supers.isEmpty())
		{
			sb.append(" extends ");
			StringBuilder intfs = new StringBuilder();
			for (IInterfaceDefinition intfName : supers)
			{
				String genericStr = "";
				List<String> gas = intfName.getGenericArguments();
				for (String ga : gas)
				{
					if ("".equals(genericStr))
						genericStr = "<";
					else
						genericStr += ", ";
					genericStr += ga;
				}
				if (!"".equals(genericStr))
					genericStr += ">";

				intfs.append(intfName.getName().getName() + genericStr + ", ");
			}
			sb.append(intfs.subSequence(0, intfs.length() - 2));
		}

		sb.append("\n{");

		// String tmp = IClassDefinition.classHeader
		// + "\n\npackage generated.node;\n\n\n";
		//
		// tmp += "public " + "interface " + name;
		//
		// tmp += "\n{\n\n";

		for (Method m : filter(methods))
		{
			if (m.isConstructor)
			{
				continue;
			}
			sb.append(m.getJavaDoc(env) + "\n");
			sb.append(m.getSignature(env) + ";\n");
		}

		sb.append("\n}\n");
		return sb.toString();
	}

	public String getGenericsString()
	{
		StringBuilder sb = new StringBuilder();
		if (!this.genericArguments.isEmpty())
		{
			sb.append("<");
			for (Iterator<String> itr = this.genericArguments.iterator(); itr.hasNext();)
			{
				String type = itr.next();
				sb.append(type);
				if (itr.hasNext())
				{
					sb.append(", ");
				}
			}
			sb.append(">");
		}
		return sb.toString();
	}

	private List<Method> filter(List<Method> methods2)
	{
		// List<Method> filtered = new Vector<Method>();
		// if(filterMethodsIfInherited)
		// {
		// for (Method method : methods2)
		// {
		// boolean found = false;
		// System.out.println("Trying to filter: "+method.name);
		// Set<Method> inherited = getMethod(method.name);
		// for (Method method2 : inherited)
		// {
		// if(method!=method2 && method.isSignatureEqual(method2))
		// {
		// found = true;
		// }
		// }
		// if(!found)
		// {
		// filtered.add(method);
		// }
		// }
		// return filtered;
		// }
		return methods2;
	}

	/*
	 * (non-Javadoc)
	 * @see org.overture.tools.astcreator.IInterfaceDefinition#getVdmSourceCode()
	 */

	public String getVdmSourceCode(StringBuilder sb)
	{
		return "";
	}

	protected List<String> getGenericClassArguments()
	{
		List<String> args = new Vector<String>();

		if (getNameWithGenericArguments().contains("<"))
		{
			String tmp = getNameWithGenericArguments().substring(getNameWithGenericArguments().indexOf('<') + 1, getNameWithGenericArguments().indexOf('>'));
			for (String string : tmp.split(","))
			{
				args.add(string);

			}
		}
		return args;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getTag()
	{
		return this.tag;
	}

	// public void setGenericArguments(String... arguments)
	// {
	// if (arguments != null)
	// {
	// this.genericArguments.addAll(Arrays.asList(arguments));
	// }
	// }

	public void setGenericArguments(List<String> arguments)
	{
		if (arguments != null)
		{
			this.genericArguments.addAll(arguments);
		}
	}

	public List<String> getGenericArguments()
	{
		return this.genericArguments;
	}

	public void setAnnotation(String annotation)
	{
		this.annotation = annotation;
	}

	public List<Method> getMethods()
	{
		return methods;
	}

	public Set<Method> getMethod(String name)
	{
		Set<Method> matches = new HashSet<Method>();
		for (IInterfaceDefinition s : supers)
		{
			matches.addAll(s.getMethod(name));
		}

		for (Method m : methods)
		{
			if (m.name != null && m.name.equals(name))
			{
				matches.add(m);
			}
		}

		return matches;
	}

	public void addMethod(Method m)
	{
		this.methods.add(m);
	}

	public Set<IInterfaceDefinition> getSuperDefs()
	{
		return this.supers;
	}

	public boolean isJavaSourceWritten()
	{
		return isWritten;
	}

	public void setJavaSourceWritten(boolean isWritten)
	{
		this.isWritten = isWritten;
	}

	// @Override
	// public int hashCode()
	// {
	// return getName().hashCode();
	// }

	public String getAstPackage()
	{
		return astPackage;
	}

	@Override
	public void setIsBaseTree(boolean b)
	{
		this.isBase = b;
	}

	@Override
	public boolean isBaseTree()
	{
		return this.isBase;
	}

	@Override
	public void setIsExtTree(boolean b)
	{
		this.isExt = b;
	}

	@Override
	public boolean isExtTree()
	{
		return this.isExt;
	}

}
