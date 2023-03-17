package com.zzw.Entity.redisServer;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisStruct.redisList.imp.ListNode;
import com.zzw.Entity.redisStruct.redisList.redisList;
import com.zzw.Entity.redisStruct.redisObject;

import java.util.List;


//redis 服务器状态
public class redisServer {

    ListNode<redisClient> clients;

    redisDB[] redisDBs;

    int dbnNum;

    public redisServer() {

        dbnNum = 16;

        redisDBs = new redisDB[dbnNum];

        for(int i=0;i<redisDBs.length;++i){
            redisDBs[i] = new redisDB();
        }

        clients = new ListNode<>();
    }

    public ListNode<redisClient> getClients() {
        return clients;
    }

    public void setClients(ListNode<redisClient> clients) {
        this.clients = clients;
    }

    public redisDB[] getRedisDBs() {
        return redisDBs;
    }

    public void setRedisDBs(redisDB[] redisDBs) {
        this.redisDBs = redisDBs;
    }

    public int getDbnNum() {
        return dbnNum;
    }

    public void setDbnNum(int dbnNum) {
        this.dbnNum = dbnNum;
    }
}
