package com.naimi.amine.vsm.Network;


import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.Models.Pojo.PDC;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



/**
 * Endpoint for the Search Engine REST API.
 * Using Retrofit annotations.
 */
public interface ProcessService {







    @GET("Processes/")
    public Call<List<IndusProcess>> getProcess();



    @GET("Processes/{id}/pdc")
    public Call<List<PDC>> getProcessPDCs(@Path("id") String id);
}
