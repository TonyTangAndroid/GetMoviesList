package com.example.android.getmovielist;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

@GenerateTypeAdapter
@AutoValue
abstract class ProductInfo {

  // Failed to invoke public com.example.android.getmovielist.ProductDto() with no args
//     Caused by: java.lang.NullPointerException: Null logoPath

  //immutable
  //explicit contract --> clear and easy to understand and maintain.

  @Nullable
  @SerializedName("logo_path")
  abstract String logoPath();

  @SerializedName("name")
  abstract String companyName();

  @SerializedName("origin_country")
  abstract String originCountry();

  public static ProductInfo create(String logoPath, String name, String originCountry) {
    return new AutoValue_ProductInfo(logoPath, name, originCountry);
  }

}