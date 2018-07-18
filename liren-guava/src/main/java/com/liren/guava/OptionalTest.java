package com.liren.guava;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.google.common.base.Optional;

public class OptionalTest {

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbsent() {
		Optional<String> absent = Optional.absent();
		boolean present = absent.isPresent();
		String string = absent.get();
		if(present){
			System.out.println("-----------");
		}
	}

	@Test
	public void testOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testFromNullable() {
		fail("Not yet implemented");
	}

	@Test
	public void testFromJavaUtil() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJavaUtilOptionalOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testOptional() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPresent() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrT() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrOptionalOfQextendsT() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrSupplierOfQextendsT() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrNull() {
		fail("Not yet implemented");
	}

	@Test
	public void testAsSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransform() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJavaUtil() {
		fail("Not yet implemented");
	}

	@Test
	public void testPresentInstances() {
		fail("Not yet implemented");
	}

}
