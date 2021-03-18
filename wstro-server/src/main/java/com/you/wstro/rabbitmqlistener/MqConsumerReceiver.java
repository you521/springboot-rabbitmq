package com.you.wstro.rabbitmqlistener;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP.Channel;
import com.you.wstro.bean.User;
import com.you.wstro.constant.RabbitMqConstant;
import com.you.wstro.util.FastJsonUtil;

/**
 * 消费者监听器类，也就是消费者消费数据的类
 * @author Administrator
 *
 */

@Component
public class MqConsumerReceiver
{
    private static final Logger logger = LoggerFactory.getLogger(MqConsumerReceiver.class);
    
    private FastJsonUtil fastJsonUtil;
    
    // 依赖注入
    public MqConsumerReceiver(FastJsonUtil fastJsonUtil)
    {
      this.fastJsonUtil=fastJsonUtil;
    }
    
  
    @RabbitListener(queues= {RabbitMqConstant.SMS_QUEUE}) // 监听队列的名称
    public void receiverMessage(byte[] message) throws Exception {
//        System.out.println("map------>"+map.toString());
//        System.out.println("headers------>"+headers);
//        //获取消息体
//        byte[] body = message.getBody();
//        System.out.println(body);   //[B@fe4bdc2
//        //获得消息属性
//        MessageProperties properties = message.getMessageProperties();
//        System.out.println(properties);
        logger.info("当前线程："+Thread.currentThread().getName()+"默认消费端接受到消息：message {}", new String(message,"utf-8"));
        logger.info("当前线程："+Thread.currentThread().getName()+"默认消费端接受到消息：message {}", new String(message,"utf-8").toString());
        try {
            Thread.sleep(5000); // 模拟业务处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onUserMessage(@Payload User user, @Headers Map<String,Object> headers) throws Exception {
       logger.info("当前线程："+Thread.currentThread().getName()+"消费端接受到特定对象消息：message {}",user);
       logger.info("当前线程："+Thread.currentThread().getName()+"消费端接受到特定对象消息：message {}",user.toString());
        try {
            Thread.sleep(5000); // 模拟业务处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @RabbitHandler
    public void onMapMessage(@Payload JSONObject jsonObject, @Headers Map<String,Object> headers) throws Exception {
        System.out.println("消费端接受到数据为--------》"+jsonObject.toJSONString());
        String string = JSON.toJSONString(jsonObject);
        User user = fastJsonUtil.jsonToBean(string, User.class);
        logger.info("当前线程："+Thread.currentThread().getName()+"消费端接受到map对象消息：message {}",user);
        logger.info("当前线程："+Thread.currentThread().getName()+"消费端接受到map对象消息：message {}",user.toString());
        try {
            Thread.sleep(5000); // 模拟业务处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
