package com.you.wstro.mapper;

import com.you.wstro.bean.RabbitBindingInfo;
import java.util.List;

public interface RabbitBindingInfoMapper {
    int deleteByPrimaryKey(Integer bindingId);

    int insert(RabbitBindingInfo record);

    RabbitBindingInfo selectByPrimaryKey(Integer bindingId);

    List<RabbitBindingInfo> selectAll();

    int updateByPrimaryKey(RabbitBindingInfo record);
}