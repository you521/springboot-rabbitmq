package com.you.wstro.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.you.wstro.constant.RabbitMqConstant;

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
    
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend(RabbitMqConstant.USER_REGISTER_EXCHANGE, RabbitMqConstant.SYSTEM_ROUTING_KEY, map);
        return "ok";
    }

}
