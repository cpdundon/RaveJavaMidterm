package com.example.ravejavamidterm.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ravejavamidterm.model.Crypto;
import com.example.ravejavamidterm.model.PexelsModel;
import com.example.ravejavamidterm.model.Photo;
import com.example.ravejavamidterm.repo.CryptoRepository;
import com.example.ravejavamidterm.repo.PhotoRepository;
import com.example.ravejavamidterm.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoViewModel extends ViewModel {
    private Crypto crypto;
    private MutableLiveData<Crypto.Ticker> _ticker = new MutableLiveData<>();
    public LiveData<Crypto.Ticker> getCrypto() { return _ticker; }
    private MutableLiveData<String> _status = new MutableLiveData<>();
    public LiveData<String> getStatus() { return _status; };

    public void fetchPrice(String coin) {
        CryptoRepository.getInstance()
                .getCoinData(coin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result.ticker)
                .subscribe(new Observer<Crypto.Ticker>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        _status.setValue("Loading...");
                    }

                    @Override
                    public void onNext(@NonNull Crypto.Ticker ticker) {
                        _ticker.setValue(ticker);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        _status.setValue("Delivered.");
                    }
                }

        );

    }

}
