package com.naimi.amine.vsm.Models;

import com.naimi.amine.vsm.Models.Pojo.PDC;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 25/11/16.
 */
public class PDCContents {

  public static List<PDC> ITEMS = new ArrayList<>();

    static {

        addItem(new PDC("25535","PDC1"));
        addItem(new PDC("25536","PDC2"));
        addItem(new PDC("25537","PDC3"));


    }

    private static void addItem(PDC item) {
        ITEMS.add(item);
    }
}
