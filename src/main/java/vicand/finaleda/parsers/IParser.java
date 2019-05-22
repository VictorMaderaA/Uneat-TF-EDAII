package vicand.finaleda.parsers;

import org.apache.tika.metadata.Metadata;

import vicand.finaleda.models.ParserData;

public interface IParser {

	public ParserData GetMetadata(String filePath);
}
