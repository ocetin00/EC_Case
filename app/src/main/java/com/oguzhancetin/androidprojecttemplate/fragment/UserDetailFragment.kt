package com.oguzhancetin.androidprojecttemplate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.oguzhancetin.androidprojecttemplate.data.NetworkResult
import com.oguzhancetin.androidprojecttemplate.data.UserDetailViewModel
import com.oguzhancetin.androidprojecttemplate.databinding.FragmentUserDetailBinding
import com.oguzhancetin.androidprojecttemplate.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserDetailFragment() : BaseFragment<FragmentUserDetailBinding>() {
    private val viewModel by viewModels<UserDetailViewModel>()
    private val args by navArgs<UserDetailFragmentArgs>()
    override fun getVB(): FragmentUserDetailBinding {
        return FragmentUserDetailBinding.inflate(LayoutInflater.from(requireContext()),null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUserInfo(args.userName)


    }

    private fun fetchUserInfo(userName:String){

        viewModel.getUserInfo(userName)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userInfoStateFlow.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE

                        }
                        is NetworkResult.Error -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                        }
                        is NetworkResult.Success -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            it.data?.let { user ->
                                binding.apply {
                                    txtName.text =  user.name
                                    txtLocation.text = user.location
                                    txtMail.text = user.email
                                    Glide.with(requireContext()).load(user.avatarUrl).fitCenter().into(imageView2)
                                }
                            }

                        }
                    }

                }
            }
        }
    }


}