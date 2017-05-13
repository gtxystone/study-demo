package com.liren.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.liren.spring.event.ContentEvent;

@Component  
public class UnorderedListener2 implements ApplicationListener<ContentEvent> {  
    @Override  
    public void onApplicationEvent(final ContentEvent event) {  
        System.out.println("张三收到了新的内容：" + event.getSource());  
    }  
}  