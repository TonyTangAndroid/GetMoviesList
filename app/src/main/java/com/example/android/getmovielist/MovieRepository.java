package com.example.android.getmovielist;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import io.reactivex.Single;
import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

  public static ProductDto fetch(String parameterUuid) throws IOException {
    throw new RuntimeException();
  }

  public static Single<NewsDto> newsStreaming(String key) {
    return service().news(key);
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
        .build();
    return retrofit.create(MovieService.class);
  }
}