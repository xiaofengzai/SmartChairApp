package com.wen.util;

/**
 * Created by wenfeng on 2017/11/14.
 */
public class Constant {
    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER=0XD3F6;

    /**
     * 消息类型（请求0，应答1）
     */
    public static final int MESSAGE_REQUEST=0;
    public static final int MESSAGE_RESPONSE=1;

    /**
     * 消息结果（成功0，失败1,未登录）
     */
    public static final int MESSAGE_SUCCESS=0;
    public static final int MESSAGE_FAILED=1;
    public static final int MESSAGE_OFFLINE=2;

    public static final int LOGIN=0X0001;
    public static final int HEART=0X0002;
    public static final int PAY=0X0003;
    public static final int EXIT=0X0004;
    public static final int PAY_WEB=0X0005;

    public static final int FIX_MESSAGE_LENGTH=40;

    public static final int USERNAME_LENGTH=15;

    public static final String USERNAME="ABCDE1234567890";

    public static final String WEBUSER="ABCDE1234567891";

}
