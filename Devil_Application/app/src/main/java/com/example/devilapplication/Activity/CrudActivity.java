//package com.example.devilapplication.Activity;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.Observer;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.devilapplication.Adapter.AdapterListener;
//import com.example.devilapplication.R;
//import com.example.devilapplication.Room.UserDao;
//import com.example.devilapplication.Room.UserDatabase;
//import com.example.devilapplication.Room.UserRepository;
//import com.example.devilapplication.Room.Users;
//import com.example.devilapplication.Adapter.UserAdapter;
//
//import java.util.List;
//
//public class CrudActivity extends AppCompatActivity implements AdapterListener {
//
//    EditText editName, emailEdit;
//    Button insertButton;
//    RecyclerView myrecycler;
//
//    private UserDatabase userDatabase;
//    private UserDao userDao;
//
//    private static UserAdapter userAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crud);
//
//        userDatabase=UserDatabase.getInstance(this);
//        userDao=userDatabase.getDao();
//
//        emailEdit = findViewById(R.id.email);
//        editName = findViewById(R.id.name);
//        insertButton = findViewById(R.id.insert);
//        myrecycler=findViewById(R.id.usersRecycler);
//
//        userAdapter=new UserAdapter(this,this);
//        myrecycler.setAdapter(userAdapter);
//        myrecycler.setLayoutManager(new LinearLayoutManager(this));
//
////        insertButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String name=editName.getText().toString();
////                String email = emailEdit.getText().toString();
////
////                Users users = new Users(0,name,email);
////                userAdapter.addUser(users);
////                userDao.insert(users);
////                editName.setText("");
////                emailEdit.setText("");
////                Toast.makeText(CrudActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
////            }
////        });
//        insertButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editName.getText().toString();
//                String email = emailEdit.getText().toString();
//
//                Users users = new Users(0, name, email);
//                new UserRepository.InsertUserAsyncTask(userDao).execute(users);
//                editName.setText("");
//                emailEdit.setText("");
//                Toast.makeText(CrudActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//// ...
//
//        class InsertUserAsyncTask extends AsyncTask<Users, Void, Void> {
//            private UserDao userDao;
//
//            private InsertUserAsyncTask(UserDao userDao) {
//                this.userDao = userDao;
//            }
//
//            @Override
//            protected Void doInBackground(Users... users) {
//                userDao.insert(users[0]);
//                return null;
//            }
//        }
//
//
//    }
//
//    private void fetchData() {
//        userAdapter.clearData();
//
//        userDao.getAllUsers().observe(this, new Observer<List<Users>>() {
//            @Override
//            public void onChanged(List<Users> users) {
//                if (users != null) {
//                    for (Users user : users) {
//                        userAdapter.addUser(user);
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public void OnUpdate(Users users) {
//        Intent intent = new Intent(this, UpdateActivity.class);
//        intent.putExtra("model",users);
//        startActivity(intent);
//    }
//
////    @Override
////    public void OnDelete(int id, int pos) {
////        userDao.delete(id);
////        userAdapter.removeUser(pos);
////    }
//
//    @Override
//    public void OnDelete(int id, int pos) {
//        // Move the database operation to a background thread
//        new DeleteUserAsyncTask(userDao, id, pos).execute();
//    }
//
//    private static class DeleteUserAsyncTask extends AsyncTask<Void, Void, Void> {
//        private UserDao userDao;
//        private int id;
//        private int pos;
//
//        private DeleteUserAsyncTask(UserDao userDao, int id, int pos) {
//            this.userDao = userDao;
//            this.id = id;
//            this.pos = pos;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            userDao.delete(id);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            // Update your UI or perform any other necessary operations after deletion
//            userAdapter.removeUser(pos);
//        }
//    }
//
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        fetchData();
//    }
//
//}
