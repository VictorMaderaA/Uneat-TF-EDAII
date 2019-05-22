package vicand.finaleda.controllers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import vicand.finaleda.models.FileSimple;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileExplorerController {

    final static Logger logger = Logger.getLogger(FileExplorerController.class);

    private ArrayList<FileSimple> files = new ArrayList<FileSimple>();

    public FileExplorerController() {
        BasicConfigurator.configure();
    }


    public void GetFilesInFolder(String route)
    {
        //TODO - Read and store all the files in a given folder and
    }

    public void GetAllFiles(String path) {
        logger.trace("Obtaining all files from <" + path + ">");
        File file = new File(path);
        listAllFiles(file);
    }

    private void listAllFiles(File folder) {
        files.clear();
        File[] fileNames = folder.listFiles();

        for(File file : fileNames){
            if (file.isDirectory()) {
                listAllFiles(file);
            } else {
                files.add(new FileSimple(file));
                System.out.println(new FileSimple(file).toString());
            }
        }
    }

    public void SaveFilesToFile() {
        logger.trace("Saving Files List to File");
        try {
            FileOutputStream fos = new FileOutputStream("filesListData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(files);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            logger.error("Failed to serialize Files list", ioe);
        }
    }

    public void GetFilesFromFile() {
        logger.trace("Retrieving Files List from File");

    }

}