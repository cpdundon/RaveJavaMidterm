package com.example.ravejavamidterm.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ravejavamidterm.adapter.CountryAdapter;
import com.example.ravejavamidterm.adapter.PictureAdapter;
import com.example.ravejavamidterm.databinding.ActivityMainBinding;
import com.example.ravejavamidterm.databinding.CountryMainBinding;
import com.example.ravejavamidterm.model.Country;
import com.example.ravejavamidterm.model.Photo;
import com.example.ravejavamidterm.utils.Constants;
import com.example.ravejavamidterm.viewModel.CountryViewModel;
import com.example.ravejavamidterm.viewModel.MainViewModel;

import java.util.List;

public class CountryMainActivity extends AppCompatActivity {
    private CountryViewModel viewModel;
    private CountryMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CountryMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        setUpListeners();
        setUpObservers();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvFlagList.setLayoutManager(linearLayoutManager);
        viewModel.fetchCountries("State");
        //setGridLayoutMgr(gridFormat);
    }
    private void setUpObservers() {
        viewModel.getCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                binding.tvRtnTime.setText(Long.toString(System.currentTimeMillis()));
                CountryAdapter countryAdapter = new CountryAdapter(countries);
                binding.rvFlagList.setAdapter(countryAdapter);
            }
        });
    }

    private void setUpListeners() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });

//        binding.btnFormat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gridFormat = !gridFormat;
//                setGridLayoutMgr(gridFormat);
//            }
//        });

        binding.etQuery.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == Constants.ENTER_KEY_CODE) {
                    reload();
                    return true;
                }
                return false;
            }
        });
    }
    private void reload() {
        String entered = ((AppCompatEditText) binding.etQuery).getText().toString().trim();

        if (entered.equals(""))
            entered = "state";

        viewModel.fetchCountries(entered);
    }
}
