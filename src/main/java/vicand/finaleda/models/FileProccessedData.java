package vicand.finaleda.models;

import java.util.HashMap;
import java.util.Map;

public class FileProccessedData {


	private FileSimple file;
	private Map<String, MutableInt> fileWords;
	private int fileCountWords;

	public FileSimple getFile() {
		return file;
	}
	public Map<String, MutableInt> getFileWords() {
		return fileWords;
	}
	public int getFileCountWords() {
		return fileCountWords;
	}

	public FileProccessedData(FileSimple file, Map<String, MutableInt> fileWords, int fileCountWords) {
		super();
		this.file = file;
		this.fileWords = fileWords;
		this.fileCountWords = fileCountWords;
	}


}
