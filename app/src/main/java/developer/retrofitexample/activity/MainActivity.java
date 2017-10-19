package developer.retrofitexample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import developer.retrofitexample.Data;
import developer.retrofitexample.R;
import developer.retrofitexample.adapter.CardAdapter;
import developer.retrofitexample.model.UserModel;
import developer.retrofitexample.service.ServiceFactory;
import developer.retrofitexample.service.UserService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardAdapter mCardAdapter = new CardAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mCardAdapter);

        Button bClear = (Button) findViewById(R.id.button_clear);
        Button bFetch = (Button) findViewById(R.id.button_fetch);

        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCardAdapter.clear();
            }
        });

        bFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService userService = ServiceFactory.createRetrofitService(UserService.class,UserService.SERVICE_ENDPOINT);
                for (String login : Data.user){
                    userService.getUser(login)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<UserModel>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("RetrofitExample", e.getMessage());
                                }

                                @Override
                                public void onNext(UserModel userModel) {
                                    mCardAdapter.addData(userModel);
                                }
                            });
                }
            }
        });

    }
}
