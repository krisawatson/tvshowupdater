package com.kricko.tvshowupdater.utorrent;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>UTorrentTest</code> contains tests for the class <code>{@link UTorrent}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:06
 * @author kris
 * @version $Revision: 1.0 $
 */
public class UTorrentTest {
	/**
	 * Run the UTorrent() constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testUTorrent_1()
		throws Exception {

		UTorrent result = new UTorrent();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void getListOfTorrents() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testGetListOfTorrents_1()
		throws Exception {
		UTorrent fixture = new UTorrent();

		fixture.getListOfTorrents();

		// add additional test code here
	}

	/**
	 * Run the void getListOfTorrents() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testGetListOfTorrents_2()
		throws Exception {
		UTorrent fixture = new UTorrent();

		fixture.getListOfTorrents();

		// add additional test code here
	}

	/**
	 * Run the void getToken() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testGetToken_1()
		throws Exception {
		UTorrent fixture = new UTorrent();

		fixture.getToken();

		// add additional test code here
	}

	/**
	 * Run the void getToken() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.net.MalformedURLException.class)
	public void testGetToken_2()
		throws Exception {
		UTorrent fixture = new UTorrent();

		fixture.getToken();

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
		new org.junit.runner.JUnitCore().run(UTorrentTest.class);
	}
}