package com.you.wstro.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.you.wstro.bean.rabbitmq.RabbitExchange;
import com.you.wstro.service.RabbitMqService;

@RestController
@RequestMapping(value = "/api/v1/rabbit")
public class RabbitMqController
{
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    @Resource
    private RabbitMqService rabbitMqService;
    
    @RequestMapping(value = "/addexchange",method = RequestMethod.POST)
    public Object addExchange(@RequestBody RabbitExchange rabbitExchange) {
        logger.info("声明rabbit交换器接口成功，请求参数为：{}",JSONObject.toJSONString(rabbitExchange));
        Exchange exchange = rabbitMqService.createExchange(rabbitExchange);
        logger.info("创建rabbit交换器成功，返回参数为：{}",exchange);
        return exchange;
    }
}
