package com.chinasofti.rcloud.common;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.chinasofti.rcloud.web.common.FileUtil;

public class FileUtilTest {

	private static String srcFileName = "httpd.c";
	private static String srcFolder = "D:" + File.separator + "tinyhttpd-0.1.0";

	private static String destFolder = "F:" + File.separator + "file_test";

	private static String deleteFileName = "delete.txt";
	private static String moveFileName = "cookie001.txt";

	private static String string2FileName = "string2file.txt";

	private static Logger logger = Logger.getLogger(FileUtilTest.class);

	@Test
	public void testCopyFile() {
		try {
			FileUtil.copyFile(srcFolder + File.separator + srcFileName,
					destFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testDeleteFile() {
		try {
			FileUtil.deleteFile(srcFolder + File.separator + deleteFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteFolderFile() {
		try {
			FileUtil.deleteFloderFile(destFolder + File.separator);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testMoveFile() {
		try {
			FileUtil.moveFile(srcFolder + File.separator + moveFileName,
					destFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testMoveModifyFile() {
		try {
			FileUtil.moveModifyFile(srcFolder + File.separator + srcFileName,
					destFolder+File.separator+"application.war");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testGenFileSize() {

		long len = FileUtil.genFileSize(srcFolder + File.separator
				+ srcFileName);
		logger.info(srcFolder + File.separator + srcFileName + "文件大小" + len);
	}

	// @Test
	// public void testIsExist() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testListFilebySuffix() {
	// fail("Not yet implemented");
	// }
	//
	@Ignore
	@Test
	public void testString2File() {
		FileUtil.string2File("张三", destFolder + File.separator
				+ string2FileName);
		FileUtil.string2File("丽水", destFolder + File.separator
				+ string2FileName);
	}
	
	@Ignore
	@Test
	public void lineseparator() {
		System.out.println("line.separator" + System.getProperty("line.separator"));
		if (System.getProperty("line.separator").equals("/r/n")) {
			logger.info("//r//n is for windows");
		} else if (System.getProperty("line.separator").equals("/r")) {
			logger.info("//r is for Mac");
		} else if (System.getProperty("line.separator").equals("/n")) {
			logger.info("//n is for Unix/Linux");
		}else{
			logger.info("no");
		}
	}

}
