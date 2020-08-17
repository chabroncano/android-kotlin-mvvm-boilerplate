package com.chabroncano.basemvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseMvvmActivity<VM: ViewModel?,
        DB: ViewDataBinding?>(private val layoutId: Int) : AppCompatActivity() {

    open var viewModel: VM? = null
    open var binding: DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel(setViewModelFactory(), setViewModelClass())
        initBinding()
    }

    /**
     * method for all initial steps like dagger setup, firebase creation, etc.
     */
    abstract fun init()

    /**
     * returns instance of the ViewModel factory
     */
    abstract fun setViewModelFactory(): ViewModelProvider.Factory

    /**
     * returns class of the ViewModel for this activity
     */
    abstract fun setViewModelClass(): Class<VM>

    /**
     * creates an instance of the ViewModel
     * that follows that class returned in setViewModelClass()
     */
    private fun initViewModel(factory: ViewModelProvider.Factory, vmClass: Class<VM>) {
        viewModel = ViewModelProvider(this, factory).get(vmClass)
    }

    /**
     * initializes binding following DB class
     */
    private fun initBinding() {
        binding = DataBindingUtil.setContentView<DB>(this, layoutId)
        onBind()
    }

    /**
     * do your logic here, binding is ready at this point
     */
    abstract fun onBind()
}
