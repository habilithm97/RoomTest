package com.example.roomtest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/*
#RoomDB
-데이터베이스의 전체적인 소유자로서 DB 생성 및 버전 관리를 함
-Entity 만큼 정의된 Dao 객체들을 반환할 수 있는 메서드들을 가지고 있는 추상 클래스 형태로 정의함(RecyclerView의 Adapter랑 비슷함)
-Dao를 가져와서 객체를 통해 데이터를 CRUD 함
-Room 객체는 많은 리소스를 소모하기 때문에 SingleTon 패턴으로 정의해야 됨
 */

@Database(entities = {MainModel.class}, version = 1, exportSchema = false) // exportSchema : 스키마를 폴더로 내보내도록 설정 여부를 정함
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "my_db";

    public synchronized static RoomDB getInstance(Context context) {
        if(database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries() // 메인 스레드에 접근할 수 있음
                    .fallbackToDestructiveMigration() // 스키마 버전 변경 가능
                    .build();
        }
        return database;
    }
    public abstract MainDao mainDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
