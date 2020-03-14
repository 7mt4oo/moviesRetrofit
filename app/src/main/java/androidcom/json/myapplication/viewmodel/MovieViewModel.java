package androidcom.json.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import androidcom.json.myapplication.model.ResultsItem;
import androidcom.json.myapplication.repository.MovieRepo;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepo movieRepo;
    private LiveData<List<ResultsItem>> moviesResponseLiveData;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepo = new MovieRepo();
        moviesResponseLiveData = movieRepo.getTopRatedMovies();
    }

    public LiveData<List<ResultsItem>> getMoviesResponseLiveData() {
        return moviesResponseLiveData;
    }
}
