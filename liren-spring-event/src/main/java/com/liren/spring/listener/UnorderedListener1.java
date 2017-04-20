package com.liren.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.liren.spring.event.ContentEvent;

@Component  
public class UnorderedListener1 implements ApplicationListener<ApplicationEvent> {  
    @Override  
    public void onApplicationEvent(final ApplicationEvent event) {  
        if(event instanceof ContentEvent) {  
            System.out.println("李四收到了新的内容：" + event.getSource());  
        }  
    }  
} 