package com.zzw;

// redis 常量类
public class redis_constant {

    //redis的状态 主从
    public static final int REDIS_MASTER = 1;
    public static final int REDIS_SLAVE  = 2;


    //redis client的buf的大小
    public static final int REDIS_REPLY_CHUNK_BYTES  = 16;


    //权限常量

    public static final int REDIS_AUTH_TRUE = 1;
    public static final int REDIS_AUTH_FALSE = 0;

    //RESP相关
    public static final String CRLF = "/r/n";

    //回复相关
    public static final String REDIS_REPLY_OK = "OK"+"\r\n";
}
