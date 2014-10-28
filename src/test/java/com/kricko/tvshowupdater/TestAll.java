package com.kricko.tvshowupdater;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 28/10/14 09:06
 * @author kris
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	RefactorFilesTest.class,
	DownloadShowsTest.class,
	AppTest.class,
	com.kricko.tvshowupdater.parser.TestAll.class,
	com.kricko.tvshowupdater.refactor.TestAll.class,
	com.kricko.tvshowupdater.thetvdb.TestAll.class,
	com.kricko.tvshowupdater.utils.TestAll.class,
	com.kricko.tvshowupdater.utorrent.TestAll.class,
	com.kricko.tvshowupdater.xbmc.TestAll.class,
})
public class TestAll {

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
