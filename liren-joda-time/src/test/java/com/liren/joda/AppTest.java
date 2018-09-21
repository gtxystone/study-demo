package com.liren.joda;

import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppTest {
	
	
	@Test
	public void AppTest() throws JsonProcessingException {
		DateTime dt = new DateTime();
		
		System.out.println(dt);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(dt);
		System.out.println(json);


		
	}

	
}
