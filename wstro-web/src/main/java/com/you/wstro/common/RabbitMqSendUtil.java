package com.you.wstro.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.you.wstro.bean.User;
import com.you.wstro.constant.RabbitMqConstant;
import com.you.wstro.util.FastJsonUtil;

/**
 * rabbitmq生产者发送消息工具类
 * @author Administrator
 *
 */

@Component
public class RabbitMqSendUtil
{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private FastJsonUtil fastJsonUtil;
    
    public String sendDirectMessage() {
        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message hello";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData); 
        map.put("createTime",createTime);
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息类型
        messageProperties.setContentType("application/json");
        // 设置消息编码
        messageProperties.setContentEncoding("utf-8");
        // 设置消息Id
        messageProperties.setMessageId(messageId);
        Message message = new Message(JSON.toJSONString(map).getBytes(),messageProperties);
        //rabbitTemplate.convertAndSend(RabbitMqConstant.USER_REGISTER_EXCHANGE, RabbitMqConstant.SMS_ROUTING_KEY, message,correlationData);
        rabbitTemplate.convertAndSend(RabbitMqConstant.USER_REGISTER_EXCHANGE, RabbitMqConstant.SMS_ROUTING_KEY, JSON.toJSONString(map).getBytes(),correlationData);
        return "ok";
    }
    
    public String sendDirectMessage(User user) {
        // id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
        //JSONObject jsonObject = JSON.parseObject(fastJsonUtil.getBeanToJson(user));
        rabbitTemplate.convertAndSend(RabbitMqConstant.USER_REGISTER_EXCHANGE, RabbitMqConstant.SMS_ROUTING_KEY, user, correlationData);
        return "ok";
    }

}
