package com.example.android.getmovielist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class InfoAdapter extends ArrayAdapter<Article> {

  public InfoAdapter(Activity context, ArrayList<Article> productInfo) {
    super(context, 0, productInfo);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View listItemView = convertView;
    if (listItemView == null) {
      listItemView = LayoutInflater.from(getContext()).inflate(
          R.layout.list_detail, parent, false);
    }
    Article currentProductInfo = getItem(position);

    ImageView imageView = (ImageView) listItemView.findViewById(R.id.iv_thumb);
    TextView tv_author = listItemView.findViewById(R.id.tv_author);
    TextView tv_title = listItemView.findViewById(R.id.tv_title);

    Glide.with(parent).load(currentProductInfo.url()).error(R.drawable.no_data).into(imageView);
    tv_title.setText(currentProductInfo.title());
    tv_author.setText(currentProductInfo.author());

    return listItemView;
  }
}
