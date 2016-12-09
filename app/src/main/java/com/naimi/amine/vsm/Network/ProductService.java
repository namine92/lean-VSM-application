package com.naimi.amine.vsm.Network;


import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.Models.Pojo.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Endpoint for the Search Engine REST API.
 * Using Retrofit annotations.
 */
public interface ProductService {







    @GET("Products/")
    public Call<List<Product>> getProduct();



}
