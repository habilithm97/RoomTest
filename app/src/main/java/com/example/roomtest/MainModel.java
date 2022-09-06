package com.example.roomtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/*
#Entity
-DB 내의 테이블(클래스의 변수가 컬럼), DB에 저장할 데이터 형식
 */

@Entity(tableName = "my_tb")
public class MainModel implements Serializable {

    @PrimaryKey(autoGenerate = true) // 데이터가 생성될 때마다 자동으로 고유 id 값이 1씩 증가함
    private int id;

    @ColumnInfo(name = "text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
