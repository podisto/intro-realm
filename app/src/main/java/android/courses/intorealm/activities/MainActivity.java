package android.courses.intorealm.activities;

import android.content.Intent;
import android.courses.intorealm.R;
import android.courses.intorealm.db.DbManager;
import android.courses.intorealm.interfaces.GithubService;
import android.courses.intorealm.models.Repo;
import android.courses.intorealm.models.User;
import android.courses.intorealm.adapters.UserAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    private ListView mListView;

    private GithubService api;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        dbManager = DbManager.getInstance(this);
        RealmResults<User> users = dbManager.allUsers();
        Log.d(TAG, "users queried" + users);

        final UserAdapter adapter = new UserAdapter(this, R.id.listView, users, true);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);


        getUserRepos();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //
    private void getUserRepos() {
        api = DbManager.getApi();
        api.listReposAsync("podisto", new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repos, Response response) {
                afficherRepos(repos);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    //
    private void afficherRepos(List<Repo> repos) {
        Toast.makeText(this, "nombre de repos : " + repos.size(), Toast.LENGTH_SHORT).show();
    }
}
