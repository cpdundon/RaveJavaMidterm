package com.example.ravejavamidterm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ravejavamidterm.model.Country;
import com.example.ravejavamidterm.model.Crypto;
import com.example.ravejavamidterm.repo.CountryRepository;
import com.example.ravejavamidterm.repo.CryptoRepository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends ViewModel {
    private Crypto crypto;
    private MutableLiveData<List<Country>> _countries = new MutableLiveData<>();
    public LiveData<List<Country>> getCountries() { return _countries; }
    private MutableLiveData<Boolean> _status = new MutableLiveData<>();
    public LiveData<Boolean> getStatus() { return _status; };

    public void fetchCountries(String name) {
        CountryRepository.getInstance()
                .getCountries(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Country>>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {
                            _status.setValue(false);
                       }

                       @Override
                       public void onNext(@NonNull List<Country> countries) {
                            _countries.setValue(countries);
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {

                       }

                       @Override
                       public void onComplete() {
                           _status.setValue(true);
                       }
                   }

                );

    }

}
