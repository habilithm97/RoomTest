 package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/*
#Room
-ORM(Object Relational Mapping) 라이브러리로서 DB 데이터를 자바 객체로 매핑해줌
-SQLite를 내부적으로 사용하고 있지만, DB를 구조적으로 분리하여 데이터 접근의 편의성을 높여주고 유지보수에 편리함
-다양한 Annotation을 통해 컴파일 시 코드들을 자동으로 만들어주며 RxJava와 같은 Observation 형태를 지원하고
MVP, MVVM과 같은 아키텍쳐 패턴에 쉽게 활용할 수 있음
-UI와 DB를 연결해줌

*SQLite보다 Room 사용을 권장하는 이유
1. 컴파일 타임 중 SQL에 대한 유효성 검사
2. 스키마 변경 시 자동 업데이트
3. 자바 데이터 객체를 변경하기 위해 상용구 코드 없이 ORM 라이브러리를 통해 매핑 가능
4. LiveData와 RxJava를 위한 Observation 생성 및 동작

*Room 라이브러리는 Entity(model), 데이터 접근 객체(dao), 데이터베이스(db)로 구성되어 있음
 */

 public class MainActivity extends AppCompatActivity {

     RoomDB database;
     List<MainModel> items = new ArrayList<>();
     ItemAdapter adapter;

     EditText inputEdt;
     Button insertBtn, resetBtn;
     RecyclerView recyclerView;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         inputEdt = findViewById(R.id.inputEdt);
         insertBtn = findViewById(R.id.insertBtn);
         resetBtn = findViewById(R.id.resetBtn);
         recyclerView = findViewById(R.id.recyclerView);

         database = RoomDB.getInstance(this);
         items = database.mainDao().getAll();
         adapter = new ItemAdapter(MainActivity.this, items);

         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(adapter);

         insertBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String str = inputEdt.getText().toString().trim();
                 if (!str.equals("")) {
                     MainModel item = new MainModel();
                     item.setText(str);
                     database.mainDao().insert(item);

                     inputEdt.setText("");
                     items.clear();

                     items.addAll(database.mainDao().getAll());
                     adapter.notifyDataSetChanged();
                 }
             }
         });

         resetBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 database.mainDao().reset(items);

                 items.clear();

                 items.addAll(database.mainDao().getAll());
                 adapter.notifyDataSetChanged();
             }
         });
     }
 }