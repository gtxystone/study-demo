package com.liren.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class User implements Comparable<User>{  
    private Integer id;  
    private String name;  
    private String address;  
  
    public String toString(){  
        return MoreObjects.toStringHelper(this).add("id",id).add("name",name).add("address",address).toString();  
    }  
  
    public int hashCode(){  
        return Objects.hashCode(id,name,address);  
    }  
  
    @Override  
    public int compareTo(User o) {  
        return ComparisonChain.start().compare(this.id,o.id).compare(this.name,o.name).compare(this.address,o.address).result();  
    }  
}  