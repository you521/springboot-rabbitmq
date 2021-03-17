package com.you.wstro.config;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.you.wstro.config.bean.BaseRabbitMqBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RabbitMqConfig
{
    @Autowired
    private BaseRabbitMqBean baseRabbitMqBean;
    
    /**
     * spring AMQP默认使用CachingConnectionFactory创建一个应用程序共享的连接工厂，也是用途最广泛的ConnectionFactory构建方法
     *  这里改变rabbitmq默认配置
     * 与AMQP通信的工作单元实际上是channel信道，tcp连接可以共享；
     * connectionFactory分为两种模式，一种是缓存channel，一种是缓存connection（同时也缓存该connection的channel），默认是缓存channel的模式
     * 注意：高可用集群场景下（镜像队列），通过负载均衡器连接至集群中不同的实例时，可以通过setCacheMode设置为缓存connection的模式
     * 
     * 在缓存connection模式下，不支持自动声明队列、exchange、binding等，
     * rabbitmq-client默认只提供了5个线程处理connection，因此，当connection较多时，应该自定义线程池，并配置到CachingConnectionFactory中
     * 自定义的线程池将会被所有connection共享，建议线程池的最大线程数设置的与预期connection数相等，因为可能存在对于大部分connection都有多个channel的情况
     */
    
    @Bean
    public ConnectionFactory connectionFactory(){
        log.info("====================生产者连接工厂设置开始，连接地址为：{}====================",baseRabbitMqBean.getHost());
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        // rabbitmq服务器地址
        cachingConnectionFactory.setHost(baseRabbitMqBean.getHost());
        // 端口号
        cachingConnectionFactory.setPort(baseRabbitMqBean.getPort());
        // 用户名
        cachingConnectionFactory.setUsername(baseRabbitMqBean.getUserName());
        // 密码
        cachingConnectionFactory.setPassword(baseRabbitMqBean.getPassWord());
        // 配置虚拟主机
        cachingConnectionFactory.setVirtualHost(baseRabbitMqBean.getVirtualHost());
        // 设置连接超时时间
        cachingConnectionFactory.setConnectionTimeout(baseRabbitMqBean.getConnectionTimeout());
        //设置连接工厂缓存模式：CONNECTION；这里工厂缓存模式有两种：CONNECTION和CHANNEL
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        //设置缓存连接数，注意：仅在CONNECTION模式使用，设置Connection的缓存数量
        cachingConnectionFactory.setConnectionCacheSize(baseRabbitMqBean.getCache().getConnection().getSize());
        //设置缓存信道数
        cachingConnectionFactory.setChannelCacheSize(baseRabbitMqBean.getCache().getChannel().getSize());
        // 打开rabbitmq的消息确认机制(Confirm)
        cachingConnectionFactory.setPublisherConfirms(baseRabbitMqBean.isPublisherConfirms());
        // 打开rabbitmq的消息确认的返回机制(Return)
        cachingConnectionFactory.setPublisherReturns(baseRabbitMqBean.isPublisherReturns());
        
        log.info("====================生产者连接工厂设置完成，连接地址为：{}====================",baseRabbitMqBean.getHost());
        return cachingConnectionFactory;
    }
    
    
  //===========================注入rabbitTemplate组件================================

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        //rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        /*
         * 为了能让生产者知道消息是否进入到消息队列中 ，并且避免使用事务大幅度降低消息的发送以及消费效率，可以用confirm和return机制来代替事务
         */
        
        /*
         * 实现ConfirmCallback回调函数，判断消息是否成功发送到Exchange, 注意：每一条发出的消息都会调用ConfirmCallback
         * 
         * 方法入参：
         * 
         * correlationData：RabbitTemplate的send系列方法中有带这个参数的，如果传了这个参数，会在回调时拿到
         * 
         * ack：消息进入exchange，为true，未能进入exchange，为false，由于Connection中断发出的消息进入exchange但没有收到confirm信息的情况，也会是false
         * 
         * cause: 消息发送失败时的失败原因信息
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("================消息成功发送到Exchange================");
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }
        });

        // 实现ReturnCallback回调函数，判断消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 设置消息重试机制
        //rabbitTemplate.setRetryTemplate();
        log.info("=======================RabbitTemplate连接模板设置完成=========================");
        return rabbitTemplate;
    }
    
    /*
     * 配置RabbitAdmin管理组件，RabbitAdmin 类可以很好的操作 rabbitMQ，在 Spring 中直接进行注入即可
     * 
     * 原理：RabbitAdmin 类 的底层实现就是从spring容器中获取 exchange、Bingding、routingkey以及queue的 @bean声明
     *     然后使用 rabbitTemplate的execute方法去执行对应的声明、修改、删除等一系列 rabbitMQ基础功能操作
     *  
     * 作用：RabbitAdmin主要用于在Java代码中对队列进行管理，用于创建、绑定、删除队列与交换机，发送消息等 
     */
    
    // ConnectionFactory形参名字和connectionFactory()方法名保持一致
    @Bean("rabbitAdmin")
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 注意，autoStartup 必须设置为 true，否则 Spring 容器不会加载 RabbitAdmin 类
        rabbitAdmin.setAutoStartup(true);
        // 该选项指示RabbitAdmin记录异常，并继续声明其他元素;
        // 当设置为true时，如果有异常抛出则不会捕获，应用程序继续执行;
        // 当设置为false时，有异常抛出会捕获异常;
        rabbitAdmin.setIgnoreDeclarationExceptions(false);
        return  rabbitAdmin;
     }
   
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
