package com.utility;

import java.time.LocalDateTime;
import java.util.Random;
/**
 * JavaUtility is used to write java related utility codes like time stamp and etc...
 */
public class JavaUtility {
	/**
	 * getTimeStamp() is used to fetch the local data & time
	 * @return system data
	 */
	public static String getTimeStamp() {
		return LocalDateTime.now().toString().replace(":", "-");
	}
}