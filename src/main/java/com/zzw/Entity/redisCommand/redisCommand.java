package com.zzw.Entity.redisCommand;

import com.zzw.Entity.redisClient.redisClient;

public interface redisCommand {

    String name = null;
    String execute(redisClient client);
}
