package com.example.a20230307_mfa_nycschools.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A retrofit factory class
 */
public class RetrofitHelper {
    /**
     *
     * @param base_url The Common portion of the url needed to make API calls
     * @return An instance of Retrofit.
     */
    public static Retrofit getRetrofit(String base_url){
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
