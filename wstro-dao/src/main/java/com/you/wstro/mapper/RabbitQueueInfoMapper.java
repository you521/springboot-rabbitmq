package com.you.wstro.mapper;

import com.you.wstro.bean.RabbitQueueInfo;
import java.util.List;

public interface RabbitQueueInfoMapper {
    int deleteByPrimaryKey(Integer queueId);

    int insert(RabbitQueueInfo record);

    RabbitQueueInfo selectByPrimaryKey(Integer queueId);

    List<RabbitQueueInfo> selectAll();

    int updateByPrimaryKey(RabbitQueueInfo record);
}