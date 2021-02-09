package com.example.ravejavamidterm.repo;

import com.example.ravejavamidterm.model.Country;
import com.example.ravejavamidterm.repo.remote.CountryRetroInstance;
import com.example.ravejavamidterm.repo.remote.CountryService;

import java.util.List;

import io.reactivex.Observable;

public class CountryRepository {
    private static CountryRepository INSTANCE = null;
    private CountryRepository() {}

    public Observable<List<Country>> getCountries(String name) {
        CountryService countryService = CountryRetroInstance.getInstance();
        return countryService.getCountries(name);
    }

    public static CountryRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CountryRepository();
        }
        return INSTANCE;
    }
}
