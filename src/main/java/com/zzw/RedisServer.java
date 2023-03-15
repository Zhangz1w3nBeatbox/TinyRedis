package com.zzw;

import com.zzw.Entity.redisClient;
import com.zzw.Entity.redisCommand.imp.setCommand;
import com.zzw.Entity.redisCommand.redisCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.zzw.Entity.cmdDict;

import static com.zzw.Entity.cmdDict.cmdDict;
import static com.zzw.redis_constant.CRLF;
import static com.zzw.redis_constant.REDIS_REPLY_OK;

public class RedisServer {

    OutputStream outputStream;

    public RedisServer(int port) throws Exception {

        if(port<1||port>65535) throw new Exception("端口异常!");

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("服务器已经启动 正在监听"+port+"端口");

        //使用线程池 处理并发的请求
        ExecutorService threadPool = Executors.newFixedThreadPool(50);

        while(true){

            Socket clientSocket = serverSocket.accept();

            System.out.println("接收到客户端请求:"+clientSocket.getPort());

            if(clientSocket!=null&&!clientSocket.isClosed()){

                //构建任务
                Runnable work =()->{
                    try {
                        handleClientRequest(clientSocket);
                    }catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                };
                //提价任务
                threadPool.submit(work);

            }

        }

    }

    private void handleClientRequest(Socket clientSocket) throws IOException {

        //获取输出流---向控制台写信息
        outputStream = clientSocket.getOutputStream();

        //获取输入流---读取控制台的输入信息
        InputStream inputStream = clientSocket.getInputStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);



        bufferedReader.lines().forEach(s-> {
            try {
                handleLine(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void handleLine(String s) throws IOException {

        //resp
        s = encodeRESP(s);

        if(s==null) return;;

        System.out.println(s);



        //把RESP文件解析
        redisClient redisClient = decodeRESP(s);

        //处理解析后的文件
        String msg = handle(redisClient);

        //回复
        if(msg==null){
            responseToClient(REDIS_REPLY_OK);
        }else {
            responseToClient(msg+"\r\n");
        }



    }

    // *2
    // $3 1
    // set 2
    // $2 3
    // k1
    private redisClient decodeRESP(String s) {
        if(s.charAt(0)=='*'){
            //List<List<String>> decodeList = new ArrayList<>();
            String[] strArr = s.split(CRLF);

            int argc = strArr[0].charAt(1) - '0';

            String[] argv = new String[argc];

            int idx=0;

            for(int i=1;i<strArr.length;i=i+2){
                //String unit = strArr[i];
                //int argLen = unit.charAt(1) - '0';
                //decodeList.add(new ArrayList<>(Arrays.asList(String.valueOf(argLen),strArr[i+1])));
                argv[idx++] = strArr[i+1];
            }

            for (int i = 0; i < argc; i++) {
                System.out.println(argv[i]);
            }

            //封装redisClient对象
            redisClient redisClient = new redisClient(s,argv,argc);

            //去哈希表中查找 命令对应的执行操作
            redisCommand redisCommand =  findCmdFunction(redisClient);

            redisClient.setCmd(redisCommand);

            return redisClient;
        }
        return null;
    }

    private String handle(redisClient redisClient) {
        redisCommand cmd = redisClient.getCmd();
        String res = cmd.execute(redisClient);
        return  res;
    }

    private redisCommand findCmdFunction(redisClient client) {
        String[] argv = client.getArgv();
        String cmd = argv[0];
        System.out.println(cmd);

        redisCommand redisCommand = cmdDict.get(cmd);

        return redisCommand;
    }

    private void responseToClient(String msg) throws IOException {
        outputStream.write(msg.getBytes());
        outputStream.flush();
    }

    //将输入的字符串转为RESP协议的字符串
    private String encodeRESP(String s) {
        if(s.equals("")|| s ==null|| s.equals(" ")) return null;

        s = s.trim();
        s = s.toLowerCase();

        String[] split = s.split(" ");

        if(split[0]==" ") return null;

        StringBuffer sb = new StringBuffer();

        int argc=0;

        for (String str:split) {
            str = str.trim();
            if(str.equals("")||str.equals(" ")) continue;
            argc++;
            sb.append("$").append(str.length()).append(CRLF).append(str).append(CRLF);
        }

        if(argc==0) return null;

        if(argc==1) return sb.toString();

        sb.insert(0,"*"+argc+CRLF);

        if(sb==null) return null;

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        InitRedisConfig();
        RedisServer redisServer = new RedisServer(6364);
    }

    private static void InitRedisConfig() {
        cmdDict dict= new cmdDict();
    }
}
