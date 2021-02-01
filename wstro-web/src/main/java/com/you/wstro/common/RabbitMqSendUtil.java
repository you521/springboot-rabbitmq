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
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        // 将map数据转成json数据
        //String strMap = fastJsonUtil.mapToString(map);
        rabbitTemplate.convertAndSend(RabbitMqConstant.USER_REGISTER_EXCHANGE, RabbitMqConstant.SMS_ROUTING_KEY, map);
        return "ok";
    }

}
