package com.zzw.Entity.commanDict;

import com.zzw.Entity.redisCommand.imp.*;
import com.zzw.Entity.redisCommand.imp.setCommand;
import com.zzw.Entity.redisCommand.redisCommand;

import java.util.HashMap;
import java.util.Map;

public class cmdDict {

    public static Map<String, redisCommand> cmdDict;

    public cmdDict() {
        this.cmdDict = new HashMap<>();
        cmdDict.put("set",new setCommand());
        cmdDict.put("get",new getCommand());
        cmdDict.put("del",new delCommand());
        cmdDict.put("select",new selectCommand());
        cmdDict.put("expire",new expireCommand());
        cmdDict.put("ttl",new ttlCommand());
    }


}
