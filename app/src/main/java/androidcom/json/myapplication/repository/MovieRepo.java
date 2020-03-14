package androidcom.json.myapplication.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import androidcom.json.myapplication.model.MoviesResponse;
import androidcom.json.myapplication.model.ResultsItem;
import androidcom.json.myapplication.service.EndPoint;
import androidcom.json.myapplication.service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {
    private EndPoint endPoint;
    private static final String TAG = MovieRepo.class.getCanonicalName();

    public MovieRepo() {
        endPoint = RetrofitClient.getRetrofit();
    }

    public LiveData<List<ResultsItem>> getTopRatedMovies(){
        final MutableLiveData<List<ResultsItem>> moviesResponseMutableLiveData = new MutableLiveData<>();
        endPoint.getTopRatedMovies("4b7cd8e98329c8c4d386e20623c3c679")
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if (response.isSuccessful()){
                            Log.d(TAG, "onResponse: success: " + response.body().toString());
                            moviesResponseMutableLiveData.postValue(response.body().getResults());

                        }else {
                            moviesResponseMutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: failed", t);
                        moviesResponseMutableLiveData.postValue(null);
                    }
                });

        return moviesResponseMutableLiveData;
    }
}
