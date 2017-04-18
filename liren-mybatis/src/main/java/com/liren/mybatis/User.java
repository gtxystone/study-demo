package com.liren.mybatis;  
  
import java.io.Serializable;  
  

@SuppressWarnings("serial")  
public class User implements Serializable{  
      
    private String id;  
    private String password;  
    
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    public User(String id, String password) {  
        super();  
        this.id = id;  
        this.password = password;  
    }  
    public User() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
}  