package com.example.android.getmovielist;

import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  private TextView tv_empty_view;
  private EditText et_input;
  private ListView newsListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();

    bindDataInput();

  }

  private void bindDataInput() {

    RxTextView.textChanges(et_input)
        .skipInitialValue()
        .debounce(500, TimeUnit.MILLISECONDS)
        .map(CharSequence::toString)
        .switchMapSingle(MovieRepository::newsStreaming)
        .map(NewsDto::list)
        .map(ArrayList::new)
        .observeOn(AndroidSchedulers.mainThread())
        .as(autoDisposable(from(this)))
        .subscribe(this::bindAdapter);

    et_input.requestFocus();
  }


  private void bindAdapter(ArrayList<Article> arrayList) {
    newsListView.setAdapter(new InfoAdapter(this,arrayList));
  }

  private void initView() {
    et_input = findViewById(R.id.et_input);
    tv_empty_view = findViewById(R.id.tv_empty_view);
    newsListView = (ListView) findViewById(R.id.list);
    newsListView.setEmptyView(tv_empty_view);
    newsListView.setAdapter(new InfoAdapter(this, new ArrayList<>()));
  }


}