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
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  private TextView tv_empty_view;
  private EditText et_input;
  private ListView newsListView;
  private ProgressBar loading_bar;

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
        .map(ArrayList::new)
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


  private void bindAdapter(ArrayList<Article> arrayList) {
    loading_bar.setVisibility(View.GONE);
    newsListView.setAdapter(new InfoAdapter(this,arrayList));
  }

  private void initView() {
    et_input = findViewById(R.id.et_input);
    tv_empty_view = findViewById(R.id.tv_empty_view);
    loading_bar = findViewById(R.id.loading_bar);
    newsListView = (ListView) findViewById(R.id.list);
    newsListView.setEmptyView(tv_empty_view);
    newsListView.setAdapter(new InfoAdapter(this, new ArrayList<>()));
  }


}