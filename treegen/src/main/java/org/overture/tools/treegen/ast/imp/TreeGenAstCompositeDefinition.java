// this file is automatically generated by treegen. do not modify!

package org.overture.tools.treegen.ast.imp;

// import the abstract tree interfaces
import org.overture.tools.treegen.ast.itf.*;

// import java collection types
import java.util.*;

public class TreeGenAstCompositeDefinition extends TreeGenAstDefinitions implements ITreeGenAstCompositeDefinition
{
	// private member variable (composite_name)
	private String m_composite_name = new String();

	// public operation to retrieve the embedded private field value
	public String getCompositeName()
	{
		return m_composite_name;
	}

	// public operation to set the embedded private field value
	public void setCompositeName(String p_composite_name)
	{
		// consistency check (field must be non null!)
		assert(p_composite_name != null);

		// instantiate the member variable
		m_composite_name = p_composite_name;
	}

	// private member variable (fields)
	private List<ITreeGenAstCompositeField> m_fields = new Vector<ITreeGenAstCompositeField>();

	// public operation to retrieve the embedded private field value
	public List<ITreeGenAstCompositeField> getFields()
	{
		return m_fields;
	}

	// public operation to set the embedded private field value
	public void setFields(List<ITreeGenAstCompositeField> p_fields)
	{
		// consistency check (field must be non null!)
		assert(p_fields != null);

		// instantiate the member variable
		m_fields = p_fields;
	}

	// default constructor
	public TreeGenAstCompositeDefinition()
	{
		super();
		m_composite_name = null;
		m_fields = null;
	}

	// visitor support
	public void accept(ITreeGenAstVisitor pVisitor) { pVisitor.visitCompositeDefinition(this); }

	// the identity function
	public String identify() { return "TreeGenAstCompositeDefinition"; }
}
