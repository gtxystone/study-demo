package com.liren.json.jackson;
import java.io.File;
import java.io.FileWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;  
  
public class StreamGeneratorJson {  
      
    public static void main(String[] args) throws Exception {  
        JsonFactory factory = new JsonFactory();  
        //从JsonFactory创建一个JsonGenerator生成器的实例  
        JsonGenerator generator = factory.createGenerator(new FileWriter(new File("country3.json")));  
          
        generator.writeStartObject();  
        generator.writeFieldName("country_id");  
        generator.writeString("China");  
        generator.writeFieldName("provinces");  
        generator.writeStartArray();  
        generator.writeStartObject();  
        generator.writeStringField("name", "Shanxi");  
        generator.writeNumberField("population", 33750000);  
        generator.writeEndObject();  
        generator.writeEndArray();  
        generator.writeEndObject();  
          
        generator.close();  
    }  
  
}  