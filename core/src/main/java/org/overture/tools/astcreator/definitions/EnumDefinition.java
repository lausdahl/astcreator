package org.overture.tools.astcreator.definitions;

import java.util.List;
import java.util.Vector;

import org.overture.tools.astcreator.env.Environment;
import org.overture.tools.astcreator.java.definitions.JavaName;

public class EnumDefinition extends BaseClassDefinition
{
	public List<String> elements = new Vector<String>();

	public EnumDefinition(JavaName name, String astPackage)
	{
		super(name, astPackage);

	}

	@Override
	public String getJavaSourceCode(StringBuilder sb, Environment env)
	{
		sb.append(IInterfaceDefinition.copyrightHeader + "\n");
		sb.append(IClassDefinition.classHeader + "\n");

		if (getName().getPackageName() != null)
		{
			sb.append("\npackage " + getName().getPackageName() + ";\n");
		}

		for (String importName : getImports(env))
		{
			sb.append("import " + importName + ";\n");
		}
		sb.append("\n\n" + getJavaDoc());
		sb.append("public enum " + getName());
		sb.append("\n{\n");

		if (!elements.isEmpty())
		{
			StringBuilder intfs = new StringBuilder();
			for (String intfName : elements)
			{
				intfs.append("\t" + intfName + ",\n");
			}
			sb.append(intfs.subSequence(0, intfs.length() - 2));
		}

		sb.append("\n}\n");

		return sb.toString();
	}

}
