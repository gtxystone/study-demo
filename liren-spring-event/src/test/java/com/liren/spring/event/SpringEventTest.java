package com.liren.spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-config-event.xml"})  
public class SpringEventTest {  
  
    @Autowired  
    private ApplicationContext applicationContext;  
    @Test  
    public void testPublishEvent() {  
        applicationContext.publishEvent(new ContentEvent("今年是龙年的博客更新了"));  
    }  
  
}  