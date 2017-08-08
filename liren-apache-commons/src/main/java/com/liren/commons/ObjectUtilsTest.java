package com.liren.commons;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

public class ObjectUtilsTest {

	@Test
	public void testObjectUtils() {
		fail("Not yet implemented");
	}

	@Test
	public void testDefaultIfNull() {
		String s = "fsdfsdf";
		String defaultIfNull = ObjectUtils.defaultIfNull(s, "default");
		System.out.println(defaultIfNull);
	}

	@Test
	public void testFirstNonNull() {
//		String s1 = null;
//		String s2 = "s2";
//		String s3 = "s3";
//		String[] ss = new String[]{null,"2",null};
//		String firstNonNull = ObjectUtils.firstNonNull(ss);
//		System.out.println(firstNonNull);
		
		App a1 = new App();
		App a2 = null;
		App firstNonNull = ObjectUtils.firstNonNull(a1,a2);
		System.out.println(firstNonNull);
	}

	@Test
	public void testAnyNotNull() {
		fail("Not yet implemented");
	}

	@Test
	public void testAllNotNull() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObjectObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotEqual() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCodeObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCodeMulti() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentityToStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentityToStringAppendableObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentityToStringStrBuilderObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentityToStringStringBufferObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentityToStringStringBuilderObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringObjectString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMin() {
		fail("Not yet implemented");
	}

	@Test
	public void testMax() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTT() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTTBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testMedianTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testMedianComparatorOfTTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testMode() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloneT() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloneIfPossible() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTByte() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONST_BYTE() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTChar() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTShort() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONST_SHORT() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTFloat() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testCONSTT() {
		fail("Not yet implemented");
	}

}
