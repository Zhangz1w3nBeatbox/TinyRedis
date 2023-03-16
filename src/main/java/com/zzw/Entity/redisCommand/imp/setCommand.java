package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;
import com.zzw.Constans.redis_constant;

import java.util.Map;

public class setCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {
        //
        String[] argv = client.getArgv();

        String key = null;
        String value = null;

        try {
            key = argv[1];
            value = argv[2];
        } catch (Exception e) {
            return "PUT_"+redis_constant.REDIS_REPLY_FAIL;
        }

        redisDB db = client.getDb();
        Map<String, String> MAP = db.getDict();

        String res = MAP.put(key, value);

        if(res==null){
            return "PUT_"+redis_constant.REDIS_REPLY_OK;
        }

        return "UPDATE_"+redis_constant.REDIS_REPLY_OK;
    }
}
