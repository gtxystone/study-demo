package com.liren.mybatis;  
import org.apache.ibatis.session.SqlSession;  
  
public class Main {  
    public static void main(String[] args) {  
        SqlSession sqlSession=SqlSessionFactoryUtil.openSession();  
        UserDao userDao = sqlSession.getMapper(UserDao.class);  
        String id = "admin";  
        User curUser = userDao.findUserById(id);  
        if(curUser!=null){  
            System.out.println("HelloWorld:"+curUser.getId());  
        }  
    }  
}  