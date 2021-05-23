package com.example.bottomnavigationactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.MaskFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationactivity.adapter.RecycleViewCardActorAdapter;
import com.example.bottomnavigationactivity.model.CreditsPeople;
import com.example.bottomnavigationactivity.model.Genre;
import com.example.bottomnavigationactivity.model.Movie;
import com.example.bottomnavigationactivity.model.Person;
import com.example.bottomnavigationactivity.model.ProductionCountries;
import com.example.bottomnavigationactivity.model.Video;
import com.example.bottomnavigationactivity.model.VideoTrailer;
import com.example.bottomnavigationactivity.network.MediaAPI;
import com.example.bottomnavigationactivity.network.NetworkService;
import com.example.bottomnavigationactivity.view_fragments.ActorDetailedFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieExtendedActivity extends AppCompatActivity {
    private int id;
    private final MediaAPI mediaAPI = NetworkService.getAPI();
    private Movie currentMovie;
    private TextView original_title, chr1, chr2, chr3, overview;
    private ImageView movieImage;
    private String video_key;
    private int actor_id;
    private RecyclerView actors_recycler;
    ActorDetailedFragment actorDetailedFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_extended_layout);


        findViewByIds();
        //Get item id
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("cinemaId"));

        initializePage();
    }

    private void initializePage() {

        mediaAPI.getCreditsMovieById(id).enqueue(new Callback<CreditsPeople>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<CreditsPeople> call, Response<CreditsPeople> response) {
                CreditsPeople creditsPeople = response.body();
                Person[] peopleCast = creditsPeople.getCast();
                Person[] peopleCrew = creditsPeople.getCrew();
                List<Person> peopleList = new ArrayList<Person>();
                for (int i = 0; i < peopleCast.length; i++) {
                    peopleList.add(peopleCast[i]);
                }
                RecycleViewCardActorAdapter recycleViewCardActorAdapter = new RecycleViewCardActorAdapter(getApplicationContext(),peopleList);
                actors_recycler.setAdapter(recycleViewCardActorAdapter);
                actors_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                if (peopleCast == null){
                    chr2.setText("Actors: no actors");
                }else {
                    chr2.setText("Actors: " + peopleCast[0].getName() + ", " + peopleCast[1].getName());
                }
            }

            @Override
            public void onFailure(@NotNull Call<CreditsPeople> call, Throwable t) {

            }

        });
        mediaAPI.getMovieById(id).enqueue(new Callback<Movie>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Movie> call, Response<Movie> response) {
                Log.i("response",response.message());
                if (!response.isSuccessful()){
                    return;
                }

                currentMovie = response.body();
                overview.getPaint().setMaskFilter(null);
                overview.setText(currentMovie.getOverview());
                overview.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
                overview.getPaint().setMaskFilter(null);
                original_title.setText(currentMovie.getTitle());

                Genre[] genres = currentMovie.getGenres();
                StringBuilder all_genres = new StringBuilder();
                for (int i = 0; i < genres.length; i++) {
                    if (i == genres.length - 1){
                        all_genres.append(genres[i].getName());
                        continue;
                    }
                    all_genres.append(genres[i].getName() + ", ");
                }

                Glide.with(getApplicationContext()).load(NetworkService.BASE_URL_IMAGES + currentMovie.getBackdrop_path())
                        .centerCrop()
                        .into(movieImage);
                ProductionCountries[] productionCountries;
                productionCountries = currentMovie.getProductionCountries();
                if (productionCountries.length == 0){
                    productionCountries =  new ProductionCountries[1];
                    productionCountries[0] = new ProductionCountries();
                    productionCountries[0].setName("Unknown country");
                }
                chr1.setText(currentMovie.getRelease_date() + ", " + productionCountries[0].getName() + ", " + all_genres.toString());

                chr3.setText("time: " + currentMovie.getRuntime() + ", " + "rating: " + currentMovie.getVote_average());
            }


            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.i("response",t.getMessage());
            }
        });
        mediaAPI.getVideoMovieById(id).enqueue(new Callback<VideoTrailer>() {
            @Override
            public void onResponse(Call<VideoTrailer> call, Response<VideoTrailer> response) {
                if (!response.isSuccessful()){
                    Log.i("response Video",response.message());
                    return;
                }
                VideoTrailer videoTrailer = response.body();
                Video[] videos = videoTrailer.getResults();
                if (videos.length == 0){
                    video_key = null;
                    return;
                }
                video_key = videos[0].getKey();
            }

            @Override
            public void onFailure(Call<VideoTrailer> call, Throwable t) {

            }
        });

    }

    public void findViewByIds(){
        original_title = findViewById(R.id.original_title);
        chr1 = findViewById(R.id.chr1);
        chr2 = findViewById(R.id.chr2);
        chr3 = findViewById(R.id.chr3);
        overview = findViewById(R.id.overview);
        movieImage = findViewById(R.id.movie_image);
        actors_recycler = findViewById(R.id.actors_recycler);
    }

    public void openTrailer(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if(video_key == null){
            Toast.makeText(getBaseContext(),"The thrailer doesn't provided for this video",Toast.LENGTH_LONG).show();
        }
        intent.setData(Uri.parse(NetworkService.BASE_YOUTUBE_VIDEO_URL + video_key));
        startActivity(intent);
    }

    public void openActor(View v){
        TextView id = v.findViewById(R.id.id);
        actor_id = Integer.parseInt(id.getText()+"");
        Toast.makeText(getApplicationContext(),"Actor ID: " +actor_id,Toast.LENGTH_LONG).show();
        actorDetailedFragment = ActorDetailedFragment.newInstance(actor_id);
        getSupportFragmentManager().beginTransaction().add(R.id.detailed_actor_fragment_container,actorDetailedFragment,"Hello").commit();
    }

    public void openMovie(View view){
        TextView id = view.findViewById(R.id.id);
        Intent intent = new Intent(getApplicationContext(), MovieExtendedActivity.class);
        intent.putExtra("cinemaId",id.getText());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (getBaseContext() != null){
            Log.i("actor_id",actor_id+"");
            if (actor_id != 0){
                getSupportFragmentManager().beginTransaction().remove(actorDetailedFragment).commit();
                actor_id = 0;
                return;
            }
            super.onBackPressed();
        }

    }

    public void backButtonClicked(View v){
        onBackPressed();
    }
}
