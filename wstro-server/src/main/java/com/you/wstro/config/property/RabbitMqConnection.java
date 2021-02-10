package com.you.wstro.config.property;

import java.io.Serializable;

import lombok.Data;

/**
 * rabbitmq连接配置实体类
 * @author Administrator
 *
 */

@Data
public class RabbitMqConnection implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    // 缓存的连接数，只有是CONNECTION模式时生效
    private int size;
    
    // 连接工厂缓存模式：CHANNEL 和 CONNECTION
    private String mode;
}
