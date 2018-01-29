package com.ygsoft.twsystem.interfaces;

import com.ygsoft.twsystem.beans.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wgw on 2018/1/29.
 */

public interface RetrofitService {
    @GET("book/search")
    Call<Book> getSearchBook(@Query("q") String name,@Query("tag") String tag,@Query("start") int start,@Query("count") int count);
}
