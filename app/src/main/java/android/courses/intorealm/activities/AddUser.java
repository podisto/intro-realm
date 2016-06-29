package android.courses.intorealm.activities;

import android.content.Intent;
import android.courses.intorealm.R;
import android.courses.intorealm.models.User;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AddUser extends AppCompatActivity {

    private final String TAG = "AddUserActivity";

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void saveUser(View view) {
        EditText edit_fname = (EditText) findViewById(R.id.edit_fname);
        EditText edit_lname = (EditText) findViewById(R.id.edit_lname);

        String firstName = edit_fname.getText().toString();
        String lastName = edit_lname.getText().toString();

        // Obtain a Realm instance
        Realm realm = Realm.getDefaultInstance();
        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.beginTransaction();

        User user = realm.createObject(User.class); // Create a new Object
        user.setId(1);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();

        Intent intent = new Intent(AddUser.this, MainActivity.class);
        startActivity(intent);

        // Log.d(TAG, "Name " +firstName);

    }

}
