package com.example.android.getmovielist;

import java.util.List;

/**
 * data transfer object
 */
public class ProductDto {

  private final String title;
  private final List<ProductInfo> production_companies;

  public String getTitle() {
    return title;
  }

  public List<ProductInfo> getProduction_companies() {
    return production_companies;
  }

  public ProductDto(String title,
      List<ProductInfo> production_companies) {

    this.title = title;
    this.production_companies = production_companies;
  }
}