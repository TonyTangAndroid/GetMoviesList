package com.example.android.getmovielist;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

/**
 * data transfer object
 */
@GenerateTypeAdapter
@AutoValue
public abstract class NewsDto {

  @SerializedName("totalResults")
  public abstract int totalResults();

  @SerializedName("status")
  public abstract String status();

  @SerializedName("articles")
  public abstract List<Article> list();


}