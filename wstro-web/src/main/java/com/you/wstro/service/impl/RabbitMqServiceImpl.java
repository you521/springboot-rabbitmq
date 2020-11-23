package com.you.wstro.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.you.wstro.bean.RabbitExchangeInfo;
import com.you.wstro.bean.Enum.HttpStatus;
import com.you.wstro.bean.model.ResponseModel;
import com.you.wstro.bean.rabbitmq.RabbitExchange;
import com.you.wstro.mapper.RabbitExchangeInfoMapper;
import com.you.wstro.service.RabbitMqService;
import com.you.wstro.util.RabbitHandlerUtil;
import com.you.wstro.util.ResultUtil;

@Service("rabbitMqService")
public class RabbitMqServiceImpl implements RabbitMqService
{
   
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqServiceImpl.class);
    
    @Resource
    private RabbitExchangeInfoMapper rabbitExchangeInfoMapper;
    
    /**
     * 创建交换器exchange
     */
    @Override
    public ResponseModel createExchange(RabbitExchange rabbitExchange)
    {
        RabbitExchangeInfo rabbitExchangeInfo;
        // 通过交换器名称从数据库查询是否以及创建过该交换器
        String exchangeName = rabbitExchange.getName();
        rabbitExchangeInfo = rabbitExchangeInfoMapper.getExchangeByName(exchangeName);
        if (rabbitExchangeInfo != null)
        {
            return ResultUtil.success(HttpStatus.EXCHANGE_HAVE_EXIST.getCode(), HttpStatus.EXCHANGE_HAVE_EXIST.getMessage());
        }
        // 判断其他参数是否为空
        if (rabbitExchange.getArguments() == null || rabbitExchange.getArguments().isEmpty())
        {
            rabbitExchange.setArguments(new HashMap<String, Object>());
        }
        // 声明一个交换器
        Exchange exchange = RabbitHandlerUtil.createExchange(rabbitExchange);
        if (exchange == null)
        {
            // 返回交换器创建失败
            return ResultUtil.success(HttpStatus.EXCHANGE_CREATE_FAIL.getCode(), HttpStatus.EXCHANGE_CREATE_FAIL.getMessage());
        } else {
            rabbitExchangeInfo = new RabbitExchangeInfo();
            // 交换器创建成功后，将信息保存到数据库
            rabbitExchangeInfo.setExchangeName(rabbitExchange.getName());
            // 将枚举类型的交换器类型转换成字符串类型
            rabbitExchangeInfo.setExchangeType(rabbitExchange.getType().toString());
            // 是否持久化
            rabbitExchangeInfo.setExchangeDurable(rabbitExchange.isDurable());
            // 是否为延迟队列
            rabbitExchangeInfo.setExchangeDelayed(rabbitExchange.isDelayed());
            // 是否自动删除
            rabbitExchangeInfo.setExchangeAutoDelete(rabbitExchange.isAutoDelete());
            // 其他参数
            rabbitExchangeInfo.setExchangeOtherParam(rabbitExchange.getArguments().toString());
            // 是否绑定
            rabbitExchangeInfo.setIsBinding(false);
            rabbitExchangeInfo.setCreateAtor("you");
            rabbitExchangeInfo.setCreateTime(new Date());
            // 调用mapper插入方法
            int resultNumber = rabbitExchangeInfoMapper.insert(rabbitExchangeInfo);
            if (resultNumber != 1)
            {
                return ResultUtil.success(HttpStatus.EXCHANGE_CREATE_FAIL.getCode(), HttpStatus.EXCHANGE_CREATE_FAIL.getMessage());
            }
            JSONObject jsonObject = new  JSONObject();
            jsonObject.put("exchangeId", rabbitExchangeInfo.getExchangeId());
            return ResultUtil.success(jsonObject);
        }
    }

    @Override
    public ResponseModel getExchangeByType(String exchangetype)
    {
        List<Map<String, Object>> listMap = rabbitExchangeInfoMapper.getExchangeByType(exchangetype);
        // 判断list集合是否为空
        if (listMap == null || listMap.isEmpty())
        {
            HttpStatus httpStatus = HttpStatus.EXCHANGE_IS_NULL;
            return ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
        }
        return ResultUtil.success(listMap);
    }

    @Override
    public ResponseModel getAllExchange()
    {
        List<RabbitExchangeInfo> rabbitExchangeInfos = rabbitExchangeInfoMapper.selectAll();
        // 判断集合是否为空说
        if (rabbitExchangeInfos == null || rabbitExchangeInfos.isEmpty())
        {
            return ResultUtil.success("数据库中暂时没有交换器信息");
        }
        return ResultUtil.success(rabbitExchangeInfos);
    }

    @Override
    public ResponseModel getExchangeByName(String exchangeName)
    {
        Map<String, Object> map = null;
        ResponseModel responseModel = new ResponseModel();
        try
        {
            map = rabbitExchangeInfoMapper.selectExchangeByName(exchangeName);
            if (map == null || map.isEmpty())
            {
                HttpStatus httpStatus = HttpStatus.EXCHANGE_NOT_EXIST;
                responseModel = ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
            } else {
                responseModel = ResultUtil.success(map); 
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            logger.debug("根据交换器名称查询数据出错，错误信息为：{}",e);
            return ResultUtil.success(HttpStatus.EXCHANGE_SELECT_ERR.getCode(), HttpStatus.EXCHANGE_SELECT_ERR.getMessage());
        }
        return responseModel;
    }

    @Override
    public ResponseModel deleteById(String exchangeId)
    {
        int deleteNumber = 0;
        ResponseModel responseModel = new  ResponseModel();
        HttpStatus httpStatus = HttpStatus.EXCHANGE_DELETE_ERR;
        try
        {
            // 删除服务器上的交换器之前先去数据库查询一下，该交换器是否存在
            RabbitExchangeInfo rabbitExchangeInfo = rabbitExchangeInfoMapper.selectByPrimaryKey(Integer.parseInt(exchangeId));
            // 判断是否为空
            if (rabbitExchangeInfo == null)
            {
                // 如果为空，说明该交换器为空
                return ResultUtil.success(HttpStatus.EXCHANGE_NOT_EXIST.getCode(), HttpStatus.EXCHANGE_NOT_EXIST.getMessage());
            }
            // 删除服务器上的交换器
            boolean flag = RabbitHandlerUtil.deleteExchange(rabbitExchangeInfo.getExchangeName());
            if (flag)
            {
                deleteNumber = rabbitExchangeInfoMapper.deleteByPrimaryKey(Integer.parseInt(exchangeId));
                if (deleteNumber == 0)
                {
                    responseModel = ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
                }
                responseModel = ResultUtil.success("数据删除成功");
            } else {
                // 返回删除失败提示信息
                responseModel = ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage()); 
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            logger.error("根据ID删除交换器信息失败，抛出异常信息：{}",e);
            return ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
        }
        return responseModel;
    }

    @Override
    public ResponseModel getExchangeByAny(Map<String, Object> map)
    {
        ResponseModel responseModel = new ResponseModel();
        HttpStatus httpStatus = HttpStatus.EXCHANGE_IS_NULL;
        try
        {
            List<Map<String, Object>> listMap = rabbitExchangeInfoMapper.getExchangeByAny(map);
            // 判断list集合是否为空
            if (listMap == null || listMap.isEmpty() || listMap.size() <= 0)
            {
                return ResultUtil.success(httpStatus.getCode(), httpStatus.getMessage());
            }
            responseModel = ResultUtil.success(listMap);
        } catch (Exception e)
        {
           e.printStackTrace();
           logger.error("根据多条件查询数据库时出错，抛出异常信息为：{}",e);
           return ResultUtil.success(HttpStatus.EXCHANGE_SELECT_ERR.getCode(),HttpStatus.EXCHANGE_SELECT_ERR.getMessage());
        }
        return responseModel;
    }

}
