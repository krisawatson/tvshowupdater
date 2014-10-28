package com.kricko.tvshowupdater;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>RefactorFilesTest</code> contains tests for the class <code>{@link RefactorFiles}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:04
 * @author kris
 * @version $Revision: 1.0 $
 */
public class RefactorFilesTest {
	/**
	 * Run the void tidyFolders(boolean) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testTidyFolders_1()
		throws Exception {
		boolean existing = false;

		RefactorFiles.tidyFolders(existing);

		// add additional test code here
	}

	/**
	 * Run the void tidyFolders(boolean) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testTidyFolders_2()
		throws Exception {
		boolean existing = true;

		RefactorFiles.tidyFolders(existing);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(RefactorFilesTest.class);
	}
}