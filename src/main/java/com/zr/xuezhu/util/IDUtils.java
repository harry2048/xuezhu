package com.zr.xuezhu.util;

import java.util.Calendar;
import java.util.Random;

/**
 */
public class IDUtils {

	/**
	 */
	public static String genImageName() {
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		Random random = new Random();
		int end3 = random.nextInt(999);
		String str = millis + String.format("%03d", end3);

		return str;
	}

	public static String genLuJing() {
		Calendar now = Calendar.getInstance();

		String year = now.get(Calendar.YEAR)+"";
		String month = now.get(Calendar.MONTH) + 1 +"";
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		String path = year+"/"+month+"/"+day+"/";
		return path;
	}


	/**
	 * ��Ʒid����
	 */
	public static long genItemId() {
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	public static void main(String[] args) {
		for(int i=0;i< 100;i++)
		System.out.println(genItemId());
	}
}
