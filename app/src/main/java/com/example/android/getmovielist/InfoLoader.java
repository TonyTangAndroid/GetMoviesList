package com.example.android.getmovielist;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoLoader extends AsyncTaskLoader<List<ProductInfo>> {
    public static final String LOG_TAG = AsyncTaskLoader.class.getSimpleName();
    private final String mUrl;

    public InfoLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"This is onStartLoading() method");
        forceLoad();
    }

    @Nullable
    @Override
    public List<ProductInfo> loadInBackground() {
        Log.i(LOG_TAG,"This is loadInBackground() method");
        try {
            ProductDto productDto = MovieRepository.fetch("cf23af04-e70f-4fb8-8222-9253aeb7a4a3");
            return productDto.getProduction_companies();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
