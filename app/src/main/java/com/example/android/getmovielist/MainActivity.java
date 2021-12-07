package com.example.android.getmovielist;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

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

  }

  private void initView() {
    et_input = findViewById(R.id.et_input);
    tv_empty_view = findViewById(R.id.tv_empty_view);
    newsListView = (ListView) findViewById(R.id.list);
    newsListView.setEmptyView(tv_empty_view);
    newsListView.setAdapter(new InfoAdapter(this, new ArrayList<>()));
  }


}