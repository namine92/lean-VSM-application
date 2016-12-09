package com.naimi.amine.vsm.Models;

import com.naimi.amine.vsm.Models.Pojo.IndusProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 25/11/16.
 */
public class ProcessContents {
    public static List<IndusProcess> ITEMS = new ArrayList<>();

    static {

         addItem(new IndusProcess("P1","Process 1"));
        addItem(new IndusProcess("P2","Process 2"));
        addItem(new IndusProcess("P3","Process 3"));

    }

    public static void addItem(IndusProcess item) {
        ITEMS.add(item);
    }
}
