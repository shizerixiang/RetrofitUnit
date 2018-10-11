package com.beviswang.retrofitlib.request

import com.beviswang.retrofitlib.enpty.Result
import com.beviswang.retrofitlib.model.SatinModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 段子
 */
interface ISatinService {
    /**
     * 获取推荐段子
     */
    @GET("satinApi")
    fun getRecommendSatins(@Query("type") type: Int, @Query("page") page: Int): Call<Result<List<SatinModel>>>
}