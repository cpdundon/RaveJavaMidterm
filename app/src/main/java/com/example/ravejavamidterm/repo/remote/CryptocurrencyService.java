package com.example.ravejavamidterm.repo.remote;

import io.reactivex.Observable;
import com.example.ravejavamidterm.model.Crypto;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptocurrencyService {

    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);
}
