package vicand.finaleda.models;

public class FileSearchData {

//	private String fileName;
	private String filePath;
	private double tf_idf;
	
//	public String getFileName() {
//		return fileName;
//	}
	public String getFilePath() {
		return filePath;
	}
	public double getTf_idf() {
		return tf_idf;
	}
	
	public FileSearchData(String filePath, double tf_idf) {
		super();
		this.filePath = filePath;
		this.tf_idf = tf_idf;
	}

	


}
