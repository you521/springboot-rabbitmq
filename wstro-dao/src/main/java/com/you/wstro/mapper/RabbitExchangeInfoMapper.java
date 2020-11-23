package com.you.wstro.mapper;

import com.you.wstro.bean.RabbitExchangeInfo;
import java.util.List;
import java.util.Map;


public interface RabbitExchangeInfoMapper {
    
    /**
     * 根据主键id删除数据，单条数据删除
     * @param exchangeId
     * @return
     */
    int deleteByPrimaryKey(Integer exchangeId);

    int insert(RabbitExchangeInfo record);

    RabbitExchangeInfo selectByPrimaryKey(Integer exchangeId);
    
    /**
     * 获取数据库表中所有交换器信息
     * @return
     */
    List<RabbitExchangeInfo> selectAll();

    int updateByPrimaryKey(RabbitExchangeInfo record);

    /**
     * 根据交换器名称查询交换器信息
     * @param exchangeName
     * @return RabbitExchangeInfo
     */
    RabbitExchangeInfo getExchangeByName(String exchangeName);
    
    /**
     * 根据交换器类型查询交换器信息，查寻结果列表返回 List<Map<String, Object>>；注意，查询结果可能是一个可能是多个
     * @param exchangetype
     * @return
     */
    List<Map<String, Object>> getExchangeByType(String exchangetype);
    
    /**
     * 跟军交换器名称查询交换器类型，查询结果返回Map<String, Object>；注意查询结果为一个
     * @param exchangeName
     * @return Map<String, Object>
     */
    Map<String, Object> selectExchangeByName(String exchangeName);
    
    /**
     * 根据多条件筛选交换器信息
     * @param map
     * @return
     */
    List<Map<String, Object>> getExchangeByAny(Map<String, Object> map);
}