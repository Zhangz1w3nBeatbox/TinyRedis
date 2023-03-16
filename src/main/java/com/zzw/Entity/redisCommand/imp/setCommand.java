package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;

import java.util.HashMap;
import java.util.Map;

public class setCommand implements redisCommand {

    @Override
    public String execute(redisClient client) {

        String[] argv = client.getArgv();
        String key = argv[1];
        String value = argv[2];

        String res = MAP.put(key, value);

        if(res==null){
            System.out.println("插入成功");
        }

        return res;
    }
}
