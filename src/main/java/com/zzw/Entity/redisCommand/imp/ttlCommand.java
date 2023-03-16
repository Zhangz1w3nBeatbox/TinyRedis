package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.zzw.Constans.redis_constant.REDIS_REPLY_FAIL;
import static com.zzw.Constans.redis_constant.REDIS_REPLY_OK;

public class ttlCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {

        redisDB db = client.getDb();
        Map<String, Long> dbExpiresDict = db.getExpires();
        Map<String, String> dict = db.getDict();

        String[] argv = client.getArgv();
        String key;

        try {
            key = argv[1];

            if(!dict.containsKey(key)|| !dbExpiresDict.containsKey(key)){
                return "TTL_NO_SUCH_KEY_"+REDIS_REPLY_FAIL;
            }


        } catch (Exception e){
            return "TTL_ARGS_"+REDIS_REPLY_FAIL;
        }


        Long expireTime = dbExpiresDict.get(key);
        Long currTime = new Date().getTime();

        Long sub = expireTime - currTime;

        return "TTL_"+sub+"_"+REDIS_REPLY_OK;
    }
}
