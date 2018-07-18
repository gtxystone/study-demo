package com.liren.spring.docker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @项目名称:lidong-dubbo
 * @类名:SampleController
 * @类的描述:
 * @作者:lidong
 * @创建时间:2017/2/19 上午9:34
 * @公司:chni
 * @QQ:1561281670
 * @邮箱:lidong1665@163.com
 */
@Controller
@SpringBootApplication
public class SampleController {

    @ResponseBody
    @RequestMapping(value = "/")
    String home(){
        return "Hello Docker World";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class,"--server.port=82");
    }
}