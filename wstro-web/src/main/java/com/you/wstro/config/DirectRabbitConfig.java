package com.you.wstro.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.you.wstro.constant.RabbitMqConstant;


/**
 * Direct交换机和队列声明配置类
 * @author Administrator
 *
 */
@Component
public class DirectRabbitConfig
{
    @Autowired
    private RabbitAdmin rabbitAdmin;
    
    /*
     * 配置声明直连式交换机DirectExchange
     * 
     * 参数说明
     * @param name  交换器名称
     * @param durable  是否持久化
     * @param autoDelete  是否自动删除
     * @param arguments   交换器的其他属性参数
     */
    
    public DirectExchange DirectExchange() {
        return new DirectExchange(RabbitMqConstant.USER_REGISTER_EXCHANGE,true,false,null);
    }
    
  /**
   * 声明消息队列(direct类型)
   * 
   * 参数说明
   * @param name  队列名称
   * @param durable  是否持久化
   * @param exclusive  是否排外的； 当设置exclusive = true时有两个作用:
   *                   1、当连接关闭时connection.close()该队列会会自动删除
   *                   2、会对当前队列加锁，其他通道channel是不能访问的.如果强制访问会报异常.一般等于true的话用于一个队列只能有一个消费者来消费的场景
   * 
   * @param autoDelete  是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除
   *                    可以通过RabbitMQ Management，查看某个队列的消费者数量，当consumers = 0时队列就会自动删除
   * @param arguments   队列的其他属性参数
   */
  
    // 短信队列声明
    public Queue sms_Queue() {
        return new Queue(RabbitMqConstant.SMS_QUEUE, true, false, false, null); 
    }

    // 邮件队列声明
    public Queue mail_Queue() {
        return new Queue(RabbitMqConstant.EMAIL_QUEUE, true, false, false, null);
    }
    
    // 站内短信队列声明
    public Queue system_Queue() {
        return new Queue(RabbitMqConstant.SYSTEM_QUEUE, true, false, false, null);
    }
    
    /**
     * 一个交换机可以绑定多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去
     * 注意：with中填写的是路由键，直连交换机中路由键和绑定键一样
     */
    
    // 将短信队列绑定到交换机上
    public Binding sms_Binding() {
        return BindingBuilder.bind(sms_Queue()).to(DirectExchange()).with(RabbitMqConstant.SMS_ROUTING_KEY);
    }
    
    // 将邮件队列绑定到交换机上
    public Binding mail_Binding() {
        return BindingBuilder.bind(mail_Queue()).to(DirectExchange()).with(RabbitMqConstant.EMAIL_ROUTING_KEY);
    }
    
    // 将站内短信队列绑定到交换机上
    public Binding system_Binding() {
        return BindingBuilder.bind(system_Queue()).to(DirectExchange()).with(RabbitMqConstant.SYSTEM_ROUTING_KEY);
    }
    
    // 通过rabbitAdmin管理组件创建交换机和对列以及绑定交换机和队列
    @Bean
    public void createExchangeQueue() {
        /*
         * 声明交换机
         */
        rabbitAdmin.declareExchange(DirectExchange());
        /*
         * 声明消息队列,这里声明了三个消息队列
         */
        rabbitAdmin.declareQueue(sms_Queue());
        rabbitAdmin.declareQueue(mail_Queue());
        rabbitAdmin.declareQueue(system_Queue());
        /*
         * 将消息队列绑定到交换机上,三个不同的队列绑定到同一个交换机上
         */
        rabbitAdmin.declareBinding(sms_Binding());
        rabbitAdmin.declareBinding(mail_Binding());
        rabbitAdmin.declareBinding(system_Binding());
    }

}
