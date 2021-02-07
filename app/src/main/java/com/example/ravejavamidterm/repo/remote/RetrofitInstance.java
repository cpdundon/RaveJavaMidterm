package com.example.ravejavamidterm.repo.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://api.pexels.com/v1/";
    private static PexelsService INSTANCE = null;

    // Singleton -> Make constructor private
    private RetrofitInstance() {

    }

    public static PexelsService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getClient()).build().create(PexelsService.class);

        return INSTANCE;
    }
    private static OkHttpClient getClient () {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }
}
