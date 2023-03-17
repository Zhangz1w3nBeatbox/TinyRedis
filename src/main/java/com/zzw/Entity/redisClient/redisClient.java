package com.zzw.Entity.redisClient;

import com.zzw.Entity.redisCommand.redisCommand;
import com.zzw.Entity.redisServer.redisDB;
import com.zzw.Entity.redisStruct.redisList.imp.ListNode;

import java.util.Date;

import static com.zzw.Constans.redis_constant.REDIS_AUTH_TRUE;
import static com.zzw.Constans.redis_constant.REDIS_REPLY_CHUNK_BYTES;

// redis客户端对象
public class redisClient {

    //客户端正在使用的数据库
    redisDB db;

    int dbIdx;

    int fd; //套接字描述符

    String name;

    int flag; //权限和状态 redis_constant中的状态类

    String queryBuf;//输入缓冲区  *2\r\n$3\r\nget\r\n$3\r\nkey\r\n

    //参数数组
    String[] argv; // get key

    //参数个数
    int argc;// 2

    redisCommand cmd; // 存储着命令对应的执行实现方法

    String[] outBuf;//固定大小的输出缓冲区

    int outBufPos; //outBuf.len

    ListNode<String> reply; // 可变大小的输出缓冲区

    int authenticated; //记录是否通过权限验证

    Date creat_Time;//客户端创建时间

    Date last_interaction_Time;//最后一次和服务器交互的的时间

    Date outBuf_soft_limit_reached_time;//数据缓冲区第一次到达软性限制的时间

    public redisClient(){

    }


    public redisClient(String queryBuf, String[] argv, int argc) {
        this.dbIdx=0;
        this.queryBuf = queryBuf;
        this.argv = argv;
        this.argc = argc;
    }

    public int getFd() {
        return fd;
    }

    public void setFd(int fd) {
        this.fd = fd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getQueryBuf() {
        return queryBuf;
    }

    public void setQueryBuf(String queryBuf) {
        this.queryBuf = queryBuf;
    }

    public String[] getArgv() {
        return argv;
    }

    public void setArgv(String[] argv) {
        this.argv = argv;
    }

    public int getArgc() {
        return argc;
    }

    public void setArgc(int argc) {
        this.argc = argc;
    }

    public redisCommand getCmd() {
        return cmd;
    }

    public void setCmd(redisCommand cmd) {
        this.cmd = cmd;
    }

    public String[] getOutBuf() {
        return outBuf;
    }

    public void setOutBuf(String[] outBuf) {
        this.outBuf = outBuf;
    }

    public int getOutBufPos() {
        return outBufPos;
    }

    public void setOutBufPos(int outBufPos) {
        this.outBufPos = outBufPos;
    }

    public ListNode<String> getReply() {
        return reply;
    }

    public void setReply(ListNode<String> reply) {
        this.reply = reply;
    }

    public int getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(int authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreat_Time() {
        return creat_Time;
    }

    public void setCreat_Time(Date creat_Time) {
        this.creat_Time = creat_Time;
    }

    public Date getLast_interaction_Time() {
        return last_interaction_Time;
    }

    public void setLast_interaction_Time(Date last_interaction_Time) {
        this.last_interaction_Time = last_interaction_Time;
    }

    public Date getOutBuf_soft_limit_reached_time() {
        return outBuf_soft_limit_reached_time;
    }

    public void setOutBuf_soft_limit_reached_time(Date outBuf_soft_limit_reached_time) {
        this.outBuf_soft_limit_reached_time = outBuf_soft_limit_reached_time;
    }

    public redisDB getDb() {
        return db;
    }

    public void setDb(redisDB db) {
        this.db = db;
    }

    public int getDbIdx() {
        return dbIdx;
    }

    public void setDbIdx(int dbIdx) {
        this.dbIdx = dbIdx;
    }

    public redisClient(int fd, String name, int flag, String queryBuf, String[] argv, int argc, redisCommand cmd, String[] outBuf, int outBufPos, ListNode<String> reply, int authenticated, Date creat_Time, Date last_interaction_Time, Date outBuf_soft_limit_reached_time) {
        this.dbIdx=0;
        this.fd = fd;
        this.name = name;
        this.flag = flag;
        this.queryBuf = queryBuf;
        this.argv = argv;
        this.argc = argc;
        this.cmd = cmd;
        this.outBuf = new String[REDIS_REPLY_CHUNK_BYTES];
        this.outBufPos = outBufPos;
        this.reply = reply;
        this.authenticated = REDIS_AUTH_TRUE;
        this.creat_Time = new Date();
        this.last_interaction_Time = last_interaction_Time;
        this.outBuf_soft_limit_reached_time = outBuf_soft_limit_reached_time;
    }
}
