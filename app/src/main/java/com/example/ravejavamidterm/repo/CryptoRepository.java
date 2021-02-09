package com.example.ravejavamidterm.repo;

import io.reactivex.Observable;
import com.example.ravejavamidterm.model.Crypto;
import com.example.ravejavamidterm.repo.remote.CryptoRetroInstance;
import com.example.ravejavamidterm.repo.remote.CryptocurrencyService;


public class CryptoRepository {
    private static CryptoRepository INSTANCE = null;
    private CryptoRepository() {}

    public Observable<Crypto> getCoinData(String coin) {
        CryptocurrencyService cryptoService = CryptoRetroInstance.getInstance();
        return cryptoService.getCoinData(coin);
    }

    public static CryptoRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CryptoRepository();
        }
        return INSTANCE;
    }
}
