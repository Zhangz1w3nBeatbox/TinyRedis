package com.zzw.Entity.redisCommand.imp;

import com.zzw.Constans.redis_constant;
import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.zzw.Constans.redis_constant.REDIS_REPLY_FAIL;
import static com.zzw.Constans.redis_constant.REDIS_REPLY_OK;

public class expireCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {

        redisDB db = client.getDb();
        Map<String, Long> dbExpiresDict = db.getExpires();
        Map<String, String> dict = db.getDict();

        String[] argv = client.getArgv();
        String key;
        String expiresDealt;
        long expiresTimeNum;

        Calendar calendar = Calendar.getInstance();

        try {
            key = argv[1];

            if(!dict.containsKey(key)){
                return "EXPIRES_NO_SUCH_KEY_"+REDIS_REPLY_FAIL;
            }

            expiresDealt = argv[2];
            calendar.add(Calendar.MINUTE, Integer.parseInt(expiresDealt));
            Date expiresTime = calendar.getTime();
            expiresTimeNum= expiresTime.getTime();

        } catch (Exception e){
            return "EXPIRES_ARGS_"+REDIS_REPLY_FAIL;
        }


       // System.out.println("过期时间："+expiresTimeNum);

        Long val = dbExpiresDict.put(key, expiresTimeNum);

        System.out.println("过期时间插入成功:"+expiresTimeNum);
        System.out.println("当前时间:"+new Date().getTime());

        Long sub = expiresTimeNum-new Date().getTime();

        if(val==null){
            return "EXPIRES_"+sub+"_"+REDIS_REPLY_OK;
        }

        return "EXPIRES_UPDATE_"+sub+"_"+ REDIS_REPLY_OK;
    }
}
