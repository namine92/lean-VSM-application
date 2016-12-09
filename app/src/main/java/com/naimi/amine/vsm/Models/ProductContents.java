package com.naimi.amine.vsm.Models;

import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.Models.Pojo.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 25/11/16.
 */
public class ProductContents {
    public static List<Product> ITEMS = new ArrayList<>();

    static {

         addItem(new Product("Prod1","Product 1"));
        addItem(new Product("Prod2","Product 2"));
        addItem(new Product("Prod3","Product 3"));

    }

    public static void addItem(Product item) {
        ITEMS.add(item);
    }
}
