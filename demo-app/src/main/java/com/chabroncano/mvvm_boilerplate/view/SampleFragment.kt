package com.chabroncano.mvvm_boilerplate.view

import com.chabroncano.basemvvm.base.BaseMvvmFragment
import com.chabroncano.mvvm_boilerplate.R
import com.chabroncano.mvvm_boilerplate.databinding.FragmentSampleBinding

class SampleFragment: BaseMvvmFragment<Nothing, FragmentSampleBinding>(R.layout.fragment_sample) {
    override fun setViewModelFactory(): Nothing? = null

    override fun setViewModelClass() = Nothing::class.java

    override fun onBind() {
        binding?.message = getString(R.string.i_am_a_fragment)
    }
}