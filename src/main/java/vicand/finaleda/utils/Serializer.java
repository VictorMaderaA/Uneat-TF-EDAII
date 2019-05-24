package vicand.finaleda.utils;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import vicand.finaleda.controllers.FileExplorerController;

public class Serializer {

	final static Logger logger = Logger.getLogger(Serializer.class);

	// https://www.baeldung.com/jackson-xml-serialization-and-deserialization
	public ByteArrayOutputStream Serialize(Object data) {
		XmlMapper xmlMapper = new XmlMapper();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			xmlMapper.writeValue(byteArrayOutputStream, data);
		} catch (Exception e) {
			logger.error("Failed to serialize " + data.toString());
			return null;
		}

		return byteArrayOutputStream;
	}

}
