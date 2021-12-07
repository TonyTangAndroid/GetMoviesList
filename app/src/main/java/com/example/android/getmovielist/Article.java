package com.example.android.getmovielist;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/**
 * data transfer object
 */
@GenerateTypeAdapter
@AutoValue
public abstract class Article {

  @SerializedName("author")
  public abstract String author();

  @SerializedName("title")
  public abstract String title();

  @SerializedName("url")
  public abstract String url();

  @SerializedName("urlToImage")
  public abstract String urlToImage();

}