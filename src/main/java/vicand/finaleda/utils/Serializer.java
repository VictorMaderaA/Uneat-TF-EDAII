package vicand.finaleda.utils;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Serializer {

	//https://www.baeldung.com/jackson-xml-serialization-and-deserialization
	public void Serialize(Object data)
	{
		XmlMapper xmlMapper = new XmlMapper();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			xmlMapper.writeValue(byteArrayOutputStream, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(byteArrayOutputStream);
	}

}
