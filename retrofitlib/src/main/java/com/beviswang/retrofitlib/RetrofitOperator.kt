package com.beviswang.retrofitlib

import com.beviswang.retrofitlib.enpty.Result
import com.beviswang.retrofitlib.model.SatinModel
import com.beviswang.retrofitlib.request.ISatinService
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitOperator : IRetrofitOperator {
    private lateinit var mRetrofit: Retrofit

    override fun init() {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()

        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    override fun getSatins(type: Int, page: Int, callback: (ArrayList<SatinModel>?) -> Unit) {
        val satins: ArrayList<SatinModel> = ArrayList()
        val satinService = mRetrofit.create(ISatinService::class.java)
        val call = satinService.getRecommendSatins(type, page)
        call.enqueue(object : Callback<Result<List<SatinModel>>> {
            override fun onFailure(call: Call<Result<List<SatinModel>>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Result<List<SatinModel>>>, response: Response<Result<List<SatinModel>>>) {
                try {
                    satins.addAll(response.body()?.data ?: throw Exception("Null of result!"))
                    callback(satins)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    companion object {
        private const val BASE_URL = "https://www.apiopen.top/"
    }
}