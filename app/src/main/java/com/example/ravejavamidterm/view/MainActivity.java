package com.example.ravejavamidterm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ravejavamidterm.R;
import com.example.ravejavamidterm.databinding.ActivityMainBinding;
import com.example.ravejavamidterm.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        //Toast.makeText(this, animal, Toast.LENGTH_SHORT).show();
        viewModel.fetchPictures("sailboat lighthouse", 850);
    }
}