package com.you.wstro.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.you.wstro.bean.rabbitmq.RabbitExchange;
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

    @RequestMapping(value = "/addexchange", method = RequestMethod.POST, consumes = "application/json",
        produces = "application/json", params = {"params=true"}, headers = {"Host=127.0.0.1"})
    public Object addExchange(@RequestBody RabbitExchange rabbitExchange) {
        // logger.info("声明rabbit交换器接口成功，请求参数为：{}",JSONObject.toJSONString(rabbitExchange));
        // Exchange exchange = rabbitMqService.createExchange(rabbitExchange);
        // logger.info("创建rabbit交换器成功，返回参数为：{}",exchange);
        // return exchange;
        return null;
    }
}
