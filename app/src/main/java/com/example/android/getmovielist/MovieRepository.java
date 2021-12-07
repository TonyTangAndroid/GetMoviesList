package com.example.android.getmovielist;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import io.reactivex.Single;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {


  public static Single<List<Article>> newsStreaming(String key) {
    return service().news(key).map(NewsDto::newsList)
        .onErrorReturnItem(Collections.emptyList());
  }

  @NonNull
  private static MovieService service() {
    Gson enhancedGson = new GsonBuilder()
        .registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY)
        .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create(enhancedGson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(new OkHttpClient.Builder().addInterceptor(logger()).build())
        .build();
    return retrofit.create(MovieService.class);
  }

  private static Interceptor logger() {
    return new HttpLoggingInterceptor().setLevel(Level.BASIC);
  }
}