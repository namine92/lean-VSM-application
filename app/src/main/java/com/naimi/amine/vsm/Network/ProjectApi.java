package com.naimi.amine.vsm.Network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 02/06/16.
 */
public class ProjectApi {


    private static ProjectApi instance;

    ProcessService processServ;
    ProductService productServ;
    TimingService timingServ;
    /**
     * Returns the instance of this singleton.
     */
    public static ProjectApi getInstance() {
        if (instance == null) {
            instance = new ProjectApi();

        }
        return instance;
    }


    private ProjectApi() {
        Retrofit retrofit = buildRetrofit();
        processServ = retrofit.create(ProcessService.class);
        productServ = retrofit.create(ProductService.class);

        timingServ = retrofit.create(TimingService.class);

    }


    public Retrofit buildRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    public ProcessService getProcessServ() {
        return processServ;
    }

    public ProductService getProductServ() {
        return productServ;
    }
    public TimingService gettimingServ() {
        return timingServ;
    }



}
