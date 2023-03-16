package com.zzw.Entity.redisServer;

import com.zzw.Entity.redisClient.redisClient;
import com.zzw.Entity.redisStruct.ListNode;


//redis 服务器状态
public class redisServer {

    ListNode<redisClient> clients;

    redisDB[] redisDBs;

    int dbnNum;

    public redisServer() {
        redisDBs = new redisDB[16];
        for(int i=0;i<16;++i){
            redisDBs[i] = new redisDB();
        }
        dbnNum = 16;
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
