package com.example.ravejavamidterm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.ravejavamidterm.adapter.PictureAdapter;
import com.example.ravejavamidterm.databinding.ActivityMainBinding;
import com.example.ravejavamidterm.model.Crypto;
import com.example.ravejavamidterm.model.Photo;
import com.example.ravejavamidterm.utils.Constants;
import com.example.ravejavamidterm.viewModel.MainViewModel;

import java.util.List;

import static com.example.ravejavamidterm.utils.Constants.PEXELS_LAYOUT_SPAN;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private boolean gridFormat = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setUpListeners();
        setUpObservers();

        viewModel.fetchPictures(Constants.PEXELS_DEFAULT_QUERY, Constants.PEXELS_DEFAULT_COUNT);
        setGridLayoutMgr(gridFormat);
    }

    private void setUpObservers() {
        viewModel.getPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                binding.tvRtnTime.setText(Long.toString(System.currentTimeMillis()));
                PictureAdapter pictureAdapter = new PictureAdapter(photos);
                binding.rvImageList.setAdapter(pictureAdapter);
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

        binding.btnFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridFormat = !gridFormat;
                setGridLayoutMgr(gridFormat);
            }
        });

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

        binding.rvImageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.rvImageList.setOnContextClickListener(new View.OnContextClickListener() {
            @Override
            public boolean onContextClick(View v) {
                int index = v.getVerticalScrollbarPosition();
                Toast.makeText(MainActivity.this, String.valueOf(index), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void setGridLayoutMgr(boolean grid) {
        if (grid) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, PEXELS_LAYOUT_SPAN);
            binding.rvImageList.setLayoutManager(gridLayoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.rvImageList.setLayoutManager(linearLayoutManager);
        }
    }
    private void reload() {
        String query;
        String entered = ((AppCompatEditText) binding.etQuery).getText().toString().trim();

        if (entered.equals(""))
            entered = "lighthouse";

        viewModel.fetchPictures(entered, Constants.PEXELS_DEFAULT_COUNT);
    }
}