package com.beviswang.retrofitunit

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.beviswang.capturelib.util.PermissionHelper
import com.beviswang.retrofitlib.IRetrofitOperator
import com.beviswang.retrofitlib.RetrofitOperator
import com.beviswang.retrofitlib.model.SatinModel
import com.beviswang.retrofitunit.adapter.SatinListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), PermissionHelper.OnRequestPermissionsResultCallbacks {
    private lateinit var listAdapter: SatinListAdapter
    private val satinList: ArrayList<SatinModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (PermissionHelper.requestPermissions(this, REQUEST_CODE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET)) bindData() else
            toast("请确认权限！")
    }

    private fun bindData() {
        mSatinList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        listAdapter = SatinListAdapter(this@MainActivity, satinList)
        mSatinList.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()
        getSatin()
    }

    private fun getSatin() {
        doAsync {
            val retrofitOpt: IRetrofitOperator = RetrofitOperator()
            retrofitOpt.init()
            retrofitOpt.getSatins(1, 1, callback = { callback(it) })
        }
    }

    private fun callback(satins: ArrayList<SatinModel>?) {
        if (satins == null) {
            toast("没有获取到推荐段子")
            return
        }
        satinList.clear()
        satinList.addAll(satins)
        listAdapter.notifyDataSetChanged()
        mSatinList.startLayoutAnimation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?, isAllDenied: Boolean) {
        toast("没有权限无法运行程序！")
        finish()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?, isAllGranted: Boolean) {
        bindData()
    }

    companion object {
        private val REQUEST_CODE = 0x19
    }
}
