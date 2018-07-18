package com.liren.guava;

import java.util.Map;

import org.junit.Test;

import com.google.common.base.Splitter;

public class SplitterDemo {  
    /** 
     * a common task for programmers is to take a string with some delimiter character 
     * and plit that string on the delimiter and obtain an array of the parts of the string 
     */  
    @Test  
    public void common(){  
        String testString="Monday, Tuesday,,Thursday,Friday,,";  
        Iterable<String> parts=Splitter.on(",").trimResults().omitEmptyStrings().split(testString);  
        for(String s:parts)  
            System.out.println(s);  
    }  
  
    /** 
     * Splitter.trimResults 去掉结果集Iterable每个元素的首尾的空格 
     */  
    @Test  
    public void trimResults(){  
        //Just like the Joiner class,Splitter is immutable on creation,  
        //so care must be taken to not call the trimResults method after  
        //creating the original Splitter class  
        Splitter splitter=Splitter.on("|").trimResults();  
        Iterable<String> parts=splitter.split("1| 2 | 3 ||");  
        for(String s:parts)  
            System.out.println(s);  
    }  
  
    /** 
     * 将指定格式的字符串分割成map 
     */  
    @Test  
    public void testSplitToMap(){  
        String testString="Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";  
        Splitter.MapSplitter mapSplitter=Splitter.on("#").withKeyValueSeparator("=");  
        Map<String,String> testMap=mapSplitter.split(testString);  
        for(String key:testMap.keySet())  
            System.out.println(testMap.get(key));  
  
    }  
    
    @Test  
    public void testTest(){  
       String src = ",2,6,";
       String des = ",6,";
       int indexOf = src.indexOf(des);
       System.out.println(indexOf);
  
    }  
}  