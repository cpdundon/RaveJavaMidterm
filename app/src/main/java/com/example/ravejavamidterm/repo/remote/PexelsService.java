package com.example.ravejavamidterm.repo.remote;
import com.example.ravejavamidterm.model.PexelsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PexelsService {

    @GET("/search")
    Call<PexelsModel> getPhotos(@QueryMap Map<String, String> options);
}
