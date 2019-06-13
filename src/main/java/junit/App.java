package junit;

import java.io.File;
import java.io.IOException;
 
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.avis.drools.RevenueSegmentationData;
 
public class App {
  public static void main(String[] args) throws IOException {
	  RevenueSegmentationData customer = new RevenueSegmentationData();
    
    // 1. Write Object to XML String
      String xml = write2XMLString(customer);
      
      System.out.println(xml);
      /*
      <?xml version="1.0" encoding="UTF-8"?>
      <Customer>
         <name>Mary</name>
         <age>37</age>
         <address>
            <street>NANTERRE CT</street>
            <postcode>77471</postcode>
         </address>
      </Customer>
       */
      
      // 2. Write Object to XML File
      String pathFile = System.getProperty("user.dir") + "\\resource" + "\\output" + "\\RulesOutput.xml";
    //  String pathFile = System.getProperty("user.dir") + "\\RulesInout.xml";
      write2XMLFile(customer, pathFile);
      // -> content of customer.xml file:
      /*
      <?xml version="1.0" encoding="UTF-8"?>
      <Customer>
         <name>Mary</name>
         <age>37</age>
         <address>
            <street>NANTERRE CT</street>
            <postcode>77471</postcode>
         </address>
      </Customer>
       */
  }
  
  /*
   * Convert Object to XML String
   */
  public static String write2XMLString(Object object) 
              throws JsonProcessingException {
    
    XmlMapper xmlMapper = new XmlMapper();
    // use the line of code for pretty-print XML on console. We should remove it in production.
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    
      return xmlMapper.writeValueAsString(object);
  }
  
  /*
   * Write Object to XML file
   */
  public static void write2XMLFile(Object object, String pathFile) 
        throws JsonGenerationException, JsonMappingException, IOException {
    
      XmlMapper xmlMapper = new XmlMapper();
      // use the line of code for pretty-print XML on console. We should remove it in production.
      xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
      
      xmlMapper.writeValue(new File(pathFile), object);
  }
}