package com.liren.spring.event;

import org.springframework.context.ApplicationEvent;

public class ContentEvent extends ApplicationEvent {  
	
	private static final long serialVersionUID = 1L;

	public ContentEvent(final String content) {  
        super(content);  
    }  
}  