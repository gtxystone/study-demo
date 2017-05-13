package com.liren.guava;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class JoinerDemo {

	List<String> stringList = new ArrayList<String>();

	@Before
	public void init() {
		stringList.add("a");
		stringList.add("b");
		stringList.add("c");
		stringList.add(null);
	}

	// 默认情况，遇到null，抛exception
	@Test
	public void defaultJoin() {
		String strDefault = Joiner.on("|").join(stringList);
		System.out.println(strDefault);
	}

	@Test
	public void skipNull() {
		// 忽略null
		String str = Joiner.on("|").skipNulls().join(stringList);
		System.out.println(str);
	}

	@Test
	public void useForNull() {
		// 不忽略null
		String str2 = Joiner.on("|").useForNull("no value").join(stringList);
		System.out.println(str2);
	}

	// joiner 可以使用实现Appendable接口的类
	@Test
	public void join() throws Exception {
		FileWriter fileWriter = new FileWriter("D://log//haha.txt");
		Joiner joiner = Joiner.on("#").useForNull(" ");
		joiner.appendTo(fileWriter, stringList);
		fileWriter.close();
	}

	// 用于对map进行连接
	@Test
	public void testMapJoiner() {
		Map<String, String> testMap = Maps.newLinkedHashMap();
		testMap.put("Washington D.C", "Redskins");
		testMap.put("New York City", "Giants");
		testMap.put("Philadelphia", "Eagles");
		testMap.put("Dallas", "Cowboys");
		String returnedString = Joiner.on("#").withKeyValueSeparator("=").join(testMap);
		System.out.println(returnedString);
	}
}