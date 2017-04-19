package com.liren.json.jackson;
import java.io.File;  
import java.io.IOException;  
import java.util.Iterator;  
  
import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.JsonNode;  
import com.fasterxml.jackson.databind.ObjectMapper;  
  
public class DeserializationExampleTreeModle2 {  
      
    public static void main(String[] args) throws JsonProcessingException, IOException{  
        ObjectMapper mapper = new ObjectMapper();  
        JsonNode node = mapper.readTree(new File("country2.json"));  
        //path方法获取JsonNode时，当对象不存在时，返回MISSING类型的JsonNode  
        JsonNode missingNode = node.path("test");  
        if(missingNode.isMissingNode()){  
            System.out.println("JsonNodeType : " + missingNode.getNodeType());  
        }  
  
        System.out.println("country_id:"+node.path("country_id").asText());  
          
        JsonNode provinces = node.path("provinces");  
        for (JsonNode provinceElements : provinces) {  
            Iterator<String> provincesFields = provinceElements.fieldNames();  
            while (provincesFields.hasNext()) {  
                String fieldName = (String) provincesFields.next();  
                String province;  
                if("name".equals(fieldName)){  
                    province = fieldName +":"+ provinceElements.path(fieldName).asText();  
                }else{  
                    province = fieldName +":"+ provinceElements.path(fieldName).asInt();  
                }  
                System.out.println(province);  
            }  
        }  
    }  
  
}  