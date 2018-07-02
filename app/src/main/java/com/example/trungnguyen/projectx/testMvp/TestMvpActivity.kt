package com.example.trungnguyen.projectx.testMvp

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.trungnguyen.projectx.R
import com.example.trungnguyen.projectx.base.baseALertDialog.LoadingDialog
import com.example.trungnguyen.projectx.entity.AndroidVersion
import com.example.trungnguyen.projectx.entity.Hello
import com.example.trungnguyen.projectx.entity.JavaEntity
import com.example.trungnguyen.projectx.entity.User
import com.example.trungnguyen.projectx.repository.Repository
import com.example.trungnguyen.projectx.service.ApiService
import com.example.trungnguyen.projectx.ui.dating.main.MainActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_test_mvp_activity.*
import net.aksingh.owmjapis.core.OWM
import net.aksingh.owmjapis.model.CurrentWeather
import timber.log.Timber
import timber.log.Timber.*

/**
 * Created by Trung Nguyen on 18-May-18.
 */
class TestMvpActivity : AppCompatActivity(), TestMvpView {

    private lateinit var listVer: ArrayList<AndroidVersion>
    private lateinit var adapter: AndroidAdapter
    private lateinit var testMvpPresenter: TestMvpPresenter
    private lateinit var jv: JavaEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test_mvp_activity)
        e("test mvp activity")
        listVer = ArrayList<AndroidVersion>()
//        adapter = AndroidAdapter(listVer!!)
        rv_android_ver.layoutManager = LinearLayoutManager(this)
//        rv_android_ver.adapter = adapter
        testMvpPresenter = TestMvpPresenter()
        testMvpPresenter.attachView(this)

        if (3 == 2)
            if (4 - 1 == 2)
                e("aaaaa")

        initData()

        ///test entity initial
        JavaEntity().id = 2
        jv = JavaEntity()
        jv.id = 9

        var user = User()

        var list = arrayListOf<User>()

        //// test api Weather...
        btn_btn.setOnClickListener { view ->
            if (sttBtn == 0) {
                testMvpPresenter.getWeatherInfo(edt_city.text.toString().trim(), OWM.Country.VIETNAM)
            } else if (sttBtn == 1) {
                sttBtn = 0
                tv_result.text = ""
                tv_result.visibility = View.GONE
                edt_city.text.clear()
                btn_btn.text = "search"
            }

        }
    }

    fun initData() {
        testMvpPresenter.getUser()
    }

    override fun showUserInfo(result: List<AndroidVersion>) {
        listVer = result as ArrayList<AndroidVersion>
        adapter = AndroidAdapter(listVer)
        rv_android_ver.adapter = adapter
    }

    override fun showLoading() {
        LoadingDialog.show(this)
    }

    override fun hideLoading() {
        LoadingDialog.hide()
    }

    private var sttBtn = 0

    override fun showWeather(result: CurrentWeather?) {
        e(Gson().toJson(result))
        e("aaaaaaaaaaaaa")
        tv_result.visibility = View.VISIBLE
        tv_result.text = Gson().toJson(result)
        btn_btn.text = "clear"
        btn_btn.isFocusable = true
        sttBtn = 1
    }

    override fun showError() {
        Toast.makeText(this, "not found name of city", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    @SuppressLint("ServiceCast")
    override fun onBackPressed() {

//        val pm = getPackageManager()
//        //get a list of installed apps.
//        var packages = pm.getInstalledApplications(0);
//
//        val mActivityManager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//
//        for (packageInfo in packages) {
//            if(packageInfo.flags == 1 && ApplicationInfo.FLAG_SYSTEM == 1) continue
////            if(packageInfo.packageName.equals("mypackage")) continue
//            mActivityManager.killBackgroundProcesses(packageInfo.packageName)
//        }
//
        e("killlllllllllllllllllllll")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        } else {
            finish()
        }

        super.onBackPressed()
    }

    override fun onDestroy() {

        super.onDestroy()
    }
}