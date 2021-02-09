package com.example.ravejavamidterm.repo.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CryptoRetroInstance {
    private static final String BASE_URL = "https://api.cryptonator.com/api/full/";
    private static CryptocurrencyService INSTANCE = null;

    // Singleton -> Make constructor private
    private CryptoRetroInstance() {}

    public static CryptocurrencyService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(CryptocurrencyService.class);

        return INSTANCE;
    }
    private static OkHttpClient getClient () {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }
}
