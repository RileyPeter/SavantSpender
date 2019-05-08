package com.savantspender;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.savantspender.db.AppDatabase;
import com.savantspender.model.DataRepository;
import com.savantspender.worker.DownloadTransactionsWorker;
import com.savantspender.worker.UpdateGoalsWorker;

import java.util.concurrent.TimeUnit;

public class SavantSpender extends Application {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
        initializePeriodicWorkRequests();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }

    public AppExecutors getExecutors() {
        return mAppExecutors;
    }


    private void initializePeriodicWorkRequests() {
        WorkManager mgr = WorkManager.getInstance();

        initializeDownloadRequests(mgr);
        initializeUpdateRequests(mgr);

        // todo: month summary (use AlarmManager instead of WorkManager)
    }


    private void initializeDownloadRequests(@NonNull WorkManager mgr) {
        String periodicTransWorkTag = getResources().getString(R.string.work_dl_trans_p);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest downloadTransactions =
                new PeriodicWorkRequest.Builder(DownloadTransactionsWorker.class, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .addTag(periodicTransWorkTag)
                        .build();

        // ensure download transaction task is scheduled; this will occur in background even if
        // app not in foreground or active
        mgr.enqueueUniquePeriodicWork(periodicTransWorkTag, ExistingPeriodicWorkPolicy.KEEP, downloadTransactions);
    }


    private void initializeUpdateRequests(@NonNull WorkManager mgr) {
        String updateTransTag = getResources().getString(R.string.work_update_goals_s);

        OneTimeWorkRequest updateGoalsNow = new OneTimeWorkRequest.Builder(UpdateGoalsWorker.class)
                .addTag(updateTransTag)
                .build();

        mgr.enqueue(updateGoalsNow);

        // todo: also periodic, daily
    }
}
