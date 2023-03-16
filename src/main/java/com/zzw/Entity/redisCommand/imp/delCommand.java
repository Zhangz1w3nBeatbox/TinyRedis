package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;

import java.util.Map;

import static com.zzw.Constans.redis_constant.REDIS_REPLY_FAIL;
import static com.zzw.Constans.redis_constant.REDIS_REPLY_OK;

public class delCommand implements redisCommand {

    @Override
    public String execute(redisClient client) {

        redisDB db = client.getDb();
        Map<String, String> MAP = db.getDict();
        String[] argv = client.getArgv();
        String key = argv[1];
        String remove = MAP.remove(key);
        System.out.println(remove);

        if(remove==null) return  "DEL_"+REDIS_REPLY_FAIL;

        return "DEL_"+REDIS_REPLY_OK;
    }
}
