package com.kricko.tvshowupdater.xbmc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.util.LRUMap;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>XbmcJsonRpcTest</code> contains tests for the class <code>{@link XbmcJsonRpc}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:05
 * @author kris
 * @version $Revision: 1.0 $
 */
public class XbmcJsonRpcTest {
	/**
	 * Run the XbmcJsonRpc(String,int,boolean,int) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testXbmcJsonRpc_1()
		throws Exception {
		String server = "";
		int port = 1;
		boolean useHttp = true;
		int maxRetries = 1;

		XbmcJsonRpc result = new XbmcJsonRpc(server, port, useHttp, maxRetries,"xbmc",null);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the JSONObject callMethod(String,int,Map<String,Object>) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testCallMethod_1()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);
		String method = "";
		int id = 1;
		Map<String, Object> params = new LRUMap<String, Object>(1, 1);

		JSONObject result = fixture.callMethod(method, id, params);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the JSONObject callMethod(String,int,Map<String,Object>) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testCallMethod_2()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);
		String method = "";
		int id = 1;
		Map<String, Object> params = new LRUMap<String, Object>(1, 1);

		JSONObject result = fixture.callMethod(method, id, params);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the JSONObject callMethod(String,int,Map<String,Object>) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testCallMethod_3()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);
		String method = "";
		int id = 1;
		Map<String, Object> params = new LRUMap<String, Object>(1, 1);

		JSONObject result = fixture.callMethod(method, id, params);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the JSONObject callMethod(String,int,Map<String,Object>) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testCallMethod_4()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);
		String method = "";
		int id = 1;
		Map<String, Object> params = new LRUMap<String, Object>(1, 1);

		JSONObject result = fixture.callMethod(method, id, params);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the JSONObject callMethod(String,int,Map<String,Object>) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testCallMethod_5()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 0,"xbmc",null);
		String method = "";
		int id = 1;
		Map<String, Object> params = new LRUMap<String, Object>(1, 1);

		JSONObject result = fixture.callMethod(method, id, params);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the void cleanVideoLibrary() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testCleanVideoLibrary_1()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.cleanVideoLibrary();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testExecuteAddons_1()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("192.168.1.30", 9090, false, 1,"xbmc",null);

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("addonid", "script.trakt");
		
		fixture.executeAddon(params);

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_2()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_3()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_4()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_5()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_6()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_7()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_8()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testRun_9()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void updateVideoLibrary() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
	 * @throws Exception */
	@Test
	public void testUpdateVideoLibrary_1()
		throws Exception {
		XbmcJsonRpc fixture = new XbmcJsonRpc("", 1, true, 1,"xbmc",null);

		fixture.updateVideoLibrary();

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:05
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
	 * @generatedBy CodePro at 28/10/14 09:05
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
	 * @generatedBy CodePro at 28/10/14 09:05
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(XbmcJsonRpcTest.class);
	}
}