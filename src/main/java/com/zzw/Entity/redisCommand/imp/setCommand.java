package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.redis_constant;

import java.util.HashMap;
import java.util.Map;

public class setCommand implements redisCommand {

    @Override
    public String execute(redisClient client) {
        //
        System.out.println("处理set");
        String[] argv = client.getArgv();

        String key = null;
        String value = null;


        try {
            key = argv[1];
            value = argv[2];
        } catch (Exception e) {
            System.out.println("参数类型错误出现异常");
            return "PUT_"+redis_constant.REDIS_REPLY_FAIL;
        }

        String res = MAP.put(key, value);

        if(res==null){
            return "PUT_"+redis_constant.REDIS_REPLY_OK;
        }

         return "UPDATE_"+redis_constant.REDIS_REPLY_OK;
    }
}
