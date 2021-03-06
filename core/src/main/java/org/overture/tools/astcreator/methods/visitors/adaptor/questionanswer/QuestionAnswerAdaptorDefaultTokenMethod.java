package org.overture.tools.astcreator.methods.visitors.adaptor.questionanswer;

import org.overture.tools.astcreator.env.Environment;
import org.overture.tools.astcreator.methods.visitors.adaptor.analysis.AnalysisAdaptorDefaultTokenMethod;

public class QuestionAnswerAdaptorDefaultTokenMethod extends
		AnalysisAdaptorDefaultTokenMethod
{
	public QuestionAnswerAdaptorDefaultTokenMethod()
	{

	}

	@Override
	protected void prepare(Environment env)
	{
		addReturnToBody = true;
		super.prepare(env);
		this.returnType = "A";
	}

	@Override
	protected void setupArguments(Environment env)
	{
		super.setupArguments(env);
		this.arguments.add(new Argument("Q", "question"));
	}
}
