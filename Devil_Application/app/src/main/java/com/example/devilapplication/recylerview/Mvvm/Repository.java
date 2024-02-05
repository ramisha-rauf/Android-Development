package com.example.devilapplication.recylerview.Mvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.devilapplication.recylerview.database.ListItemEntity;
import com.example.devilapplication.recylerview.database.dao;
import com.example.devilapplication.recylerview.database.roomDatabase;

import java.util.List;

public class Repository {

    private dao dao;
    private LiveData<List<ListItemEntity>> allData;

    public Repository(Context context) {
        roomDatabase db = roomDatabase.getDatabase(context);
        dao = db.Dao();
        allData = dao.getAllData();
    }

    public LiveData<List<ListItemEntity>> getAllData() {
        return allData;
    }

    public void insertData(ListItemEntity listItemEntity) {
        new InsertAsyncTask(dao).execute(listItemEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<ListItemEntity, Void, Void> {
        private dao asyncTaskDao;

        InsertAsyncTask(dao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ListItemEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //delete
    public void delete(ListItemEntity listItemEntity) {
        new DeleteAsyncTask(dao).execute(listItemEntity);
    }
    private static class DeleteAsyncTask extends AsyncTask<ListItemEntity, Void, Void> {
        private dao asyncTaskDao;

        DeleteAsyncTask(dao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ListItemEntity... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }

    //update
    public LiveData<ListItemEntity> getItemByName(String name) {
        return dao.getItemByName(name);
    }

    public void updateData(ListItemEntity listItemEntity) {
        new UpdateAsyncTask(dao).execute(listItemEntity);
    }

    private static class UpdateAsyncTask extends AsyncTask<ListItemEntity, Void, Void> {
        private com.example.devilapplication.recylerview.database.dao asyncTaskDao;

        UpdateAsyncTask(dao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ListItemEntity... params) {
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
    // deleteAll
    public void deleteAllData() {
        new DeleteAllDataAsyncTask(dao).execute();
    }

    private static class DeleteAllDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private dao Dao;

        private DeleteAllDataAsyncTask(dao Dao) {
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Dao.deleteAllData();
            return null;
        }
    }
}
