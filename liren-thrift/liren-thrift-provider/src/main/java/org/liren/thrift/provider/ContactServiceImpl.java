package org.liren.thrift.provider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.liren.thrift.service.Contact;
import org.liren.thrift.service.ContactService;

public class ContactServiceImpl implements ContactService.Iface{

	public List<Contact> getAll() throws TException {  
        List<Contact> contacts = new ArrayList<Contact>();  
        contacts.add(new Contact(1,"zhangpu",Calendar.getInstance().getTimeInMillis(),"1389612222","192.168.2.1",null));  
        return contacts;  
    }  
  
    public List<Contact> query(Map<String, String> conditions) throws TException {  
        List<Contact> contacts = new ArrayList<Contact>();  
        contacts.add(new Contact(1,"zhangpu",Calendar.getInstance().getTimeInMillis(),"1389612222","192.168.2.1",null));  
        return contacts;  
    }  
  
    public void remove(int id) throws TException {  
        System.out.println("invoke: remove,id = " + id);  
    }  
  
    public void save(Contact contact) throws TException {  
        System.out.println("invoke: save,contact = " + contact);  
          
    }  

}
