package com.kricko.tvshowupdater.utils;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Shows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The class <code>TvShowUtilsTest</code> contains tests for the class <code>{@link TvShowUtils}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:06
 * @author kris
 * @version $Revision: 1.0 $
 */
public class TvShowUtilsTest {
	private static Config config;
	/**
	 * Run the TvShowUtils() constructor test.
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception
	 */
	@Test
	public void testTvShowUtilsConstructor()
		throws Exception {
		TvShowUtils result = new TvShowUtils();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void appendDirToTidyUpList() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testAppendDirToTidyUpList_1()
		throws Exception {

		TvShowUtils.appendDirToTidyUpList();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:142)
		//       at java.io.FileWriter.<init>(FileWriter.java:78)
		//       at com.kricko.tvshowupdater.utils.TvShowUtils.appendDirToTidyUpList(TvShowUtils.java:137)
	}

	/**
	 * Run the void appendDirToTidyUpList() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testAppendDirToTidyUpList_2()
		throws Exception {

		TvShowUtils.appendDirToTidyUpList();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:142)
		//       at java.io.FileWriter.<init>(FileWriter.java:78)
		//       at com.kricko.tvshowupdater.utils.TvShowUtils.appendDirToTidyUpList(TvShowUtils.java:137)
	}

	/**
	 * Run the void appendDirToTidyUpList() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testAppendDirToTidyUpList_3()
		throws Exception {

		TvShowUtils.appendDirToTidyUpList();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:142)
		//       at java.io.FileWriter.<init>(FileWriter.java:78)
		//       at com.kricko.tvshowupdater.utils.TvShowUtils.appendDirToTidyUpList(TvShowUtils.java:137)
	}

	/**
	 * Run the String buildFileName(Episode) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testBuildFileName_1()
		throws Exception {
		Episode ep = new Episode();
		ep.setEpisodeName("");
		ep.setEpisodeNumber(1);
		ep.setSeasonNumber(1);

		String result = TvShowUtils.buildFileName(ep);

		// add additional test code here
		assertEquals("S01E01 - ", result);
	}

	/**
	 * Run the void downloadNewItems(Item,Details) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Throwable */
	@Test(expected = java.lang.NullPointerException.class)
	public void testDownloadNewItems_1()
		throws Throwable {
		Item item = new Item();
		item.setRawTitle("");
		Details detail = new Details();
		detail.setRegexName("");
		detail.setPath("");

		TvShowUtils.downloadNewItems(config, item, detail);

		// add additional test code here
	}

	/**
	 * Run the void downloadNewItems(Item,Details) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Throwable */
	@Test(expected = java.lang.NullPointerException.class)
	public void testDownloadNewItems_2()
		throws Throwable {
		Item item = new Item();
		item.setRawTitle("");
		item.setLink("");
		Details detail = new Details();
		detail.setRegexName("");
		detail.setPath("");

		TvShowUtils.downloadNewItems(config, item, detail);

		// add additional test code here
	}

	/**
	 * Run the void downloadNewItems(Item,Details) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Throwable */
	@Test(expected = java.lang.NullPointerException.class)
	public void testDownloadNewItems_3()
		throws Throwable {
		Item item = new Item();
		item.setRawTitle("");
		item.setLink("");
		Details detail = new Details();
		detail.setRegexName("");
		detail.setPath("");

		TvShowUtils.downloadNewItems(config, item, detail);

		// add additional test code here
	}

	/**
	 * Run the void downloadNewItems(Item,Details) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Throwable */
	@Test(expected = java.lang.NullPointerException.class)
	public void testDownloadNewItems_4()
		throws Throwable {
		Item item = new Item();
		item.setRawTitle("");
		item.setLink("");
		Details detail = new Details();
		detail.setRegexName("");
		detail.setPath("");

		TvShowUtils.downloadNewItems(config, item, detail);

		// add additional test code here
	}

	/**
	 * Run the void downloadNewItems(Item,Details) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Throwable */
	@Test(expected = java.lang.NullPointerException.class)
	public void testDownloadNewItems_5()
		throws Throwable {
		Item item = new Item();
		item.setRawTitle("");
		Details detail = new Details();
		detail.setRegexName("");
		detail.setPath("");

		TvShowUtils.downloadNewItems(config, item, detail);

		// add additional test code here
	}

	/**
	 * Run the void downloadNewItems(Item,Details) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Throwable */
	@Test(expected = java.lang.NullPointerException.class)
	public void testDownloadNewItems_6()
		throws Throwable {
		Item item = new Item();
		item.setRawTitle("");
		Details detail = new Details();
		detail.setRegexName("");

		TvShowUtils.downloadNewItems(config, item, detail);

		// add additional test code here
	}

