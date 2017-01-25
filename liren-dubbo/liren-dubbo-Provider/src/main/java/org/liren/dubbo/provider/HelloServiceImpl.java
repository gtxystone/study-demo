package org.liren.dubbo.provider;

import org.liren.dubbo.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("这里是Provider");
        System.out.println("HelloWorld Provider！");
    }
}