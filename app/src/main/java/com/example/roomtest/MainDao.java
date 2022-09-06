package com.example.roomtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/*
*DAO
-데이터베이스에 접근하여 수행할 작업을 메서드 형태로 정의함(SQL 쿼리 지정 가능)
 */

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE) // update와 동일한 기능을 할 수 있음
    void insert(MainModel mainModel);

    @Delete
    void delete(MainModel mainModel);

    @Delete
    void reset(List<MainModel> mainModel);

    @Query("UPDATE my_tb SET text = :uText WHERE id = :uID") // 아이디를 기준으로 텍스트 수정
    void update(int uID, String uText);

    @Query("SELECT * FROM my_tb") // 테이블의 모든 컬럼을 조회
    List<MainModel> getAll();
}
