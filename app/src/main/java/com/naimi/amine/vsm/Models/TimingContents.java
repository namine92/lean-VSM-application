package com.naimi.amine.vsm.Models;

import com.naimi.amine.vsm.Models.Pojo.Timing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 25/11/16.
 */
public class TimingContents {

  public static List<Timing> ITEMS = new ArrayList<>();

    static {

       // addItem(new Timing("11/11/2016",2,"type","nature","0","0","0"));
       // addItem(new Timing("11/11/2016",56,"type","nature","1","0","0"));
       // addItem(new Timing("11/11/2016",54,"type","nature","2","0","0"));
      //  addItem(new Timing("11/11/2016",53,"type","nature","3","0","0"));

    }

    public static void addItem(Timing item) {
        ITEMS.add(item);
    }
}
