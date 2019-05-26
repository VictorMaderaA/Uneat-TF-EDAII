package vicand.finaleda.models;

public class FileSearchData implements Comparable<FileSearchData> {


	private String filePath;
	private double tf_idf;

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

	@Override
	public int compareTo(FileSearchData o) {
		if (tf_idf > o.tf_idf) {
			return -1;
		}
		if (tf_idf < o.tf_idf) {
			return 1;
		}
		return 0;
	}

}
