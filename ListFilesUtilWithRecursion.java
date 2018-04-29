package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListFilesUtilWithRecursion {

	public String directoryName = "C://Users//SANJU//Downloads//Demo_XML";

	public ArrayList<String> listFilesAndFilesSubDirectories(String directoryName) {

		ArrayList<String> original = new ArrayList<String>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				String temp = file.getAbsolutePath();
				if (temp.endsWith(".xml")) {
					original.add(temp);
				}
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}
		return original;
	}
	public int i = 0;
	public void doChange(ArrayList<String> currentList, String directoryName) {
		ArrayList<String> newList = listFilesAndFilesSubDirectories(directoryName);
		System.out.println(i++);
		if (newList.size() != currentList.size()) { // if size increased
			for (String temp : newList) {
				if (!currentList.contains(temp)) {
					modifyFile(temp,"request","response");
				}
			}
			currentList = listFilesAndFilesSubDirectories(directoryName);
			doChange(currentList, directoryName);
		} else {
			doChange(currentList, directoryName);
		}

	}

	public void deleteFile(String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write("aString1\n");
			out.close();
			boolean success = (new File(fileName)).delete();
			if (success) {
			}
		} catch (IOException e) {
			System.out.println("exception occoured" + e);
		}
	}

	static void modifyFile(String filePath, String oldString, String newString) {
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(oldString, newString);
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ListFilesUtil listFilesUtil = new ListFilesUtil();
		final String directoryWindows = "C://Users//Downloads//Demo_XML";
		// listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows);

		listFilesUtil.doChange(listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows), directoryWindows);

	}
}

-- 
Regards,
Sachin Kumar

