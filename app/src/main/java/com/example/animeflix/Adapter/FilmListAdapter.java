package com.example.animeflix.Adapter;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeflix.Activity.DetailActivity;
import com.example.animeflix.Domain.ListFilm;
import com.example.animeflix.Domain.FilmItem;
import com.example.animeflix.R;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    ListFilm item;
    Context context;

    public FilmListAdapter(ListFilm item) {
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(com.example.animeflix.R.layout.viewholder_film,parent,false);
        //LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        context = parent.getContext();
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(item.getData().get(position).getTitle());
        holder.ScoreTxt.setText(""+item.getData().get(position).getImdbRating());
        Glide.with(holder.itemView.getContext())
                .load(item.getData().get(position).getPoster())
                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id",item.getData().get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return item.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt , ScoreTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(com.example.animeflix.R.id.title_txt);
            ScoreTxt = itemView.findViewById(com.example.animeflix.R.id.score_txt);
            pic = itemView.findViewById(com.example.animeflix.R.id.pic);
        }
    }
}
