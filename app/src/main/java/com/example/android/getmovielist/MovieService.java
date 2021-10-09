package com.example.android.getmovielist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

  //https://run.mocky.io/v3/cf23af04-e70f-4fb8-8222-9253aeb7a4a3
  @GET("v3/{uuid}")
  Call<ProductDto> listRepos(@Path("uuid") String parameterUuid);
}