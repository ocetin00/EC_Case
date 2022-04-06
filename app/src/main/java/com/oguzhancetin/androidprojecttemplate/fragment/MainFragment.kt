package com.oguzhancetin.androidprojecttemplate.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhancetin.androidprojecttemplate.R
import com.oguzhancetin.androidprojecttemplate.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun inflateView(): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater, null, false)


}