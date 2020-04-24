package com.wondersgroup.base.login.util;

import java.util.HashMap;
import java.util.Map;

public class FileType {
	public static final String JPG = "JPG";
	public static final String PNG = "PNG";
	public static final String GIF = "GIF";

	private static final Map<String, String> types = new HashMap<String, String>() {
		{
			put(FileType.JPG, ".jpg");
			put(FileType.PNG, ".png");
			put(FileType.GIF, ".gif");
		}
	};

	public static String getSuffix(String key) {
		return FileType.types.get(key);
	}

	/**
	 * 
	 *
	 * @描述：根据给定的文件名,获取其后缀信息
	 *
	 * @param filename
	 * @return
	 * String
	 * @创建人  ：guokangcen
	 * @创建时间：2014年10月26日下午1:46:06
	 * @修改人  ：guokangcen
	 * @修改时间：2014年10月26日下午1:46:06
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static String getSuffixByFilename(String filename) {

		return filename.substring(filename.lastIndexOf(".")).toLowerCase();

	}
}
