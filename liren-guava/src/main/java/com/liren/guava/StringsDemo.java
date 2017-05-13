package com.liren.guava;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;

public class StringsDemo {  
    /** 
     * Charsets 
     */  
    public void testCharsets(){  
        byte[] bytes="haha".getBytes(Charsets.UTF_8);  
    }  
  
    /** 
     * Strings 
     static String nullToEmpty(String string) 如果string为null，则返回"" 
     static String emptyToNull(String string) 如果string为"",返回null 
     static boolean isNullOrEmpty(String string) 判断string是否为null或长度是否为0 
     static String commonPrefix(CharSequence a, CharSequence b) 返回a,b共同的最长前缀 
     static String commonSuffix(CharSequence a, CharSequence b) 返回a,b共同的最长后缀 
     static String padEnd(String string, int minLength, char padChar) 使用padChar在尾部将string填充到指定长度minLength 
     static String padStart(String string, int minLength, char padChar) 使用padChar在头部将string填充到指定长度minLength 
     static String repeat(String string, int count) 将字符串重复N次，返回新字符串 
     */  
    @Test  
    public void testStrings(){  
        String res=Strings.padEnd("start",10,'a');  
        System.out.println(res);  
  
        res=Strings.repeat("hello",5);  
        System.out.println(res);  
    }  
  
    @Test  
    public void testCharMatcher(){  
        //换行使用空格替换  
//        CharMatcher.BREAKING_WHITESPACE.replaceFrom(strinfWithlineBreaks," ");  
        //多个空字符使用' ' 替换  
        String tabsAndSpaces="String with         spaces and        tabs";  
        String scrubbed=CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces,' ');  
        System.out.println(scrubbed);  
  
        //去掉首尾空字符，多个空字符使用' ' 替换  
        String tabsAndSpaces2="    String with         spaces and        tabs";  
        scrubbed=CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces2,' ');  
        System.out.println(scrubbed);  
  
        //只保留数字  
        String lettersAndNumbers="foo988yxbar12323";  
        String retained=CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);  
        System.out.println(retained);  
  
        //匹配多个模式  
        String lettersAndOthers="foo988xasfas   dfasd123";  
        CharMatcher cm=CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);  
        retained=cm.retainFrom(lettersAndOthers);  
        System.out.println(retained);  
    }  
}  