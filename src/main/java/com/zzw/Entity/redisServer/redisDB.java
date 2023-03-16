package com.zzw.Entity.redisServer;


import java.util.HashMap;
import java.util.Map;

// redis数据库对象
public class redisDB {

    int dbIndex;

    //正常 键值对字典
    Map<String,String> dict = new HashMap<>();


    //过期字典
    Map<String,String> expires = new HashMap<>();

    public Map<String, String> getExpires() {
        return expires;
    }

    public void setExpires(Map<String, String> expires) {
        this.expires = expires;
    }

    public Map<String, String> getDict() {
        return dict;
    }

    public void setDict(Map<String, String> dict) {
        this.dict = dict;
    }
}
