package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;
import com.zzw.Constans.redis_constant;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.zzw.Constans.redis_constant.REDIS_EXPIRES_DEFAULT_TIME_VALUE;

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
        Map<String, Long> expiresDict = db.getExpires();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,REDIS_EXPIRES_DEFAULT_TIME_VALUE);
        Date expiresTime = calendar.getTime();
        long defaultExpiresTimeNum= expiresTime.getTime();

        String res = MAP.put(key, value);
        Long Long = expiresDict.put(key,defaultExpiresTimeNum);

        if(res==null){
            return "PUT_"+redis_constant.REDIS_REPLY_OK;
        }

        return "UPDATE_"+redis_constant.REDIS_REPLY_OK;
    }
}
