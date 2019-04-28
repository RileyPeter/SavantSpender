package com.savantspender.db.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.savantspender.db.entity.CataloggedEntity;

@Dao
public interface CataloggedDao {
    @Insert
    void insert(CataloggedEntity centity);

    @Delete
    void delete(CataloggedEntity centity);

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM catalogged WHERE accountId = :accountId AND transactionId = :transId) THEN 1 ELSE 0 END")
    LiveData<Boolean> isAlreadyCatalogged(@NonNull String transId, @NonNull String accountId);
}