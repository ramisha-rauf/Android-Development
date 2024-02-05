package com.example.devilapplication.Room;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<Users>> allUsers;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.getDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<Users>> getAllUsers() {
        return allUsers;
    }

    public void insert(Users users) {
        new InsertAsyncTask(userDao).execute(users);
    }

    public void delete(int id) {
        new DeleteAsyncTask(userDao).execute(id);
    }


    private static class InsertAsyncTask extends AsyncTask<Users, Void, Void> {
        private UserDao userDao;

        private InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private UserDao userDao;

        private DeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Integer... ids) {
            userDao.delete(ids[0]);
            return null;
        }
    }
}
