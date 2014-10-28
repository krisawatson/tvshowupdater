package com.kricko.tvshowupdater.refactor;

import org.junit.*;

import static org.junit.Assert.*;

import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;

/**
 * The class <code>FileRefactorerTest</code> contains tests for the class <code>{@link FileRefactorer}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:06
 * @author kris
 * @version $Revision: 1.0 $
 */
public class FileRefactorerTest {
	/**
	 * Run the FileRefactorer() constructor test.
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testFileRefactorer_1()
		throws Exception {
		FileRefactorer result = new FileRefactorer();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_1()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "The Big Bang Theory";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_2()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_3()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_4()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_5()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_6()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_7()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Run the void doRefactor(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	@Test
	public void testDoRefactor_8()
		throws Exception {
		FileRefactorer fixture = new FileRefactorer();
		fixture.tvdb = new TheTVDBApi(Constants.API_KEY);
		String seriesName = "";
		String parentDirectory = "";

		fixture.doRefactor(seriesName, parentDirectory);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
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
	 * @generatedBy CodePro at 28/10/14 09:06
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
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(FileRefactorerTest.class);
	}
}