package androidcom.json.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidcom.json.myapplication.databinding.MovieListItemBinding;
import androidcom.json.myapplication.model.ResultsItem;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<ResultsItem> movies;
    private int rowLayout;
    private Context context;


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding binding;

        public MovieViewHolder(MovieListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bintTo(ResultsItem item){
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500/" + item.getPosterPath())
                    .into(binding.coverImage);

            binding.movieRate.setRating((float) item.getVoteAverage());
            binding.title.setText(item.getTitle());
            binding.subtitle.setText(item.getOverview());
        }
    }

    public MoviesAdapter(List<ResultsItem> movies, Context context) {
        this.movies = movies;
        this.context = context;

    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        MovieListItemBinding binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        ResultsItem item = movies.get(position);
       holder.bintTo(item);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}