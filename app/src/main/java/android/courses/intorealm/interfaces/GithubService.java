package android.courses.intorealm.interfaces;

import android.courses.intorealm.models.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Podisto on 12/05/2016.
 */
public interface GithubService {

    public static final String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}/repos")
    void listReposAsync(@Path("user") String user, Callback<List<Repo>> callback);
}
