package com.atm.util;

/**
 * ö��ʵ�幤����
 */
public enum StatusEnumEntity {

    //����״̬ö��ֵ
    STATUS_LOCK("STATUS_LOCK","����"),
    STATUS_LOGIN("STATUS_LOGIN","������¼"),
    STATUS_LOGINFAILED("STATUS_LOGINFAILED","��¼ʧ��"),
    STATUS_LOGOUT("STATUS_LOGOUT","�˳�ϵͳ"),
    STATUS_CHANGEPWD("STATUS_CHANGEPWD","�޸�����"),
    STATUS_CHANGESUCCESS("STATUS_CHANGESUCCESS","�޸ĳɹ�"),
    STATUS_CHANGEFAILED("STATUS_CHANGEFAILED","�޸�ʧ��"),
    STATUS_QUERYSUCCESS("STATUS_QUERYSUCCESS","��ѯ�ɹ�"),
    STATUS_EXIT("STATUS_EXIT","�˳�"),
    STATUS_SAVE("STATUS_SAVE","��Ǯ"),
    STATUS_FETCH("STATUS_FETCH","ȡǮ"),
    STATUS_FETCHSUCCESS("STATUS_FETCHSUCCESS","ȡǮ�ɹ�"),
    STATUS_INTRANSFER("STATUS_INTRANSFER","����ת��"),
    STATUS_OUTTRANSFER("STATUS_OUTTRANSFER","����ת��"),
    STATUS_IN("STATUS_IN","ת��(ת��)"),
    STATUS_OUT("STATUS_OUT","ת��(ת��)"),
    STATUS_MODIFY("STATUS_MODIFY","�޸�����"),
    FAILED("FAILED","����ʧ��"),
    SUCCESS_LOG("SUCCESS_LOG","������¼"),
    ERROR_LOG("ERROR_LOG","�����¼"),
    EXCEPTION_LOG("EXCEPTION_LOG","�����쳣��¼");

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
        //��ȡ����ö�ٵĶ���
        StatusEnumEntity[] values = StatusEnumEntity.values();
        //�����������е����ж���
        for (StatusEnumEntity see :values){
            //�ж�����ö����value���ڴ����valueֵ,��ô����codeֵ
            if (see.value().equals(value)){
                return see.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        //��ȡ����ö�ٵĶ���
        StatusEnumEntity[] values = StatusEnumEntity.values();
        //�����������е����ж���
        for (StatusEnumEntity see :values){
            //�ж�����ö����code���ڴ����codeֵ,��ô����valueֵ
            if (see.code().equals(code)){
                return see.value();
            }
        }
        return null;
    }
}
