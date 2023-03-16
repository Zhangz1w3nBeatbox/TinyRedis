package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;

import java.util.Map;

import static com.zzw.Constans.redis_constant.REDIS_REPLY_FAIL;

public class getCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {

        redisDB db = client.getDb();
        Map<String, String> MAP = db.getDict();

        String[] argv = client.getArgv();
        String key = argv[1];
        String val = MAP.get(key);

        if(val==null){
            return "GET_"+REDIS_REPLY_FAIL;
        }

        return val+"\r\n";
    }
}
