package com.you.wstro.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 交换器实体类
 * @author Administrator
 *
 */
public class RabbitExchangeInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * 交换器主键id
     */
    private Integer exchangeId;

    /**
     * 交换器名称
     */
    private String exchangeName;

    /**
     * 交换器类型
     */
    private String exchangeType;

    /**
     * 是否为延迟交换器
     */
    private Boolean exchangeDelayed;

    /**
     * 交换器是否持久化
     */
    private Boolean exchangeDurable;
    
    /**
     * 没有消息队列绑定时，是否自动删除交换器
     */
    private Boolean exchangeAutoDelete;

    /**
     * 交换器其他参数
     */
    private String exchangeOtherParam;

    /**
     * 是否绑定了消息队列
     */
    private Boolean isBinding;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String createAtor;

    /**
     * 更新时间
     */
    @JSONField(serialize = false, format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @JSONField(serialize = false)
    private String updateAtor;

    /**
     * 交换器表和绑定表是一对多关系，交换器是一的一方，绑定表是多的一方
     */
    @JSONField(serialize = false)
    private List<RabbitBindingInfo> rabbitBindingInfos;
    
    public List<RabbitBindingInfo> getRabbitBindingInfos()
    {
    
        return rabbitBindingInfos;
    }

    public void setRabbitBindingInfos(List<RabbitBindingInfo> rabbitBindingInfos)
    {
    
        this.rabbitBindingInfos = rabbitBindingInfos;
    }

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName == null ? null : exchangeName.trim();
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType == null ? null : exchangeType.trim();
    }

    public Boolean getExchangeDelayed() {
        return exchangeDelayed;
    }

    public void setExchangeDelayed(Boolean exchangeDelayed) {
        this.exchangeDelayed = exchangeDelayed;
    }

    public Boolean getExchangeDurable() {
        return exchangeDurable;
    }

    public void setExchangeDurable(Boolean exchangeDurable) {
        this.exchangeDurable = exchangeDurable;
    }

    public Boolean getExchangeAutoDelete() {
        return exchangeAutoDelete;
    }

    public void setExchangeAutoDelete(Boolean exchangeAutoDelete) {
        this.exchangeAutoDelete = exchangeAutoDelete;
    }

    public String getExchangeOtherParam() {
        return exchangeOtherParam;
    }

    public void setExchangeOtherParam(String exchangeOtherParam) {
        this.exchangeOtherParam = exchangeOtherParam == null ? null : exchangeOtherParam.trim();
    }

    public Boolean getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Boolean isBinding) {
        this.isBinding = isBinding;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateAtor() {
        return createAtor;
    }

    public void setCreateAtor(String createAtor) {
        this.createAtor = createAtor == null ? null : createAtor.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateAtor() {
        return updateAtor;
    }

    public void setUpdateAtor(String updateAtor) {
        this.updateAtor = updateAtor == null ? null : updateAtor.trim();
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
        RabbitExchangeInfo other = (RabbitExchangeInfo) that;
        return (this.getExchangeId() == null ? other.getExchangeId() == null : this.getExchangeId().equals(other.getExchangeId()))
            && (this.getExchangeName() == null ? other.getExchangeName() == null : this.getExchangeName().equals(other.getExchangeName()))
            && (this.getExchangeType() == null ? other.getExchangeType() == null : this.getExchangeType().equals(other.getExchangeType()))
            && (this.getExchangeDelayed() == null ? other.getExchangeDelayed() == null : this.getExchangeDelayed().equals(other.getExchangeDelayed()))
            && (this.getExchangeDurable() == null ? other.getExchangeDurable() == null : this.getExchangeDurable().equals(other.getExchangeDurable()))
            && (this.getExchangeAutoDelete() == null ? other.getExchangeAutoDelete() == null : this.getExchangeAutoDelete().equals(other.getExchangeAutoDelete()))
            && (this.getExchangeOtherParam() == null ? other.getExchangeOtherParam() == null : this.getExchangeOtherParam().equals(other.getExchangeOtherParam()))
            && (this.getIsBinding() == null ? other.getIsBinding() == null : this.getIsBinding().equals(other.getIsBinding()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateAtor() == null ? other.getCreateAtor() == null : this.getCreateAtor().equals(other.getCreateAtor()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateAtor() == null ? other.getUpdateAtor() == null : this.getUpdateAtor().equals(other.getUpdateAtor()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExchangeId() == null) ? 0 : getExchangeId().hashCode());
        result = prime * result + ((getExchangeName() == null) ? 0 : getExchangeName().hashCode());
        result = prime * result + ((getExchangeType() == null) ? 0 : getExchangeType().hashCode());
        result = prime * result + ((getExchangeDelayed() == null) ? 0 : getExchangeDelayed().hashCode());
        result = prime * result + ((getExchangeDurable() == null) ? 0 : getExchangeDurable().hashCode());
        result = prime * result + ((getExchangeAutoDelete() == null) ? 0 : getExchangeAutoDelete().hashCode());
        result = prime * result + ((getExchangeOtherParam() == null) ? 0 : getExchangeOtherParam().hashCode());
        result = prime * result + ((getIsBinding() == null) ? 0 : getIsBinding().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateAtor() == null) ? 0 : getCreateAtor().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateAtor() == null) ? 0 : getUpdateAtor().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", exchangeId=").append(exchangeId);
        sb.append(", exchangeName=").append(exchangeName);
        sb.append(", exchangeType=").append(exchangeType);
        sb.append(", exchangeDelayed=").append(exchangeDelayed);
        sb.append(", exchangeDurable=").append(exchangeDurable);
        sb.append(", exchangeAutoDelete=").append(exchangeAutoDelete);
        sb.append(", exchangeOtherParam=").append(exchangeOtherParam);
        sb.append(", isBinding=").append(isBinding);
        sb.append(", createTime=").append(createTime);
        sb.append(", createAtor=").append(createAtor);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateAtor=").append(updateAtor);
        sb.append(", rabbitBindingInfos=").append(rabbitBindingInfos);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}