package com.you.wstro.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.you.wstro.bean.Enum.HttpStatus;
import com.you.wstro.bean.model.ResponseModel;
import com.you.wstro.bean.rabbitmq.RabbitExchange;
import com.you.wstro.service.RabbitMqService;
import com.you.wstro.util.ResultUtil;

@RestController
@RequestMapping(value = "/api/v1/exchange")
public class ExchangeController
{
     private static Logger logger = LoggerFactory.getLogger(ExchangeController.class);
     
     @Autowired
     private RabbitMqService rabbitMqService;
     
     /**
      * 创建rabbit交换器方法
      * @param rabbitExchange
      * @return
      */
     @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
     public Object addExchange(@RequestBody RabbitExchange rabbitExchange) {
         logger.info("声明rabbit交换器接口成功，请求参数为：{}",JSONObject.toJSONString(rabbitExchange));
         ResponseModel responseModel = rabbitMqService.createExchange(rabbitExchange);
         logger.info("创建rabbit交换器成功，返回参数为：{}",responseModel);
         return responseModel;
     }
     
     /**
      * 根据交换器类型查询交换器信息
      * @param exchange 交换器类型
      * @return
      */
     @RequestMapping(value = "/selectbytype", method = RequestMethod.GET, produces = "application/json")
     public Object getExchangeByType(@RequestParam(value = "exchangetype")String exchangetype) {
         logger.info("查询exchange交换器的接口连接成功，请求参数为：{}",exchangetype);
         ResponseModel responseModel = new ResponseModel();
         // 判断交换器类型是否为空
         if (StringUtils.isBlank(exchangetype))
         {
            HttpStatus httpStatus = HttpStatus.EXCHANGE_TYPE_IS_NULL;
            return ResultUtil.success(httpStatus.getCode(),httpStatus.getMessage());
         }
         responseModel = rabbitMqService.getExchangeByType(exchangetype);
         logger.info("exchange交换器查询成功，返回结果为：{}",responseModel);
         return responseModel;
     }
     
     /**
      * 查询所有交换器类型
      * @return
      */
     @RequestMapping(value = "/get" , method = RequestMethod.GET, produces = "application/json")
     public Object getAllExchange() {
         logger.info("《==============查询所有交换器信息的接口连接成功=============》");
         ResponseModel responseModel = new ResponseModel();
         responseModel = rabbitMqService.getAllExchange();
         logger.info("查询所有交换器信息成功，返回结果为：{}",responseModel);
         return responseModel;
     }
     
     /**
      * 根据交换器名称查询交换器信息
      * @param exchangeName
      * @return
      */
     @RequestMapping(value = "/selectbyname", method = RequestMethod.GET, produces = "application/json")
     public Object getExchangeByName(@RequestParam(value = "exchangename")String exchangeName) {
         logger.info("根据交换器名称查询信息的接口连接成功，请求参数为：{}",exchangeName);
         ResponseModel responseModel = new ResponseModel();
         // 判断交换器名称是否为空
         if (exchangeName == null || "".equals(exchangeName) || exchangeName.length() <= 0)
         {
            HttpStatus httpStatus = HttpStatus.EXCHANGE_NAME_IS_NULL;
            return ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
         }
         responseModel = rabbitMqService.getExchangeByName(exchangeName);
         logger.info("根据交换器名称查询信息成功，返回结果为：{}",responseModel);
         return responseModel;
     }
     
     /**
      * 根据交换器ID删除数据，这里是通过@RequestParam接受表单数据
      * @param exchangeId  交换器ID
      * @return
      */
     @RequestMapping(value = "/deletebyId", method = RequestMethod.POST)
     public Object deleteExchangeById(@RequestParam(value = "exchangeId") String exchangeId) {
         logger.info("根据交换器ID删除交换器信息接口连接成功，请求参数为：{}",exchangeId);
         ResponseModel responseModel = new ResponseModel();
         //判断交换器id是否为空
         if (StringUtils.isBlank(exchangeId))
         {
            HttpStatus httpStatus = HttpStatus.EXCHANGE_ID_NULL;
            return ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
         }
         responseModel = rabbitMqService.deleteById(exchangeId);
         return responseModel;
     }
     
     /**
      * 根据多条件筛选交换器信息，通过map类型接受form表单数据，多条件包括交换器名称、交换器类型、交换器是否绑定、开始时间、结束时间等等
      * @param map
      * @return
      */
     @RequestMapping(value = "/selectbyany", method = RequestMethod.POST)
     public Object getExchangeByAny(@RequestParam Map<String, Object> map) {
         logger.info("根据多条件筛选交换器信息接口连接成功，请求参数为：{}",map.toString());
         ResponseModel responseModel = new ResponseModel();
         if (map == null || map.isEmpty())
         {
            return ResultUtil.success("参数属性不能为空");
         }
         responseModel = rabbitMqService.getExchangeByAny(map);
         logger.info("根据多条件筛选交换器信息成功，返回结果为：{}",responseModel);
         return responseModel;
     }
}
