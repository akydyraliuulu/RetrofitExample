package developer.retrofitexample.service;

import developer.retrofitexample.model.UserModel;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by appaz on 10/19/17.
 */

public interface UserService {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/users/{login}")
    Observable<UserModel> getUser(@Path("login") String login);
}
