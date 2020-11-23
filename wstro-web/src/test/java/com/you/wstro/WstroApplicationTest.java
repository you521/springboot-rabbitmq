package com.you.wstro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WstroApplicationTest
{
     private static final Logger logger = LoggerFactory.getLogger(WstroApplicationTest.class);
     
     @Test
     public void contextLoads() {
     }
     
     @Autowired
     private RabbitAdmin rabbitAdmin;
     
    
     
     @Test
     public void testAdmin() {
//         // 声明消息队列(direct类型)
//         /**
//          * 参数说明
//          * @param name  队列名称
//          * @param durable  是否持久化
//          * @param exclusive  是否排外的； 当设置exclusive = true时有两个作用:
//          *                   1、当连接关闭时connection.close()该队列会会自动删除
//          *                   2、会对当前队列加锁，其他通道channel是不能访问的.如果强制访问会报异常.一般等于true的话用于一个队列只能有一个消费者来消费的场景
//          * 
//          * @param autoDelete  是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除
//          *                    可以通过RabbitMQ Management，查看某个队列的消费者数量，当consumers = 0时队列就会自动删除
//          * @param arguments   队列的其他属性参数
//          */
//         rabbitAdmin.declareQueue(new Queue("test.direct.queue", true, false, false, null));
//         rabbitAdmin.declareQueue(new Queue("test.fanout.queue", true, false, false, null));
//         rabbitAdmin.declareQueue(new Queue("test.topic.queue", true, false, false, null));
//         
//         // 声明交换器
//         /**
//          * 参数说明
//          * @param name  交换器名称
//          * @param durable  是否持久化
//          * @param autoDelete  是否自动删除
//          * @param arguments   交换器的其他属性参数
//          */
//         
//         // direct类型
//         rabbitAdmin.declareExchange(new DirectExchange("test.direct.exchange", true, false, null));
//         // fanout类型
//         rabbitAdmin.declareExchange(new FanoutExchange("test.fanout.exchange", true, false, null));
//         // topic类型
//         rabbitAdmin.declareExchange(new TopicExchange("test.topic.exchange", true, false, null));
//         
//         // 绑定交换机与队列
//         // 创建绑定关系 Binding构造函数的参数： 队列名称、绑定类型、交换机名称、绑定键(路由键)
//         rabbitAdmin.declareBinding(new Binding("test.direct.queue", Binding.DestinationType.QUEUE, "test.direct.exchange", "test", null)); 
//         
//         // fanout类型绑定
         /**
          * 链式写法
          *
          */
//         rabbitAdmin.declareBinding(
//                 BindingBuilder.
//                         bind(new Queue("test.fanout.queue", true, false, false, null))
//                         .to(new FanoutExchange("test.fanout.exchange", true, false, null)));
//         
//         // topic类型绑定
         /**
          * 链式写法
          *
          */
//         rabbitAdmin.declareBinding(
//                 BindingBuilder.
//                         bind(new Queue("test.topic.queue", true, false, false, null))
//                         .to(new TopicExchange("test.topic.exchange", true, false, null))
//                         .with("you.#"));
         
         // 清空队列数据
         //rabbitAdmin.purgeQueue("test.direct.queue", false);
         // 删除队列操作
         //rabbitAdmin.deleteQueue("test.direct.queue", true, true);
         // 删除Exchange操作
         //rabbitAdmin.deleteExchange("test.direct.exchange");
         // 解除绑定操作
         rabbitAdmin.removeBinding(BindingBuilder.
                 bind(new Queue("test.fanout.queue", true, false, false, null))
                 .to(new FanoutExchange("test.fanout.exchange", true, false, null)));
     }
}
