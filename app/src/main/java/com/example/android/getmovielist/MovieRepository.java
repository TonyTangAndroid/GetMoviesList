package com.example.android.getmovielist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

  public static ProductDto fetch(String parameterUuid) throws IOException {

    Gson enhancedGson = new GsonBuilder()
        .registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY)
        .create();
    GsonConverterFactory factory = GsonConverterFactory.create(enhancedGson);
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(factory)
        .build();
    MovieService service = retrofit.create(MovieService.class);
    return service.listRepos(parameterUuid).execute().body();
  }
}