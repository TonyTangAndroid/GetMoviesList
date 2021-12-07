package com.example.android.getmovielist;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

  //https://newsapi.org/v2/everything?q=tesla&apiKey=282a2471edf64e5c890e385daf87196d
  @GET("everything?apiKey=282a2471edf64e5c890e385daf87196d")
  Single<NewsDto> news(@Query("q") String key);
}