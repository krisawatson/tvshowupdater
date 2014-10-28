package com.kricko.tvshowupdater.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>HttpUtilsTest</code> contains tests for the class <code>{@link HttpUtils}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:06
 * @author kris
 * @version $Revision: 1.0 $
 */
public class HttpUtilsTest {
	/**
	 * Run the void clearProxy() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testClearProxy_1()
		throws Exception {

		HttpUtils.clearProxy();

		// add additional test code here
	}

	/**
	 * Run the String get(String,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testGet_1()
		throws Exception {
		String strUrl = "";
		String data = "";
		String authString = "";

		String result = HttpUtils.get(strUrl, data, authString);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String get(String,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testGet_2()
		throws Exception {
		String strUrl = "";
		String data = "";
		String authString = "";

		String result = HttpUtils.get(strUrl, data, authString);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String post(String,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testPost_1()
		throws Exception {
		String strUrl = "";
		String data = "";
		String authString = "";

		String result = HttpUtils.post(strUrl, data, authString);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String post(String,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testPost_2()
		throws Exception {
		String strUrl = "";
		String data = "";
		String authString = "";

		String result = HttpUtils.post(strUrl, data, authString);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void setProxy(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testSetProxy_1()
		throws Exception {
		String host = "";
		String port = "";

		HttpUtils.setProxy(host, port);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
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
	 * @generatedBy CodePro at 28/10/14 09:06
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
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(HttpUtilsTest.class);
	}
}