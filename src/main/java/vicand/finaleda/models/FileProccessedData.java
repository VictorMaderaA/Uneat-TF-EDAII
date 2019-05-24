package vicand.finaleda.models;

import java.util.HashMap;
import java.util.Map;

public class FileProccessedData {

	private FileSimple file;
	private Map<String, MutableInt> fileWords;

	public FileSimple getFile() {
		return file;
	}

	public Map<String, MutableInt> getFileWords() {
		return fileWords;
	}

	public FileProccessedData(FileSimple file, Map<String, MutableInt> fileWords) {
		super();
		this.file = file;
		this.fileWords = fileWords;
	}

}
