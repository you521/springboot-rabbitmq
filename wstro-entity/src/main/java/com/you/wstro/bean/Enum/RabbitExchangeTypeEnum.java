package com.you.wstro.bean.Enum;

/*
 * 枚举对象定义
 */
public enum RabbitExchangeTypeEnum
{
    /**
     * 直连模式
     */
    DIRECT,

    /**
     * 通配符模式
     */
    TOPIC,

    /**
     * 广播模式
     */
    FANOUT,

    HEADER
}
