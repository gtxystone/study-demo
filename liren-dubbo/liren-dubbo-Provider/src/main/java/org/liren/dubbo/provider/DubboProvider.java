package org.liren.dubbo.provider;

import org.liren.dubbo.service.HelloService;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

public class DubboProvider {
	
	public static void main(String[] args) {
		
		HelloService helloService = new HelloServiceImpl();
		
		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubbo_service");
		
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("192.168.1.169:2181");
		registry.setProtocol("zookeeper");
		//registry.setUsername("aaa");
		//registry.setPassword("bbb");
		
		// 服务提供者协议配置
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(12345);
		protocol.setThreads(200);
		 
		// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
		 
		// 服务提供者暴露服务配置
		ServiceConfig<HelloService> service = new ServiceConfig<HelloService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service.setProtocol(protocol); // 多个协议可以用setProtocols()
		service.setInterface(HelloService.class);
		service.setRef(helloService);
		service.setVersion("1.0.0");
		 
		// 暴露及注册服务
		service.export();
		
	}

}
