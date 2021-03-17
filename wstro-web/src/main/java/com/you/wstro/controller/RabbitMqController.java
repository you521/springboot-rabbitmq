package com.you.wstro.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.you.wstro.bean.User;
import com.you.wstro.bean.rabbitmq.RabbitExchange;
import com.you.wstro.common.RabbitMqSendUtil;
import com.you.wstro.service.RabbitMqService;

/**
 * rabbitmq控制器
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping(value = "/api/v1/rabbit")
public class RabbitMqController {
    // private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    @Resource
    private RabbitMqService rabbitMqService;
    
    @Resource
    private RabbitMqSendUtil rabbitMqSendUtil;

    @RequestMapping(value = "/addexchange", method = RequestMethod.POST, consumes = "application/json",
        produces = "application/json", params = {"params=true"}, headers = {"Host=127.0.0.1"})
    public Object addExchange(@RequestBody RabbitExchange rabbitExchange) {
        // logger.info("声明rabbit交换器接口成功，请求参数为：{}",JSONObject.toJSONString(rabbitExchange));
        // Exchange exchange = rabbitMqService.createExchange(rabbitExchange);
        // logger.info("创建rabbit交换器成功，返回参数为：{}",exchange);
        // return exchange;
        return null;
    }
    
    @RequestMapping(value="/sendmsg",method = RequestMethod.GET)
    public String sendMsg(@RequestParam(value = "str")String str) {
        System.out.println("str----------------->"+str);
        if (str.equals("1"))
        {
            rabbitMqSendUtil.sendDirectMessage();
        }else {
            User user = new User();
            user.setAge(25);
            user.setId(1);
            user.setName("哈哈哈");
            rabbitMqSendUtil.sendDirectMessage(user);

        }
        return "ok";
    }
}
