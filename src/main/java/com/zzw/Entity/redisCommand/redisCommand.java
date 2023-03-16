package com.zzw.Entity.redisCommand;

import com.zzw.Entity.redisClient;

import java.util.HashMap;
import java.util.Map;

public interface redisCommand {

    String name = null;

    String execute(redisClient client);
}
