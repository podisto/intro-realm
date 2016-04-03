package android.courses.intorealm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        realmConfig = new RealmConfiguration.Builder(this).build();
        // Get a realm instance for this thread
        realm = Realm.getInstance(realmConfig);

        List<User> users = allUsers();
        Log.d(TAG, "users queried" +users);

        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(MainActivity.this, android.R.layout.simple_list_item_1, users);

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

    public void saveUser(View view) {
        EditText edit_fname = (EditText) findViewById(R.id.edit_fname);
        EditText edit_lname = (EditText) findViewById(R.id.edit_lname);

        String firstName = edit_fname.getText().toString();
        String lastName = edit_lname.getText().toString();

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.beginTransaction();

        User user = realm.createObject(User.class);
        user.setId(1);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();

        Log.d(TAG, "User inserted.");

    }

    private List<User> allUsers() {
        RealmResults<User> users = realm.where(User.class).findAll();

        return users;
    }
}
