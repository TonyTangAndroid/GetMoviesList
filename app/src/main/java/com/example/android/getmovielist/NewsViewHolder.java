package com.example.android.getmovielist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class NewsViewHolder extends RecyclerView.ViewHolder {

  private final ImageView imageView;
  private final TextView tv_author;
  private final TextView tv_title;

  public NewsViewHolder(@NonNull View itemView) {
    super(itemView);
    imageView = (ImageView) itemView.findViewById(R.id.iv_thumb);
    tv_author = itemView.findViewById(R.id.tv_author);
    tv_title = itemView.findViewById(R.id.tv_title);

  }

  public void bind(Article currentProductInfo) {
    Glide.with(itemView).load(currentProductInfo.url()).error(R.drawable.no_data).into(imageView);
    tv_title.setText(currentProductInfo.title());
    tv_author.setText(currentProductInfo.author());
  }
}
