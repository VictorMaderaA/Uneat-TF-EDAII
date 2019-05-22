package vicand.finaleda.models;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;

public class ParserData {
	public BodyContentHandler contentHandler;
	public Metadata metadata;
	
	public ParserData(BodyContentHandler handler, Metadata  meta)
	{
		contentHandler = handler;
		metadata = meta;
	}
}
