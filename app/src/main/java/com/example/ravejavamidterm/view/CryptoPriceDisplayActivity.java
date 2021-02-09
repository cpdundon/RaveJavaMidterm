package com.example.ravejavamidterm.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ravejavamidterm.databinding.CryptoPriceDisplayBinding;
import com.example.ravejavamidterm.model.Crypto;
import com.example.ravejavamidterm.viewModel.CryptoViewModel;

import java.util.List;

public class CryptoPriceDisplayActivity extends AppCompatActivity {
    private CryptoViewModel viewModel;
    private CryptoPriceDisplayBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CryptoPriceDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CryptoViewModel.class);

        setUpObservers();
        setUpListeners();
        viewModel.fetchPrice("btc");
    }

    private void setUpObservers() {
        viewModel.getCrypto().observe(this, new Observer<Crypto.Ticker>() {
            @Override
            public void onChanged(Crypto.Ticker ticker) {
                binding.tvPrice.setText(ticker.price);
            }
        });

        viewModel.getStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvStatus.setText(s);
            }
        });
    }

    private void setUpListeners() {
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.fetchPrice("BTC");
            }
        });


    }
}
