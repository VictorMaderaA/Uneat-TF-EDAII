package vicand.finaleda.models;

import java.util.HashMap;

public class TermFrequencyData {
	private String word;
	private HashMap<String, Integer> occurrencesCountFile;
	private int ocurrencesDocuments = 0;

	private void incrementDocument() { ocurrencesDocuments++; }

	public void addFileOccrrence(FileProccessedData fileData)
	{
		occurrencesCountFile.put(fileData.getFile().getPath(), fileData.getFileCountWords());
		ocurrencesDocuments++;
	}

	public TermFrequencyData(String word, FileProccessedData fileData)
	{
		this.word= word;
		addFileOccrrence(fileData);
	}

}
