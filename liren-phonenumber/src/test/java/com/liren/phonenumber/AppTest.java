package com.liren.phonenumber;

import org.junit.Test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void test() {
		String swissNumberStr = "044 668 18 00";
		String chinaNumberStr = "+08615919332554";
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "CH");
			System.out.println(swissNumberProto);
			PhoneNumber chinaNumberProto = phoneUtil.parse(chinaNumberStr, "CN");
			System.out.println(chinaNumberProto);
			boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
			System.out.println("isValidNumber:" + isValid);

			// Produces "+41 44 668 18 00"
			System.out.println(phoneUtil.format(swissNumberProto, PhoneNumberFormat.INTERNATIONAL));
			// Produces "044 668 18 00"
			System.out.println(phoneUtil.format(swissNumberProto, PhoneNumberFormat.NATIONAL));
			// Produces "+41446681800"
			System.out.println(phoneUtil.format(swissNumberProto, PhoneNumberFormat.E164));

		} catch (NumberParseException e) {
			System.err.println("NumberParseException was thrown: " + e.toString());
		}
	}

	@Test
	public void testPhonenumber() {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber pn = new PhoneNumber();
		int countryCode = 86;
		long phone = 15919332554l;
		pn.setCountryCode(countryCode);
		pn.setNationalNumber(phone);
		boolean validNumber = phoneUtil.isValidNumber(pn);
		System.out.println("validNumber:" + validNumber);
	}

}
