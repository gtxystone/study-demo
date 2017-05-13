package com.liren.guava;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class OrderingDemo {

	@Test
	public void testNatural() {
		List<String> list = Lists.newArrayList();
		list.add("peida");
		list.add("jerry");
		list.add("harry");
		list.add("eva");
		list.add("jhon");
		list.add("neron");

		System.out.println("list:" + list);

		Ordering<String> naturalOrdering = Ordering.natural();
		Ordering<Object> usingToStringOrdering = Ordering.usingToString();
		Ordering<Object> arbitraryOrdering = Ordering.arbitrary();

		System.out.println("naturalOrdering:" + naturalOrdering.sortedCopy(list));
		System.out.println("usingToStringOrdering:" + usingToStringOrdering.sortedCopy(list));
		System.out.println("arbitraryOrdering:" + arbitraryOrdering.sortedCopy(list));
	}

	@Test
	public void testFromComparatorOfT() {
		 List<String> list = Lists.newArrayList();
	        list.add("peida");
	        list.add("jerry");
	        list.add("harry");
	        list.add("eva");
	        list.add("jhon");
	        list.add("neron");
	        
	        System.out.println("list:"+ list);
	        
	        Ordering<String> naturalOrdering = Ordering.natural();
	        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));    

	        List<Integer> listReduce= Lists.newArrayList();
	        for(int i=9;i>0;i--){
	            listReduce.add(i);
	        }
	        
	        List<Integer> listtest= Lists.newArrayList();
	        listtest.add(1);
	        listtest.add(1);
	        listtest.add(1);
	        listtest.add(2);
	        
	        
	        Ordering<Integer> naturalIntReduceOrdering = Ordering.natural();
	        
	        System.out.println("listtest:"+ listtest);
	        System.out.println(naturalIntReduceOrdering.isOrdered(listtest));
	        System.out.println(naturalIntReduceOrdering.isStrictlyOrdered(listtest));
	        
	        
	        System.out.println("naturalIntReduceOrdering:"+ naturalIntReduceOrdering.sortedCopy(listReduce));
	        System.out.println("listReduce:"+ listReduce);
	        
	        
	        System.out.println(naturalIntReduceOrdering.isOrdered(naturalIntReduceOrdering.sortedCopy(listReduce)));
	        System.out.println(naturalIntReduceOrdering.isStrictlyOrdered(naturalIntReduceOrdering.sortedCopy(listReduce)));
	        

	        Ordering<String> natural = Ordering.natural();
	              
	        List<String> abc = ImmutableList.of("a", "b", "c");
	        System.out.println(natural.isOrdered(abc));
	        System.out.println(natural.isStrictlyOrdered(abc));
	        
	        System.out.println("isOrdered reverse :"+ natural.reverse().isOrdered(abc));
	 
	        List<String> cba = ImmutableList.of("c", "b", "a");
	        System.out.println(natural.isOrdered(cba));
	        System.out.println(natural.isStrictlyOrdered(cba));
	        System.out.println(cba = natural.sortedCopy(cba));
	        
	        System.out.println("max:"+natural.max(cba));
	        System.out.println("min:"+natural.min(cba));
	        
	        System.out.println("leastOf:"+natural.leastOf(cba, 2));
	        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));    
	        System.out.println("leastOf list:"+naturalOrdering.leastOf(list, 3));
	        System.out.println("greatestOf:"+naturalOrdering.greatestOf(list, 3));
	        System.out.println("reverse list :"+ naturalOrdering.reverse().sortedCopy(list));    
	        System.out.println("isOrdered list :"+ naturalOrdering.isOrdered(list));
	        System.out.println("isOrdered list :"+ naturalOrdering.reverse().isOrdered(list));
	        list.add(null);
	        System.out.println(" add null list:"+list);
	        System.out.println("nullsFirst list :"+ naturalOrdering.nullsFirst().sortedCopy(list));
	        System.out.println("nullsLast list :"+ naturalOrdering.nullsLast().sortedCopy(list));

	}

	@Test
	public void testFromOrderingOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testExplicitListOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testExplicitTTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testAllEqual() {
		fail("Not yet implemented");
	}

	@Test
	public void testUsingToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testArbitrary() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrdering() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverse() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullsFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullsLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnResultOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnKeys() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompoundComparatorOfQsuperU() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompoundIterableOfQextendsComparatorOfQsuperT() {
		fail("Not yet implemented");
	}

	@Test
	public void testLexicographical() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompare() {
		fail("Not yet implemented");
	}

	@Test
	public void testMinIteratorOfE() {
		fail("Not yet implemented");
	}

	@Test
	public void testMinIterableOfE() {
		fail("Not yet implemented");
	}

	@Test
	public void testMinEE() {
		fail("Not yet implemented");
	}

	@Test
	public void testMinEEEEArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaxIteratorOfE() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaxIterableOfE() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaxEE() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaxEEEEArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeastOfIterableOfEInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeastOfIteratorOfEInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGreatestOfIterableOfEInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGreatestOfIteratorOfEInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortedCopy() {
		fail("Not yet implemented");
	}

	@Test
	public void testImmutableSortedCopy() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOrdered() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsStrictlyOrdered() {
		fail("Not yet implemented");
	}

	@Test
	public void testBinarySearch() {
		fail("Not yet implemented");
	}

}
