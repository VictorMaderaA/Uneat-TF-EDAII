package vicand.finaleda.tfidf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import vicand.finaleda.models.FileProccessedData;
import vicand.finaleda.models.FileSimple;
import vicand.finaleda.models.MutableInt;
import vicand.finaleda.models.TermFrequencyData;

public class TermFrequencyController {

	private Calculations calc = new Calculations();
	HashMap<String, TermFrequencyData> wordTermFreqData = new HashMap<String, TermFrequencyData>();

	public void GetSerchTF(ArrayList<FileProccessedData> filesData)
	{
		PrepareData(filesData);
	}

	private void PrepareData(ArrayList<FileProccessedData> filesData)
	{
		for (FileProccessedData f : filesData) {
			Map<String, MutableInt> hashWords = f.getFileWords();
			String filePath = f.getFile().getPath();

			Iterator<Entry<String, MutableInt>> it = hashWords.entrySet().iterator();
			while (it.hasNext()) {

				Map.Entry<String, MutableInt> pair = it.next();

				String word = pair.getKey();
				int frequency = pair.getValue().get();

				TermFrequencyData WTFD = wordTermFreqData.get(filePath);
				if(WTFD == null)
					wordTermFreqData.put(filePath, new TermFrequencyData());
				else
					WTFD.addFileOccrrence(f);

				it.remove(); // avoids a ConcurrentModificationException
			}


		}
	}
}
