package vicand.finaleda.tfidf;

public class Calculations {

	private double InverseDocumentFrequency(int numDocumentos, int termFreq) {
		double r = Math.log10(1 + (numDocumentos / termFreq) + 1);
		return r;
	}

	public double getTf_idf(int termFrequency, int numDocs)
	{
		double idf = InverseDocumentFrequency(numDocs, termFrequency);
		double r = termFrequency * idf;
		return r;
	}
}
