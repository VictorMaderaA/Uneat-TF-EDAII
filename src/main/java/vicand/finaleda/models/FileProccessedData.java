package vicand.finaleda.models;

import java.util.HashMap;
import java.util.Map;

public class FileProccessedData {


	private String fileName;
	private String filePath;
	private Map<String, MutableInt> fileWords;

	public String getFileName() {
		return fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public Map<String, MutableInt> getFileWords() {
		return fileWords;
	}

	public FileProccessedData(String fileName, String filePath, Map<String, MutableInt> fileWords) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileWords = fileWords;
	}


}
