package com.you.wstro.bean;

import java.io.Serializable;
import java.util.Date;

public class RabbitBindingInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    private Integer bindingId;

    /**
     * 消息队列id主键
     */
    private Integer queueId;
    
    /**
     * 交换器id主键
     */
    private Integer exchangeId;

    /**
     * 绑定键
     */
    private String bindingKey;

    /**
     * 绑定时间
     */
    private Date bindingTime;

    /**
     * 绑定者
     */
    private String bindingAtor;

    /**
     * 一对多关系的关联
     */
    private RabbitExchangeInfo rabbitExchangeInfo;

    public RabbitExchangeInfo getRabbitExchangeInfo()
    {
    
        return rabbitExchangeInfo;
    }

    public void setRabbitExchangeInfo(RabbitExchangeInfo rabbitExchangeInfo)
    {
    
        this.rabbitExchangeInfo = rabbitExchangeInfo;
    }

    public Integer getBindingId() {
        return bindingId;
    }

    public void setBindingId(Integer bindingId) {
        this.bindingId = bindingId;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getBindingKey() {
        return bindingKey;
    }

    public void setBindingKey(String bindingKey) {
        this.bindingKey = bindingKey == null ? null : bindingKey.trim();
    }

    public Date getBindingTime() {
        return bindingTime;
    }

    public void setBindingTime(Date bindingTime) {
        this.bindingTime = bindingTime;
    }

    public String getBindingAtor() {
        return bindingAtor;
    }

    public void setBindingAtor(String bindingAtor) {
        this.bindingAtor = bindingAtor == null ? null : bindingAtor.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RabbitBindingInfo other = (RabbitBindingInfo) that;
        return (this.getBindingId() == null ? other.getBindingId() == null : this.getBindingId().equals(other.getBindingId()))
            && (this.getQueueId() == null ? other.getQueueId() == null : this.getQueueId().equals(other.getQueueId()))
            && (this.getExchangeId() == null ? other.getExchangeId() == null : this.getExchangeId().equals(other.getExchangeId()))
            && (this.getBindingKey() == null ? other.getBindingKey() == null : this.getBindingKey().equals(other.getBindingKey()))
            && (this.getBindingTime() == null ? other.getBindingTime() == null : this.getBindingTime().equals(other.getBindingTime()))
            && (this.getBindingAtor() == null ? other.getBindingAtor() == null : this.getBindingAtor().equals(other.getBindingAtor()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBindingId() == null) ? 0 : getBindingId().hashCode());
        result = prime * result + ((getQueueId() == null) ? 0 : getQueueId().hashCode());
        result = prime * result + ((getExchangeId() == null) ? 0 : getExchangeId().hashCode());
        result = prime * result + ((getBindingKey() == null) ? 0 : getBindingKey().hashCode());
        result = prime * result + ((getBindingTime() == null) ? 0 : getBindingTime().hashCode());
        result = prime * result + ((getBindingAtor() == null) ? 0 : getBindingAtor().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bindingId=").append(bindingId);
        sb.append(", queueId=").append(queueId);
        sb.append(", exchangeId=").append(exchangeId);
        sb.append(", bindingKey=").append(bindingKey);
        sb.append(", bindingTime=").append(bindingTime);
        sb.append(", bindingAtor=").append(bindingAtor);
        sb.append(", rabbitExchangeInfo=").append(rabbitExchangeInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}