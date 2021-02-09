package com.example.ravejavamidterm.repo.remote;

import io.reactivex.Observable;

import com.example.ravejavamidterm.model.Country;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryService {
    @GET("{country}")
    Observable<List<Country>> getCountries(@Path("country") String country);
}