	/**
	 * Run the int[] getEpisodeIds(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisodeIds_1()
		throws Exception {
		String value = "";

		int[] result = TvShowUtils.getEpisodeIds(value, "E", 1);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.StringIndexOutOfBoundsException: String index out of range: -2
		//       at java.lang.String.substring(String.java:1911)
		//       at com.kricko.tvshowupdater.utils.TvShowUtils.getEpisodeIds(TvShowUtils.java:176)
		assertNotNull(result);
	}

	/**
	 * Run the int[] getEpisodeIds(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisodeIds_2()
		throws Exception {
		String value = "a";

		int[] result = TvShowUtils.getEpisodeIds(value, "E", 1);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.StringIndexOutOfBoundsException: String index out of range: -2
		//       at java.lang.String.substring(String.java:1911)
		//       at com.kricko.tvshowupdater.utils.TvShowUtils.getEpisodeIds(TvShowUtils.java:176)
		assertNotNull(result);
	}

	/**
	 * Run the int[] getEpisodeIds(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisodeIds_3()
		throws Exception {
		String value = "a";

		int[] result = TvShowUtils.getEpisodeIds(value, "E", 1);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.StringIndexOutOfBoundsException: String index out of range: -2
		//       at java.lang.String.substring(String.java:1911)
		//       at com.kricko.tvshowupdater.utils.TvShowUtils.getEpisodeIds(TvShowUtils.java:176)
		assertNotNull(result);
	}

	/**
	 * Run the Shows getListOfShows() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testGetListOfShows_1()
		throws Exception {

		Shows result = TvShowUtils.getListOfShows();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Shows getListOfShows() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testGetListOfShows_2()
		throws Exception {

		Shows result = TvShowUtils.getListOfShows();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Shows getListOfShows() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testGetListOfShows_3()
		throws Exception {

		Shows result = TvShowUtils.getListOfShows();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the List<String> getListOfTidyUpDirs() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetListOfTidyUpDirs_1()
		throws Exception {

		List<String> result = TvShowUtils.getListOfTidyUpDirs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<String> getListOfTidyUpDirs() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetListOfTidyUpDirs_2()
		throws Exception {

		List<String> result = TvShowUtils.getListOfTidyUpDirs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<String> getListOfTidyUpDirs() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetListOfTidyUpDirs_3()
		throws Exception {

		List<String> result = TvShowUtils.getListOfTidyUpDirs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<String> getListOfTidyUpDirs() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetListOfTidyUpDirs_4()
		throws Exception {

		List<String> result = TvShowUtils.getListOfTidyUpDirs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<String> getListOfTidyUpDirs() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetListOfTidyUpDirs_5()
		throws Exception {

		List<String> result = TvShowUtils.getListOfTidyUpDirs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Item> removeDuplicateEpisodes(List<Item>,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testRemoveDuplicateEpisodes_1()
		throws Exception {
		List<Item> items = new ArrayList<Item>();
		String regex = "";

		List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Item> removeDuplicateEpisodes(List<Item>,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testRemoveDuplicateEpisodes_2()
		throws Exception {
		List<Item> items = new ArrayList<Item>();
		String regex = "";

		List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Item> removeDuplicateEpisodes(List<Item>,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testRemoveDuplicateEpisodes_3()
		throws Exception {
		List<Item> items = new ArrayList<Item>();
		String regex = "";

		List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Item> removeDuplicateEpisodes(List<Item>,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testRemoveDuplicateEpisodes_4()
		throws Exception {
		List<Item> items = new ArrayList<Item>();
		String regex = "";

		List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Item> removeDuplicateEpisodes(List<Item>,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testRemoveDuplicateEpisodes_5()
		throws Exception {
		List<Item> items = new ArrayList<Item>();
		String regex = "";

		List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Item> removeDuplicateEpisodes(List<Item>,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testRemoveDuplicateEpisodes_6()
		throws Exception {
		List<Item> items = new ArrayList<Item>();
		String regex = "";

		List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String replaceSpecialChars(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testReplaceSpecialChars_1()
		throws Exception {
		String value = "";

		String result = TvShowUtils.replaceSpecialChars(value);

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the boolean valid(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testValid_1()
		throws Exception {
		String s = "";

		boolean result = TvShowUtils.valid(s);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean valid(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testValid_2()
		throws Exception {
		String s = null;

		boolean result = TvShowUtils.valid(s);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean valid(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testValid_3()
		throws Exception {
		String s = "";

		boolean result = TvShowUtils.valid(s);

		// add additional test code here
		assertEquals(false, result);
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
		config = new Config();
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
		new org.junit.runner.JUnitCore().run(TvShowUtilsTest.class);
	}
}