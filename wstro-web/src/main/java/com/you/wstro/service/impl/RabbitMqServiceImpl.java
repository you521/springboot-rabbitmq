package com.you.wstro.service.impl;

import org.springframework.amqp.core.Exchange;
import org.springframework.stereotype.Service;

import com.you.wstro.bean.rabbitmq.RabbitExchange;
import com.you.wstro.service.RabbitMqService;
import com.you.wstro.util.RabbitHandlerUtil;

@Service("rabbitMqService")
public class RabbitMqServiceImpl implements RabbitMqService
{

    @Override
    public Exchange createExchange(RabbitExchange rabbitExchange)
    {
        return RabbitHandlerUtil.createExchange(rabbitExchange);
    }

}
