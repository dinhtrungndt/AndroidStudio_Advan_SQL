package com.example.asm1_nguyendinhtrung_pk02294.others;

import com.example.asm1_nguyendinhtrung_pk02294.model.EduListNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.ListUserNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.channelEduNews;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceAPI {
    String BASE_SERVICE = "https://apis.dinhnt.com/";

    @GET("edu.json")
    Observable<EduListNews> getData();
}
