package com.you.wstro.constant;

/**
 * rabbitmq交换机，队列等常量
 * @author Administrator
 *
 */

public class RabbitMqConstant
{
    /*
     * 用户注册交换机
     */
    public static final String USER_REGISTER_EXCHANGE = "user_register_direct_exchange";
    /*
     * 发送短信队列
     */
    public static final String SMS_QUEUE = "sms_queue";
    /*
     * 发送邮件队列
     */
    public static final String EMAIL_QUEUE = "mail_queue";
    /*
     * 站内信息队列
     */
    public static final String SYSTEM_QUEUE = "system_queue";
    
    /*
     * 短信队列路由键
     */
    public static final String SMS_ROUTING_KEY = "sms_routing_key";
    /*
     * 邮件队列路由键
     */
    public static final String EMAIL_ROUTING_KEY = "mail_routing_key";
    /*
     * 站内信息队列路由键
     */
    public static final String SYSTEM_ROUTING_KEY = "system_routing_key";

}
