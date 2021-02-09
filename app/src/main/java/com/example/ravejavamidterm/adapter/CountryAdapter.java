package com.example.ravejavamidterm.adapter;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.ravejavamidterm.databinding.CountryCardBinding;
import com.example.ravejavamidterm.model.Country;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private final List<Country> countries;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CountryCardBinding binding = CountryCardBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CountryViewHolder(binding);
    }

    @Override
    public int getItemCount() { return countries.size(); }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.setCountry(country);
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        private final CountryCardBinding binding;

        public CountryViewHolder(@NonNull CountryCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setCountry(Country country) {
            ImageView imgFlag = binding.ivFlag;
            RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                    .init()
                    .with(this.binding.getRoot().getContext())
                    .getRequestBuilder();

            requestBuilder
                    .load(country.getFlag())
                    .into(imgFlag);

            //Glide.with(this.itemView).load(country.getFlag()).into(imgFlag);
            binding.tvCountryName.setText(country.getName());
            setListeners(country);
        }

        private void setListeners(Country country) {
            binding.ivFlag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailActivity(country);
                }
            });
        }

        private void goToDetailActivity(Country country) {

//            Intent intent = new Intent(binding.getRoot().getContext(), DetailView.class);
//
//            intent.putExtra(Constants.INTENT_KEY_PHOTO_URL, photo.src.medium);
//            intent.putExtra(Constants.INTENT_KEY_PHOTOGRAPHER, photo.photographer);
//            intent.putExtra(Constants.INTENT_KEY_PHOTOGRAPHER_URL, photo.photographer_url);
//
//            binding.getRoot().getContext().startActivity(intent);
        }

    }
}
