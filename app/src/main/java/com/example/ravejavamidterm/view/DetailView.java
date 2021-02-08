package com.example.ravejavamidterm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ravejavamidterm.R;
import com.example.ravejavamidterm.adapter.PictureAdapter;
import com.example.ravejavamidterm.databinding.ActivityMainBinding;
import com.example.ravejavamidterm.databinding.DetailViewBinding;
import com.example.ravejavamidterm.model.Photo;
import com.example.ravejavamidterm.utils.Constants;
import com.example.ravejavamidterm.viewModel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.example.ravejavamidterm.utils.Constants.PEXELS_LAYOUT_SPAN;

public class DetailView extends AppCompatActivity {
    DetailViewBinding binding;
    String photographerUrl;
    String photoUrl;
    String photographer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DetailViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Get data from intent
        photographerUrl = getIntent().getStringExtra(Constants.INTENT_KEY_PHOTOGRAPHER_URL);
        photoUrl = getIntent().getStringExtra(Constants.INTENT_KEY_PHOTO_URL);
        photographer = getIntent().getStringExtra(Constants.INTENT_KEY_PHOTOGRAPHER);

        binding.tvPhotographer.setText(photographer);
        binding.tvWebsite.setText(photographerUrl);

        Glide.with(binding.getRoot().getRootView()).load(photoUrl).into(this.binding.ivImage);
        setListeners(photoUrl);
        
        Toast.makeText(DetailView.this, "DONE!", Toast.LENGTH_SHORT).show();
    }

    private void setListeners(String imgUrl) {
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, imgUrl);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
   }

}
