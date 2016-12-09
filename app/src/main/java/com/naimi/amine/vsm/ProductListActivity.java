package com.naimi.amine.vsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.naimi.amine.vsm.Adapters.ProcessListAdapter;
import com.naimi.amine.vsm.Adapters.ProductListAdapter;
import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.Models.Pojo.Product;
import com.naimi.amine.vsm.Models.ProductContents;
import com.naimi.amine.vsm.Network.ProjectApi;
import com.naimi.amine.vsm.Util.DataUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    List<Product> productList;
    ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productListView = (ListView) findViewById(R.id.product_list_view);





        Call<List<Product>> call = ProjectApi.getInstance().getProductServ().getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                if (productList != null) {

                    final ProductListAdapter productAdpt = new ProductListAdapter(ProductListActivity.this, productList);

                    productListView.setAdapter(productAdpt);


                    productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            DataUtil.setCurrentProductID(ProductListActivity.this, productAdpt.getProductIDbyPosition(position));
                            Intent i = new Intent(ProductListActivity.this, TimingsListActivity.class);
                            startActivity(i);
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


    }
}
