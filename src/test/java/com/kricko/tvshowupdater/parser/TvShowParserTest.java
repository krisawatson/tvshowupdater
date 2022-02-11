package com.kricko.tvshowupdater.parser;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
	@Test
	public void testParseShow() throws Exception {
		TvShowParser fixture = new TvShowParser();

		Shows result = fixture.parseShows();

		List<Details> details = result.getShows();
		// add additional test code here

		assertEquals(1, details.size());
		assertEquals("Brooklyn Nine Nine", details.get(0).getName());
		assertEquals("Brooklyn Nine Nine", details.get(0).getRegexName());
		assertEquals( "269586", details.get(0).getTvdbSeriesId().orElseGet(null));
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