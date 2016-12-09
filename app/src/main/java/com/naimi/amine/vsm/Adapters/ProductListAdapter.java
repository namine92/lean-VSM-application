package com.naimi.amine.vsm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naimi.amine.vsm.Models.Pojo.Product;
import com.naimi.amine.vsm.Models.Pojo.Timing;
import com.naimi.amine.vsm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/05/16.
 */
public class ProductListAdapter extends BaseAdapter {

    Context mContext;

    List<Product> productList = new ArrayList();
    List<Product> productListBackup = new ArrayList();

    public ProductListAdapter(Context c, List<Product> productList) {

        mContext = c;
        productListBackup.addAll(productList);
        this.productList.addAll(productList);
    }


    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_product, container, false);
        }

        final Product product = (Product) getItem(position);

        ((TextView) convertView.findViewById(R.id.product_item_title)).setText(product.label);



        return convertView;
    }


    public void flitre(String query) {
        productList.clear();
        query = query.toLowerCase();


        notifyDataSetChanged();

    }

    public void update(List<Product> productList) {

        productListBackup.clear();
        productListBackup.addAll(productList);


        this.productList.clear();
        this.productList.addAll(productList);

        notifyDataSetChanged();

    }

    public String getProductIDbyPosition(int pos){
        return productList.get(pos).reference;

    }

}