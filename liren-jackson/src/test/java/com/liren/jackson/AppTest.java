package com.liren.jackson;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;


public class AppTest{
	
	@Test
	public void whenSerializingJodaTime_thenCorrect() throws JsonProcessingException {
		DateTime date = new DateTime(2014, 12, 20, 2, 30, DateTimeZone.forID("Europe/London"));
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String result = mapper.writeValueAsString(date);
		System.out.println(result);
		assertThat(result, containsString("2014-12-20T02:30:00.000Z"));
				
//	    DateTime date = new DateTime(2014, 12, 20, 2,30, DateTimeZone.forID("Europe/London"));
//	    ObjectMapper mapper = new ObjectMapper();
//	    mapper.registerModule(new JodaModule());
//	    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//	 
//	    String result = mapper.writeValueAsString(date);
//	    assertThat(result, containsString("2014-12-20T02:30:00.000Z"));
	}
}
