package com.example.ravejavamidterm.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.example.ravejavamidterm.databinding.ImageCardBinding;
import com.example.ravejavamidterm.model.Photo;
import com.example.ravejavamidterm.utils.Constants;
import com.example.ravejavamidterm.view.DetailView;

import java.util.List;

    // TODO: 2/3/2021 Create adapter to take a List<Person>/**
// * Step 1: Extend RecyclerView.Adapter<>
// * Step 2: Create custom ViewHolder class and extend RecyclerView.ViewHolder
// * Step 3: Create constructor matching super [use Alt + Enter] for our CustomViewHolder
// * Step 4: Pass custom ViewHolder into the RecyclerView.Adapter<PASS_IT_HERE>
// * Step 5: Implement the Adapter methods: onCreateViewHolder, onBindViewHolder, getItemCount [use Alt + Enter]
// * Step 6: Pass list to the Adapter using Constructor or any other way
// * Step 7: Pass the list size to getItemCount as the return value
// * Step 8: setup onCreateViewHolder by inflating the layout using LayoutInflater or ViewBinding
// * Ex: LayoutInflater.from(parent.getContext()).inflate(R.layout.my_layout_name, parent, false);
// * Ex: MyItemBinding.inflate(layoutInflater, parent, false);
// * Step 9: Finish onCreateViewHolder setup by passing the inflated view to the customViewHolder class and returning it
// * Step 10: Setup views in your custom view holder class
// * Step 11 Setup onBindViewHolder, retrieve the item from list using the provided position and
// * use it to load the ItemView
// */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    private final List<Photo> pictures;

    public PictureAdapter(List<Photo> pictures) {
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageCardBinding binding = ImageCardBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new PictureViewHolder(binding);
    }

    @Override
    public int getItemCount() { return pictures.size(); }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        Photo picture = pictures.get(position);
        holder.setPicture(picture);
    }


    static class PictureViewHolder extends RecyclerView.ViewHolder {
        private final ImageCardBinding binding;

        public PictureViewHolder(@NonNull ImageCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setPicture(Photo picture) {
            ImageView imgPicture = binding.ivImage;
            Glide.with(this.itemView).load(picture.src.medium).into(imgPicture);
            binding.tvPhotographer.setText(picture.photographer);
            setListeners(picture);
        }

        private void setListeners(Photo picture) {
            binding.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailActivity(picture);
                }
            });

        }

        private void goToDetailActivity(Photo photo) {

            Intent intent = new Intent(binding.getRoot().getContext(), DetailView.class);

            intent.putExtra(Constants.INTENT_KEY_PHOTO_URL, photo.src.medium);
            intent.putExtra(Constants.INTENT_KEY_PHOTOGRAPHER, photo.photographer);
            intent.putExtra(Constants.INTENT_KEY_PHOTOGRAPHER_URL, photo.photographer_url);

            binding.getRoot().getContext().startActivity(intent);
        }
  }


}

