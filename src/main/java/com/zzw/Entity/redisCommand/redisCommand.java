package com.zzw.Entity.redisCommand;

import com.zzw.Entity.redisClient;

import java.util.HashMap;
import java.util.Map;

public interface redisCommand {

    Map<String,String> MAP = new HashMap<>();

    String name = null;

    String execute(redisClient client);
}
