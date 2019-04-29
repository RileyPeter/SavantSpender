package com.savantspender.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.savantspender.AppExecutors;
import com.savantspender.db.converter.DateConverter;
import com.savantspender.db.dao.PlaidItemDao;
import com.savantspender.db.dao.acountDoa;
import com.savantspender.db.dao.initutionDoa;
import com.savantspender.db.dao.itemDoa;
import com.savantspender.db.dao.transactionDOA;
import com.savantspender.db.entity.PlaidItemEntity;
import com.savantspender.model.PlaidAccount;
import com.savantspender.model.PlaidItem;
import com.savantspender.model.PlaidTransaction;
import com.savantspender.model.Tag;


@Database(entities = {
        PlaidItemEntity.class
//        account_enity.class,
//        transaction_enity.class,
//        Tag.class
}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
@SuppressWarnings("deprecation")
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mAppDatabase;
    private static final String DATABASE_NAME = "SavantSpenderDB";


    public abstract PlaidItemDao plaidItemDao();
    public abstract transactionDOA transDOA();
    public abstract initutionDoa initutionDOA();
    public abstract acountDoa acountDoa();
    public abstract itemDoa itemDOA();

    public static AppDatabase getInstance(final Context appContext, final AppExecutors executors) {
        if (mAppDatabase == null) {
            synchronized (AppDatabase.class) {
                mAppDatabase = buildDatabase(appContext.getApplicationContext(), executors);
            }
        }

        return mAppDatabase;
    }

    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {

        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // todo: populate anything needed here
                        });
                    }
                })
                .build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }
}




