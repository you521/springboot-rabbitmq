package com.you.wstro.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息队列实体 类
 * @author Administrator
 *
 */
public class RabbitQueueInfo implements Serializable {
    /**
     * 消息队列id
     */
    private Integer queueId;

    /**
     * 消息队列名称
     */
    private String queueName;

    /**
     * 消息队列是否持久化
     */
    private Boolean queueDurable;

    /**
     * 消息队列是否自动删除
     */
    private Boolean queueAutoDelete;
    
    /**
     * 消息队列的其他参数
     */
    private String queueOtherParam;

    /**
     * 是否绑定了交换器
     */
    private Boolean isBinding;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createAtor;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新时间
     */
    private String updateAtor;

    private static final long serialVersionUID = 1L;

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName == null ? null : queueName.trim();
    }

    public Boolean getQueueDurable() {
        return queueDurable;
    }

    public void setQueueDurable(Boolean queueDurable) {
        this.queueDurable = queueDurable;
    }

    public Boolean getQueueAutoDelete() {
        return queueAutoDelete;
    }

    public void setQueueAutoDelete(Boolean queueAutoDelete) {
        this.queueAutoDelete = queueAutoDelete;
    }

    public String getQueueOtherParam() {
        return queueOtherParam;
    }

    public void setQueueOtherParam(String queueOtherParam) {
        this.queueOtherParam = queueOtherParam == null ? null : queueOtherParam.trim();
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
        RabbitQueueInfo other = (RabbitQueueInfo) that;
        return (this.getQueueId() == null ? other.getQueueId() == null : this.getQueueId().equals(other.getQueueId()))
            && (this.getQueueName() == null ? other.getQueueName() == null : this.getQueueName().equals(other.getQueueName()))
            && (this.getQueueDurable() == null ? other.getQueueDurable() == null : this.getQueueDurable().equals(other.getQueueDurable()))
            && (this.getQueueAutoDelete() == null ? other.getQueueAutoDelete() == null : this.getQueueAutoDelete().equals(other.getQueueAutoDelete()))
            && (this.getQueueOtherParam() == null ? other.getQueueOtherParam() == null : this.getQueueOtherParam().equals(other.getQueueOtherParam()))
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
        result = prime * result + ((getQueueId() == null) ? 0 : getQueueId().hashCode());
        result = prime * result + ((getQueueName() == null) ? 0 : getQueueName().hashCode());
        result = prime * result + ((getQueueDurable() == null) ? 0 : getQueueDurable().hashCode());
        result = prime * result + ((getQueueAutoDelete() == null) ? 0 : getQueueAutoDelete().hashCode());
        result = prime * result + ((getQueueOtherParam() == null) ? 0 : getQueueOtherParam().hashCode());
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
        sb.append(", queueId=").append(queueId);
        sb.append(", queueName=").append(queueName);
        sb.append(", queueDurable=").append(queueDurable);
        sb.append(", queueAutoDelete=").append(queueAutoDelete);
        sb.append(", queueOtherParam=").append(queueOtherParam);
        sb.append(", isBinding=").append(isBinding);
        sb.append(", createTime=").append(createTime);
        sb.append(", createAtor=").append(createAtor);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateAtor=").append(updateAtor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}