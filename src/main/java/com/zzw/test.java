package com.zzw;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {

        //long currTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long time = new Date().getTime();
        System.out.println(time);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,2);
        Date time1 = calendar.getTime();
        long time2 = time1.getTime();



        System.out.println(time2);
    }
}
