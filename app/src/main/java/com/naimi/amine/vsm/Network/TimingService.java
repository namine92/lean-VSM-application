package com.naimi.amine.vsm.Network;


import com.naimi.amine.vsm.Models.Pojo.Product;
import com.naimi.amine.vsm.Models.Pojo.Timing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Endpoint for the Search Engine REST API.
 * Using Retrofit annotations.
 */
public interface TimingService {





    @POST("Timings/")
    @FormUrlEncoded
    public Call<Timing> addTiming(@Field("timeStamp") String timeStamp, @Field("time")  int time,
                                      @Field("type")  String type, @Field("productId")  String productId,
                                      @Field("cycleId")  String cycleId);




}
