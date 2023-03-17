package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;

import java.util.Date;
import java.util.Map;

import static com.zzw.Constans.redis_constant.REDIS_REPLY_FAIL;

public class getCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {

        redisDB db = client.getDb();
        Map<String, String> dict = db.getDict();
        Map<String, Long> expiresDict = db.getExpires();

        String[] argv = client.getArgv();
        String key = argv[1];

        if(!dict.containsKey(key)){
            return "GET_"+REDIS_REPLY_FAIL;
        }


        if(!expiresDict.containsKey(key)){
            return "GET_EXPIRES_KEY_"+REDIS_REPLY_FAIL;
        }

        long expiresTime = expiresDict.get(key);
        long currTime = new Date().getTime();
        long sub =  expiresTime -currTime;

        if(sub<=0){
            dict.remove(key);
            expiresDict.remove(key);
            return "GET_EXPIRES_"+REDIS_REPLY_FAIL;
        }

        String val = dict.get(key);

        return val+"\r\n";
    }
}
