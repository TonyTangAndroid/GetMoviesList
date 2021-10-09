package com.example.android.getmovielist;

public class ProductInfo {

    private final String logo_path;
    private final String name;
    private final String origin_country;

    public ProductInfo(String logo_path, String name, String origin_country) {
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }
    public String getLogoPath(){
        return logo_path;
    }
    public String getCompanyName(){
        return name;
    }
    public String getOriginCountry(){
        return origin_country;
    }
}