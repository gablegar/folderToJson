package com.canvasestudio;

import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Contains some methods to list files and folders from a directory
 *
 * @author Loiane Groner
 * http://loiane.com (Portuguese)
 * http://loianegroner.com (English)
 */
public class FolderToJson {
	/**
	 * List all the files and folders from a directory
	 * @param directoryName to be listed
	 */
	public void listFilesAndFolders(String directoryName){
		File directory = new File(directoryName);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList){
			System.out.println(file.getName());
		}
	}
	/**
	 * List all the files under a directory
	 * @param directoryName to be listed
	 */
	public JSONArray listFiles(String directoryName){
		JSONArray fileList = new JSONArray();
		File directory = new File(directoryName);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isFile() & !file.getName().contains(".json") & !file.getName().equals(".DS_Store")){
				fileList.add(file.getName());
				System.out.println(file.getName());
			}
		}
		return fileList;
	}
	/**
	 * List all the folder under a directory
	 * @param directoryName to be listed
	 */
	public void listFolders(String directoryName){
		File directory = new File(directoryName);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isDirectory()){
				System.out.println(file.getName());
			}
		}
	}
	/**
	 * List all files from a directory and its subdirectories
	 * @param directoryName to be listed
	 */
	public void listFilesAndFilesSubDirectories(String directoryName){
		File directory = new File(directoryName);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isFile()){
				System.out.println(file.getAbsolutePath());
			} else if (file.isDirectory()){
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}
	}
	public static void main (String[] args){
		FolderToJson listFilesUtil = new FolderToJson();
		final String directoryLinuxMac ="/Users/glegarda/Downloads";
		//Windows directory example
		final String directoryWindows ="C://test";

		//JSONObject obj = new JSONObject();
		//obj.put("name", "mkyong.com");
		//obj.put("age", new Integer(100));

		JSONArray list = listFilesUtil.listFiles(args[0]);

		//obj.put("elements", list);

		try (FileWriter file = new FileWriter(args[0] + "/folderConverted.json")) {
			file.write("photos = '" + list.toJSONString() +"'");
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(list);
	}
}