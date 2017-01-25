package org.liren.hessian.client;

import java.net.MalformedURLException;

import org.liren.hessian.bean.User;
import org.liren.hessian.service.UserService;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * Hello world!
 *
 */
public class HessianClient 
{
    public static void main( String[] args )
    {
    	String url = "http://localhost:8080/hessianapp/hessian/userService"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        UserService userService;
		try {
			userService = (UserService)factory.create(UserService.class, url);
			User user = userService.getUser(1000L);
			 System.out.println(user.getId()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
    }
}
