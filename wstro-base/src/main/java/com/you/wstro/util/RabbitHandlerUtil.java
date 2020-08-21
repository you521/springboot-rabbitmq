package com.you.wstro.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import com.you.wstro.bean.rabbitmq.RabbitExchange;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Administrator
 *
 */

@Slf4j
public abstract class RabbitHandlerUtil
{
    private final static String DELAYED_TYPE = "x-delayed-type";

    private final static String DELAYED_MESSAGE = "x-delayed-message";
    
    // 引入rabbitAdmin组件
    private static RabbitAdmin rabbitAdmin;
    
    static {
        rabbitAdmin = (RabbitAdmin) SpringContextUtil.getBean("rabbitAdmin");
    }
    
    /**
     * 创建交换机
     *
     * @param rabbitExchange 创建交换机的对象
     * @return 交换机
     */
    public static Exchange createExchange(RabbitExchange rabbitExchange) {
        // 初始化交换机
        Exchange exchange = initExchange(rabbitExchange);
        // 声明交换机
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }
    
    /**
     * 初始化交换机
     */
    private static Exchange initExchange(RabbitExchange rabbitExchange) {
        // 判断是否是延迟交换机
        if (rabbitExchange.isDelayed()) {
            // 定义延迟交换机
            Map<String, Object> arguments = new HashMap<>();
            arguments.put(DELAYED_TYPE, rabbitExchange.getType().name().toLowerCase());
            return new CustomExchange(rabbitExchange.getName(),
                    DELAYED_MESSAGE,
                    rabbitExchange.isDurable(),
                    rabbitExchange.isAutoDelete(),
                    arguments);
        }
        // 获取交换机类型
        switch (rabbitExchange.getType()) {
            case DIRECT:  // 直连模式
                return new DirectExchange(rabbitExchange.getName(),
                        // 交换机是否持久化
                        rabbitExchange.isDurable(),
                        // 当所有的绑定关系被删除时，自动删除队列
                        rabbitExchange.isAutoDelete(),
                        // 交换器的其他参数，可以为空
                        rabbitExchange.getArguments());
            case TOPIC:  //通配符模式
                return new TopicExchange(rabbitExchange.getName(),
                        rabbitExchange.isDurable(),
                        rabbitExchange.isAutoDelete(),
                        rabbitExchange.getArguments());
            case FANOUT:  //广播模式
                return new FanoutExchange(rabbitExchange.getName(),
                        rabbitExchange.isDurable(),
                        rabbitExchange.isAutoDelete(),
                        rabbitExchange.getArguments());
            case HEADER: //该类型不常见
                return new HeadersExchange(rabbitExchange.getName(),
                        rabbitExchange.isDurable(),
                        rabbitExchange.isAutoDelete(),
                        rabbitExchange.getArguments());
            default:
                return null;
        }
    }
}
