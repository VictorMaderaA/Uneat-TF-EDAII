package vicand.finaleda.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vicand.finaleda.models.FileSimple;
import vicand.finaleda.utils.Serializer;

public class AppController {

	final static Logger logger = Logger.getLogger(AppController.class);

	public void StartIndex(String folderPath)
	{
		if("".contentEquals(folderPath))
		{
			logger.trace("Arg: folderPath was empty.");
			return;
		}

		logger.info("Starting Indexing for path: " + folderPath);

		FileExplorerController fileExplController = new FileExplorerController();
		ArrayList<FileSimple> files = fileExplController.GetFilesInFolder(folderPath);

		new ParserController().ParseFileList(files);
	}


	public byte WriteToFile(String fileName)
	{
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		RandomAccessFile stream = null;
		try {
			File yourFile = new File("res\\" + fileName + ".txt");
			yourFile.createNewFile();
			System.out.println(yourFile.getAbsolutePath());
			stream = new RandomAccessFile(yourFile.getAbsolutePath(), "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		catch (IOException e) { //
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		FileChannel channel = stream.getChannel();
		byte[] strBytes = fileContent.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		try {
			channel.write(buffer);
			stream.close();
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

}
