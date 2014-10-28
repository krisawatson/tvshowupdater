package com.kricko.tvshowupdater.utils;

import java.util.Properties;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ConfigTest</code> contains tests for the class <code>{@link Config}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:04
 * @author kris
 * @version $Revision: 1.0 $
 */
public class ConfigTest {
	/**
	 * Run the Config() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testConfig_1()
		throws Exception {

		Config result = new Config();

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.updateBeforeDownload());
	}

	/**
	 * Run the Config() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testConfig_2()
		throws Exception {

		Config result = new Config();

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.updateBeforeDownload());
	}

	/**
	 * Run the Config() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testConfig_3()
		throws Exception {

		Config result = new Config();

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.updateBeforeDownload());
	}

	/**
	 * Run the Config() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testConfig_4()
		throws Exception {

		Config result = new Config();

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.updateBeforeDownload());
	}

	/**
	 * Run the boolean getBooleanProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetBooleanProperty_1()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		boolean result = fixture.getBooleanProperty(propertyName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean getBooleanProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetBooleanProperty_2()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		boolean result = fixture.getBooleanProperty(propertyName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean getBooleanProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetBooleanProperty_3()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		boolean result = fixture.getBooleanProperty(propertyName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the Config getInstance() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetInstance_1()
		throws Exception {

		Config result = Config.getInstance();

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.updateBeforeDownload());
	}

	/**
	 * Run the Config getInstance() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetInstance_2()
		throws Exception {

		Config result = Config.getInstance();

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.updateBeforeDownload());
	}

	/**
	 * Run the Integer getIntProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetIntProperty_1()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		Integer result = fixture.getIntProperty(propertyName);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Integer getIntProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetIntProperty_2()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		Integer result = fixture.getIntProperty(propertyName);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Integer getIntProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetIntProperty_3()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		Integer result = fixture.getIntProperty(propertyName);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Properties getProperties() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetProperties_1()
		throws Exception {
		Config fixture = Config.getInstance();

		Properties result = fixture.getProperties();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testGetProperty_1()
		throws Exception {
		Config fixture = Config.getInstance();
		String propertyName = "";

		String result = fixture.getProperty(propertyName);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the boolean updateBeforeDownload() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testUpdateBeforeDownload_1()
		throws Exception {
		Config fixture = Config.getInstance();

		boolean result = fixture.updateBeforeDownload();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean updateBeforeDownload() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Test
	public void testUpdateBeforeDownload_2()
		throws Exception {
		Config fixture = Config.getInstance();

		boolean result = fixture.updateBeforeDownload();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
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
		new org.junit.runner.JUnitCore().run(ConfigTest.class);
	}
}