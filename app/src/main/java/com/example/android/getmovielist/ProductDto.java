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
public abstract class ProductDto {

  @SerializedName("title")
  public abstract String title();

  @SerializedName("production_companies")
  public abstract List<ProductInfo> productionCompanyList();

  public static ProductDto create(String title, List<ProductInfo> productionCompanyList) {
    return new AutoValue_ProductDto(title, productionCompanyList);
  }


}