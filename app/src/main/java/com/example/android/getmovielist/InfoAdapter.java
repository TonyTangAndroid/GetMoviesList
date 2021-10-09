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

public class InfoAdapter extends ArrayAdapter<ProductInfo> {
    public InfoAdapter(Activity context, ArrayList<ProductInfo> productInfo) {
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
        ProductInfo currentProductInfo = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.company_logo);
        String url = "https://image.tmdb.org/t/p/w500" + currentProductInfo.logoPath();
        Glide.with(parent).load(url).error(R.drawable.no_data).into(imageView);

        TextView companyName = listItemView.findViewById(R.id.company_name);
        companyName.setText(currentProductInfo.companyName());

        TextView originCountry = listItemView.findViewById(R.id.origin_country);
        String OriginCountryName = currentProductInfo.originCountry();
        String countryWithFlag = CountryFlags.getCountryFlagByCountryCode(OriginCountryName);
        originCountry.setText(countryWithFlag + " " + OriginCountryName);

        return listItemView;
    }
}
