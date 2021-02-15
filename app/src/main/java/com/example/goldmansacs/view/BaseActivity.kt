package com.example.goldmansacs.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.goldmansacs.viewmodels.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {

    private val TAG = BaseViewModel::class.java.simpleName
    lateinit var progressDialogBar : ProgressDialog
    lateinit var progressDialog : ProgressDialog
    //lateinit var rewardedAd : RewardedVideoAd
    //lateinit var rewardedAd : RewardedAd
    lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        initialize()
        progressDialog = ProgressDialog(this)
        progressDialogBar = ProgressDialog(this)

        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)

        observers()
    }

    abstract fun layoutId() : Int
    abstract fun initialize()

    private fun observers() {

    }

    fun showProgressDialog(message : String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }

        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss()
        }
    }

}