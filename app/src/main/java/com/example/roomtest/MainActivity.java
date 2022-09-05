 package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*

#Room
-ORM(Object Relational Mapping) 라이브러리로서 DB 데이터를 자바 객체로 매핑해줌
-SQLite를 내부적으로 사용하고 있지만, DB를 구조적으로 분리하여 데이터 접근의 편의성을 높여주고 유지보수에 편리함
-다양한 Annotation을 통해 컴파일 시 코드들을 자동으로 만들어주며 RxJava와 같은 Observation 형태를 지원하고
MVP, MVVM과 같은 아키텍쳐 패턴에 쉽게 활용할 수 있음

*SQLite보다 Room 사용을 권장하는 이유
1. 컴파일 타임 중 SQL에 대한 유효성 검사
2. 스키마 변경 시 자동 업데이트
3. 자바 데이터 객체를 변경하기 위해 상용구 코드 없이 ORM 라이브러리를 통해 매핑 가능
4. LiveData와 RxJava를 위한 Observation 생성 및 동작

 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}