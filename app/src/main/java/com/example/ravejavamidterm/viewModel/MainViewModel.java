package com.example.ravejavamidterm.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.ravejavamidterm.model.PexelsModel;
import com.example.ravejavamidterm.model.Photo;
import com.example.ravejavamidterm.repo.PhotoRepository;
import com.example.ravejavamidterm.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
//    private MutableLiveData<PexelsModel> _pictures = new MutableLiveData<>();
//    private LiveData<PexelsModel> getPictures() {
//        return _pictures;
//    }

    private List<Photo> photoList;
    private MutableLiveData<List<Photo>> _photos = new MutableLiveData<>();
    public LiveData<List<Photo>> getPhotos() { return _photos; }

    private void fetchPictures(String query, int pageNumber, int imageCap) {
        PhotoRepository.getInstance().getPhotos(query, pageNumber).enqueue(new Callback<PexelsModel>() {
            @Override
            public void onResponse(
                    @NotNull Call<PexelsModel> call,
                    @NotNull Response<PexelsModel> response) {
                PexelsModel pexData = response.body();

                if ((pexData.page <= ((pexData.total_results - 1) / Constants.PEXELS_MAX_PAGE_SIZE))
                        && (photoList.size() + pexData.photos.size() <= imageCap )){

                        photoList.addAll(pexData.photos);
                        fetchPictures(query, pexData.page + 1, imageCap);
                } else {
                    photoList.addAll(pexData.photos);
                    _photos.setValue(photoList);
                    Log.e("Size: ", Integer.toString(photoList.size()));
                }
            }

            @Override
            public void onFailure(Call<PexelsModel> call, Throwable t) {
            }
        });
    }

    public void fetchPictures(String query, int imageCap) {
        PhotoRepository.getInstance().getPhotos(query, 1).enqueue(new Callback<PexelsModel>() {
            @Override
            public void onResponse(
                    @NotNull Call<PexelsModel> call,
                    @NotNull Response<PexelsModel> response) {
                PexelsModel pexData = response.body();
//
                if (pexData.page <= ((pexData.total_results - 1) / Constants.PEXELS_MAX_PAGE_SIZE)) {
                    photoList = pexData.photos;
                    fetchPictures(query, pexData.page + 1, imageCap);
                } else {
                    _photos.setValue(pexData.photos);
                }
            }

            @Override
            public void onFailure(Call<PexelsModel> call, Throwable t) {
            }
        });
    }
}
