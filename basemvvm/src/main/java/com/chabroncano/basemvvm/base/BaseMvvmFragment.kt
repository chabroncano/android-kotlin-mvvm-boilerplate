package com.chabroncano.basemvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseMvvmFragment<VM: ViewModel?,
        DB: ViewDataBinding?>(private val layoutId: Int) : Fragment() {

    open var viewModel: VM? = null
    open var binding: DB? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setViewModelFactory()?.let { factory ->
            initViewModel(factory, setViewModelClass())
        }
        initBinding(inflater, container)
        return binding?.root
    }

    /**
     *
     *
     */
    abstract fun setViewModelFactory(): ViewModelProvider.Factory?

    /**
     *
     *
     */
    abstract fun setViewModelClass(): Class<VM>

    /**
     *
     *
     */
    private fun initViewModel(factory: ViewModelProvider.Factory, vmClass: Class<VM>) {
        viewModel = ViewModelProvider(this, factory).get(vmClass)
    }

    /**
     *
     *
     */
    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate<DB>(inflater, layoutId, container, false)
        onBind()
    }

    /**
     *
     *
     */
    abstract fun onBind()
}
