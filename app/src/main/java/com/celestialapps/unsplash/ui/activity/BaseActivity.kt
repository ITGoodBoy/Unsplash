package com.celestialapps.unsplash.ui.activity

import android.annotation.SuppressLint
import android.support.design.widget.Snackbar

import com.arellomobile.mvp.MvpAppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : MvpAppCompatActivity() {


    fun getSnackBar(message: String): Snackbar {
        return Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_INDEFINITE)
    }
}
