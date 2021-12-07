package com.example.android.getmovielist;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

  //https://run.mocky.io/v3/cf23af04-e70f-4fb8-8222-9253aeb7a4a3
  @GET("v3/{uuid}")
  Call<ProductDto> listRepos(@Path("uuid") String parameterUuid);

  //https://newsapi.org/v2/everything?q=tesla&apiKey=282a2471edf64e5c890e385daf87196d
  @GET("everything")
  Single<NewsDto> news(@Query("q") String key);
}