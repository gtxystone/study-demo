package com.liren.mybatis;  
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.MapperHelper;  
  
public class Main {  
    public static void main(String[] args) {  
        /*SqlSession sqlSession=SqlSessionFactoryUtil.openSession();  
        UserDao userDao = sqlSession.getMapper(UserDao.class);  
        String id = "admin";  
        User curUser = userDao.findUserById(id);  
        if(curUser!=null){  
            System.out.println("HelloWorld:"+curUser.getId());  
        }  */
    	
    	
    	SqlSession sqlSession=SqlSessionFactoryUtil.openSession();  
    	
    	MapperHelper mapperHelper = new MapperHelper();
    	//特殊配置
    	Config config = new Config();
    	//具体支持的参数看后面的文档
//    	config.setXXX(XXX);
    	//设置配置
    	mapperHelper.setConfig(config);
    	// 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
    	mapperHelper.registerMapper(Mapper.class);
    	//配置完成后，执行下面的操作
    	mapperHelper.processConfiguration(sqlSession.getConfiguration());
    	
    	
    	
//    	SqlSession sqlSession=SqlSessionFactoryUtil.openSession();  
    	TestMapper testMapper = sqlSession.getMapper(TestMapper.class);  
        Test test = new Test();
        //test.setId(1L);
        test.setName("test1");
        test.setPassword("test1");
        testMapper.select(test);
//        Test selectOne = testMapper.selectOne(test);
//        System.out.println(selectOne.getName());  
        
       /* Example example = new Example(Test.class);
        example.createCriteria().andEqualTo("id", 1L);
        example.orderBy("id");
        
        List<Test> selectByExample = testMapper.selectByExample(example);
        int size = selectByExample.size();
        for(Test t: selectByExample){
        	System.out.println(t.getName());
        }*/
//      
//       
//        
//		List<Test> selectByExampleAndRowBounds = testMapper.selectByExampleAndRowBounds(example, new RowBounds(5, 7));
//		int size2 = selectByExampleAndRowBounds.size();
//		System.out.println(size2);
    	
    	/*SqlSession sqlSession = MybatisHelper.getSqlSession();
    	try {
    	    //获取Mapper
    	    UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
    	    UserInfo userInfo = new UserInfo();
    	    userInfo.setUsername("abel533");
    	    userInfo.setPassword("123456");
    	    userInfo.setUsertype("2");
    	    userInfo.setEmail("abel533@gmail.com");
    	    //新增一条数据
    	    Assert.assertEquals(1, mapper.insert(userInfo));
    	    //ID回写,不为空
    	    Assert.assertNotNull(userInfo.getId());
    	    //6是当前的ID
    	    Assert.assertEquals(6, (int)userInfo.getId());
    	    //通过主键删除新增的数据
    	    Assert.assertEquals(1,mapper.deleteByPrimaryKey(userInfo));
    	} finally {
    	    sqlSession.close();
    	}*/
        
       
    }  
}  