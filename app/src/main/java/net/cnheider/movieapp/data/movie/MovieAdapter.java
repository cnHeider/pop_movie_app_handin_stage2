package net.cnheider.movieapp.data.movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.cnheider.movieapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heider on 27/01/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
  private static final String TAG = MovieAdapter.class.getSimpleName();
  private final MovieAdapterOnClickHandler mClickHandler;
  private List<Movie> mMovieDataset;
  private Context mContext;

  public MovieAdapter(Context context, List<Movie> myDataset, MovieAdapterOnClickHandler clickHandler) {
    mContext = context;
    mMovieDataset = myDataset;
    mClickHandler = clickHandler;
  }

  public void setMovieData(List<Movie> movieData) {
    mMovieDataset = movieData;
    notifyDataSetChanged();
  }

  public interface MovieAdapterOnClickHandler {
    void onClick(Movie movie);
  }

  public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.movie_image)
    ImageView movie_poster_imageview;
    @BindView(R.id.movie_title)
    TextView movie_title_textview;
    @BindView(R.id.movie_rating)
    TextView movie_rating_textview;

    public MovieAdapterViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      movie_poster_imageview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int adapterPosition = getAdapterPosition();
      Movie movie = mMovieDataset.get(adapterPosition);
      mClickHandler.onClick(movie);
    }
  }

  @Override
  public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_movie, parent, false);
    MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(MovieAdapterViewHolder view_holder, int position) {
    final Movie movie = mMovieDataset.get(position);
    view_holder.movie_title_textview.setText(movie.title);
    view_holder.movie_rating_textview.setText(String.valueOf(movie.user_rating));
    Glide.with(mContext).load(movie.poster_image).into(view_holder.movie_poster_imageview);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public int getItemCount() {
    return mMovieDataset.size();
  }
}
