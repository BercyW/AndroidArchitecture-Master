package com.example.c052735.simpleexamplemvvm.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.c052735.simpleexamplemvvm.db.entity.CommentEntity;

import java.util.List;
@Dao
public interface CommentDao {
    @Query("select * from comments where productId = :productId")
    LiveData<List<CommentEntity>> loadComments(int productId);

    @Query("select * from comments where productId = :productId")
    List<CommentEntity> loadCommentsSync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentEntity> comments);

}
