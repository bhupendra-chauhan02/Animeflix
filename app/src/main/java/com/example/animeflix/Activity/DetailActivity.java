package com.example.animeflix.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.animeflix.Adapter.ImagesListAdapter;
import com.example.animeflix.Domain.FilmItem;
import com.example.animeflix.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringQueue;
    private ProgressBar progressBar;
    private TextView titleTxt , movieRateTxt , movieTimeTxt ,movieDateText, movieSummaryInfo , movieActorsInfo;
    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic;
    private ImageView pic2 , backImg;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterImgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        mStringQueue = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/"+idFilm, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response,FilmItem.class);
            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getRated());
            movieTimeTxt.setText(item.getRuntime());
            movieDateText.setText(item.getReleased());
            movieSummaryInfo.setText(item.getPlot());
            movieActorsInfo.setText(item.getActors());

            if(item.getImages()!=null){
                adapterImgList = new ImagesListAdapter(item.getImages());
                recyclerView.setAdapter(adapterImgList);
            }

        }, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("Bhupendra", "OnErrorResponse"+error.toString());

        });
        mRequestQueue.add(mStringQueue);
    }

    private void initView() {
        titleTxt = findViewById(R.id.movienametxt);
        progressBar = findViewById(R.id.detailloading);
        scrollView = findViewById(R.id.scrollView4);
        pic = findViewById(R.id.posternormaling);
        pic2 = findViewById(R.id.posterbigimage);
        movieRateTxt = findViewById(R.id.movieRatetxt);
        movieTimeTxt = findViewById(R.id.movietimetxt);
        movieDateText = findViewById(R.id.moviedatetxt);
        movieSummaryInfo = findViewById(R.id.moviesummaryinfo);
        movieActorsInfo = findViewById(R.id.movieactorinfo);
        backImg = findViewById(R.id.moviebackImg);
        recyclerView = findViewById(R.id.imagesRecylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false));
        backImg.setOnClickListener(view -> finish());

    }
}