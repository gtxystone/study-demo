package com.liren.guava;

import com.google.common.base.Strings;

public class Test {

	@org.junit.Test
	public void test() {
		System.out.println("isNullOrEmpty");

		String s1 = " ";

		String s2 = null;

		String s3 = "str";

		System.out.println(Strings.isNullOrEmpty(s1));

		System.out.println(Strings.isNullOrEmpty(s2));

		System.out.println(Strings.isNullOrEmpty(s3));
	}

}
