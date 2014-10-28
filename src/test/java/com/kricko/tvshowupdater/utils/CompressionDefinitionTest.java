package com.kricko.tvshowupdater.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>CompressionDefinitionTest</code> contains tests for the class <code>{@link CompressionDefinition}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:04
 * @author kris
 * @version $Revision: 1.0 $
 */
public class CompressionDefinitionTest {
	/**
	 * Run the CompressionDefinition(String,String,String,List<String>) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testCompressionDefinition_1()
		throws Exception {
		String name = "";
		String command = "";
		String encodeToExt = "";
		List<String> verificationLines = new ArrayList<String>();

		CompressionDefinition result = new CompressionDefinition(name, command, encodeToExt, verificationLines);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getEncodeToExt());
		assertEquals("", result.getName());
		assertEquals("", result.getCommand());
	}

	/**
	 * Run the CompressionDefinition(String,String,String,List<String>) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testCompressionDefinition_2()
		throws Exception {
		String name = "";
		String command = "";
		String encodeToExt = "";
		List<String> verificationLines = new ArrayList<String>();

		CompressionDefinition result = new CompressionDefinition(name, command, encodeToExt, verificationLines);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getEncodeToExt());
		assertEquals("", result.getName());
		assertEquals("", result.getCommand());
	}

	/**
	 * Run the CompressionDefinition(String,String,String,List<String>) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testCompressionDefinition_3()
		throws Exception {
		String name = "";
		String command = "";
		String encodeToExt = "";
		List<String> verificationLines = new ArrayList<String>();

		CompressionDefinition result = new CompressionDefinition(name, command, encodeToExt, verificationLines);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getEncodeToExt());
		assertEquals("", result.getName());
		assertEquals("", result.getCommand());
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testEquals_1()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());
		Object o = new Object();

		boolean result = fixture.equals(o);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testEquals_2()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());
		Object o = new CompressionDefinition("", "", "", new ArrayList<String>());

		boolean result = fixture.equals(o);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testEquals_3()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());
		Object o = new CompressionDefinition("", "", "", new ArrayList<String>());

		boolean result = fixture.equals(o);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the String getCommand() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetCommand_1()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());

		String result = fixture.getCommand();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getEncodeToExt() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEncodeToExt_1()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());

		String result = fixture.getEncodeToExt();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getName() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetName_1()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());

		String result = fixture.getName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the List<String> getVerificationLines() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetVerificationLines_1()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());

		List<String> result = fixture.getVerificationLines();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the int hashCode() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testHashCode_1()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition((String) null, "", "", new ArrayList<String>());

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(581, result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testHashCode_2()
		throws Exception {
		CompressionDefinition fixture = new CompressionDefinition("", "", "", new ArrayList<String>());

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(581, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception
	 *         if the initialization fails for some reason */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception
	 *         if the clean-up fails for some reason */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CompressionDefinitionTest.class);
	}
}