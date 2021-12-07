package com.example.android.getmovielist;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<NewsViewHolder> {

  private final List<Article> list = new ArrayList<>();

  public InfoAdapter() {
  }

  @SuppressLint("NotifyDataSetChanged")
  public void bindData(List<Article> newDataList) {
    list.clear();
    list.addAll(newDataList);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new NewsViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
    holder.bind(list.get(position));
  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
