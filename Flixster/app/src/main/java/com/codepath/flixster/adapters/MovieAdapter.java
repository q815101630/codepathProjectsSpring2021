package com.codepath.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.codepath.flixster.DetailActivity;
import com.codepath.flixster.MainActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.models.Movie;

import org.parceler.Parcel;
import org.parceler.Parcels;

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
        RelativeLayout container;
        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // through the itemView inheritance, we can refer to the xml file
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            progressBar = itemView.findViewById(R.id.progressBar);
            container = itemView.findViewById(R.id.container);
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


            // Navigate to a new activity


            // Register click listener on the whole row
            container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("title", movie.getTitle());
                    i.putExtra("movie", Parcels.wrap(movie));
                    Pair<View, String> p1 = Pair.create((View)tvOverview, "overview");
                    Pair<View, String> p2 = Pair.create((View)tvTitle, "title");
                    Pair<View, String> p3 = Pair.create((View)ivPoster, "youtube");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity)context, p1, p2, p3);
                    context.startActivity(i, options.toBundle());
                }
            });


        }
    }
}
