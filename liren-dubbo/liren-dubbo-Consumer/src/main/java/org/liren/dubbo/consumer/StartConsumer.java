package org.liren.dubbo.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liren.dubbo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dubbo-consumer.xml")
public class StartConsumer {
 
    @Autowired
    private HelloService helloService;
 
    @Test
    public void test(){
        System.out.println("dubbo-consumer服务启动，调用！");
        helloService.sayHello();
        System.out.println("调用结束---------");
 
    }
}