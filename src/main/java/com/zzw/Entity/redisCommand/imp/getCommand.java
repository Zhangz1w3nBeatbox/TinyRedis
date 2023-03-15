package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;

import java.util.HashMap;
import java.util.Map;

public class getCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {
        String[] argv = client.getArgv();
        String key = argv[1];
        String val = MAP.get(key);
        return val;
    }
}
