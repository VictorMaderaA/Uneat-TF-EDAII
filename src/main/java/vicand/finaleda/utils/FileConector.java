package vicand.finaleda.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class FileConector {

	final static Logger logger = Logger.getLogger(FileConector.class);

	public byte WriteToFile(String fileName, String data) {
		return WriteToFile(fileName, data, "txt");
	}
	
	public byte WriteToFile(String fileName, String data, String fileExtension) {
		RandomAccessFile stream = null;
		try {
			File yourFile = new File("res\\" + fileName + "." + fileExtension);
			yourFile.createNewFile();
			System.out.println(yourFile.getAbsolutePath());
			stream = new RandomAccessFile(yourFile.getAbsolutePath(), "rw");
		} catch (FileNotFoundException e) {
			logger.error("File not found", e);
			e.printStackTrace();
			return -1;
		} catch (IOException e) { //
			logger.error("IOException ", e);
			e.printStackTrace();
			return -1;
		}
		FileChannel channel = stream.getChannel();
		byte[] strBytes = data.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		try {
			channel.write(buffer);
			stream.close();
			channel.close();
		} catch (IOException e) {
			logger.error("IOException ", e);
			e.printStackTrace();
		}
		
		logger.info("Writed to file: " + fileName);
		return 1;
	}
	
	public StringBuilder ReadFile(String fileName)
	{
		File yourFile = new File("res\\" + fileName);
		
		InputStream is;
		try {
			is = new FileInputStream(yourFile);
		} catch (FileNotFoundException e) {
			logger.error("Could not read from file " + fileName, e);
			return null;
		}
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		        
		String line = null;
		try {
			line = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		        
		while(line != null){
		   sb.append(line).append("\n");
		   try {
			line = buf.readLine();
		} catch (IOException e) {
			logger.error("Could not read from Line" + fileName, e);
		}
		}
		        
		return sb;
	}

}
