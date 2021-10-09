package com.example.android.getmovielist;

import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

  public static ProductDto fetch(String parameterUuid) throws IOException {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    MovieService service = retrofit.create(MovieService.class);
    return service.listRepos(parameterUuid).execute().body();
  }
}