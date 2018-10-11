package com.beviswang.retrofitlib.enpty

class Result<T> {
    var code: Int = 0
    var msg: String? = null
    var data: T? = null
    var count: Long = 0
    var page: Long = 0

    override fun toString(): String {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\''.toString() +
                ", data=" + data +
                ", count=" + count +
                ", page=" + page +
                '}'.toString()
    }
}