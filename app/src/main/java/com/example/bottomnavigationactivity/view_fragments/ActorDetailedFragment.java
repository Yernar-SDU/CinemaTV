package com.example.bottomnavigationactivity.view_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationactivity.IntentBundleKeys;
import com.example.bottomnavigationactivity.R;
import com.example.bottomnavigationactivity.adapter.RecycleViewCardMovieAdapter;
import com.example.bottomnavigationactivity.model.CreditsMovie;
import com.example.bottomnavigationactivity.model.Movie;
import com.example.bottomnavigationactivity.model.Person;
import com.example.bottomnavigationactivity.network.MediaAPI;
import com.example.bottomnavigationactivity.network.NetworkService;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorDetailedFragment extends Fragment {
    private RecyclerView actor_movies_recycler;
    private int actor_id;
    private TextView actor_name, actor_job;
    private ImageView actor_image;
    private final MediaAPI mediaAPI = NetworkService.getAPI();
    public static ActorDetailedFragment newInstance(int actor_id) {

        Bundle args = new Bundle();
        args.putInt(IntentBundleKeys.ACTOR_ID,actor_id);
        ActorDetailedFragment fragment = new ActorDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.actor_detailed_fragment,container,false);
        findViewByIds(root);
        Bundle args = getArguments();
        actor_id = args.getInt(IntentBundleKeys.ACTOR_ID);
        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI() {
        mediaAPI.getPersonDetails(actor_id).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(@NotNull Call<Person> call, @NotNull Response<Person> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Person person = response.body();
                actor_name.setText(person.getName());
                actor_job.setText(person.getKnown_for_department());
                if(getContext() != null){
                    Glide.with(getContext()).load(NetworkService.BASE_URL_IMAGES + person.getProfile_path())
                            .centerCrop()
                            .into(actor_image);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Person> call, @NotNull Throwable t) {

            }
        });

        mediaAPI.getPersonMovies(actor_id).enqueue(new Callback<CreditsMovie>() {
            @Override
            public void onResponse(@NotNull Call<CreditsMovie> call, @NotNull Response<CreditsMovie> response) {
                if (!response.isSuccessful()){
                    return;
                }
                CreditsMovie creditsMovie = response.body();
                Movie[] cast = creditsMovie.getCast();
                List<Movie> movieList = Arrays.asList(cast);
                RecycleViewCardMovieAdapter recycleViewCardMovieAdapter = new RecycleViewCardMovieAdapter(getContext(),movieList);
                actor_movies_recycler.setAdapter(recycleViewCardMovieAdapter);
                actor_movies_recycler.setLayoutManager(new GridLayoutManager(getContext(),3));
            }

            @Override
            public void onFailure(@NotNull Call<CreditsMovie> call, @NotNull Throwable t) {

            }
        });
    }

    public void findViewByIds(View root){
        actor_name = root.findViewById(R.id.actor_name);
        actor_image = root.findViewById(R.id.actor_image);
        actor_job = root.findViewById(R.id.actor_job);
        actor_movies_recycler = root.findViewById(R.id.actor_movies_recycler);
    }


}
