package android.courses.intorealm.db;

import android.content.Context;
import android.courses.intorealm.activities.MainActivity;
import android.courses.intorealm.interfaces.GithubService;
import android.courses.intorealm.models.Repo;
import android.courses.intorealm.models.User;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

/**
 * Created by Podisto on 12/05/2016.
 */
public class DbManager {

    final String TAG = "DbManager";
    private Realm realm = null;
    private Context context = null;

    private static GithubService api = null;

    private static DbManager instance = null;

    /**
     * La presence d'un constructeur prive supprime le constructeur public par defaut
     * De plus, seul le singleton peut s'instancier lui-meme
     */
    private DbManager() {
        super();
    }

    public static DbManager getInstance(Context ctx) {
        if(DbManager.instance == null) {
            synchronized (DbManager.class) {
                if(DbManager.instance == null)
                    DbManager.instance = new DbManager(ctx);
            }
        }

        return DbManager.instance;
    }

    private DbManager(Context ctx) {
        //
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(ctx).build();
        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return realm;
    }

    //
    public RealmResults<User> allUsers() {
        RealmResults<User> users = realm.where(User.class).findAll();

        return users;
    }

    //
    public ArrayList<User> getUsers() {
        RealmResults<User> results = realm.where(User.class).findAll();

        ArrayList<User> users = new ArrayList<User>();
        for (User u: results) {
            users.add(u);
        }

        return users;
    }


    public static GithubService getApi() {
        Log.d("RETROFIT", "Initialisation de l'API...");

        if(api == null) {
            api = new RestAdapter.Builder()
                    .setEndpoint(GithubService.ENDPOINT)
                    .setLog(new AndroidLog("retrofit"))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
                    .create(GithubService.class);
        }


        return api;

    }

}
