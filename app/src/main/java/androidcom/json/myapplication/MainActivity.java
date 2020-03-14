package androidcom.json.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import androidcom.json.myapplication.adapter.MoviesAdapter;
import androidcom.json.myapplication.databinding.MainActivityBinding;
import androidcom.json.myapplication.model.ResultsItem;
import androidcom.json.myapplication.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private MovieViewModel viewModel;

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

             binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setHasFixedSize(true);


        ViewModelFactory factory = new ViewModelFactory(getApplication());
        viewModel = ViewModelProviders.of(this, factory)
                .get(MovieViewModel.class);

        viewModel.getMoviesResponseLiveData().observe(this, new Observer<List<ResultsItem>>() {
            @Override
            public void onChanged(List<ResultsItem> moviesResponse) {
                Log.d(TAG, "onChanged: movies response: " + moviesResponse.toString());
                MoviesAdapter adapter = new MoviesAdapter(moviesResponse, MainActivity.this);
                binding.recycler.setAdapter(adapter);
            }
        });
    }
}
