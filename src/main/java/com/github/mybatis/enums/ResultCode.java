package com.github.mybatis.enums;

/**
 * API统一返回状态码
 *
 * @author czs
 * @since 2018/7/12
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),

    PARAM_IS_INVALID(100, "参数无效"),
    PARAM_IS_BLANK(101, "参数为空"),
    REPEAT_REQUEST(102, "重复提交"),

    USER_NOT_LOGGED_IN(201, "用户未登录"),
    USER_LOGIN_ERROR(202, "账号不存在或密码错误"),
    INVALID_ORDER(210,"订单不存在"),
    ORDER_IS_CANCEL(211,"订单已取消"),
    ORDER_IS_NOT_PACK(212,"订单未切配"),

    NO_VIP_RECEIVE(350, "您不是VIP，无领取权限"),
    NO_PERMISSION_RECEIVE(351, "无领取权限,请升级VIP"),
    NO_RECEIVED_TIMES(352, "您的领取次数已达上限"),
    HAVE_RECEIVED(353, "您已经领取该权益"),

    NO_ENOUGH_EQUITY(352, "权限库存不足"),
    /* 系统相关  */
    URL_NOT_FOUND(404,"接口不存在"),

    /* 权限相关  */
    PERMISSION_NO_ACCESS(601, "无访问权限"),

    /* rpc调用失败 */
    RPC_ERROR(701,"rpc调用失败"),

    /* 业务异常，自定义异常 */
    Error(999,"业务异常");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values())
        {
            if (item.name().equals(name))
            {
                return item.message;
            }
        }
        return name;
    }

    public static ResultCode getByCode(Integer code){

        for (ResultCode item : ResultCode.values())
        {
            if (item.code().equals(code))
            {
                return item;
            }
        }
        return Error;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values())
        {
            if (item.name().equals(name))
            {
                return item.code;
            }
        }
        return null;
    }
}
