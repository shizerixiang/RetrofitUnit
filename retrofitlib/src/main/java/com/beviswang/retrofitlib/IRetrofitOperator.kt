package com.beviswang.retrofitlib

import com.beviswang.retrofitlib.model.SatinModel

interface IRetrofitOperator {
    /** 初始化 */
    fun init()

    /** 获取推荐段子 */
    fun getSatins(type: Int, page: Int, callback: (ArrayList<SatinModel>?) -> Unit)
}