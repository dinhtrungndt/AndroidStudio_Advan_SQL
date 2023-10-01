package com.example.asm1_nguyendinhtrung_pk02294.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.adapter.NewsAdapter;
import com.example.asm1_nguyendinhtrung_pk02294.model.EduListNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.ListUserNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.UserNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.channelEduNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.itemEduUserNews;
import com.example.asm1_nguyendinhtrung_pk02294.others.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class News_Activity extends AppCompatActivity {
    RecyclerView recyclerListNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerListNews = findViewById(R.id.recyclerListNews);
        
        demoCallAPI();

    }

    private void demoCallAPI() {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(EduListNews EduListNews) {
        //API trả về dữ liệu thành công, thực hiện việc lấy data
        ArrayList<itemEduUserNews> list = EduListNews.getChannel().getItem();

        // tách hàm
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerListNews.setLayoutManager(linearLayoutManager);
        NewsAdapter adapter = new NewsAdapter(this, list);
        recyclerListNews.setAdapter(adapter);

        Toast.makeText(this, list.get(0).getTitle() + "-" + list.get(1).getDescription(), Toast.LENGTH_SHORT).show();
    }

    private void handleError(Throwable error) {
        String a = "";
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.e("Loi api ", error + "" );
        Toast.makeText(this, "API gọi không thành công", Toast.LENGTH_SHORT).show();
    }

}