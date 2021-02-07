package com.example.ravejavamidterm.repo;

import com.example.ravejavamidterm.model.PexelsModel;
import com.example.ravejavamidterm.repo.remote.PexelsService;
import com.example.ravejavamidterm.repo.remote.RetrofitInstance;
import com.example.ravejavamidterm.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class PhotoRepository {
    private static PhotoRepository INSTANCE = null;
    private PhotoRepository() {}

    public Call<PexelsModel> getPhotos(String query, int pageNumber) {
        PexelsService pexelsService = RetrofitInstance.getInstance();

        Map<String, String> toPass = new HashMap();
        toPass.put("query", query);
        toPass.put("per_page", Integer.toString(Constants.PEXELS_MAX_PAGE_SIZE));
        toPass.put("page", Integer.toString(pageNumber));
        return pexelsService.getPhotos(Constants.PEXELS_AUTH_KEY, toPass);
    }

    public static PhotoRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PhotoRepository();
        }
        return INSTANCE;
    }
}
