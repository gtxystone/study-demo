package com.liren.phonenumber;

import org.junit.Test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@Test
	public void test() {
		String swissNumberStr = "044 668 18 00";
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
		  PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "CH");
		  System.out.println(swissNumberProto);
		} catch (NumberParseException e) {
		  System.err.println("NumberParseException was thrown: " + e.toString());
		}
	}

}
