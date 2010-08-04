// this file is automatically generated by treegen. do not modify!

package org.overture.tools.treegen.ast.imp;

// import the abstract tree interfaces
import org.overture.tools.treegen.ast.itf.*;

public class TreeGenAstNode implements ITreeGenAstNode
{
	// link each node to a possible parent node
	private ITreeGenAstNode parent = null;

	// retrieve the parent node
	public ITreeGenAstNode getParent() { return parent; }

	// set the parent node
	public void setParent(ITreeGenAstNode pNode)
	{
		assert(pNode != null);
		parent = pNode;
	}

	// private member variable (column)
	private Integer m_column;

	// public set operation for private member variable (column)
	public void setColumn(Integer piv)
	{
		m_column = piv;
	}

	// public get operation for private member variable (column)
	public Integer getColumn()
	{
		return m_column;
	}

	// private member variable (line)
	private Integer m_line;

	// public set operation for private member variable (line)
	public void setLine(Integer piv)
	{
		m_line = piv;
	}

	// public get operation for private member variable (line)
	public Integer getLine()
	{
		return m_line;
	}

	// default constructor
	public TreeGenAstNode()
	{
		m_column = null;
		m_line = null;
	}

	// visitor support
	public void accept(ITreeGenAstVisitor pVisitor) { pVisitor.visitNode(this); }

	// the identity function
	public String identify() { return "TreeGenAstNode"; }
}
