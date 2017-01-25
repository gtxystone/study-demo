package com.liren.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
		System.out.println(str1);
	}
}
