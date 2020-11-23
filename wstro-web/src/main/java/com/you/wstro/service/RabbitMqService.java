package com.you.wstro.service;


import java.util.Map;

import com.you.wstro.bean.model.ResponseModel;
import com.you.wstro.bean.rabbitmq.RabbitExchange;

public interface RabbitMqService
{

    /*
     * 创建交换器
     */
    ResponseModel createExchange(RabbitExchange rabbitExchange);

    /*
     * 根据类型查询交互器类型
     */
    ResponseModel getExchangeByType(String exchangetype);

    /*
     * 查询所有交换器信息
     */
    ResponseModel getAllExchange();

    /*
     * 根据交换器名称查询交换器信息
     */
    ResponseModel getExchangeByName(String exchangeName);

    /*
     * 根据交换器ID删除信息
     */
    ResponseModel deleteById(String exchangeId);

    /*
     * 根据多条件筛选交换器信息
     */
    ResponseModel getExchangeByAny(Map<String, Object> map);

}
