package com.example.ravejavamidterm.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ravejavamidterm.databinding.CountryCardBinding;
import com.example.ravejavamidterm.databinding.CryptoPriceDisplayBinding;
import com.example.ravejavamidterm.viewModel.CountryViewModel;
import com.example.ravejavamidterm.viewModel.CryptoViewModel;

public class CountryCardActivity extends AppCompatActivity {
    private CountryViewModel viewModel;
    private CountryCardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CountryCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CountryViewModel.class);

//        setUpObservers();
//        setUpListeners();
        viewModel.fetchCountries("Unit");
    }
}
