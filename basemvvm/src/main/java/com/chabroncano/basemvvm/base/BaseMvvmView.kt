package com.chabroncano.basemvvm.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

abstract class BaseMvvmView<VM: ViewModel?>: View {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initView()
        setViewModelClass()?.let { vmClass ->
            val viewModel = createVmInstance(vmClass)
            (context as? LifecycleOwner)?.let { lifecycleOwner ->
                setObservers(viewModel, lifecycleOwner)
            }
            onViewModelCreated(viewModel)
        }
    }

    private fun createVmInstance(classType: Class<VM>): VM? {
        return classType.newInstance()
    }

    /**
     * returns instance of the ViewModel factory
     */
    abstract fun setViewModelClass(): Class<VM>?

    /**
     * initialize your view here,
     * you can customize it via data binding or
     * you can check view attributes here as well
     */
    abstract fun initView()

    /**
     * observe ViewModel live data here
     */
    abstract fun setObservers(viewModel: VM?, lifecycleOwner: LifecycleOwner)

    /**
     * call ViewModel methods here
     */
    abstract fun onViewModelCreated(viewModel: VM?)
}
