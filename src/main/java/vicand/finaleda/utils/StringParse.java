package vicand.finaleda.utils;

public class StringParse {

	public void GetWordFromString(String s)
	{
		String[] words = s.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");
		}
	}
}
