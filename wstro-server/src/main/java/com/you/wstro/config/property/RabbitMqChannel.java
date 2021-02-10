package com.you.wstro.config.property;

import java.io.Serializable;

import lombok.Data;

/**
 * 信道Channel配置实体类
 * @author Administrator
 *
 */

@Data
public class RabbitMqChannel implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    // 缓存中保持的channel数量
    private int size;
    
    // 当缓存数量被设置时，从缓存中获取一个channel的超时时间，单位毫秒；如果为0，则总是创建一个新channel
    private int checkoutTimeout;

}
