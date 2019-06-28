package junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


public class RulesDataProcess {

	/*
	 * Convert Object to XML String
	 */
	public static String printConsoleInput(Object object) throws JsonProcessingException {

		XmlMapper xmlMapper = new XmlMapper();
		// use the line of code for pretty-print XML on console. We should remove it in
		// production.
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

		return xmlMapper.writeValueAsString(object);
	}

	/*
	 * Write Object to XML file
	 */
	public static void writeXMLOutput(Object object, String pathFile)
			throws JsonGenerationException, JsonMappingException, IOException {

		XmlMapper xmlMapper = new XmlMapper();
		// use the line of code for pretty-print XML on console. We should remove it in
		// production.
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

		xmlMapper.writeValue(new File(pathFile), object);
	}

	public static Object ReadXMLInput(String pathFile, Class<?> cls)
			throws JsonParseException, JsonMappingException, IOException {

		XmlMapper xmlMapper = new XmlMapper();
		Object object = xmlMapper.readValue(new File(pathFile), cls);
		return object;
	}
	
	

}