package com.naimi.amine.vsm.Models.Pojo;

import java.util.concurrent.TimeUnit;

/**
 * Created by macbookpro on 27/11/16.
 */
public class Timing {
   public String timeStamp;
    public long time;
    public String type;

    public String id;
    public   String productId;
    public String cycleId;

    public Timing(String timeStamp, long time, String type,
                  String id, String cycleId, String productId) {
        this.timeStamp = timeStamp;
        this.time = time;
        this.type = type;

        this.id = id;
        this.cycleId = cycleId;
        this.productId = productId;
    }

    public String getTime(){

        return String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }
}
