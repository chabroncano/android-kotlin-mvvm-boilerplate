package com.chabroncano.mvvm_boilerplate.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chabroncano.basemvvm.base.BaseMvvmView
import com.chabroncano.mvvm_boilerplate.R
import com.chabroncano.mvvm_boilerplate.databinding.ViewSampleTextBinding
import com.chabroncano.mvvm_boilerplate.viewmodel.SampleTextViewViewModel

class SampleTextView: BaseMvvmView<SampleTextViewViewModel> {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private lateinit var binding: ViewSampleTextBinding

    override fun setViewModelClass() = SampleTextViewViewModel::class.java

    override fun initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.view_sample_text, parent as ViewGroup, true)
        binding.root.layoutParams = this.layoutParams
    }

    override fun setObservers(viewModel: SampleTextViewViewModel?, lifecycleOwner: LifecycleOwner) {
        viewModel?.counterLiveData?.observe(lifecycleOwner, Observer { timerCount ->
            binding.count = timerCount
        })
    }

    override fun onViewModelCreated(viewModel: SampleTextViewViewModel?) {
        viewModel?.startTimer()
    }
}
