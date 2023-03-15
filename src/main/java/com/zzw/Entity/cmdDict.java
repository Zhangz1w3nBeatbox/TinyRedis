package com.zzw.Entity;

import com.zzw.Entity.redisCommand.imp.getCommand;
import com.zzw.Entity.redisCommand.imp.setCommand;
import com.zzw.Entity.redisCommand.imp.setCommand;
import com.zzw.Entity.redisCommand.redisCommand;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class cmdDict {

    public static Map<String, redisCommand> cmdDict;

    public cmdDict() {
        this.cmdDict = new HashMap<>();
        cmdDict.put("set",new setCommand());
        cmdDict.put("get",new getCommand());
    }


}
