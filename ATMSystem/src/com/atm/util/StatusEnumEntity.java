package com.atm.util;

/**
 * 枚举实体工具类
 */
public enum StatusEnumEntity {

    //定义状态枚举值
    STATUS_LOCK("STATUS_LOCK","锁定"),
    STATUS_LOGIN("STATUS_LOGIN","正常登录"),
    STATUS_LOGINFAILED("STATUS_LOGINFAILED","登录失败"),
    STATUS_LOGOUT("STATUS_LOGOUT","退出系统"),
    STATUS_CHANGEPWD("STATUS_CHANGEPWD","修改密码"),
    STATUS_CHANGESUCCESS("STATUS_CHANGESUCCESS","修改成功"),
    STATUS_CHANGEFAILED("STATUS_CHANGEFAILED","修改失败"),
    STATUS_QUERYSUCCESS("STATUS_QUERYSUCCESS","查询成功"),
    STATUS_EXIT("STATUS_EXIT","退出"),
    STATUS_SAVE("STATUS_SAVE","存钱"),
    STATUS_FETCH("STATUS_FETCH","取钱"),
    STATUS_FETCHSUCCESS("STATUS_FETCHSUCCESS","取钱成功"),
    STATUS_INTRANSFER("STATUS_INTRANSFER","行内转账"),
    STATUS_OUTTRANSFER("STATUS_OUTTRANSFER","跨行转账"),
    STATUS_IN("STATUS_IN","转账(转入)"),
    STATUS_OUT("STATUS_OUT","转账(转出)"),
    STATUS_MODIFY("STATUS_MODIFY","修改密码"),
    FAILED("FAILED","操作失败"),
    SUCCESS_LOG("SUCCESS_LOG","正常记录"),
    ERROR_LOG("ERROR_LOG","报错记录"),
    EXCEPTION_LOG("EXCEPTION_LOG","程序异常记录");

    public final String code;
    public final String value;

    StatusEnumEntity(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code(){
        return this.code;
    }
    private String value(){
        return this.value;
    }

    public static String getCode(String value) {
        //获取所有枚举的对象
        StatusEnumEntity[] values = StatusEnumEntity.values();
        //遍历该数组中的所有对象
        for (StatusEnumEntity see :values){
            //判断如果该对象的value等于传入的value值,那么返回code值
            if (see.value().equals(value)){
                return see.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        //获取所有枚举的对象
        StatusEnumEntity[] values = StatusEnumEntity.values();
        //遍历该数组中的所有对象
        for (StatusEnumEntity see :values){
            //判断如果该对象的code等于传入的code值,那么返回value值
            if (see.code().equals(code)){
                return see.value();
            }
        }
        return null;
    }
}
