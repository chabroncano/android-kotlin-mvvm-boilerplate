package com.chabroncano.mvvm_boilerplate.view

import com.chabroncano.basemvvm.base.BaseMvvmActivity
import com.chabroncano.mvvm_boilerplate.R
import com.chabroncano.mvvm_boilerplate.databinding.ActivityMainBinding
import com.chabroncano.mvvm_boilerplate.factory.MainActivityViewModelFactory
import com.chabroncano.mvvm_boilerplate.viewmodel.MainActivityViewModel

class MainActivity : BaseMvvmActivity<MainActivityViewModel,
        ActivityMainBinding>(R.layout.activity_main) {

    override fun init() { }

    override fun setViewModelFactory() = MainActivityViewModelFactory()

    override fun setViewModelClass() = MainActivityViewModel::class.java

    override fun onBind() {
        binding?.message = getString(R.string.i_am_an_activity)
        supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
    }
}
