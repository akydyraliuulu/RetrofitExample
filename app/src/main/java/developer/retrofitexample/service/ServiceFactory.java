package developer.retrofitexample.service;

import retrofit.RestAdapter;

/**
 * Created by appaz on 10/19/17.
 */

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
