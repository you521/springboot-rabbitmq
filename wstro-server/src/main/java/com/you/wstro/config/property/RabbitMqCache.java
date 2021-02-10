package com.you.wstro.config.property;

import lombok.Data;

/**
 * rabbitmq 缓存配置
 * @author Administrator
 *
 */

@Data
public class RabbitMqCache
{
    private RabbitMqChannel channel;
    
    private RabbitMqConnection connection;
}
