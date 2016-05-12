package android.courses.intorealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private Realm realm;
    private RealmConfiguration realmConfig;

    private ListView mListView;

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

        realmConfig = new RealmConfiguration.Builder(this).build();
        //Realm.deleteRealm(realmConfig);
        // Set the default Realm configuration at the beginning.
        Realm.setDefaultConfiguration(realmConfig);
        // Get a realm instance for this thread
        realm = Realm.getDefaultInstance();
//        realm = Realm.getInstance(realmConfig);

        RealmResults<User> users = allUsers();
        Log.d(TAG, "users queried" +users);
//        Log.d(TAG, "users queried" +users.get(0).getFirstName());

        final UserAdapter adapter = new UserAdapter(this, R.id.listView, users, true);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);

//        ArrayAdapter<User> adapter = new ArrayAdapter<User>(MainActivity.this, android.R.layout.simple_list_item_1, users);
//        mListView.setAdapter(adapter);

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

    private RealmResults<User> allUsers() {
        RealmResults<User> users = realm.where(User.class).findAll();

        return users;
    }
}
