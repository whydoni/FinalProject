package com.example.mybtpns.networking;

import com.example.mybtpns.model.Nasabah;
import com.example.mybtpns.model.NasabahResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NasabahApi {

//    @GET("nasabah")
//    Call<NasabahResponse> getNasabah(@Path("id") String id);

//    @POST("login")
//    Call<NasabahResponse> userLogin(@Body Nasabah nasabah);

    @FormUrlEncoded
    @POST("login")
    Call<NasabahResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
