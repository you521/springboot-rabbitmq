package com.you.wstro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.you.wstro.config.property.BaseRabbitMqProperty;

/**
 * rabbitmq消费者配置类
 * @author Administrator
 *
 */

@Configuration
public class RabbitMqConsumerConfig
{
      private final static Logger logger = LoggerFactory.getLogger(RabbitMqConsumerConfig.class);
      
      @Autowired
      private BaseRabbitMqProperty baseRabbitMqProperty;
      
      /*
       * 最小消费者数量
       */
      @Value("${spring.rabbitmq.listener.simple.concurrency}")
      private int concurrency;
      /*
       * 最大消费者数量
       */
      @Value("${spring.rabbitmq.listener.simple.max-concurrency}")
      private int maxConcurrency;
      /*
       *  消费者每次从队列获取的消息数量
       */
      @Value("${spring.rabbitmq.listener.simple.prefetch}")
      private int prefetch;
      
      
      /**
       * 创建rabbitmq连接工厂
       * @return
       */
      @Bean
      public ConnectionFactory connectionFactory(){
          logger.info("====================消费者连接工厂设置开始，连接地址为：{}====================",baseRabbitMqProperty.getHost());
          CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
          // rabbitmq服务器地址
          cachingConnectionFactory.setHost(baseRabbitMqProperty.getHost());
          // 端口号
          cachingConnectionFactory.setPort(baseRabbitMqProperty.getPort());
          // 用户名
          cachingConnectionFactory.setUsername(baseRabbitMqProperty.getUserName());
          // 密码
          cachingConnectionFactory.setPassword(baseRabbitMqProperty.getPassWord());
          // 配置虚拟主机
          cachingConnectionFactory.setVirtualHost(baseRabbitMqProperty.getVirtualHost());
          // 设置连接超时时间
          cachingConnectionFactory.setConnectionTimeout(baseRabbitMqProperty.getConnectionTimeout());
          //设置连接工厂缓存模式：CONNECTION；这里工厂缓存模式有两种：CONNECTION和CHANNEL
          cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
          //设置缓存连接数，注意：仅在CONNECTION模式使用，设置Connection的缓存数量
          cachingConnectionFactory.setConnectionCacheSize(baseRabbitMqProperty.getCache().getConnection().getSize());
          //设置缓存信道数
          cachingConnectionFactory.setChannelCacheSize(baseRabbitMqProperty.getCache().getChannel().getSize());
          logger.info("====================消费者连接工厂设置完成，连接地址为：{}====================",baseRabbitMqProperty.getHost());
          return cachingConnectionFactory;
      }
      
      /**
       * 配置rabbitmq监听器工厂：SimpleRabbitListenerContainerFactory
       * @return
       */
      
      @Bean
      public RabbitListenerContainerFactory<?> listenerContainerFactory() {
          SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
          // 设置工厂连接
          factory.setConnectionFactory(connectionFactory());
          // 默认采用下面的这种转换器
          // container.setMessageConverter(new SimpleMessageConverter());
          // 消费端接受数据json反序列化
          factory.setMessageConverter(consumerJackson2MessageConverter());
          // 设置消费端手动确认机制；   none：不确认  auto：自动确认  manual：手动确认
          factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
          // 最小消费者数量,设置每个MessageListenerContainer将会创建的Consumer的最小数量，默认是1个
          factory.setConcurrentConsumers(concurrency);
          // 最大消费者数量
          factory.setMaxConcurrentConsumers(maxConcurrency);
          // 一个请求最大处理的消息数量
          factory.setPrefetchCount(prefetch);
          // 是否设置Channel的事务
          //factory.setChannelTransacted(false);
          // setTxSize：设置事务当中可以处理的消息数量
          //factory.setTxSize( 1 );
          // 设置当rabbitmq收到nack/reject确认信息时的处理方式，设为true，扔回queue头部，设为false，丢弃
          //factory.setDefaultRequeueRejected(true);
          return factory;
      }
      
      /**
       * 消费端数据序列化-json
       */
      @Bean
      public Jackson2JsonMessageConverter consumerJackson2MessageConverter() {
          return new Jackson2JsonMessageConverter();
      }
}
