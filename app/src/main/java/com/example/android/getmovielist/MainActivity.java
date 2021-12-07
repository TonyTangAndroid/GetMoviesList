package com.example.android.getmovielist;

import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  private TextView tv_empty_view;
  private EditText et_input;
  private RecyclerView rv_news;
  private ProgressBar loading_bar;
  private InfoAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    bindDataInput();

  }

  private void bindDataInput() {
    searchKeyStream()
        .observeOn(AndroidSchedulers.mainThread())
        .as(autoDisposable(from(this)))
        .subscribe(this::showLoading);


    searchKeyStream()
        .switchMapSingle(MovieRepository::newsStreaming)
        .map(NewsDto::list)
        .observeOn(AndroidSchedulers.mainThread())
        .as(autoDisposable(from(this)))
        .subscribe(this::bindAdapter,this::onError);

    et_input.requestFocus();
  }

  private void showLoading(String keyword) {
    Log.d("KEY_WORD","Begin to search :" + keyword);
    loading_bar.setVisibility(View.VISIBLE);
  }

  private Observable<String> searchKeyStream() {
    return RxTextView.textChanges(et_input)
        .skipInitialValue()
        .debounce(500, TimeUnit.MILLISECONDS)
        .map(CharSequence::toString);
  }

  private void onError(Throwable throwable) {
    throwable.printStackTrace();
    tv_empty_view.setText(throwable.getMessage());
    loading_bar.setVisibility(View.GONE);
  }


  private void bindAdapter(List<Article> arrayList) {
    loading_bar.setVisibility(View.GONE);
    adapter.bindData(arrayList);
    updateViewVisibility(arrayList);
  }

  private void updateViewVisibility(List<Article> arrayList) {
    if (arrayList.size()==0){
      tv_empty_view.setVisibility(View.VISIBLE);
      rv_news.setVisibility(View.GONE);
      tv_empty_view.setText(R.string.no_data);
    }else{
      rv_news.setVisibility(View.VISIBLE);
      tv_empty_view.setVisibility(View.GONE);

    }
  }

  private void initView() {
    et_input = findViewById(R.id.et_input);
    tv_empty_view = findViewById(R.id.tv_empty_view);
    loading_bar = findViewById(R.id.loading_bar);
    rv_news = (RecyclerView) findViewById(R.id.rv_news);
    rv_news.setLayoutManager(new LinearLayoutManager(this));
    adapter = new InfoAdapter();
    rv_news.setAdapter(adapter);
  }


}