package com.you.wstro.bean.Enum;

/**
 * 
    * @ClassName: HttpStatus  
    * @Description: 定义响应码枚举类型 
    * @author you  
    * @date 2020年3月12日  
    *
 */

public enum HttpStatus
{
    SUCCESS(1000, "成功"),
    
    FAIL(1001, "失败"),

    PARAM_ERROR(1002, "参数不合法"),
    
    TOKEN_ERROR(1003, "token异常"),
    
    SIGN_ERROR(1004, "签名异常"),

    DATABASE_ERROR(1005, "数据库异常"),
    
    TOKEN_REFRESH(1006, "token重新刷新生成"),
    
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    
    /**
     * rabbit自定义消息提示
     */
    EXCHANGE_HAVE_EXIST(2001,"该名称的交换器已经存在"),
    
    EXCHANGE_CREATE_FAIL(2002,"交换器创建失败"),
    
    EXCHANGE_TYPE_IS_NULL(2003,"交换器类型不能为空"),
    
    EXCHANGE_IS_NULL(2004,"该类型的交换器暂无数据"),
    
    EXCHANGE_NAME_IS_NULL(2005,"交换器名称不能为空"),
    
    EXCHANGE_NOT_EXIST(2006,"交换器信息不存在"),
    
    EXCHANGE_SELECT_ERR(2007,"查询交换器信息出错"),
    
    EXCHANGE_ID_NULL(2008,"交换器ID不能为空"),
    
    EXCHANGE_DELETE_ERR(2009,"根据交换器ID删除信息失败");
    
    private Integer code;
    private String message;

    HttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
