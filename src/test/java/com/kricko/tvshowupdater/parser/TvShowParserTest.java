package com.kricko.tvshowupdater.parser;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;

/**
 * The class <code>TvShowParserTest</code> contains tests for the class <code>{@link TvShowParser}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:04
 * @author kris
 * @version $Revision: 1.0 $
 */
public class TvShowParserTest {
	/**
	 * Run the Shows parseShows() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testParseShows_1()
		throws Exception {
		TvShowParser fixture = new TvShowParser();

		Shows result = fixture.parseShows();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Shows parseShows() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testParseShows_2()
		throws Exception {
		TvShowParser fixture = new TvShowParser();

		Shows result = fixture.parseShows();

		List<Details> details = result.getShows();
		// add additional test code here
		assertNotNull(details);
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
		new org.junit.runner.JUnitCore().run(TvShowParserTest.class);
	}
}