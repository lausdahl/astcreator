package org.overture.tools.astcreator.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.overture.tools.astcreator.AstCreatorException;
import org.overture.tools.astcreator.Main;

public class DublicateTest extends TestCase
{
	private static final String TESTDATA_BASE = "src\\test\\resources\\";

	public void testRoots() throws IOException, InstantiationException,
			IllegalAccessException, AstCreatorException
	{
		System.out.println(new File(".").getAbsolutePath());
		File output = new File(FilePathUtil.getPlatformPath("target/testData/dublicates/roots"));
		String inputFile = TESTDATA_BASE + "dublicates\\dublicateRoots.astv2";
		Main.create(null, new FileInputStream(new File(new File(".").getParentFile(), FilePathUtil.getPlatformPath(inputFile)).getAbsolutePath()), output, true, false);
	}

	public void testSubRoots() throws IOException, InstantiationException,
			IllegalAccessException, AstCreatorException
	{
		System.out.println(new File(".").getAbsolutePath());
		File output = new File(FilePathUtil.getPlatformPath("target/testData/dublicates/subs"));
		String inputFile = TESTDATA_BASE
				+ "dublicates\\dublicateSubRoots.astv2";
		Main.create(null, new FileInputStream(new File(new File(".").getParentFile(), FilePathUtil.getPlatformPath(inputFile)).getAbsolutePath()), output, true, false);
	}
	
	

	public void testSubRootNoParent() throws IOException, InstantiationException,
			IllegalAccessException, AstCreatorException
	{
		System.out.println(new File(".").getAbsolutePath());
		File output = new File(FilePathUtil.getPlatformPath("target/testData/dublicates/noparent"));
		String inputFile = TESTDATA_BASE
				+ "dublicates\\unconnectedSub.astv2";
		Main.create(null, new FileInputStream(new File(new File(".").getParentFile(), FilePathUtil.getPlatformPath(inputFile)).getAbsolutePath()), output, true, false);
	}
}
