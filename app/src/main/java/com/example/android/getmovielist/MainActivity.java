package com.example.android.getmovielist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ProductInfo>> {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private InfoAdapter mAdapter;
    private static final String MOVIE_LIST_URL =
            "https://run.mocky.io/v3/cf23af04-e70f-4fb8-8222-9253aeb7a4a3";
    private static final int DATA_LOADER_ID = 1;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new InfoAdapter(this, new ArrayList<ProductInfo>());
        ListView ProductInfoListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        ProductInfoListView.setEmptyView(mEmptyStateTextView);

        ProductInfoListView.setAdapter(mAdapter);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG, "This is initLoader()");
            getSupportLoaderManager().initLoader(DATA_LOADER_ID, null, this).forceLoad();
        } else {
            ProgressBar prgBar = (ProgressBar) findViewById(R.id.loading_bar);
            prgBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No Internet Connection...");
        }
    }

    @NonNull
    @Override
    public Loader<List<ProductInfo>> onCreateLoader(int id, @Nullable Bundle bundle) {
        Log.i(LOG_TAG, "This is onCreateLoader() callback");
        return new InfoLoader(MainActivity.this, MOVIE_LIST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<ProductInfo>> loader, List<ProductInfo> data) {
        ProgressBar prgBar = (ProgressBar) findViewById(R.id.loading_bar);
        prgBar.setVisibility(View.GONE);
        Log.i(LOG_TAG, "This is onLoadFinished() callback");
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            mEmptyStateTextView.setText("No Data can be found...");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<ProductInfo>> loader) {
        Log.i(LOG_TAG, "This is onLoaderReset() callback");
        mAdapter.clear();
    }
}