package com.coding.basic.tree;

import java.io.File;

public class FileList {
	
	public static void list(File f) {	
		if (!f.exists()) {
			return;
		}
		if (f.isDirectory()) {
			System.out.println("dir:"+f.getAbsolutePath());
			File[] files = f.listFiles();
			for (File file : files) {
				list(file);
			}
		} 
		if (f.isFile()) {
			System.out.println("file:" + f.getAbsolutePath());
		}
	}
}