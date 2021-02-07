package com.example.ravejavamidterm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ravejavamidterm.model.PexelsModel;
import com.example.ravejavamidterm.repo.PhotoRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {


    private MutableLiveData<PexelsModel> _pictures = new MutableLiveData<>();
    public LiveData<PexelsModel> getPictures() {
        return _pictures;
    }

    public void fetchPictures(String query, int page_size) {
        PhotoRepository.getInstance().getPhotos(query, page_size).enqueue(new Callback<PexelsModel>() {
            @Override
            public void onResponse(
                    @NotNull Call<PexelsModel> call,
                    @NotNull Response<PexelsModel> response) {
                _pictures.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PexelsModel> call, Throwable t) {
            }
        });
    }
}
