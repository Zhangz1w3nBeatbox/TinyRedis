package com.zzw.Entity.redisCommand.imp;

import com.zzw.Entity.redisClient;
import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;

import java.util.Map;

import static com.zzw.RedisServerStart.redisServer;
import static com.zzw.redis_constant.REDIS_REPLY_FAIL;
import static com.zzw.redis_constant.REDIS_REPLY_OK;

public class selectCommand implements redisCommand {


    @Override
    public String execute(redisClient client) {

        String[] argv = client.getArgv();

        int dbIdx = 0;// 2

        try {
            dbIdx = Integer.parseInt(argv[1]);
        } catch (NumberFormatException e) {
            return "SELECT_TRANSFER_"+REDIS_REPLY_FAIL;
        }

        if(dbIdx<1||dbIdx>16) return "SELECT_"+REDIS_REPLY_FAIL;


        redisDB[] redisDBs = redisServer.getRedisDBs();
        redisDB redisDB = redisDBs[dbIdx - 1];

        client.setDb(redisDB);
        client.setDbIdx(dbIdx-1);

        return "SELECT_"+REDIS_REPLY_OK;
    }
}
