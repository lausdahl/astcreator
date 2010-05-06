// this file is automatically generated by treegen. do not modify!

package nl.marcelverhoef.treegen.ast.imp;

// import the abstract tree interfaces
import nl.marcelverhoef.treegen.ast.itf.*;

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
	private java.util.Vector<? extends TreeGenAstCompositeField> m_fields = new java.util.Vector<TreeGenAstCompositeField>();

	// public operation to retrieve the embedded private field value
	public java.util.List<? extends ITreeGenAstCompositeField> getFields()
	{
		return m_fields;
	}

	// public operation to set the embedded private field value
	public void setFields(java.util.Vector<? extends TreeGenAstCompositeField> p_fields)
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

	// the identity function
	public String identify() { return "TreeGenAstCompositeDefinition"; }
}
