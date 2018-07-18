package org.liren.collection;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapTest {
	
	@Test
	public void treeMapTest(){
		TreeMap<Integer,Integer> treeMap = new TreeMap<Integer,Integer>();
		treeMap.put(1, 1);
		treeMap.put(5, 5);
		treeMap.put(2, 2);
		treeMap.put(4, 4);
		treeMap.put(3, 3);
		
		for(Map.Entry<Integer,Integer> entry : treeMap.descendingMap().entrySet()){
			System.out.println("key:" + entry.getKey() + ",valus:" + entry.getValue());
		}
		
	}

}
