package vicand.finaleda.controllers;

import org.apache.log4j.Logger;

import vicand.finaleda.models.FileSimple;

import java.io.File;
import java.util.ArrayList;

public class FileExplorerController {

	final static Logger logger = Logger.getLogger(FileExplorerController.class);

	private ArrayList<FileSimple> files = new ArrayList<FileSimple>();

	public FileExplorerController() {

	}

	public ArrayList<FileSimple> GetFilesInFolder(String path) {
		logger.trace("Obtaining all files from <" + path + ">");
		File file = new File(path);
		listAllFiles(file);
		return files;
	}

	private void listAllFiles(File folder) {
		files.clear();
		File[] fileNames = folder.listFiles();

		for(File file : fileNames){
			if (file.isDirectory()) {
				listAllFiles(file);
			} else {
				files.add(new FileSimple(file));
				//System.out.println(new FileSimple(file).toString());
			}
		}
	}

}