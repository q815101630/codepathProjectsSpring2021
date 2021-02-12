package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.codepath.flixster.R;
import com.codepath.flixster.models.Movie;

import java.util.List;

/* extending to the VieHolder comes later after completion of
specific viewHolder,
otherwise it cannot be refer to the abstract class */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // inflating a layout from XML and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate: add XML to activity on runtime
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // bind data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get movie at the position
        Movie movie = movies.get(position);
        // bind movie data into ViewHolder;
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //viewHolder for each movie cell by connection with XML, (The model of XML)
    public class ViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // through the itemView inheritance, we can refer to the xml file
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imgURL;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imgURL = movie.getBackdropPath();
            }
            else{
                imgURL = movie.getPosterPath();
            }
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(imgURL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(ivPoster);
        }
    }
}
