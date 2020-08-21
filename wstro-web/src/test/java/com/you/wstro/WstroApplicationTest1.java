package com.you.wstro;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WstroApplicationTest1
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage() {
        //创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc", "信息描述");
        messageProperties.getHeaders().put("type", "自定义消息类型");
        Message message = new Message("Hello RabbitMQ".getBytes(), messageProperties);
        //最好是id +时间戳 全局唯一
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        //发送消息
        rabbitTemplate.convertAndSend("test.direct.exchange", "test", message, new MessagePostProcessor() {
            //在执行消息转换后添加/修改标题或属性然后在进行发送
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().getHeaders().put("desc", "额外修改的信息描述");
                message.getMessageProperties().getHeaders().put("attr", "额外新添加的属性");
                return message;
            }
        });
    }
}
