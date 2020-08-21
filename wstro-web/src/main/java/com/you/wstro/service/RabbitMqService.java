package com.you.wstro.service;

import org.springframework.amqp.core.Exchange;

import com.you.wstro.bean.rabbitmq.RabbitExchange;

public interface RabbitMqService
{

    /*
     * 创建交换器
     */
    Exchange createExchange(RabbitExchange rabbitExchange);

}
